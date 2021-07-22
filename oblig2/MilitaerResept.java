
class MilitaerResept extends HvitResept{

    public MilitaerResept(Legemiddel middel, Lege lg, int pId, int rt){
        super(middel, lg, pId, rt);

    }

    @Override
    public int prisAaBetale(){
        int pris = legemiddel.hentPris();
        return pris*0;
    }

    public String toString(){
        String streng = super.toString();
        streng+= "Pris å betale: " + prisAaBetale() + "\n";
        streng+= "Farge: " + farge() + "\n";
        return streng;
    }
}
