import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class Restance
{
    public Restance() {}

    protected int   skyldteGaeld = 0;
    protected int   medlemsKontingent;
    protected int   indmelteAar;
    protected Medlem medlem;




    public Restance(int skyldteGaeld)
    {
     this.skyldteGaeld = skyldteGaeld;
    }

    
    public int countDageFraOprettelses




//    public int getIndmelteAar(Medlem medlem)
//    {
//        LocalDate iDag = LocalDate.now();
//        LocalDate oprettelsesDato = medlem.oprettelsesDato;
//
//        int indmelteAar = 0;
//        int totalDays = 0;
//
//        if (oprettelsesDato.isBefore(iDag) || oprettelsesDato.isEqual(iDag))
//            totalDays++;





//
//
//        long dageSidenOprettelse = ChronoUnit.DAYS.between(medlem.oprettelsesDato, LocalDate.now());
//
//        indmelteAar = (int) (dageSidenOprettelse / 365);
//
//        return indmelteAar;
    }



// Aarbetalt
    // AarIkkeBetalte




    public int getSkyldteGaeld()
    {
        return skyldteGaeld;
    }



}
