public class Dato {
    private final int datoen; // Format YYYYMMDD

    public Dato(int dag, int maaned, int aar) {
        this.datoen = aar * 10000 + maaned * 100 + dag;
    }

    public int getDag() {
        return datoen % 100;
    }

    public int getMaaned() {
        return (datoen / 100) % 100;
    }

    public int getAar() {
        return datoen / 10000;
    }

    public boolean erSkudAar() {
        int aar = getAar();
        return (aar % 4 == 0 && aar % 100 != 0) || (aar % 400 == 0);
    }

    public int dagIAar() {
        int[] akkumuleredeDage = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int dagIAar = akkumuleredeDage[getMaaned() - 1] + getDag();
        return dagIAar + (erSkudAar() && getMaaned() > 2 ? 1 : 0);
    }
}
