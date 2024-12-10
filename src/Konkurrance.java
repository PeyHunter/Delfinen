import java.time.LocalDate;
import java.util.ArrayList;

public class Konkurrance
{

    public Konkurrance() {}

    protected String staevne;
    protected String adresse;
    protected LocalDate dagsDato;

    private ArrayList<KonkurranceResultat> deltagerResultater = new ArrayList<>();


    public Konkurrance(String staevne, String adresse, LocalDate dagsDato)
    {
        this.staevne = staevne;
        this.adresse = adresse;
        this.dagsDato = dagsDato;
        this.deltagerResultater = new ArrayList<>();
    }

    public String getStaevne()
    {
        return staevne;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public LocalDate getDagsDato()
    {
        return dagsDato;
    }

    public ArrayList<KonkurranceResultat> getDeltagerResultater()
    {
        return deltagerResultater;
    }


}
