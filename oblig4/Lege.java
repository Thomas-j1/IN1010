/**
 * Oppretter klassen Lege som implementerer grensesnittet Comparable<lege>
 */
class Lege implements Comparable<Lege> {
    protected String navn;
    protected Lenkeliste<Resept> utskrevedeResepter;

    /**
     * Oppretter konstruktoeren, og tar med paramteren String n.
     */
    public Lege(String n) {
        navn = n;
        utskrevedeResepter = new Lenkeliste<>();
    }

    /**
     * Returnerer navn
     */
    public String hentNavn() {
        return navn;
    }

    /**
     * Returnerer en utskrift som er tilpasset klassen paa en ryddig og fin maate.
     */
    public String toString() {
        String streng = navn + "\n";
        return streng;
    }

    /**
     * Metoden skrivHvitResept er en metode som oppretter et HvitResept objekt og
     * legger den til i en lenkeliste som bestaar av Resept objekter
     */
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            if (!(this instanceof Spesialist)) {
                throw new UlovligUtskrift(this, legemiddel);
            }
        }
        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(hvitResept);
        return hvitResept;
    }

    /**
     * Metoden skrivMilitaerResept er en metode som oppretter et MilitaerResept
     * objekt og legger den til i en lenkeliste som bestaar av Resept objekter
     */
    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            if (!(this instanceof Spesialist)) {
                throw new UlovligUtskrift(this, legemiddel);
            }
        }
        MilitaerResept militaerResept = new MilitaerResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(militaerResept);
        return militaerResept;
    }

    /**
     * Metoden skrivPResept er en metode som oppretter et PResept objekt og legger
     * den til i en lenkeliste som bestaar av Resept objekter
     */
    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            if (!(this instanceof Spesialist)) {
                throw new UlovligUtskrift(this, legemiddel);
            }
        }
        PResept pResept = new PResept(legemiddel, this, pasient);
        utskrevedeResepter.leggTil(pResept);
        return pResept;
    }

    /**
     * Metoden skrivBlaaResept er en metode som oppretter et BlaaResept objekt og
     * legger den til i en lenkeliste som bestaar av Resept objekter
     */
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            if (!(this instanceof Spesialist)) {
                throw new UlovligUtskrift(this, legemiddel);
            }
        }
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(blaaResept);
        return blaaResept;
    }

    /**
     * CompareTo metoden brukes for at Legene sorteres alfabetisk etter navn.
     */
    @Override
    public int compareTo(Lege o) {
        return navn.compareTo(o.hentNavn());
    }

    /*
     * Oppretter en instans av Lenkeliste for aa holde styr paa utskrevede resepter.
     * returnerer en liste av disse reseptene.
     */
    public Lenkeliste<Resept> hentUtskrevedeResepter() {
        return utskrevedeResepter;
    }
}
