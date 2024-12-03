import java.lang.reflect.Array;
import java.util.ArrayList;

public class Betalinger
{

    public Betalinger()
    {
    }


    protected int medlemsKontingent;



    public Betalinger(int medlemsKontingent)
    {
        this.medlemsKontingent = medlemsKontingent;
    }


    public int udregnBetalinger(Medlem medlem)
    {
        int medlemsKontingent = 0;

        if (medlem.getMedlemStatus().equalsIgnoreCase("Aktiv"))
        {

            int alder = medlem.getAlder();
            if (medlem.getAlderKatogori().equalsIgnoreCase("Junior"))
            {
                medlemsKontingent = 1000;
            } else if (alder >= 60)
            {
                medlemsKontingent = (int) (1600 * 0.75);
            } else
            {
                medlemsKontingent = 1600;
            }
        } else
        {
            medlemsKontingent = 500;
        }
        return medlemsKontingent;
    }


    public int udregnRestance(Medlem medlem)
    {
        int restance = 0;

        if (medlem.getMedlemStatus().equalsIgnoreCase("Aktiv"))
        {

            int alder = medlem.getAlder();
            if (medlem.getAlderKatogori().equalsIgnoreCase("Junior"))
            {
                restance = - 1000;
            } else if (alder >= 60)
            {
                restance = (int) (-1600 * 0.75);
            } else
            {
                restance = -1600;
            }
        } else
        {
            restance = -500;
        }
        return restance;
    }



}





