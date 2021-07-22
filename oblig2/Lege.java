
class Lege{
    protected String navn;

    public Lege(String n){
        navn = n;
    }

    public String hentNavn(){
        return navn;
    }

    public String toString(){
        String streng = "Navn: " + navn + "\n";
        return streng;
    }
}
