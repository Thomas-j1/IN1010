
class Narkotisk extends Legemiddel{
    int styrke;

    public Narkotisk(String navn, int pris, double virkestoff, int s){
        super(navn, pris, virkestoff);
        styrke = s;
    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }

    @Override
    public String toString(){
        String streng = super.toString();
        streng+= "Styrke: " + styrke + "\n";
        return streng;
    }
}
