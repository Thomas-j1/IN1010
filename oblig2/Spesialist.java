
class Spesialist extends Lege implements Godkjenningsfritak{
    protected String kontrollId;

    public Spesialist(String navn, String kId){
        super(navn);
        kontrollId = kId;
    }

    public String hentKontrollId(){
        return kontrollId;
    }

    public String toString(){
        String streng = super.toString();
        streng += "kontroll ID: " + kontrollId + "\n";
        return streng;
    }
}
