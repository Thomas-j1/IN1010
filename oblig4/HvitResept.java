/**
 * Oppretter den abstracte classen HviteResept som er en subklasse av Resept
 */
class HvitResept extends Resept {
    /**
     * Oppretter konstruktoeren, og tar med paramterne fra super klassen
     */
    public HvitResept(Legemiddel middel, Lege lg, Pasient p, int rt) {
        super(middel, lg, p, rt);

    }

    /**
     * Overrider metoden: farge() fra superklassen, og returnerer Stringer "hvit"
     */
    @Override
    public String farge() {
        return "hvit";
    }

    /**
     * Overrider metoden: prisAaBetale() fra superklassen, og returnerer det som
     * pasienten skal betale
     */
    public int prisAaBetale() {
        int pris = legemiddel.hentPris();
        return pris;
    }

    /**
     * Returnerer en utskrift som er tilpasset klassen paa en ryddig og fin maate.
     */
    public String toString() {
        String streng = super.toString();
        streng += "Pris aa betale: " + prisAaBetale() + "\n";
        streng += "Farge: " + farge() + "\n";
        return streng;
    }
}
