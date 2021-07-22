
class Vanedannende extends Legemiddel{
    int styrke;

    public Vanedannende(String navn, int pris, double virkestoff, int s){
        super(navn, pris, virkestoff);
        styrke = s;
    }

    public int hentVanedannendeStyke(){
        return styrke;
    }

    @Override
    public String toString(){
        String streng = super.toString();
        streng+= "Styrke: " + styrke + "\n";
        return streng;
    }
}
