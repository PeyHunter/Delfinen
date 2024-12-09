import java.time.LocalDate;
import java.util.Random;


public class CPR
{
    String nummeret;

    public CPR()
    {
    }

    public CPR(String n)
    {
        nummeret = n;
    }


    public CPR(boolean generateRandom)
    {
        if (generateRandom)
        {
            this.nummeret = generateRandomCPR();
        } else
        {
            this.nummeret = "";
        }
    }


    public int getGender()
    {
        return Integer.parseInt(nummeret.substring(9));
    }

    public String getCprNr()
    {
        return nummeret;
    }

    public int getDag()
    {
        return Integer.parseInt(nummeret.substring(0, 2));
    }

    public int getMaaned()
    {
        return Integer.parseInt(nummeret.substring(2, 4));
    }


    public int getAar()
    {

        int yearPart = Integer.parseInt(nummeret.substring(4, 6));

        int century;

        if (yearPart < 24)
        {
            century = 2000;
        } else
        {
            century = 1900;
        }


        int year = century + yearPart;


        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;


        if (age < 10)
        {
            year -= 100;
        } else if (age > 100)
        {
            year += 100;
        }

        return year;
    }


    public Dato getDato()
    {
        Dato d = new Dato(getDag(), getMaaned(), getAar());
        return d;
    }

    ;

    public int getAlder()
    {
        LocalDate foedselsdag = LocalDate.of(getAar(), getMaaned(), getDag());
        LocalDate iDag = LocalDate.now();

        int alder = iDag.getYear() - foedselsdag.getYear();


        if (iDag.getMonthValue() < foedselsdag.getMonthValue() ||
                (iDag.getMonthValue() == foedselsdag.getMonthValue() && iDag.getDayOfMonth() < foedselsdag.getDayOfMonth()))
        {
            alder--;  // Træk 1 fra, hvis fødselsdagen ikke er sket endnu
        }

        return alder;
    }


    public boolean erMand()
    {
        if (Integer.parseInt(nummeret.substring(9, 10)) % 2 != 0)
            return true;
        return false;
    }

    public boolean erKvinde()
    {
        return !erMand();
    }

    public boolean isValid()
    {
        if (nummeret.length() != 10)
            return false;

        for (int i = 0; i < 10; i++)
            if (nummeret.charAt(i) < '0' || nummeret.charAt(i) > '9')
                return false;

        if (!getDato().valid())
            return false;

        int udregnet = 0;
        int[] vaegte = {4, 3, 2, 7, 6, 5, 4, 3, 2, 1};

        for (int i = 0; i < 10; i++)
            udregnet += vaegte[i] * Integer.parseInt("" + nummeret.charAt(i));

        if (udregnet % 11 != 0)
            return false;

        return true;
    }

    public String toString()
    {
        return nummeret;
    }


    private String generateRandomCPR()
    {
        Random random = new Random();

        LocalDate currentDate = LocalDate.now();

        int minAge = 10;
        int maxAge = 100;

        int minYear = currentDate.getYear() - maxAge;
        int maxYear = currentDate.getYear() - minAge;

        int year = minYear + random.nextInt(maxYear - minYear + 1);

        int month = 1 + random.nextInt(12);

        int day = 1 + random.nextInt(28);

        String birthDate = String.format("%02d%02d%02d", day, month, year % 100);

        int randomDigits = random.nextInt(10000); // Random 4 digits
        String randomPart = String.format("%04d", randomDigits); // Always 4 digits

        return birthDate + "-" + randomPart;
    }


}