import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {

        MedlemsOversigt medlemsOversigt = new MedlemsOversigt();
       medlemsOversigt.addAlleMedlemmeretoRestanceList();


        RestancePersistens restancePersistens = new RestancePersistens(medlemsOversigt);
        restancePersistens.writeRestancePersistens();

       System.out.println(medlemsOversigt);
        System.out.println(restancePersistens);

        Medlem isabella = new Medlem("isabella,", new CPR("250394-2502"), 10239834, "jkldsaljk@gmail.com", LocalDate.now(), true, true, 93930, new Betalinger(12342), false);
        System.out.println(isabella);

        // public Medlem(String navn, CPR cpr, int telNr, String mail, LocalDate oprettelsesDato, boolean aktivStatus, boolean erMotionist, int medlemsId, Betalinger betalinger, boolean erRestance)



    }
}

