import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RestancePersistens
{

    protected Betalinger betalinger;
    protected MedlemsOversigt medlemsOversigt;

    public RestancePersistens(MedlemsOversigt medlemsOversigt) {
        this.medlemsOversigt = medlemsOversigt;
        this.betalinger = new Betalinger();  // Initialize betalinger
    }


    public void writeRestancePersistens()
    {
        File restancePersistens = new File("/Users/peytonhunter/Library/CloudStorage/OneDrive-Personal/Documents/Datamatiker/1 Semester/Programmering/InteliJ/Delfinen/src/restancePersistens.txt");

        try (FileWriter writer = new FileWriter(restancePersistens, false))
        {
            if (restancePersistens.length() == 0)
            {
                writer.write("LISTE OVER MEDLEMMERE I RESTANCE \n\n");
            }

            medlemsOversigt.addAlleMedlemmeretoRestanceList();
            ArrayList<Medlem> restanceListe = medlemsOversigt.getRestance().getRestanceListe();

            for (int i = 0; i < restanceListe.size(); i++)
            {
                Medlem m = restanceListe.get(i);

                writer.append(String.format(
                        "Medlem: %s\n" +
                                "CPR: %s\n" +
                                "Alder: %d\n" +
                                "Aktiv/Passiv: %s\n" +
                                "Skylder: %d DKK\n\n",

                        m.getNavn(),
                        m.getCpr(),
                        m.getAlder(),
                        m.getMedlemStatus(),
                        betalinger.udregnRestance(m)  // Calculate the arrears for the member
                ));

                writer.append(System.lineSeparator());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }




}
