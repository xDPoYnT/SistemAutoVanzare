package org.example;

public class Angajat extends Persoana {
    private String functie;

    public Angajat(String nume, String cnp, String functie) {
        super(nume, cnp);
        this.functie = functie;
    }

    @Override
    public String getTip() {
        return "Angajat";
    }

    public String getFunctie() {
        return functie;
    }
}
