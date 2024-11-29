
public class Dato
{
    private int datoen; // Format YYYYMMDD

    public Dato() // Default constructor
    {}

    public Dato(int enDato)
    {
        datoen = enDato;
    }

    public Dato(int enDag, int enMaaned, int etAar)
    {
        datoen = etAar * 10000 + enMaaned * 100 + enDag;
    }


    public int setDatoPlusEn()
    {
        datoen++;
        while (!valid())
        {
            datoen++;
        }
        {
            return datoen;
        }
    }

    public int setDatoMinusEn()
    {
        datoen--;
        while (!valid())
        {
            datoen--;
        }
        {
            return datoen;
        }
    }



    public int forskelIDage(Dato enDato)
    {
        int antalDage = 0;

        Dato kopiDato = new Dato();
        kopiDato.datoen = datoen; // . metoden gør at vi udfører samme metode på den givne objekt/ parameter

        if (kopiDato.datoen < enDato.datoen) // i nedenstående, så er der 3 udfald - over, under eller lig med

            while (kopiDato.datoen != enDato.datoen)
            {
                kopiDato.setDatoPlusEn();
                antalDage++;
            }

        else
        if (kopiDato.datoen > enDato.datoen)
            while (kopiDato.datoen != enDato.datoen)
            {
                kopiDato.setDatoMinusEn();
                antalDage--;
            }

        return antalDage;
    }


    public int forskelIDageIgen(Dato enDato)
    {
        int antalDage = 0;

        Dato kopiDato = new Dato();
        kopiDato.datoen = datoen;

        if(kopiDato.datoen < enDato.datoen)

            while (kopiDato.datoen != enDato.datoen)
            {
                kopiDato.setDatoPlusEn();
                antalDage++;
            }

        else
        if (kopiDato.datoen > enDato.datoen)
            while (kopiDato.datoen != enDato.datoen)
            {
                kopiDato.setDatoMinusEn();
                antalDage--;

            }
        return antalDage;
    }

    static final String[]dag = {"","man", "tirs", "ons", "tors", "fre", "lør", "søn"};
    // et forsøg på at sparer plads. final betyder at det ikke kan ændres.

    //udnyt forskel i dag, med
    public int ugeDag()
    {
        Dato udgDato = new Dato(); //sætter den = 0
        udgDato.datoen = 17000301; // 0 = 17000301. Så er det nulpunktet

        return udgDato.forskelIDage(this) % 7 + 1; // this er det aktuelle objekt

        // % 7 == 17000301 == 1
    }


    public final String ugeDageTekst() //her kalder vi ugedag metoden på dag array'en, så vi får returneret en string med navn
    {
        return dag[ugeDag()];
    }


    public int getDatoen()
    {
        return datoen;
    }

    public int getAar()
    {
        return datoen / 10000;
    }

    public int getMaaned()
    {
        int maaned = 0;
        maaned = datoen / 100;
        maaned = maaned % 100;
        return maaned;
    }
    public int getMaanedshort()
    {
        return (datoen / 100) % 100;
    }
    public int getDag()
    {
        return (datoen % 100);
    }
    public int getKvartal()
    {
        return (getMaaned() + 2) / 3;
    }



    //boolen kan kun retunere true eller false
    //
    public boolean skudAar()
    {
        int aar = getAar();

        if (aar % 4 !=0)
        {
            return false;
        }
        else
        if (aar % 100 == 0 && aar % 400 != 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    //prof måde at skrive det på, men efterlign den lange måde til at starte med
    public boolean skudAarProf()
    {
        int aar = getAar();
        if (aar % 4 != 0 )
            return false;
        if (aar % 100 == 0 && aar % 400 !=0)
            return false;
        return true;
    }


    /*public boolean valid()
    {
        int datoen = getDatoen();

        if (datoen < 17000301) {
            return false;
        } else if (getDag() < 1 && getDag() > 31) {
            return false;
        } else if (getMaaned() < 1 || getMaaned() > 12) {
            return false;
        } else if ((getMaaned() == 1 || getMaaned() == 4 || getMaaned() == 6 ||  getMaaned() == 9 || getMaaned() == 11) && getDag() > 30) {
            return false;
        } else if ((skudAar() == true) && getDag() > 29) {
            return false;
        } else if ((skudAar() != true) && getDag() > 28) {
            return false;
        }  else {
        return true;}
    }*/

         /*public boolean validSimpel()
     { int datoen = getDatoen();

     if (datoen < 17000301)
         return false;

     if (getDag() > 31)
         return false;

     if (getMaaned() < 1 || getMaaned() > 12)
         return false;

     if ((getMaaned() == 1 || getMaaned() == 4 || getMaaned() == 6 || getMaaned() == 9 || getMaaned() == 11) && getDag() > 30)
         return false;

     if (skudAar() == true && getDag() > 29)
         return false;

     if (skudAar() != true && getDag() > 28)
         return false;

     return true;

    }*/

    public boolean valid()
    {
        int datoen = getDatoen();

        if(datoen < 17000301)
        {
            return false;
        }

        if (getDag() > 31)
        {
            return false;
        }

        if (getMaaned() < 1 || getMaaned() > 12)
        {
            return false;
        }

        if (getDag() < 1 || getDag() > 31)
        {
            return false;
        }

        if ((getMaaned() == 4||getMaaned() == 6 || getMaaned() == 9 || getMaaned() == 11) && getDag() > 30)
        {
            return false;
        }

        if (skudAar() && getMaaned() == 2 && getDag() > 29)
        {
            return false;
        }

        if (!skudAar() && getMaaned() == 2 && getDag() > 28)
        {
            return false;
        }
        else
        {
            return true;
        }
    }




    //precondition: datoen er valid
    public int dagIAar() {
        int skudDag = 0;
        if (skudAar())
            skudDag = 1;

        if (getMaaned() == 1)
            return getDag();

        if (getMaaned() == 2)
            return 31 + getDag();

        if (getMaaned() == 3)
            return 59 + getDag() + skudDag;

        if (getMaaned() == 4)
            return 90 + getDag() + skudDag;

        if (getMaaned() == 5)
            return 120 + getDag() + skudDag;

        if (getMaaned() == 6)
            return 151 + getDag() + skudDag;

        if (getMaaned() == 7)
            return 181 + getDag() + skudDag;

        if (getMaaned() == 8)
            return 212 + getDag() + skudDag;

        if (getMaaned() == 9)
            return 243 + getDag() + skudDag;

        if (getMaaned() == 10)
            return 274 + getDag() + skudDag;

        if (getMaaned() == 11)
            return 305 + getDag() + skudDag;

        return 334 + getDag() + skudDag;
    }

    public int dagIAarShort() {
        int skudDag = 0;
        if (skudAar() && getMaaned() > 2)
            skudDag = 1;

        int[]sumDag = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

        return getDag() + skudDag + sumDag[getMaaned()] ;
    };

    public int restDagIAar ()
    {
        int totaleDage;
        if (skudAar())
        {
            totaleDage = 366;
        }
        else
        {
            totaleDage = 365;
        };

        return dagIAarShort() - totaleDage ;
    }

    public int restDageIAarXtra()
    {
        if (skudAar())
            return 366 - dagIAar();
        else
            return 365 - dagIAar();
    }
}


