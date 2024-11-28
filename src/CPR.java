public class CPR {
    private final String nummeret; // CPR-nummer i format DDMMYY-XXXX

    public CPR(String nummeret) {
        this.nummeret = nummeret;
    }

    public int getDag() {
        return Integer.parseInt(nummeret.substring(0, 2));
    }

    public int getMaaned() {
        return Integer.parseInt(nummeret.substring(2, 4));
    }

    public int getAar() {
        int centuryCode = Integer.parseInt(nummeret.substring(6, 7));
        int aar = Integer.parseInt(nummeret.substring(4, 6));
        return (centuryCode <= 3 ? 1900 : centuryCode <= 5 ? 1800 : 2000) + aar;
    }

    public Dato getFodselsdato() {
        return new Dato(getDag(), getMaaned(), getAar());
    }

    public int getAlder(int nuAar) {
        return nuAar - getAar();
    }

    public boolean erMand() {
        return Integer.parseInt(nummeret.substring(nummeret.length() - 1)) % 2 != 0;
    }
}