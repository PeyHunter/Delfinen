import java.util.ArrayList;

public class Restance
{


    private ArrayList<Medlem> restanceListe;

    public Restance()
    {
        this.restanceListe = new ArrayList<>();
    }

    // Add a member to the arrears list if not already added
    public void addMedlemToRestance(Medlem medlem)
    {
        if (!restanceListe.contains(medlem))
        {
            restanceListe.add(medlem);
        }
    }

    // Calculate arrears for a member
    public int calculateRestanceForMedlem(Medlem medlem)
    {
        // Here you can use the existing logic from Betalinger class
        return medlem.getBetalinger().udregnRestance(medlem);
    }

    // Get the list of members in arrears
    public ArrayList<Medlem> getRestanceListe()
    {
        return restanceListe;
    }

    // Check if a member is in arrears
    public boolean isMedlemInRestance(Medlem medlem)
    {
        return restanceListe.contains(medlem);
    }
}