import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.math3.stat.inference.BinomialTest;
import org.apache.commons.math3.stat.inference.AlternativeHypothesis;


public class VirusHoved{
    static int subLengde = 3; //lenge paa substrenger som skal lages
    static int antallFiler = 0; //antall filer som er lest inn
    static int antallTraader; //antall traader som skal legge sammen hashmaps

    //beholder med de som har hatt virus
    static HashMapBeholder sykBeholder = new HashMapBeholder(); 
    //beholder med de som ikke har hatt virus
    static HashMapBeholder friskBeholder = new HashMapBeholder(); 
    //oversikt over filer, og om de har vaert syk eller ikke(true/false)
    static HashMap<String, Boolean> filer = new HashMap<>(); 
    //filer som skal leses inn/ikke har blitt lest inn.
    static volatile ArrayList<String> filerTilLesing = new ArrayList<>();

    static CountDownLatch filerIgjen; //antall filer som ikke har blitt lest inn
    static CountDownLatch leggSammen; //antall hashmaps som skal legges sammen
    static String metadata; //fil som skal inneholder filer som skal leses
    static String filPath = ""; //mappe sti til filer

    public static void main(String[] args) throws InterruptedException{

        //metadata = args[1];
        int antallTraader = Integer.parseInt(args[0]);
        String metadata = "Data/metadata.csv";
        //int antallTraader = 16;
        
        if(metadata.contains("/")){ //hvis filer ligger i egen mappe
            filPath = metadata.split("/")[0] + "/";
        }

        ArrayList<String> linjer = lesFraFil(metadata); //filer som skal leses

        //legger til filer i ett hashmap, med boolean verdi true eller false for syk
        //oppretter en volatile arraylist som kan fjernes og hentes elementer fra,
        //med filer til lesing.
        for (String linje: linjer){
            String[] delt = linje.split(",");
            if(delt.length>1 && delt != null){
                filer.put(delt[0], Boolean.parseBoolean(delt[1]));
                filerTilLesing.add(delt[0]);
            }
        }

        antallFiler = filerTilLesing.size();
        filerIgjen = new CountDownLatch(antallFiler);
        
        //starter traader som leser inn data fra filer
        for (int i = 0; i <10; i++){
            Thread traad = new Thread(new FilLeserTraad(filerTilLesing, subLengde, filerIgjen, 
            filer, sykBeholder, friskBeholder, filPath));
            System.out.println("starter traad " + i);
            traad.start();
        }


        filerIgjen.await(); //venter til alle filer er lest
        //setter antall hashmaps som skal legges sammen
        leggSammen = new CountDownLatch((sykBeholder.antallHashMaps() + 
        friskBeholder.antallHashMaps())-2); 
        System.out.println("Slaar sammen: " + "\n");

        //starter traader som legger sammen hashmaps i beholdere
        for (int i = 0; i < antallTraader/2; i++) {
            Thread friskeTraad = new Thread(new SammenSlaaTraad(friskBeholder, leggSammen));
            System.out.println("starter frisk sammenslaa traad " + i);
            friskeTraad.start();

            Thread sykeTraad = new Thread(new SammenSlaaTraad(sykBeholder, leggSammen));
            System.out.println("starter syk sammenslaa traad " + i);
            sykeTraad.start();

        }

        leggSammen.await(); //venter til hashmaps er slaatt sammen

        //enkeltStatistiskTest(); //starter enkel test
        //kortTest();
        binomTest();

    }
    //skriver ut alle subsekvenser der antall forekomster hos personer som har hatt 
    //viruset minus antall forekomster hos personer som ikke har hatt viruset 
    //er større enn eller lik 5
    public static void enkeltStatistiskTest(){
        HashMap<String, SubSekvens> friske = friskBeholder.hentUtHashMap();

        for (SubSekvens sS : sykBeholder.hentUtHashMap().values()) {
            SubSekvens sF = friske.get(sS.hentSubSekvens());
            int forskjell = 0;
            if (sF != null){
                forskjell = sS.hentAntall() - sF.hentAntall();
            }
            if(forskjell>=5){
                System.out.println(sS.hentSubSekvens() + ": Forskjell: "+ forskjell + ", med antall: "
                 + sS.hentAntall() + " hos syke, og med antall: " + sF.hentAntall() + " hos friske." );
            }
        }
    }

    public static void binomTest(){
        HashMap<String, SubSekvens> friske = friskBeholder.hentUtHashMap();
        // javac -cp
        // .:/Users/tj/Desktop/Studie/IN1010/obliger/oblig5/commons-math3-3.6.1/commons-math3-3.6.1.jar
        // VirusHoved.java && java -cp
        // .:/Users/tj/Desktop/Studie/IN1010/obliger/oblig5/commons-math3-3.6.1/commons-math3-3.6.1.jar
        // VirusHoved Data/metadata.csv 16
        for (SubSekvens sS : sykBeholder.hentUtHashMap().values()) {
            SubSekvens sF = friske.get(sS.hentSubSekvens());

            int numberOfSuccesses = sS.hentAntall();
            int numberOfTrials = sS.hentAntall();

            if (sF != null) {
                numberOfTrials += sF.hentAntall();
            }
            double probability = 0.5;
            //AlternativeHypothesis alternativeHypothesis = AlternativeHypothesis.GREATER_THAN;

            BinomialTest test = new BinomialTest();
            double resultat = test.binomialTest(numberOfTrials, numberOfSuccesses, probability, 
                    AlternativeHypothesis.GREATER_THAN);
            
            if(resultat<0.05){
                System.out.println(sS.toString());
            }
        }
    }

    //skriver ut alle substringer med forekomster storre enn 4 i begge beholdere.
    public static void kortTest(){
        for (SubSekvens sS : sykBeholder.hentUtHashMap().values()) {
            if (sS.hentAntall() > 4)
                System.out.println(sS.hentSubSekvens() + "  med antall:   " + sS.hentAntall());
        }
        System.out.println("\n");

        for (SubSekvens sS : friskBeholder.hentUtHashMap().values()) {
            if (sS.hentAntall() > 4)
                System.out.println(sS.hentSubSekvens() + "  med antall:   " + sS.hentAntall());
        }
    }

    // Leser fil og returnerer en Arraylist, med strenger for hver linje i fil
    public static ArrayList<String> lesFraFil(String filnavn) {
        File fil = new File(filnavn);
        Scanner in;
        ArrayList<String> data = new ArrayList<>();

        try {
            in = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            in = new Scanner("");
        }

        while (in.hasNextLine()) {
            String linje = in.nextLine();
            data.add(linje);
        }
        in.close();
        return data;
    }
}
