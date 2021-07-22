//import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Labyrint{
    
    //private ArrayList<Rute[]> ruteArray = new ArrayList<>();
    Rute[][] ruteArray;
    int antallRader;
    int antallKolonner;
    ArrayList<ArrayList<Tuppel>> utveier = new ArrayList<>();

    public Labyrint(File fil) throws FileNotFoundException{
        lesFraFil(fil); //oppretter labyrint fra fil
        settNaboer(); //setter naboer for ruter i labyrint
    }

    @Override
    public String toString() {
        String strenge = "";
        for (int rad = 0; rad<ruteArray.length; rad++){
            for (int kolonne = 0; kolonne<ruteArray[rad].length; kolonne++){
                strenge+=ruteArray[rad][kolonne].tilTegn();
                //System.out.print("legger til: " + ruteArray[rad][kolonne].tilTegn());
            }
            strenge+="\n";
        }
        return strenge;

    }

    //leser fra fil og oppretter labyrint/rutenett og
    //oppretter rute varianter fra fil
    private void lesFraFil(File fil) throws FileNotFoundException{
        //File fil = new File(filnavn);
        Scanner in;
        in = new Scanner(fil);

        String storrelse = in.nextLine();
        String[] radKolonne = storrelse.split(" ");

        int rader = Integer.parseInt(radKolonne[0]); //henter antall rader til array
        int kolonner = Integer.parseInt(radKolonne[1]); //henter antall kolonner til array

        antallRader = rader; 
        antallKolonner = kolonner; 

        ruteArray = new Rute[rader][kolonner]; //setter storrelse paa array

        int rad = 0; //index
        while (in.hasNextLine()) {
            String linje = in.nextLine();

            //for hvert tegn/kolonne i linje fra fil
            for (int kolonne = 0; kolonne<linje.length(); kolonne++){ 

                char k = linje.charAt(kolonne);
                Character c = k;
                if(c.equals('#')){ //oppretter SortRute og legger til i labyrint
                    SortRute rute = new SortRute(kolonne, rad, this);
                    //System.out.println("legger til: " + rute.tilTegn() + ", i posisjon: " + rad + kolonne);
                    ruteArray[rad][kolonne] = rute;
                }else if(c.equals('.')){ // oppretter Hvit rute/Aapning og legger til i labyrint
                    if (rad == 0 || rad == rader-1 || kolonne == 0 || kolonne == kolonner-1){
                        Aapning rute = new Aapning(kolonne, rad, this);
                        ruteArray[rad][kolonne] = rute;
                    }
                    else{
                        HvitRute rute = new HvitRute(kolonne, rad, this);
                        //System.out.println("legger til: " + rute.tilTegn() + ", i posisjon: " + rad + kolonne);
                        ruteArray[rad][kolonne] = rute;
                    }
                }else{
                    System.out.println("Feil i innlesning fra fil. Fant annen karakter enn # og .");
                }
            }
            rad ++;
        }
        in.close();
    }

    //returnerer liste med tuppler,
    //som viser utvei fra en gitt posisjon i labyrint
    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kol, int rad) {
        utveier = new ArrayList<>();
        ruteArray[rad][kol].finnUtvei();
        Reset();
        return utveier;
    }

    //skriver ut antall utveier og kortest utvei fra en gitt posisjon i labyrint
    public void finnKortestUtveiFra(int kol, int rad) {
        utveier = new ArrayList<>();
        ruteArray[rad][kol].finnUtvei();
        if(utveier.size()>0){
            ArrayList<Tuppel> kortest = utveier.get(0);
            for (ArrayList<Tuppel> sti : utveier) {
                if (sti.size() < kortest.size()) {
                    kortest = sti;
                }
            }

            System.out.println("Antall utveier: " + utveier.size());
            System.out.println("En av de korteste utveiene: " + "\n");
            for (Tuppel t : kortest) {
                System.out.println(t);
            }
        }
        Reset();
    }

    //skriver ut antall utveier og kortest utvei fra en gitt posisjon i labyrint
    public void finnKortestUtveiFra(ArrayList<ArrayList<Tuppel>> alleUtveier) {
        if(alleUtveier.size()>0){
            ArrayList<Tuppel> kortest = alleUtveier.get(0);
            for (ArrayList<Tuppel> sti : alleUtveier) {
                if (sti.size() < kortest.size()) {
                    kortest = sti;
                }
            }

            System.out.println("Antall utveier: " + utveier.size());
            System.out.println("En av de korteste utveiene: " + "\n");
            for (Tuppel t : kortest) {
                System.out.println(t);
            }
        }
    }

    //setter naboer i ruter fra ruteArray
    private void settNaboer(){
        for (int rad = 0; rad < ruteArray.length; rad++) {
            for (int kolonne = 0; kolonne < ruteArray[rad].length; kolonne++) {
                Rute gjeldendeRute = ruteArray[rad][kolonne];
                
                if(rad + 1 < antallRader){
                    gjeldendeRute.settSyd(ruteArray[rad + 1][kolonne]);
                }
                if (rad - 1 >= 0) {
                    gjeldendeRute.settNord(ruteArray[rad - 1][kolonne]);
                }
                if (kolonne + 1 < antallKolonner){
                    gjeldendeRute.settOst(ruteArray[rad][kolonne+1]);
                }

                if (kolonne - 1 >= 0) {
                    gjeldendeRute.settVest(ruteArray[rad][kolonne - 1]);
                }
            }
        }
    }
    
    //metode som skriver ut info for aa sjekke at riktige naboer ble satt
    public void testNaboer(){
        for (int rad = 0; rad < ruteArray.length; rad++) {
            for (int kolonne = 0; kolonne < ruteArray[rad].length; kolonne++) {
                Rute gjeldendeRute = ruteArray[rad][kolonne];
                if (rad == 0 || kolonne == 0){
                    char nord = '0';
                    char syd = '0';
                    char vest = '0';
                    char ost = '0';
                    if (gjeldendeRute.nord != null){
                        nord = gjeldendeRute.nord.tilTegn();
                    }

                    if (gjeldendeRute.syd != null) {
                        syd = gjeldendeRute.syd.tilTegn();
                    }

                    if (gjeldendeRute.vest != null) {
                        vest = gjeldendeRute.vest.tilTegn();
                    }

                    if (gjeldendeRute.ost != null) {
                        ost = gjeldendeRute.ost.tilTegn();
                    }

                    System.out.print("Rad: " + rad + ", Kolonne: " + kolonne + ", Nord: " + nord
                            + ", Syd: " + syd + ", Vest: " + vest + ", Ost: "
                            + ost);
                }
            }
        }
    }

    private void Reset(){
        for (int rad = 0; rad < ruteArray.length; rad++) {
            for (int kolonne = 0; kolonne < ruteArray[rad].length; kolonne++) {
                Rute gjeldendeRute = ruteArray[rad][kolonne];
                gjeldendeRute.reset();
            }
        }
    }

}

