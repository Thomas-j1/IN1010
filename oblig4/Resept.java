/**
 * Oppretter den abstracte classen Resept Inneholder alle variblene den trenger
 */
abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient; //
    protected int reit;
    protected int id;
    protected static int idCount = 0;

    /**
     * Oppretter konstruktoeren, med parameterne den skal ha.
     */
    public Resept(Legemiddel middel, Lege lg, Pasient p, int rt) {
        legemiddel = middel;
        utskrivendeLege = lg;
        pasient = p;
        reit = rt;
        id = ++idCount;

    }

    /**
     * Returnerer this.id
     */
    public int hentId() {
        return id;
    }

    /**
     * Returnerer this.legemiddel
     */
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    /**
     * Returnerer this.utskrivendeLege
     */
    public Lege hentLege() {
        return utskrivendeLege;
    }

    /**
     * Returnerer this.pasientId
     */
    public int hentPasientId() {
        return pasient.hentID();
    }

    /**
     * Returnerer this.reit
     */
    public int hentReit() {
        return reit;
    }

    /**
     * Returnerer True eller False avhengig av om resepten har flere reit igjen
     */
    public boolean bruk() { // bruker resept en gang, og returnerer true eller
                            // false hvis det funker
        if (reit > 0) {
            reit -= 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Oppretter en abstract metode Subklassene bruker denne til aa avgjoere hvilken
     * farge resepten har
     */
    abstract public String farge();

    /**
     * Oppretter en abstract metode Subklassene bruker denne til aa avgjoere hvor
     * mye pasienten skal betale
     */
    abstract public int prisAaBetale();

    /**
     * Returnerer en utskrift som er tilpasset klassen paa en ryddig og fin maate.
     */
    public String toString() {
        String streng = "Id: " + id + "\n";
        streng += "Middel: " + legemiddel.hentNavn() + "\n";
        streng += "Lege: " + utskrivendeLege.hentNavn() + "\n";
        streng += "Pasient: " + pasient.hentID() + "\n";
        streng += "Reit: " + reit + "\n";
        streng += "Pris: " + legemiddel.hentPris() + "\n";
        return streng;
    }
}
