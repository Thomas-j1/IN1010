
class BlaaResept extends Resept{

    public BlaaResept(Legemiddel middel, Lege lg, int pId, int rt){
        super(middel, lg, pId, rt);
    }

    @Override
    public String farge(){
        return "blaa";
    }

    @Override
    public int prisAaBetale(){
        int pris = legemiddel.hentPris();
        return pris*25/100;
    }

    @Override
    public String toString(){
        String streng = super.toString();
        streng+= "Pris å betale: " + prisAaBetale() + "\n";
        streng+= "Farge: " + farge() + "\n";
        return streng;
    }
}
