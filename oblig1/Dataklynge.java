import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Dataklynge{
    private int noderPerRack;
    private ArrayList<Rack> racks; //bruker ArrayList fordi det er uspesifisert antall racks i array
    private int antNoder;
    private int antMinne;
    private int antCPU;

    public Dataklynge(String fil){ //konstruktør til klassen dataklynge
        racks = new ArrayList<Rack>();
        //noderPerRack = npr;
        File minFil = new File(fil);

        Scanner sc = null;

        try {
            sc = new Scanner(minFil);
        }
        catch(FileNotFoundException e) {
            System.out.println("Error");
            System.out.println(e);
            System.exit(0);
        }
        noderPerRack = sc.nextInt();
        while (sc.hasNext()) {
            antNoder = sc.nextInt();
            antMinne = sc.nextInt();
            antCPU = sc.nextInt();
            for (int i = 0; i < antNoder; i++) {
                plasserNodeIRack(new Node(antMinne, antCPU));
            }
        }

    }

    public void plasserNodeIRack(Node n){ //oppretter rack objekter og legger node objekter inn i dem
        if (racks.size()==0 || racks.get(racks.size()-1).antallNoder()>=noderPerRack) {
            racks.add(new Rack());
        }
        racks.get(racks.size()-1).leggTilNode(n);
    }

    public int antProsessorer(){ //returnerer antall prosessorer i dataklynge
        int prosessorer = 0;
        for (Rack i: racks) {
            prosessorer+= i.rackProsessorer();
        }
        return prosessorer;
    }

    public int noderMedNokMinne(int paakrevdMinne){ //returnerer antall noder med paakrevd minne i dataklynge
        int antallNoder = 0;
        for (Rack i: racks){
            antallNoder+= i.antallMinne(paakrevdMinne);
        }
        return antallNoder;
    }

    public int antallRacks(){ //returnerer antall racks i dataklynge
        return racks.size();
    }

}
