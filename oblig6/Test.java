import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Test{
    public static void main(String[] args) {
        
        String filnavn = "labyrinter/7.in";
        
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = new Labyrint(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }
        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        System.out.println("Skriv inn koordinater <kolonne> <rad> ('a' for aa avslutte)");
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {
            try {
                int startKol = Integer.parseInt(ord[0]);
                int startRad = Integer.parseInt(ord[1]);
                System.out.println("Utveier:");
                ArrayList<ArrayList<Tuppel>> utveier = l.finnUtveiFra(startKol, startRad);
                for (ArrayList<Tuppel> lis: utveier) {
                    for(Tuppel t: lis)
                        System.out.println(t);
                    System.out.println();
                }
                System.out.println();
                l.finnKortestUtveiFra(utveier);
                //System.out.println();
                //l.finnKortestUtveiFra(startKol, startRad);
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }         
            System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
            ord = inn.nextLine().split(" ");
        }
        inn.close();
    

        /*
        String[] d = new String[5];
        System.out.println(d.length);
        d[0] = "D";
        d[1] = "e";
        System.out.println(d.length);
        for (int i = 0; i<d.length; i++){
            if(d[i] == null){
                d[i] = "test";
                break;
            }
        }
        for (int i = 0; i<d.length; i++){
            System.out.println(d[i]);
        }*/

        /*
        String filnavn = "labyrinter/3.in";
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = new Labyrint(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }
        System.out.println(l.toString());
        //l.testNaboer();
        l.finnUtveiFra(5, 3);
        l.finnUtveiFra(5, 1);*/

    }
}