public class Tuppel {
    int x;
    int y;

    public Tuppel(int kolonne, int rad){
        x = kolonne;
        y = rad;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
