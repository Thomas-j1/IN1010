/**
 * Oppretter den abstracte classen Legemiddel Inneholder alle variblene den
 * trenger
 */
abstract class Legemiddel {
    protected String navn;
    protected int pris;
    protected double virkestoff;
    protected int id;
    protected static int idCount = 0;

    /**
     * Oppretter konstruktoeren, med parameterne den skal ha.
     */
    public Legemiddel(String n, int p, double v) {
        navn = n;
        pris = p;
        virkestoff = v;
        id = ++idCount;
    }

    /**
     * Returnerer this.id
     */
    public int hentId() {
        return id;
    }

    /**
     * Returnerer this.navn
     */
    public String hentNavn() {
        return navn;
    }

    /**
     * Returnerer this.pris
     */
    public int hentPris() {
        return pris;
    }

    /**
     * Returnerer this.virkestoff
     */
    public double hentVirkestoff() {
        return virkestoff;
    }

    /**
     * Returnerer en ny pris, som man skriver inn i parameteren.
     */
    public void settNyPris(int p) {
        pris = p;
    }

    /**
     * Returnerer en utskrift som er tilpasset klassen paa en ryddig og fin maate.
     */
    public String toString() {
        String streng = "Id: " + id + "\n";
        streng += "Navn: " + navn + "\n";
        streng += "Pris: " + pris + "\n";
        streng += "Virkestoff: " + virkestoff + "\n";
        return streng;
    }
}
