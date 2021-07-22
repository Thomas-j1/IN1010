/*
Oppretter klassen pasient.

*/
class Pasient {
    private String navn;
    private String fodeNr;
    private int iD;
    private static int teller;

    /*
     * Oppretter konstuktroeren, denne tar i mot navn og fodselsnr som string -
     * parametere.
     */
    public Pasient(String n, String f) {
        navn = n;
        fodeNr = f;
        iD = ++teller;

    }

    /*
     * metode som returnerer id.
     */
    public int hentID() {
        return iD;
    }

    @Override
    public String toString() { // overskriver toString metode til Object, returnerer leselig strenge for
                               // lenkeliste
        String strenge = "Navn: " + navn + "\nFodselsnummer: " + fodeNr;
        return strenge;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFodeNr() {
        return fodeNr;
    }
}
