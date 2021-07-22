/**
 * Oppretter classen PResept som er en subklasse av HviteResept
 */
class PResept extends HvitResept {
    /**
     * Oppretter konstruktoeren, og tar med paramterne fra super klassen
     */
    public PResept(Legemiddel middel, Lege lg, Pasient p) {
        super(middel, lg, p, 3);

    }

    /**
     * Overrider metoden: prisAaBetale() fra superklassen, og returnerer det som
     * pasienten skal betale
     */
    @Override
    public int prisAaBetale() {
        int pris = legemiddel.hentPris();
        pris -= 108;
        if (pris < 0) {
            pris = 0;
        }
        return pris;
    }
}
