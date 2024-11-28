import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Medlem extends Person
{

    protected LocalDate oprettelsesDato;
    protected int   medlemsId;
    protected boolean erAktiv;

    public Medlem(String navn, int foedselsdag, int telNr,String mail, LocalDate oprettelsesDato, int medlemsId)
    {
        super(navn, foedselsdag,telNr, mail);
        this.oprettelsesDato = oprettelsesDato;
        this.medlemsId = medlemsId;
        this.erAktiv = erAktiv;
    }


    public int getMedlemsId()
    {
        return medlemsId;
    }


    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return "Medlem: " + navn + "\n" +
                "Fødselsdag: " + foedselsdag + "\n" +
                "TlfNr: " + telNr + "\n" +
                "Mail: " + mail + "\n" +
                "Oprettelsesdato: " + oprettelsesDato + "\n" +
                "MedlemsId: " + medlemsId + "\n"
                ;

    }



}
