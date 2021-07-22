import java.util.ArrayList;

class Hovedprogram{

    public static void main(String[] args) {    //oppretter objekter av de ulike klassene
                                                //og skriver ut deres toString() metoder
        ArrayList<Object> objekter = new ArrayList<Object>();
        Lege lege = new Lege("Lege1navn");
        Spesialist spesLege = new Spesialist("Spesialist1navn", "123kontrollID");
        Vanlig vanlig = new Vanlig("Vanlig1", 100, 10.5);
        Vanedannende vane = new Vanedannende("Vanedannende1", 200, 20.5, 20);
        Narkotisk nark = new Narkotisk("Narkotisk1", 300, 30.5, 30);
        HvitResept hvitR = new HvitResept(vanlig, lege, 1, 2);
        BlaaResept blaaR = new BlaaResept(vane, spesLege, 2, 5);
        MilitaerResept milR = new MilitaerResept(nark, spesLege, 3, 10);
        PResept pR = new PResept(vanlig, lege, 4);


        objekter.add(vanlig);
        objekter.add(vane);
        objekter.add(nark);
        objekter.add(lege);
        objekter.add(spesLege);
        objekter.add(hvitR);
        objekter.add(blaaR);
        objekter.add(milR);
        objekter.add(pR);

        for (Object o : objekter){
            System.out.println(o.toString());
        }

    }
}
