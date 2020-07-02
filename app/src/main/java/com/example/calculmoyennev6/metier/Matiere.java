package com.example.calculmoyennev6.metier;

public class Matiere {
    private int id;
    private Double note ;
    private Double coeffecient ;
    private String name ;
    private String DateTest;


    public Matiere(int id, Double note, Double coeffecient, String name, String dateTest) {
        this.id = id;
        this.note = note;
        this.coeffecient = coeffecient;
        this.name = name;
        this.DateTest = dateTest;
    }

    public int getId() {
        return id;
    }

    public Double getNote() {
        return note;
    }

    public Double getCoeffecient() {
        return coeffecient;
    }

    public String getName() {
        return name;
    }

    public String getDateTest() {
        return DateTest;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public void setCoeffecient(Double coeffecient) {
        this.coeffecient = coeffecient;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateTest(String dateTest) {
        this.DateTest = dateTest;
    }

    public Double calculerNote() {

        return this.note  * this.coeffecient ;
    }
}
