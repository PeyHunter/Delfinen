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


    public ArrayList<Medlem> getRestanceListe() {
        return restanceListe;
    }

    public boolean isMedlemInRestance(Medlem medlem) {
        return restanceListe.contains(medlem);
    }


    public int getBeloebForMedlem(Medlem medlem) {
        return medlem.getBetalinger().udregnRestance(medlem);
    }

    public void removeMedlemFromRestance(Medlem medlem) {
        restanceListe.remove(medlem);  // Remove the member from the arrears list
    }
}