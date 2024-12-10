import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {


        MedlemsOversigt medlemsOversigt = new MedlemsOversigt();
        medlemsOversigt.addAlleMedlemmeretoRestanceList();

        MedlemsPersistens medlemsPersistens = new MedlemsPersistens();
        medlemsPersistens.writeMedlemPersistens(medlemsOversigt.getMedlemmerOversigt());

        RestancePersistens restancePersistens = new RestancePersistens(medlemsOversigt);
        restancePersistens.writeRestancePersistens();

        // Opret TraenerPersistens objekt og skriv trænere til fil

        TraenerOversigt traenerOversigt = new TraenerOversigt();
        TraenerPersistens traenerPersistens = new TraenerPersistens(traenerOversigt.getTraenerListe());
        traenerPersistens.writeTraenerToFile();

        GUI gui = new GUI();
        gui.setVisible(true);
        
    }
}

