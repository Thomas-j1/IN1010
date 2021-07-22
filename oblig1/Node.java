
class Node{
    private int minne;
    private int cpu;

    public Node(int m, int c){ //konstruktør til klassen node
        minne = m;
        cpu = c;
    }

    public int antallNodeCpuer() { //returnerer antall prosessorer i node
        return cpu;
    }

    public int nodeMinne() { //returnerer antall minne i node;
        return minne;
    }
}
