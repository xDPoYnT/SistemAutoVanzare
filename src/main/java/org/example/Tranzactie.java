package org.example;
public class Tranzactie {
    private Client client;
    private Masina masina;
    private String data;

    public Tranzactie(Client client, Masina masina, String data) {
        this.client = client;
        this.masina = masina;
        this.data = data;
    }

    public Client getClient() {
        return client;
    }

    public Masina getMasina() {
        return masina;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Client: " + client.getNume() +
                ", Mașină: " + masina.afisare() +
                ", Data: " + data;
    }
}

