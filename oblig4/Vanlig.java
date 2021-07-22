/**
 * Oppretter classen Vanlig som er en subklasse av legemiddel
 */
class Vanlig extends Legemiddel {
    /**
     * Oppretter konstruktøren, og tar med paramterne fra super klassen + int styrke
     */
    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }
}
