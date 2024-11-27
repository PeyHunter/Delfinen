public abstract class Person
{

    protected String navn;
    protected int foedselsdag;
    protected int telNr;
    protected String mail;

    public Person(String navn, int foeselsdag, int telNr, String mail)
    {
        this.navn = navn;
        this.foedselsdag = foeselsdag;
        this.telNr = telNr;
        this.mail = mail;
    }

    public String getNavn()
    {
        return navn;
    }

    public int getFoedselsdag()
    {
        return foedselsdag;
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
