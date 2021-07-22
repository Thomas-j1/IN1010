
abstract class Resept{
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId;
    protected int reit;
    protected int id;
    protected static int idCount = 0;

    public Resept(Legemiddel middel, Lege lg, int pId, int rt){
        legemiddel = middel;
        utskrivendeLege = lg;
        pasientId = pId;
        reit = rt;
        idCount+=1;
        id = idCount;
    }

    public int hentId(){
        return id;
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege(){
        return utskrivendeLege;
    }

    public int hentPasientId(){
        return pasientId;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){ //bruker resept en gang, og returnerer true eller
                            //false hvis det funker
        if (reit > 0){
            reit-= 1;
            return true;
        }
        else {return false;}
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    public String toString(){
        String streng = "Id: " + id + "\n";
        streng+= "Middel: " + legemiddel.hentNavn() + "\n";
        streng+= "Lege: " + utskrivendeLege.hentNavn() + "\n";
        streng+= "Pasient: " + pasientId + "\n";
        streng+= "Reit: " + reit + "\n";
        streng+= "Pris: " + legemiddel.hentPris() + "\n";
        return streng;
    }
}
