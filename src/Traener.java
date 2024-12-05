public class Traener extends Person {
    protected String diciplin;

    public Traener(String navn, CPR cpr, int telNr, String mail, String diciplin) {
        super(navn, cpr, telNr, mail);
        this.diciplin = diciplin;
    }

    public String getDiciplin()
    {
        return this.diciplin;
    }




    @Override
    public String toString() {
        return "Træner: " + getNavn() + "\n" +
                "CPR: " + getCpr() + "\n" +
                "Telefon: +45 " + getTelNr() + "\n" +
                "Mail: " + getMail() + "\n" +
                "Diciplin: " + diciplin + "\n";
    }
}