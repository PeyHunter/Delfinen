import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RestancePersistens
{

    protected Betalinger betalinger;
    protected MedlemsOversigt medlemsOversigt;

    public RestancePersistens(MedlemsOversigt medlemsOversigt)
    {
        this.medlemsOversigt = medlemsOversigt;
        this.betalinger = new Betalinger();
    }


    public void writeRestancePersistens()
    {
        File restancePersistensFile = new File("restancePersistens.txt");

        try (FileWriter writer = new FileWriter(restancePersistensFile, false))
        {
            if (restancePersistensFile.length() == 0)
            {
                writer.write("LISTE OVER MEDLEMMERE I RESTANCE \n\n");
            }

            ArrayList<Medlem> restanceListe = medlemsOversigt.getRestance().getRestanceListe();
            for (Medlem m : restanceListe)
            {
                int beløb = betalinger.udregnRestance(m);
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
                        beløb
                ));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}






