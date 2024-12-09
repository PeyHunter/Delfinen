public abstract class Person
{

    protected String navn;

    protected CPR cpr;
    protected int telNr;
    protected String mail;

    public Person(String navn, CPR cpr, int telNr, String mail)
    {
        this.navn = navn;
        this.cpr = cpr;
        this.telNr = telNr;
        this.mail = mail;
    }

    public String getNavn()
    {
        return navn;
    }

    public CPR getCpr()
    {
        return cpr;
    }

    public int getTelNr()
    {
        return telNr;
    }

    public String getMail()
    {
        return mail;
    }

}
