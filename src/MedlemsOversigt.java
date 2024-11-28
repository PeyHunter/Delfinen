import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class MedlemsOversigt
{

    protected ArrayList<Medlem> medlemmerOversigt = new ArrayList<>();


    public MedlemsOversigt()
    {
        createMedlemmereOversigt();
    }


    public void createMedlemmereOversigt()
    {
        Random random = new Random();

        String[] drengenavne = {
                "Peter", "Søren", "Jens", "Thomas", "Anders", "Frederik", "Christian", "Lars", "Hans", "Ole", "Mikkel", "Jacob", "Benjamin", "Mathias", "Emil", "David", "Victor", "Daniel", "Andreas", "Nicklas", "Simon"
        };

        String[] pigenavne = {
                "Anna", "Maria", "Karin", "Mette", "Emma", "Nina", "Camilla", "Louise", "Sofie", "Helle", "Maja", "Lise", "Line", "Katrine", "Helena", "Sarah", "Julie", "Carina", "Laura", "Hanne", "Cecilie"
        };

        String[] efternavne = {
                "Jensen", "Nielsen", "Hansen", "Pedersen", "Andersen", "Christensen", "Sorensen", "Larsen", "Rasmussen", "Madsen", "Poulsen", "Olsen", "Mikkelsen", "Jørgensen", "Kjær", "Johansen", "Berg", "Buch", "Vestergaard", "Knudsen"
        };


        for (int i = 1; i <= 10; i++)
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


    public void addMedlemmerToMedlemmerOversigt(Medlem m)
    {
        medlemmerOversigt.add(m);
    }

    public int getAntalMedlemmere() {
        return medlemmerOversigt.size();
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
