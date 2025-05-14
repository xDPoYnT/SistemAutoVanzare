package org.example;
import java.util.ArrayList;
import java.util.List;

public class DealerAuto {
    private List<Masina> masini = new ArrayList<>();
    private List<Client> clienti = new ArrayList<>();
    private List<Angajat> angajati = new ArrayList<>();
    private List<Tranzactie> tranzactii = new ArrayList<>();

    public void adaugaMasina(Masina m) {
        masini.add(m);
    }

    public List<Masina> getMasini() {
        return masini;
    }

    public void adaugaClient(Client c) {
        clienti.add(c);
    }

    public void adaugaAngajat(Angajat a) {
        angajati.add(a);
    }

    public void adaugaTranzactie(Tranzactie t) {
        tranzactii.add(t);
        // Scoatem mașina din stoc deoarece a fost vândută
        masini.remove(t.getMasina());
    }

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    public List<Client> getClienti() {
        return clienti;
    }

    public List<Angajat> getAngajati() {
        return angajati;
    }
}

