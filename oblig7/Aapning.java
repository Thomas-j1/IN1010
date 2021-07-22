import java.util.ArrayList;

public class Aapning extends HvitRute{

    public Aapning(int kol, int rd, Labyrint lab){
        super(kol, rd, lab);
    }

    @Override
    public char tilTegn() {
        return '.';
    }

    //legger til sti i array med utveier i labyrint
    public void gaa(Rute forrige, ArrayList<Tuppel> sti) {
        //System.out.println("Kolonne: " + kolonne + ", Rad: " + rad + " Aapning" + "\n");
        Tuppel kordinater = new Tuppel(kolonne, rad);
        sti.add(kordinater);
        labyrint.utveier.add(sti);
    }
    
}
