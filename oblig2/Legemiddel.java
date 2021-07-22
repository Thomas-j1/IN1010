
abstract class Legemiddel{
    protected String navn;
    protected int pris;
    protected double virkestoff;
    protected int id;
    protected static int idCount = 0;

    public Legemiddel(String n, int p, double v){
        navn = n;
        pris = p;
        virkestoff = v;
        idCount +=1;
        id = idCount;
    }

    public int hentId(){
        return id;
    }

    public String hentNavn(){
        return navn;
    }

    public int hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    public void settNyPris(int p){
        pris = p;
    }

    public String toString(){
        String streng = "Id: " + id + "\n";
        streng+= "Navn: " + navn + "\n";
        streng+= "Pris: " + pris + "\n";
        streng+= "Virkestoff: " + virkestoff + "\n";
        return streng;
    }
}
