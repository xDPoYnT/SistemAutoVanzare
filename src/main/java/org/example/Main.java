package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    private static DealerAuto dealer = new DealerAuto();

    public static void main(String[] args) {
        // Încărcare mașini din fișier CSV
        List<Masina> masiniIncarcate = FileManager.incarcaMasiniCSV("masini.csv");
        for (Masina m : masiniIncarcate) {
            dealer.adaugaMasina(m);
        }

        // Fereastra principală
        JFrame frame = new JFrame("Sistem Vânzare Auto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Zonă text afișare
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Panel pentru butoane
        JPanel buttonPanel = new JPanel();
        JButton addCarButton = new JButton("Adaugă mașină");
        JButton showCarsButton = new JButton("Afișează mașini");
        JButton sellCarButton = new JButton("Vinde mașină");
        JButton saveCSVButton = new JButton("Salvează în CSV");
        JButton viewTransactionsButton = new JButton("Vezi tranzacții");
        JButton saveTransactionsButton = new JButton("Salvează tranzacții"); //

        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10)); // 6 butoane

        buttonPanel.add(addCarButton);
        buttonPanel.add(showCarsButton);
        buttonPanel.add(sellCarButton);
        buttonPanel.add(saveCSVButton);
        buttonPanel.add(viewTransactionsButton);
        buttonPanel.add(saveTransactionsButton); //


        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Acțiune: Adaugă mașină
        addCarButton.addActionListener(e -> {
            JTextField marcaField = new JTextField();
            JTextField modelField = new JTextField();
            JTextField anField = new JTextField();
            JTextField pretField = new JTextField();

            Object[] fields = {
                    "Marca:", marcaField,
                    "Model:", modelField,
                    "An:", anField,
                    "Preț:", pretField
            };

            int result = JOptionPane.showConfirmDialog(null, fields, "Adaugă Mașină", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String marca = marcaField.getText();
                    String model = modelField.getText();
                    int an = Integer.parseInt(anField.getText());
                    double pret = Double.parseDouble(pretField.getText());

                    Masina m = new Masina(marca, model, an, pret);
                    dealer.adaugaMasina(m);
                    textArea.append("✅ Mașină adăugată: " + m.afisare() + "\n");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Date invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acțiune: Afișează mașini
        showCarsButton.addActionListener(e -> {
            textArea.setText("🚗 Mașini disponibile:\n");
            if (dealer.getMasini().isEmpty()) {
                textArea.append("Nu există mașini în stoc.\n");
            } else {
                for (Masina m : dealer.getMasini()) {
                    textArea.append(m.afisare() + "\n");
                }
            }
        });

        // Acțiune: Vinde mașină
        sellCarButton.addActionListener(e -> {
            if (dealer.getMasini().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nu există mașini de vândut.");
                return;
            }

            String[] masiniStr = dealer.getMasini().stream().map(Masina::afisare).toArray(String[]::new);
            String masinaSelectata = (String) JOptionPane.showInputDialog(frame, "Selectează mașina:",
                    "Vânzare", JOptionPane.PLAIN_MESSAGE, null, masiniStr, masiniStr[0]);

            if (masinaSelectata == null) return;

            JTextField numeClient = new JTextField();
            JTextField cnpClient = new JTextField();
            Object[] clientFields = {
                    "Nume client:", numeClient,
                    "CNP client:", cnpClient
            };

            int result = JOptionPane.showConfirmDialog(null, clientFields, "Date Client", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                Client client = new Client(numeClient.getText(), cnpClient.getText());
                dealer.adaugaClient(client);

                Masina masina = dealer.getMasini().stream()
                        .filter(m -> m.afisare().equals(masinaSelectata))
                        .findFirst().orElse(null);

                if (masina != null) {
                    Tranzactie t = new Tranzactie(client, masina, LocalDate.now().toString());
                    dealer.adaugaTranzactie(t);
                    textArea.append("💰 Tranzacție efectuată: " + t + "\n");
                }
            }
        });

        // Acțiune: Salvează în CSV
        saveCSVButton.addActionListener(e -> {
            FileManager.salveazaMasiniCSV(dealer.getMasini(), "masini.csv");
            JOptionPane.showMessageDialog(frame, "📁 Fișier salvat ca masini.csv");
        });
        // Acțiune: Salvează tranzacțiile în CSV
        saveTransactionsButton.addActionListener(e -> {
            FileManager.salveazaTranzactiiCSV(dealer.getTranzactii(), "tranzactii.csv");
            JOptionPane.showMessageDialog(frame, "📁 Tranzacțiile au fost salvate în tranzactii.csv");
        });
// Acțiune: Vezi tranzacții
        viewTransactionsButton.addActionListener(e -> {
            textArea.setText("📄 Tranzacții efectuate:\n");

            if (dealer.getTranzactii().isEmpty()) {
                textArea.append("Nu există tranzacții înregistrate.\n");
            } else {
                for (Tranzactie t : dealer.getTranzactii()) {
                    textArea.append(t.toString() + "\n");
                }
            }
        });


        frame.setVisible(true);
    }
}
