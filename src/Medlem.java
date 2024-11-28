import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Medlem extends Person
{

    protected LocalDate oprettelsesDato;
    protected int   medlemsId;
    protected boolean aktivStatus;
    protected boolean erMotionist;

    public Medlem(String navn, CPR cpr, int telNr,String mail, LocalDate oprettelsesDato, boolean aktivStatus, boolean erMotionist, int medlemsId)
    {
        super(navn, cpr,telNr, mail);
        this.oprettelsesDato = oprettelsesDato;
        this.medlemsId = medlemsId;
        this.aktivStatus = aktivStatus;
        this.erMotionist = erMotionist;
    }


    public int getMedlemsId()
    {
        return medlemsId;
    }

    public String getAktivStatus()
    {
        if(aktivStatus == true)
        {
            return "Aktiv";
        } else
        {
            return "Passiv";
        }
    }

    public String getMotionistStatus()
    {
        if(erMotionist == true)
        {
            return "Motionist";
        }else {
            return "Konkurrance Deltager";
        }

    }

    public String getJuniorStatus()
    {
        int alder = cpr.getAlder();
        if(alder < 18) {
            return "Junior";
        } else {
            return "Senior";
        }

    }

    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return "Medlem: " + navn + "\n" +
                "CPR: " + cpr + "\n" +
                "Alder: " + cpr.getAlder() + "\n" +
                "Junior/Senior: " + getJuniorStatus() + "\n" +
                "TlfNr: " + telNr + "\n" +
                "Mail: " + mail + "\n" +
                "Oprettelsesdato: " + oprettelsesDato + "\n" +
                "Aktiv/Passiv: " + getAktivStatus() + "\n" +
                "Motionist/Konkurrance: " + getMotionistStatus() + "\n" +
                "MedlemsId: " + medlemsId + "\n"
                ;

    }



}
