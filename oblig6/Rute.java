import java.util.ArrayList;

public abstract class Rute {
    protected int kolonne;
    protected int rad;
    protected Labyrint labyrint;
    protected Rute nord = null;
    protected Rute syd = null;
    protected Rute vest = null;
    protected Rute ost = null;
    protected Boolean besoekt = false;

    public Rute(int kol, int rd, Labyrint lab){
        kolonne = kol;
        rad = rd;
        labyrint = lab;
    }

    public void settNord(Rute r){
        nord = r;
    }

    public void settSyd(Rute r){
        syd = r;
    }

    public void settVest(Rute r){
        vest = r;
    }

    public void settOst(Rute r){
        ost = r;
    }

    //rekursiv metode som finner utvei fra rute i labyrint
    public void gaa(Rute forrige, ArrayList<Tuppel> sti){
        //System.out.println("Kolonne: " + kolonne + ", Rad: " + rad);
        if(!besoekt){
            Tuppel kordinater = new Tuppel(kolonne, rad);
            sti.add(kordinater);
            besoekt = true;

            ArrayList<Tuppel> nySti = new ArrayList<>(sti);
            if (nord != forrige && nord != null) {
                nord.gaa(this, nySti);
            }

            nySti = new ArrayList<>(sti);
            if (syd != forrige && syd != null) {
                syd.gaa(this, nySti);
            }

            nySti = new ArrayList<>(sti);
            if (vest != forrige && vest != null) {
                vest.gaa(this, nySti);
            }

            nySti = new ArrayList<>(sti);
            if (ost != forrige && ost != null) {
                ost.gaa(this, nySti);
            }
        }
    }

    //starter gaa fra denne ruten
    public void finnUtvei(){
        gaa(this, new ArrayList<Tuppel>());
    }

    public void reset(){
        besoekt = false;
    }
    
    public abstract char tilTegn();
}
