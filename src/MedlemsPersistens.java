import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MedlemsPersistens {

    protected ArrayList<Medlem> medlemmereOversigtArray = new ArrayList<>();

    public void writeMedlemPersistens(ArrayList<Medlem> medlemmereOversigt) {

        File medlemsListe = new File("/Users/peytonhunter/Library/CloudStorage/OneDrive-Personal/Documents/Datamatiker/1 Semester/Programmering/InteliJ/Delfinen/src/medlemOversigt.txt");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime currentDate = LocalDateTime.now();

        // Skriv filen
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(medlemsListe, true))) {


            for (int i = 0; i < medlemmereOversigt.size(); i++) {
                Medlem medlem = medlemmereOversigt.get(i);  // Hent medlem fra listen

                String formattedDate = dateFormatter.format(currentDate);
                String formattedTime = timeFormatter.format(currentDate);


                writer.write("Navn: " + medlem.getNavn() + "\n");
                writer.write("Email: " + medlem.getMail() + "\n");
                writer.write("TelefonNr: " + medlem.getTelNr() + "\n");
                writer.write("Oprettelsesdato: " + medlem.getOprettelsesDato().format(dateFormatter) + "\n");
                writer.write("MedlemsId: " + medlem.getMedlemsId() + "\n");
                writer.write("Aktivstatus: " + medlem.getMedlemStatus() + "\n");
                writer.write("MedlemsType: " + medlem.getMedlemsType() + "\n");
                writer.write("Dato for skrivning: " + formattedDate + " Tid: " + formattedTime + "\n");
                writer.write("\n");
            }

            System.out.println("Medlemmerne er nu blevet skrevet til filen!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fejl ved skrivning til fil.");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Medlemmere:\n\n");

        for (int i = 0; i < medlemmereOversigtArray.size(); i++) {
            sb.append(medlemmereOversigtArray.get(i).toString()).append("\n");
        }

        return sb.toString();
    }
}