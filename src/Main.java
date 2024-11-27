import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {

        Medlem medlem1 = new Medlem("Torben", 78348, 938393, "@gmail.com", LocalDate.now(), 3435);
        System.out.println(medlem1);

//
//        Medlem medlem1 = new Medlem(new Person("Torben", 78348, 938393, "@gmail.com") {}, 3435);
//        System.out.println(medlem1);
    }
}