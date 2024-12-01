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

    public String getMedlemStatus()
    {
        if(aktivStatus == true)
        {
            return "Aktiv";
        } else
        {
            return "Passiv";
        }
    }

    public String getMedlemsType()
    {
        if(erMotionist == true)
        {
            return "Motionist";
        }else {
            return "Konkurrance Deltager";
        }

    }

    public int getAlder()
    {
        return cpr.getAlder();
    }

    public String getAlderKatogori()
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
                "Junior/Senior: " + getAlder() + "\n" +
                "TlfNr: " + telNr + "\n" +
                "Mail: " + mail + "\n" +
                "Oprettelsesdato: " + oprettelsesDato + "\n" +
                "Aktiv/Passiv: " + getMedlemStatus() + "\n" +
                "Motionist/Konkurrance: " + getMedlemsType() + "\n" +
                "MedlemsId: " + medlemsId + "\n"
                ;

    }



}
