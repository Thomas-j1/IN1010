public class HvitRute extends Rute {

    public HvitRute(int kol, int rd, Labyrint lab){
        super(kol, rd, lab);
    }
    
    @Override
    public char tilTegn() {
        return '.';
    }

    /*
    @Override
    public void gaa(Rute forrige) {
        
    }*/
}
