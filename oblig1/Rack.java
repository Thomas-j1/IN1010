import java.util.ArrayList;

class Rack{
    private ArrayList<Node> noder; //bruker ArrayList fordi det er uspesifisert antall noder i array;

    public Rack() { //konstruktør til klassen Rack
        noder = new ArrayList<Node>();
    }

    public void leggTilNode(Node n) { //legger til node objekt i rack
        noder.add(n);
    }

    public int antallNoder() { //returnerer antall noder i rack
        return noder.size();
    }

    public int rackProsessorer() { //returnerer antall prosessorer i rack
        int prosessorer = 0;
        for (Node i: noder) {
            prosessorer += i.antallNodeCpuer();
        }
        return prosessorer;
    }

    public int antallMinne(int req) { //returnerer antall noder i rack med minne over krav(req)
        int minne = 0;
        for (Node i: noder) {
            if (i.nodeMinne()>= req) {
                minne++;
            }
        }
        return minne;
    }
}
