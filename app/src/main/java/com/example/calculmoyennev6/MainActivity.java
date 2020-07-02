package com.example.calculmoyennev6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculmoyennev6.dao.DBUtils;
import com.example.calculmoyennev6.metier.Calculs;
import com.example.calculmoyennev6.metier.Matiere;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText note1,note2, note3;
    EditText coeff1,coeff2,coeff3 ;
    ArrayList<Matiere> listmatiere = new ArrayList<Matiere>();
    TextView tv ,resultat,textview;
    CheckBox ck;
    Spinner sp1,sp2,sp3 ;
    List<String> matiere =  new LinkedList<>();
    DatePicker datePicker;
    DatePickerDialog picker;
    EditText eText,nom,coef,note,id;
    DBUtils dbutils;
    Dialog dialog1, dialog2, dialog3, dialog4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.dialog1);

        dialog2 = new Dialog(this);
        dialog2.setContentView(R.layout.dialog2);

        dialog3 = new Dialog(this);
        dialog3.setContentView(R.layout.dialog1);

        dialog4 = new Dialog(this);
        dialog4.setContentView(R.layout.dialog2);

        resultat = (TextView)findViewById(R.id.result);
        textview = (TextView) findViewById(R.id.show);
        dbutils=new DBUtils(this);
    }

    public void quitter(View view){

        finish();
    }

    public void effacer(View view){
        if(!listmatiere.isEmpty() || !matiere.isEmpty()){
            matiere.clear();
            tv.setText("");
            resultat.setText("");
            listmatiere.clear();
        }
    }

    public void afficher(View v){
        if(listmatiere.isEmpty()){
            final Toast toast = Toast.makeText(this,R.string.erreur2 , Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Double moyens = Calculs.calculeMoyenne(listmatiere);
            resultat.setText(String.valueOf("La moyenne = " + moyens));
        }
    }

    public void showDialog(View view){
        eText=(EditText) dialog1.findViewById(R.id.date);
        nom=(EditText) dialog1.findViewById(R.id.name);
        coef=(EditText) dialog1.findViewById(R.id.coefficient);
        note=(EditText) dialog1.findViewById(R.id.note);
        eText.setInputType(InputType.TYPE_NULL);
        final Calendar cldr = Calendar.getInstance();
        final int day = cldr.get(Calendar.DAY_OF_MONTH);
        final int month = cldr.get(Calendar.MONTH);
        final int year = cldr.get(Calendar.YEAR);
        eText=(EditText) dialog1.findViewById(R.id.date);
        eText.setInputType(InputType.TYPE_NULL);

        nom.setText("");
        note.setText("");
        coef.setText("");
        eText.setText("");

        eText.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // date picker dialog
                        picker = new DatePickerDialog(  MainActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                        eText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    }
                                }, year, month, day);
                        picker.show();
                    }
                });

        dialog1.show();

        Button quitter_dialog= (Button) dialog1.findViewById(R.id.quitter);
        Button valider_dialog= (Button) dialog1.findViewById(R.id.valider);

        quitter_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        valider_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matiere m1 = new Matiere(0,Double.valueOf(note.getText().toString()),Double.valueOf(coef.getText().toString()),nom.getText().toString(),eText.getText().toString());
                dbutils.inserer(m1);
                dialog1.dismiss();
            }
        });
    }

    public void showDialog2(View view){
        final Toast toast = Toast.makeText(this,R.string.erreur3 , Toast.LENGTH_SHORT);

        id=(EditText) dialog2.findViewById(R.id.id);
        dialog2.show();


        Button quitter_dialog= (Button) dialog2.findViewById(R.id.quitter2);
        Button valider_dialog= (Button) dialog2.findViewById(R.id.valider2);

        quitter_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
                id.setText("");
            }
        });

        valider_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor result = dbutils.getOneMatiere(id.getText().toString());
                if ((result != null) && (result.getCount() > 0)){
                    //put the data
                    result.moveToFirst();
                    final int  theId = result.getInt(result.getColumnIndex("id_matiere"));
                    String ne = result.getString(result.getColumnIndex("nom_matiere"));
                    double  n = result.getDouble(result.getColumnIndex("note"));
                    double   c = result.getDouble(result.getColumnIndex("coeff"));
                    String  dt = result.getString(result.getColumnIndex("date_test"));

                    id.setText("");
                    dialog2.dismiss();
                    dialog3.show();

                    eText=(EditText) dialog3.findViewById(R.id.date);
                    nom=(EditText) dialog3.findViewById(R.id.name);
                    coef=(EditText) dialog3.findViewById(R.id.coefficient);
                    note=(EditText) dialog3.findViewById(R.id.note);
                    eText.setInputType(InputType.TYPE_NULL);
                    final Calendar cldr = Calendar.getInstance();
                    final int day = cldr.get(Calendar.DAY_OF_MONTH);
                    final int month = cldr.get(Calendar.MONTH);
                    final int year = cldr.get(Calendar.YEAR);
                    eText=(EditText) dialog3.findViewById(R.id.date);
                    eText.setInputType(InputType.TYPE_NULL);

                    nom.setText(String.valueOf(ne));
                    note.setText(String.valueOf(n));
                    coef.setText(String.valueOf(c));
                    eText.setText(dt);

                    eText.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    picker = new DatePickerDialog(  MainActivity.this,
                                            new DatePickerDialog.OnDateSetListener() {
                                                @Override
                                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                    eText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                                }
                                            }, year, month, day);
                                    picker.show();
                                }
                            });


                    Button quitter_dialog2= (Button) dialog3.findViewById(R.id.quitter);
                    Button valider_dialog2= (Button) dialog3.findViewById(R.id.valider);

                    quitter_dialog2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog3.dismiss();
                        }
                    });

                    valider_dialog2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Matiere m1 = new Matiere(0,Double.valueOf(note.getText().toString()),Double.valueOf(coef.getText().toString()),nom.getText().toString(),eText.getText().toString());
                            dbutils.update(m1,theId);
                            dialog3.dismiss();
                        }
                    });

                }else{
                    toast.show();
                }

            }
        });
    }

    public void showDialog3(View view){
        final Toast toast = Toast.makeText(this,R.string.msg , Toast.LENGTH_SHORT);
        final Toast toast2 = Toast.makeText(this,R.string.erreur3 , Toast.LENGTH_SHORT);
        id=(EditText) dialog4.findViewById(R.id.id);
        dialog4.show();


        Button quitter_dialog= (Button) dialog4.findViewById(R.id.quitter2);
        Button valider_dialog= (Button) dialog4.findViewById(R.id.valider2);

        quitter_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog4.dismiss();
                id.setText("");
            }
        });

        valider_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor result = dbutils.getOneMatiere(id.getText().toString());
                if ((result != null) && (result.getCount() > 0)){
                    //put the data
                    result.moveToFirst();
                    final int  theId = result.getInt(result.getColumnIndex("id_matiere"));

                    id.setText("");
                    dbutils.delete(theId);
                    dialog4.dismiss();
                    toast.show();

                }else{
                    toast2.show();
                }

            }
        });
    }

    public void actualiser(View v){
        int id=0 ;
        double n=0,c=0;
        String ne="",dt="",tx="";
        Cursor cr= dbutils.getAll();

        while (cr.moveToNext()) {
            id = cr.getInt(cr.getColumnIndex("id_matiere"));
            ne = cr.getString(cr.getColumnIndex("nom_matiere"));
            n = cr.getDouble(cr.getColumnIndex("note"));
            c = cr.getDouble(cr.getColumnIndex("coeff"));
            dt = cr.getString(cr.getColumnIndex("date_test"));

            tx+="\n ID : " + id   + "\n Nom: " + ne  + "\n Note: " + n  + "\n Coeffecient: " + c + "\n Date Test: " + dt + "\n----------------------------------------------------\n";

        }
        textview.setText(tx);


    }


}
