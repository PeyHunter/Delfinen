import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;



public class MedlemsOversigt
{

    protected ArrayList<Medlem> medlemmerOversigt = new ArrayList<>();


    public MedlemsOversigt() {
        createMedlemmereOversigt();
    }


    public void createMedlemmereOversigt()
    {
        Random random = new Random();

        for(int i = 0; i <= 200; i++)
        {
            String navn = "Medlem" + i;
            int foedselsdag = random.nextInt(100000);
            int tlfNr = 10000000 + random.nextInt(90000000);
            String mail = navn.toLowerCase() + "@gmail.com";
            LocalDate oprettelsesDato = LocalDate.now().minusDays(random.nextInt(365 * 5));
           int medlemsId = 1000 + i;

           medlemmerOversigt.add(new Medlem(navn, foedselsdag, tlfNr, mail, oprettelsesDato, medlemsId));
        }


    }

    public ArrayList<Medlem> getMedlemmerOversigt()
    {
        return medlemmerOversigt;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Medlemsoversigt:\n");

        if (medlemmerOversigt.isEmpty()) {
            sb.append("Ingen medlemmer i oversigten.\n");
        } else {
            for (Medlem medlem : medlemmerOversigt) {
                sb.append(medlem.toString()).append("\n");
            }
        }

        return sb.toString();
    }
}
