import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class SammenSlaaTraad implements Runnable{

    HashMapBeholder hoved;
    CountDownLatch leggSammen;

    public SammenSlaaTraad(HashMapBeholder hM, CountDownLatch c){
        hoved = hM;
        leggSammen = c;
    }

    //slaar sammen to hashmaps i beholder, og legger nytt hashmap tilbake
    @Override
    public void run() {
        while (hoved.antallHashMaps() > 1) { //kjorer til det bare er ett hashmap i beholder
            leggSammen(hoved);
            leggSammen.countDown(); //teller ned countdownlatch
        }
        System.out.println("sammenslaa traad ferdig.");
    }

    //henter ut to hashmaps fra beholder, slaar sammen disse, og legger hashmap tilbake
    public void leggSammen(HashMapBeholder hM) { 
        HashMap<String, SubSekvens> mapEn = hM.hentUtHashMap();
        HashMap<String, SubSekvens> mapTo = hM.hentUtHashMap();

        HashMap<String, SubSekvens> sammen = HashMapBeholder.sammenSlaa(mapEn, mapTo);
        hM.leggTilHashMap(sammen);
        System.out.println(hM.antallHashMaps());

    }
}
