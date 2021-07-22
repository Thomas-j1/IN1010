
class PResept extends HvitResept{

    public PResept(Legemiddel middel, Lege lg, int pId){
        super(middel, lg, pId, 3);

    }

    @Override
    public int prisAaBetale(){
        int pris = legemiddel.hentPris();
        pris -= 108;
        if (pris < 0){
            pris = 0;
        }
        return pris;
    }
}
