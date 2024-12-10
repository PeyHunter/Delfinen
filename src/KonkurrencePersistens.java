public class KonkurrencePersistens
        //implements Comparable<KonkurrencePersistens>
{

    private KonkurranceResultat konkurranceResultat;

    public KonkurrencePersistens(KonkurranceResultat konkurranceResultat)
    {
        this.konkurranceResultat = konkurranceResultat;
    }

    public KonkurranceResultat getKonkurranceResultat()
    {
        return konkurranceResultat;
    }

    public void writeKonkurranPersistens()
    {
        //Skab en text fil med resultaterne
    }


    public int sorterResultat()
    {
        return 0;
        //ved brug af Comparable sortere vi resultaterne
    }


}
