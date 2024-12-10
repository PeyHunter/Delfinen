import java.util.ArrayList;

public class KonkurranceResultat
{
    private Medlem medlem;
    private int placering;
    private double tid;
    private String disiplin;

    public KonkurranceResultat(Medlem medlem, int placering, double tid, String disiplin)
    {
        this.medlem = medlem;
        this.placering = placering;
        this.tid = tid;
        this.disiplin = disiplin;
    }

    public Medlem getMedlem()
    {
        return medlem;
    }

    public int getPlacering()
    {
        return placering;
    }

    public double getTid()
    {
        return tid;
    }

    public String getDisiplin()
    {
        return disiplin;
    }



    public int udregnKonkurrenceResultat()
    {
        return 0;
    }


}
