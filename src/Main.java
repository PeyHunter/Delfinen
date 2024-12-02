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

       // System.out.println(medlemsOversigt);
        System.out.println(restancePersistens);



    }
}

