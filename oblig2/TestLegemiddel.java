import java.util.ArrayList;

class TestLegemiddel{
    private static int teller = 1;
    public static void main(String[] args) {
        ArrayList<Legemiddel> legemiddler = new ArrayList<Legemiddel>();
        legemiddler.add(new Vanlig("Vanlig1", 100, 10.5));
        legemiddler.add(new Vanedannende("Vanedannende1", 200, 20.5, 20));
        legemiddler.add(new Narkotisk("Narkotisk1", 300, 30.5, 30));

        Vanlig vanlig = (Vanlig) legemiddler.get(0);
        Vanedannende vane = (Vanedannende) legemiddler.get(1);
        Narkotisk nark = (Narkotisk) legemiddler.get(2);

        intTest(vanlig.hentId(), 1); //Test1
        stringTest(vanlig.hentNavn(), "Vanlig1"); //Test2
        intTest(vanlig.hentPris(), 100); //Test3
        vanlig.settNyPris(200);
        intTest(vanlig.hentPris(), 200); //Test4
        doubleTest(vanlig.hentVirkestoff(), 10.5); //Test5

        intTest(vane.hentId(), 2); //Test6
        stringTest(vane.hentNavn(), "Vanedannende1"); //Test7
        intTest(vane.hentPris(), 200); //Test8
        vane.settNyPris(300);
        intTest(vane.hentPris(), 300); //Test9
        doubleTest(vane.hentVirkestoff(), 20.5); //Test10
        intTest(vane.hentVanedannendeStyke(), 20); //Test11

        intTest(nark.hentId(), 3); //Test12
        stringTest(nark.hentNavn(), "Narkotisk1"); //Test13
        intTest(nark.hentPris(), 300); //Test14
        nark.settNyPris(400);
        intTest(nark.hentPris(), 400); //Test15
        doubleTest(nark.hentVirkestoff(), 30.5); //Test16
        intTest(nark.hentNarkotiskStyrke(), 30); //Test17


        for (Legemiddel l : legemiddler){
            /*System.out.println(l.hentId());
            System.out.println(l.hentNavn());
            System.out.println(l.hentPris());
            System.out.println(l.hentVirkestoff());
            System.out.println("");*/
            System.out.println(l.toString());
        }

    }

    //int test om forste parameter er lik andre parameter
    public static boolean intTest(int faktiskResultat, int forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    //String test om forste parameter er lik andre parameter
    public static boolean stringTest(String faktiskResultat, String forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    //double test om forste parameter er lik andre parameter
    public static boolean doubleTest(double faktiskResultat, double forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }
}
