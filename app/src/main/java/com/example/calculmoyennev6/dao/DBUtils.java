package com.example.calculmoyennev6.dao;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.calculmoyennev6.metier.Matiere;

public class DBUtils extends SQLiteOpenHelper {
    public static String dbName="test_matieres.db";//ici defini le nom de la base de donnée

    /*le script permettant la creation de la table dans la base de données */
    public static String CREATE_TABLES = "CREATE TABLE matiere ("+"id_matiere INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nom_matiere TEXT, " +
            "note DOUBLE," +
            "coeff INTEGER," +
            "date_test TEXT);";

    public DBUtils( Context context) {
        super(context,dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    /*Methode d'insertion dans la table matiere*/
    public  void inserer(Matiere matiere) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO matiere VALUES(null,'" +
                matiere.getName()+"','"+
                matiere.getNote()+"','"+
                matiere.getCoeffecient()+"','"+
                matiere.getDateTest()+"')");
    }
    /*Methode de mise à jour dans la table matiere*/
    public void update(Matiere matiere,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update matiere set nom_matiere ='" + matiere.getName()+
                "' ,note='" + matiere.getNote()+
                "' ,coeff ='" + matiere.getCoeffecient()+
                "' ,date_test='"+ matiere.getDateTest()+"' where id_matiere='"+id+"'");
    }
    /*Methode de recuperation de tous les matiere*/
    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultat =db.rawQuery("select * from  matiere",null);
        return resultat;
    }
    /*Methode de recuperation d'une matiere*/
    public Cursor getOneMatiere(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultat=db.rawQuery("select * from  matiere where id_matiere = ? ",new String[]{id});
        return resultat;
    }
    /*Methode pour supprimer une matiere*/
    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete  from  matiere where id_matiere='"+id+"'");
    }
}
