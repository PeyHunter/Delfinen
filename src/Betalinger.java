import java.lang.reflect.Array;
import java.util.ArrayList;

public class Betalinger
{

    public Betalinger()
    {
    }

   // protected boolean restance;
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
            // For active members, determine the membership fee based on age
            int alder = medlem.getAlder();
            if (medlem.getAlderKatogori().equalsIgnoreCase("Junior"))
            {
                medlemsKontingent = 1000;  // Junior membership fee
            } else if (alder >= 60)
            {
                medlemsKontingent = (int) (1600 * 0.75);  // 25% discount for seniors over 60
            } else
            {
                medlemsKontingent = 1600;  // Regular senior fee
            }
        } else
        {
            medlemsKontingent = 500;  // Passive members pay a smaller fee
        }
        return medlemsKontingent;  // Apply discount
    }


    public int udregnRestance(Medlem medlem)
    {
        int restance = 0;

        if (medlem.getMedlemStatus().equalsIgnoreCase("Aktiv"))
        {
            // For active members, determine the membership fee based on age
            int alder = medlem.getAlder();
            if (medlem.getAlderKatogori().equalsIgnoreCase("Junior"))
            {
                restance = - 1000;  // Junior membership fee
            } else if (alder >= 60)
            {
                restance = (int) (-1600 * 0.75);  // 25% discount for seniors over 60
            } else
            {
                restance = -1600;  // Regular senior fee
            }
        } else
        {
            restance = -500;  // Passive members pay a smaller fee
        }
        return restance;  // Apply discount
    }



}





