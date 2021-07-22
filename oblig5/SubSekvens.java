public class SubSekvens {

    private String subSekvens;
    private int antall;

    public void settSubSekvens(String subSek){
        subSekvens = subSek;
    }

    public String hentSubSekvens() {
        return subSekvens;
    }

    public void settAntall(int ant){
        antall = ant;
    }

    public int hentAntall(){
        return antall;
    }

    @Override
    public String toString() {
        String strenge = ""; //super.toString();
        strenge+="Subsekvens: " + subSekvens + ", Antall forekomster: " + antall;
        return strenge;
    }
}
