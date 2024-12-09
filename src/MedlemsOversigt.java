import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class MedlemsOversigt
{

    protected ArrayList<Medlem> medlemmerOversigt = new ArrayList<>();
    protected Restance restance;
    protected Betalinger betalinger;


    public MedlemsOversigt()
    {
        this.medlemmerOversigt = new ArrayList<>();
        this.restance = new Restance();
        this.betalinger = new Betalinger();
        createMedlemmereOversigt();
    }


    public void createMedlemmereOversigt()
    {
        Random random = new Random();

        for (int i = 1; i <= 250; i++)
        {

            CPR cpr = new CPR(true);

            String[] drengenavne = {
                    "Peter", "Søren", "Jens", "Thomas", "Anders", "Frederik", "Christian", "Lars", "Hans", "Ole", "Mikkel", "Jacob", "Benjamin", "Mathias", "Emil", "David", "Victor", "Daniel", "Andreas", "Nicklas", "Simon"
            };
            String[] pigenavne = {
                    "Anna", "Maria", "Karin", "Mette", "Emma", "Nina", "Camilla", "Louise", "Sofie", "Helle", "Maja", "Lise", "Line", "Katrine", "Helena", "Sarah", "Julie", "Carina", "Laura", "Hanne", "Cecilie"
            };
            String[] efternavne = {
                    "Jensen", "Nielsen", "Hansen", "Pedersen", "Andersen", "Christensen", "Sorensen", "Larsen", "Rasmussen", "Madsen", "Poulsen", "Olsen", "Mikkelsen", "Jørgensen", "Kjær", "Johansen", "Berg", "Buch", "Vestergaard", "Knudsen"
            };


            int lastDigit = cpr.getGender();
            boolean isOdd = (lastDigit % 2 != 0);


            String fornavn;

            if (isOdd)
            {
                fornavn = drengenavne[random.nextInt(drengenavne.length)];
            } else
            {
                fornavn = pigenavne[random.nextInt(pigenavne.length)];
            }

            String efternavn = efternavne[random.nextInt(efternavne.length)];
            String navn = fornavn + " " + efternavn;

            int tlfNr = 10000000 + random.nextInt(90000000);
            String mail = fornavn.toLowerCase() + random.nextInt(22222) + "@gmail.com";
            LocalDate oprettelsesDato = LocalDate.now().minusDays(random.nextInt(365 * 5));
            boolean erAktiv = random.nextBoolean();
            boolean erMotionist = random.nextBoolean();
            int medlemsId = 1000 + i;
            boolean restance = random.nextBoolean();


            medlemmerOversigt.add(new Medlem(navn, cpr, tlfNr, mail, oprettelsesDato, erAktiv, erMotionist, medlemsId, new Betalinger(), restance));
        }
    }


    public ArrayList<Medlem> getMedlemmerOversigt()
    {
        return medlemmerOversigt;
    }


    public void addMedlemmerToMedlemmerOversigt(Medlem m)
    {
        medlemmerOversigt.add(m);
    }

    public int getAntalMedlemmere()
    {
        return medlemmerOversigt.size();
    }


    public void addAlleMedlemmeretoRestanceList()
    {
        for (int i = 0; i < medlemmerOversigt.size(); i++)
        {
            Medlem m = medlemmerOversigt.get(i);  // hent index for Medlem
            if (m.erRestance)
            {
                restance.addMedlemToRestance(m);
                System.out.println("Added to RestanceListe: " + m.getNavn());
            }
        }
    }


    public Betalinger getBetalinger()
    {
        return betalinger;
    }

    public Restance getRestance()
    {
        return restance;
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Medlemmere:\n\n");

        for (int i = 0; i < medlemmerOversigt.size(); i++)
        {
            sb.append(medlemmerOversigt.get(i)).append("\n");
        }
        return sb.toString();
    }

}