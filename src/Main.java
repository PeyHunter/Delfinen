import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {




       MedlemsOversigt medlemsOversigt = new MedlemsOversigt();
        System.out.println(medlemsOversigt);

        Medlem peyton = new Medlem("Peyton", new CPR("111111-1111"),8972348,"gmailkdkdk", LocalDate.now(), false, false, 1023);
        System.out.println(peyton);


        medlemsOversigt.addMedlemmerToMedlemmerOversigt(peyton);

       System.out.println(medlemsOversigt);

        System.out.println(medlemsOversigt.getAntalMedlemmere());

//
//        Medlem medlem1 = new Medlem(new Person("Torben", 78348, 938393, "@gmail.com") {}, 3435);
//        System.out.println(medlem1);
    }
}



// 1. CPR SKAL Virke
// 2. TILFØJ boolean AKTIV og MOTIONIST
// 3 tilføj setmetoder for at kunne