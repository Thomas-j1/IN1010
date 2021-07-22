import java.util.HashMap;
import java.util.ArrayList;

public class HashMapBeholder {
    
    private ArrayList<HashMap<String, SubSekvens>> hMListe = new ArrayList<>();

    public void leggTilHashMap(HashMap<String, SubSekvens> hM){ 
        hMListe.add(hM);
    }

    //fjerner siste hashmap fra liste og returnerer dette
    public HashMap<String, SubSekvens> hentUtHashMap(){ 
        return hMListe.remove(hMListe.size()-1);
    }

    public int antallHashMaps(){
        return hMListe.size();
    }

    @Override
    public String toString() {
        String strenge = "";

        for(HashMap<String, SubSekvens> map : hMListe){
            for (String subSekvens: map.keySet()){
                strenge+= map.get(subSekvens).toString() + "\n";
            }
            strenge+="\n";
        }

        return strenge;
    }

    //Itererer igjennom to hashmaps fra parameter, 
    //legger til data fra begge hashmaps i nytt hashmap,
    //hvis nokkel finnes i nytt hashmap, legges antall i substring objekt sammen.
    public static HashMap<String, SubSekvens> sammenSlaa(HashMap<String, SubSekvens> hMEn, HashMap<String, SubSekvens> hMTo){
        HashMap<String, SubSekvens> sammen = new HashMap<>();

        for(String subSekvens: hMEn.keySet()){
            if(sammen.containsKey(subSekvens)){
                SubSekvens subInne = sammen.get(subSekvens);
                SubSekvens subUte = hMEn.get(subSekvens);
                subInne.settAntall(subInne.hentAntall() + subUte.hentAntall());
            }else{
                sammen.put(subSekvens, hMEn.get(subSekvens));
            }
        }
        for (String subSekvens : hMTo.keySet()) {
            if (sammen.containsKey(subSekvens)) {
                SubSekvens subInne = sammen.get(subSekvens);
                SubSekvens subUte = hMTo.get(subSekvens);
                subInne.settAntall(subInne.hentAntall() + subUte.hentAntall());
            } else {
                sammen.put(subSekvens, hMTo.get(subSekvens));
            }
        }

        return sammen;
    }
}
