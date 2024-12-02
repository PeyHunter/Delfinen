import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Medlem extends Person
{

    protected LocalDate oprettelsesDato;
    protected int medlemsId;
    protected boolean aktivStatus;
    protected boolean erMotionist;
    protected Betalinger betalinger;
    protected boolean erRestance = false;

    public Medlem(String navn, CPR cpr, int telNr, String mail, LocalDate oprettelsesDato, boolean aktivStatus, boolean erMotionist, int medlemsId, Betalinger betalinger, boolean erRestance)
    {
        super(navn, cpr, telNr, mail);
        this.oprettelsesDato = oprettelsesDato;
        this.aktivStatus = aktivStatus;
        this.erMotionist = erMotionist;
        this.betalinger = betalinger != null ? betalinger : new Betalinger();
        this.medlemsId = medlemsId;
        this.erRestance = erRestance;
    }



    public int getMedlemsId()
    {
        return medlemsId;
    }

    public String getMedlemStatus()
    {
        if (aktivStatus == true)
        {
            return "Aktiv";
        } else
        {
            return "Passiv";
        }
    }


    public String getMedlemsType()
    {
        if (erMotionist == true)
        {
            return "Motionist";
        } else
        {
            return "Konkurrance";
        }
    }

    public int getAlder()
    {
        return cpr.getAlder();
    }

    public Betalinger getBetalinger() {
        return this.betalinger;
    }

    public String getAlderKatogori()
    {
        int alder = cpr.getAlder();
        if (alder < 18)
        {
            return "Junior";
        } else
        {
            return "Senior";
        }
    }


    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        int betaling;
        if (erRestance == true)
        {
            betaling = betalinger.udregnRestance(this);
        } else
        {
            betaling = betalinger.udregnBetalinger(this);
        }

       String result =  "Medlem: " + navn + "\n" +
                        "CPR: " + cpr + "\n" +
                        "Alder: " + cpr.getAlder() + "\n" +
                        "Junior/Senior: " + getAlderKatogori() + "\n" +
                        "TlfNr: " + telNr + "\n" +
                        "Mail: " + mail + "\n" +
                        "Oprettelsesdato: " + oprettelsesDato + "\n" +
                        "Aktiv/Passiv: " + getMedlemStatus() + "\n" +
                        "Motionist/Konkurrance: " + getMedlemsType() + "\n" +
                        "MedlemsId: " + medlemsId + "\n" +
                        "Årlig medlems kontingent: " + betalinger.udregnBetalinger(this) + " DKK" + "\n";

                if(erRestance == true)
                {
                    result += "Restance og skylder: " + (betaling) + " DKK" + "\n";

                }

                return result;
    }


}
