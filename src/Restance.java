import java.util.ArrayList;

public class Restance {

    private ArrayList<Medlem> restanceListe;

    public Restance() {
        this.restanceListe = new ArrayList<>();
    }


    public void addMedlemToRestance(Medlem medlem) {
        if (!restanceListe.contains(medlem)) {
            restanceListe.add(medlem);
        }
    }

    // Get arrears list
    public ArrayList<Medlem> getRestanceListe() {
        return restanceListe;
    }

    public boolean isMedlemInRestance(Medlem medlem) {
        return restanceListe.contains(medlem);
    }


    public int getBeloebForMedlem(Medlem medlem) {
        return medlem.getBetalinger().udregnRestance(medlem);
    }
}