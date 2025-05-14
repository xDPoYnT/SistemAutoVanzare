package org.example;

public class Client extends Persoana {
    public Client(String nume, String cnp) {
        super(nume, cnp);
    }

    @Override
    public String getTip() {
        return "Client";
    }
}
