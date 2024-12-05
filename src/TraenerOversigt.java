import java.util.ArrayList;
import java.util.Random;

public class TraenerOversigt
{
    protected ArrayList<Traener> traenerListe = new ArrayList<>();
    protected String diciplin;


    // Opret oversigt over trænere

    public TraenerOversigt()
    {
        this.traenerListe = new ArrayList<>();
        this.diciplin = "";
        createTraenerOversigt();
    }

    public void createTraenerOversigt() {
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            CPR cpr = new CPR(true); // Generer CPR

            String[] traenerDFornavn = {"Magnus", "Kasper", "Henrik", "Niels", "Jonas", "Asger" };
            String[] traenerPFornavn = {"Lotte", "Pia", "Birgitte", "Katrine" };
            String[] traenerEfternavn = {"Møller", "Sørensen", "Petersen", "Kristensen", "Hansen", "Lauridsen", "Jakobsen", "Friis", "Thomsen", "Bæk" };
            String[] discipliner = {"Svømning", "Butterfly", "Freestyle", "Rygcrawl", "Brystsvømning" };


            int lastDigit = cpr.getGender();
            boolean isOdd = (lastDigit % 2 != 0);


            String fornavn;

            if (isOdd)
            {
                fornavn = traenerDFornavn[random.nextInt(traenerDFornavn.length)];
            } else
            {
                fornavn = traenerPFornavn[random.nextInt(traenerPFornavn.length)];
            }

            String efternavn = traenerEfternavn[random.nextInt(traenerEfternavn.length)];
            String navn = fornavn + " " + efternavn;


            int telNr = 10000000 + random.nextInt(90000000); // Generer telefonnummer
            String mail = fornavn.toLowerCase() + efternavn.toLowerCase() + "@mail.com";
            String diciplin = discipliner[random.nextInt(discipliner.length)];


            traenerListe.add(new Traener(navn, cpr, telNr, mail, diciplin));

        }
    }

    public String getDisciplin()
    {
        return diciplin;
    }

    public ArrayList<Traener> getTraenerListe()
    {
        return traenerListe;
    }

    public void addTraenerToTraenerOversigt(Traener t)
    {
        traenerListe.add(t);
    }

    public int getAntalTraenere()
    {
        return traenerListe.size();
    }



    @Override
    public String toString() {
        return super.toString() + "Diciplin: " + diciplin + "\n";
    }
}