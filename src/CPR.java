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


    public String getCprNr()
    {
        return nummeret;
    }

    public void setCprNr(String n)
    {
        nummeret = n;
    }

    public int getDag()
    {
        return Integer.parseInt(nummeret.substring(0, 2));
    }

    public int getMaaned()
    {
        return Integer.parseInt(nummeret.substring(2, 4));
    }


    public int getAar() {
        int yearPart = Integer.parseInt(nummeret.substring(4, 6));  // De sidste to cifre af året (yy)
        char centuryIndicator = nummeret.charAt(6);  // 7. ciffer i CPR-nummeret

        int aarhundrede;

        if (centuryIndicator >= '0' && centuryIndicator <= '3') {
            aarhundrede = 1900;
        } else if ((centuryIndicator == '4' || centuryIndicator == '9') && yearPart <= LocalDate.now().getYear() % 100) {
            aarhundrede = 2000;  // Når vi er efter år 2000
        } else if (centuryIndicator == '2' || centuryIndicator == '5' || centuryIndicator == '6') {
            aarhundrede = 1900;  // Default to 1900s for normal 2/5/6 indicators
        } else {
            aarhundrede = 1800;
        }

        int year = aarhundrede + yearPart;

        // Correct the century if age is below 10 or above 100
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;

        // Adjust the year based on the age
        if (age < 10) {
            year -= 100;  // If under 10, the year should be from the previous century
        } else if (age > 100) {
            year += 100;  // If over 100, the year should be from the next century
        }

        return year;
    }

    public Dato getDato()
    {
        Dato d = new Dato(getDag(), getMaaned(), getAar());
        return d;
    }

    ;

    public int getAlder() {
        LocalDate foedselsdag = LocalDate.of(getAar(), getMaaned(), getDag());
        LocalDate iDag = LocalDate.now();

        int alder = iDag.getYear() - foedselsdag.getYear();

        // Tjek om fødselsdagen i år endnu ikke er sket
        if (iDag.getMonthValue() < foedselsdag.getMonthValue() ||
                (iDag.getMonthValue() == foedselsdag.getMonthValue() && iDag.getDayOfMonth() < foedselsdag.getDayOfMonth())) {
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
        // valdemar gjorde sådan her: udregnet += vaegte[i] *nummeret.charAt(i) - '0';

        if (udregnet % 11 != 0)
            return false;

        return true;
    }

    public String toString()
    {
        return nummeret;
    }


    private String generateRandomCPR() {
        Random random = new Random();

        LocalDate currentDato = LocalDate.now();
        int minAge = 10;  // Minimum age
        int maxAge = 100; // Maximum age

        // Calculate the range of birth years based on the age
        int minYear = currentDato.getYear() - maxAge;  // 100 years ago
        int maxYear = currentDato.getYear() - minAge;  // 10 years ago

        // Generate a random birth year within this range
        int year = minYear + random.nextInt(maxYear - minYear + 1);

        int day = 1 + random.nextInt(28);  // Random day between 1 and 28 (to avoid invalid dates)
        int month = 1 + random.nextInt(12);  // Random month between 1 and 12

        // Birthdate (DDMMYY)
        String birthDate = String.format("%02d%02d%02d", day, month, year % 100);

        // Century indicator (based on year)
        char centuryIndicator;

        if (year >= 2000) {
            centuryIndicator = '4';
        } else if (year >= 1900) {
            centuryIndicator = '2';
        } else {
            centuryIndicator = '5';
        }

        // Generate random 4-digit number (XXXX)
        int genderPart = random.nextInt(10000);
        String genderDigit = String.format("%03d", genderPart);

        // Add gender digit (either '1' or '2')
        if (random.nextBoolean()) {
            genderDigit += "1";
        } else {
            genderDigit += "2";
        }

        // Combine birth date, century indicator, and gender part to generate the CPR number
        return birthDate + centuryIndicator + genderDigit;
    }


}
