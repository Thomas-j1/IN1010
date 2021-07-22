import java.util.ArrayList;

public class SortRute extends Rute{
    
    public SortRute(int kol, int rd, Labyrint lab){
        super(kol, rd, lab);
    }

    @Override
    public char tilTegn() {
        return '#';
    }

    @Override
    public void gaa(Rute forrige, ArrayList<Tuppel> sti){
        //System.out.println("Rad: " + rad + ", Kolonne: " + kolonne + ", Blindvei?");
        return;
    }
}
