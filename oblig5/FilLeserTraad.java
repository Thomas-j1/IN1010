import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.HashMap;

public class FilLeserTraad implements Runnable{
    
    String filNavn = "";
    int subLengde;
    ArrayList<String> filer;
    boolean stopp = false;
    CountDownLatch filerIgjen;
    HashMapBeholder sykBeholder;
    HashMapBeholder friskBeholder;
    HashMap<String, Boolean> sykEllerFrisk;
    String filPath;
    
    public FilLeserTraad(ArrayList<String> f, int l, CountDownLatch igjen, 
    HashMap<String, Boolean> sEllerF, HashMapBeholder sB, HashMapBeholder fB, String path){
        subLengde = l;
        filer = f;
        filerIgjen = igjen;
        sykEllerFrisk = sEllerF;
        sykBeholder = sB;
        friskBeholder = fB;
        filPath = path;
    }

    //Oppretter hashmaps fra filer, og legger disse til i riktige beholdere
    @Override
    public void run() {
        while(!stopp){ //kjorer saalenge arraylist med filer som skal leses ikke er tom
            if (filer.size()>0){
                filNavn = filer.remove(0); //henter en fil som skal leses fra liste og fjerner denne
            }else {
                stopp = true;
                System.out.println("Ikke flere filer igjen, stopper..");
                return;
            }

            ArrayList<String> immunreseptorer = lesFraFil(filPath + filNavn);
            HashMap<String, SubSekvens> mappet = new HashMap<>();

            for (String immunreseptor : immunreseptorer) { //itererer igjennom strenger fra fil
                int i = 0;
                int r = subLengde;
                while (r <= immunreseptor.length()) { //lager subsekvenser fra strenger
                    String subSekvens = immunreseptor.substring(i, r);

                    SubSekvens subObjekt = new SubSekvens();
                    subObjekt.settSubSekvens(subSekvens);
                    subObjekt.settAntall(1);

                    mappet.putIfAbsent(subSekvens, subObjekt);

                    i++;
                    r++;
                }
            }
            if (sykEllerFrisk.get(filNavn)){
                sykBeholder.leggTilHashMap(mappet);
            } else{
                friskBeholder.leggTilHashMap(mappet);
            }
            filerIgjen.countDown(); //teller ned countdownlatch
            System.out.println("fil ferdig");
        }
    }

    //Leser fil og returnerer en Arraylist, med strenger for hver linje i fil
    private ArrayList<String> lesFraFil(String filnavn) {
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
