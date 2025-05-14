package org.example;

import java.io.*;
import java.util.*;

public class FileManager {

    public static void salveazaMasiniCSV(List<Masina> masini, String numeFisier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(numeFisier))) {
            for (Masina m : masini) {
                writer.println(m.getMarca() + "," + m.getModel() + "," + m.getAnFabricatie() + "," + m.getPret());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void salveazaTranzactiiCSV(List<Tranzactie> tranzactii, String numeFisier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(numeFisier))) {
            for (Tranzactie t : tranzactii) {
                String linie = t.getClient().getNume() + "," +
                        t.getClient().getCnp() + "," +
                        t.getMasina().getMarca() + "," +
                        t.getMasina().getModel() + "," +
                        t.getMasina().getAnFabricatie() + "," +
                        t.getMasina().getPret() + "," +
                        t.getData();
                writer.println(linie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Masina> incarcaMasiniCSV(String numeFisier) {
        List<Masina> masini = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                String[] parti = linie.split(",");
                if (parti.length == 4) {
                    String marca = parti[0];
                    String model = parti[1];
                    int an = Integer.parseInt(parti[2]);
                    double pret = Double.parseDouble(parti[3]);
                    masini.add(new Masina(marca, model, an, pret));
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Nu s-a putut încărca fișierul " + numeFisier + ": " + e.getMessage());
        }
        return masini;
    }
}
