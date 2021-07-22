/**
 * Oppretter classen Militerresept som er en subklasse av HviteResept
 */
class MilitaerResept extends HvitResept {
    /**
     * Oppretter konstruktoeren, og tar med paramterne fra super klassen
     */
    public MilitaerResept(Legemiddel middel, Lege lg, Pasient p, int rt) {
        super(middel, lg, p, rt);

    }

    /**
     * Overrider metoden: prisAaBetale() fra superklassen, og returnerer det som
     * pasienten skal betale
     */
    @Override
    public int prisAaBetale() {
        int pris = legemiddel.hentPris();
        return pris * 0;
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
