
class Hovedprogram {
    private static Dataklynge abel;
    //private static Dataklynge d2;
    //private static Dataklynge d3;
    //private static Dataklynge d4;

    public static void main(String[] args) { //oppretter objekter av klassen Dataklynge, og skriver ut verdier av objekt

        //abel = new Dataklynge(12);
        abel = new Dataklynge("dataklynge.txt");
        //d2 = new Dataklynge("dataklynge2.txt");
        //d3 = new Dataklynge("dataklynge3.txt");
        //d4 = new Dataklynge("dataklynge4.txt");


        /*
        for (int i = 0; i < 650; i++) {
            abel.plasserNodeIRack(new Node(64, 1));
        }

        for (int i = 0; i < 16; i++) {
            abel.plasserNodeIRack(new Node(1024, 2));
        }

        System.out.println("Noder med minst 32 GB: " + abel.noderMedNokMinne(32));
        System.out.println("Noder med minst 64 GB: " + abel.noderMedNokMinne(64));
        System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));

        System.out.println("Antall prosessorer: " + abel.antProsessorer());
        System.out.println("Antall racks: " + abel.antallRacks());

        leggTilNoder(abel, 650, 64, 1);
        leggTilNoder(abel, 16, 1024, 2);
        */
        skrivUt(abel);
        //skrivUt(d2);
        //skrivUt(d3);
        //skrivUt(d4);





    }
    private static void skrivUt(Dataklynge n) {
        System.out.println("Noder med minst 32 GB: " + n.noderMedNokMinne(32));
        System.out.println("Noder med minst 64 GB: " + n.noderMedNokMinne(64));
        System.out.println("Noder med minst 128 GB: " + n.noderMedNokMinne(128));

        System.out.println("Antall prosessorer: " + n.antProsessorer());
        System.out.println("Antall racks: " + n.antallRacks());
    }
    /*
    private static void leggTilNoder(Dataklynge klynge, int antallNoder, int minne, int cpu) {
        for (int i = 0; i < antallNoder; i++) {
            klynge.plasserNodeIRack(new Node(minne, cpu));
        }
    }*/
}
