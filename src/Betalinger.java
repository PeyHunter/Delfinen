public class Betalinger
{

    public Betalinger()
    {
    }

    protected boolean   betalt = true;
    protected Restance  restance;
    protected int       rabat;

    public Betalinger(boolean betalt, Restance restance, int rabat)
    {
        this.betalt = betalt;
        this.restance = restance;
        this.rabat = rabat;
    }


    public boolean getBetalt()
    {
        return betalt;
    }

    public Restance getRestance()
    {
        return restance;
    }

    public int getRabat(){
        return rabat;
    }


}
