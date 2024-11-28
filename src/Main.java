import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {




        MedlemsOversigt medlemsOversigt = new MedlemsOversigt();
        System.out.println(medlemsOversigt);

        Medlem peyton = new Medlem("Peyton", 2503,8972348,"@gmailkdkdk", LocalDate.now(), 1023);
        Medlem isa = new Medlem("Isa", 2503,8972348,"@gmailkdkdk", LocalDate.now(), 1023);

        medlemsOversigt.addMedlemmerToMedlemmerOversigt(peyton);
        medlemsOversigt.addMedlemmerToMedlemmerOversigt(isa);

        System.out.println(medlemsOversigt);

        System.out.println(medlemsOversigt.getAntalMedlemmere());

//
//        Medlem medlem1 = new Medlem(new Person("Torben", 78348, 938393, "@gmail.com") {}, 3435);
//        System.out.println(medlem1);
    }
}