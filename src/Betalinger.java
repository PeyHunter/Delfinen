//public class Betalinger
//{
//
//    public Betalinger()
//    {
//    }
//
//    protected boolean betalt = true;
//    protected Restance restance;
//    protected int rabat;
//   // protected Medlem medlem;
//    protected int medlemsKontingent;
//
//    public Betalinger(boolean betalt, Restance restance, int rabat)
//    {
//        this.betalt = betalt;
//        this.restance = restance;
//        this.rabat = rabat;
//    }
//
//
//    public int udregnBetalinger(Medlem medlem)
//    {
//        int medlemsKontingt = 0;
//        Restance restance = this.restance;
//
//        if (medlem.getMedlemStatus().equalsIgnoreCase("Aktiv"))
//        {
//            if (medlem.getAlderKatogori().equalsIgnoreCase("Junior"))
//            {
//                return medlemsKontingt = 1000;
//
//            } else if (medlem.getAlderKatogori().equalsIgnoreCase("Senior"))
//            {
//                return medlemsKontingt = 1600;
//
//            } else if (medlem.getAlderKatogori().equalsIgnoreCase("Senior") && medlem.getAlder() >= 60)
//            {
//                return medlemsKontingent = (int) (1600 * 0.75);  // 25% discount for seniors over 60
//            }
//
//        } else
//        {
//            return medlemsKontingt = (int) (500); //passiv members
//        }
//        return medlemsKontingt - rabat;
//    }
//
//
//    public boolean getBetalt()
//    {
//        return betalt;
//    }
//
//    public Restance getRestance()
//    {
//        return restance;
//    }
//
//    public int getRabat()
//    {
//        return rabat;
//    }
//}
//
//
//// public int Aktiv (if betagetAKtivStus) == True
//// {
////      return 1600
//// {
//
////      Under 18 = 1000 kr
////-    	Over 18 = 1600 kr
////-    	Over 60 = 25 % rabat af 1600
////   	Passivt = 500
//
//// public int Aktiv (if betagetAKtivStus) == True
//// {
////      return 1600
//// {
