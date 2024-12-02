public class Betalinger
{

    public Betalinger()
    {
    }

    protected boolean betalt = true;
    protected int rabat;
    protected int medlemsKontingent;
   // protected Medlem medlem;
   // protected Restance restance;

    public Betalinger(boolean betalt)
    {
        this.betalt = betalt;
//        this.restance = restance;
        this.rabat = rabat;
    }


    public int udregnBetalinger(Medlem medlem) {
        int medlemsKontingent = 0;

        if (medlem.getMedlemStatus().equalsIgnoreCase("Aktiv")) {
            // For active members, determine the membership fee based on age
            int alder = medlem.getAlder();
            if (medlem.getAlderKatogori().equalsIgnoreCase("Junior")) {
                medlemsKontingent = 1000;  // Junior membership fee
            } else if (alder >= 60) {
                medlemsKontingent = (int) (1600 * 0.75);  // 25% discount for seniors over 60
            } else {
                medlemsKontingent = 1600;  // Regular senior fee
            }
        } else {
            medlemsKontingent = 500;  // Passive members pay a smaller fee
        }

        return medlemsKontingent;  // Apply discount
    }

//    public boolean restance(Medlem medlem) {
//        if(restance( = false)){
//
//        }
//    }


//    public Restance getRestance()
//    {
//        return restance;
//    }

    public int getRabat()
    {
        return rabat;
    }
}


// public int Aktiv (if betagetAKtivStus) == True
// {
//      return 1600
// {

//      Under 18 = 1000 kr
//-    	Over 18 = 1600 kr
//-    	Over 60 = 25 % rabat af 1600
//   	Passivt = 500

// public int Aktiv (if betagetAKtivStus) == True
// {
//      return 1600
// {
