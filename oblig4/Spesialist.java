/**
 * Oppretter classen Spesialist som arver av Lege, og implementerer
 * grensesnittet til Godkjennigsfritak Oppretter variabelen String kontrollID
 */
class Spesialist extends Lege implements Godkjenningsfritak {
    protected String kontrollId;

    /**
     * Oppretter konstruktoeren, og tar med paramterne fra super klassen + String
     * kontrollID
     */
    public Spesialist(String navn, String kId) {
        super(navn);
        kontrollId = kId;
    }

    /**
     * Metode som overrider metoden: hentKontrollID() fra interfacet
     * Godkjenningsfritak Returnerer this.kontrollID
     */
    public String hentKontrollId() {
        return kontrollId;
    }

    /**
     * Returnerer en utskrift som er tilpasset klassen paa en ryddig og fin maate.
     */
    public String toString() {
        String streng = super.toString();
        streng += "kontroll ID: " + kontrollId + "\n";
        return streng;
    }
}
