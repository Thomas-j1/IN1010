
class HvitResept extends Resept{

    public HvitResept(Legemiddel middel, Lege lg, int pId, int rt){
        super(middel, lg, pId, rt);

    }

    @Override
    public String farge(){
        return "hvit";
    }

    public int prisAaBetale(){
        int pris = legemiddel.hentPris();
        return pris;
    }

    public String toString(){
        String streng = super.toString();
        streng+= "Pris å betale: " + prisAaBetale() + "\n";
        streng+= "Farge: " + farge() + "\n";
        return streng;
    }
}
