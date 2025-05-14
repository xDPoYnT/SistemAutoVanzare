package org.example;

public class Masina {
    private String marca;
    private String model;
    private int anFabricatie;
    private double pret;

    public Masina(String marca, String model, int anFabricatie, double pret) {
        this.marca = marca;
        this.model = model;
        this.anFabricatie = anFabricatie;
        this.pret = pret;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public int getAnFabricatie() {
        return anFabricatie;
    }

    public double getPret() {
        return pret;
    }

    public String afisare() {
        return marca + " " + model + " (" + anFabricatie + ") - " + pret + " EUR";
    }
}
