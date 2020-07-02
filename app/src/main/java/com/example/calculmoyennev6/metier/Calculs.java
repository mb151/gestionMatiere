package com.example.calculmoyennev6.metier;

import java.util.ArrayList;

public class Calculs {
    public static Double calculeMoyenne( ArrayList<Matiere> matieres ){
        Double somme_coeff=0.00 ,moyenne=0.0;
        for (Matiere m:matieres) {
            moyenne += m.calculerNote();
            somme_coeff+=m.getCoeffecient();
        }
        return moyenne/somme_coeff;
    }
}
