import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Trainer extends Person
{


    protected String diciplin;

    public Trainer(String navn, int foedselsdag, int telNr, String mail, String diciplin)
    {
        super(navn, foedselsdag,telNr, mail);
        this.diciplin = diciplin;

    }



    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return "Medlem: " + navn + "\n" +
                "Fødselsdag: " + foedselsdag + "\n" +
                "TlfNr: " + telNr + "\n" +
                "Mail: " + mail + "\n" +
                "Diciplin: " + diciplin + "\n"
                ;

    }



}
