import java.util.Iterator;

//Thomas
class Lenkeliste<T> implements Liste<T> {

    protected class Node { // Nodeklasse, som holder styr paa neste element i lenkeliste, og nodens data.
        protected T data;
        protected Node neste;

        public Node(T d) {
            data = d;
        }
    }

    protected Node start;

    public Iterator<T> iterator() {
        return new LenkeListeIterator();
    }

    private class LenkeListeIterator implements Iterator<T> {
        Node iStart = start;

        /*
         * public TIterator(){ start = foerste; }
         */

        @Override
        public T next() {
            if (hasNext()) {
                T idata = iStart.data;
                iStart = iStart.neste;
                return idata;
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return (iStart != null);
        }

        /**
         * Brukes ikke
         */
        public void remove() {

        }

    }

    public int stoerrelse() { // returnerer int med stoerrelse paa lenkeliste
        int i = 0;
        Node tmp = start;
        while (!(tmp == null)) { // oker storrelse int med en til neste node er null
            tmp = tmp.neste;
            i++;
        }
        return i;
    }

    public void leggTil(int pos, T x) throws UgyldigListeIndeks { // legger nytt element i lenkeliste, resterende
                                                                  // elementer blir lagt bak i koen
        if (pos > stoerrelse() || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }
        Node tmp = start;
        Node forrige = start;

        for (int i = 0; i < pos; i++) {
            tmp = tmp.neste;
        }
        for (int i = 0; i < pos - 1; i++) {
            forrige = forrige.neste;
        }

        if (forrige == tmp) { // hvis posisjon index 0
            start = new Node(x);
            start.neste = tmp;
        } else {
            Node ny = new Node(x);
            ny.neste = tmp;
            forrige.neste = ny;
        }
    }

    public void leggTil(T x) { // legger til nytt element bakerst i lenkeliste koen
        Node tmp = start;
        if (erTom()) {
            start = new Node(x);
            return;
        }
        while (!(tmp.neste == null)) { // henter siste element og angir tmp som dette
            tmp = tmp.neste;
        }

        tmp.neste = new Node(x);
    }

    public void sett(int pos, T x) throws UgyldigListeIndeks { // overskriver element paa angitt posisjon
        if (erTom() || pos >= stoerrelse() || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }
        Node tmp = start;
        Node forrige = start;

        for (int i = 0; i < pos; i++) {
            tmp = tmp.neste;
        }
        for (int i = 0; i < pos - 1; i++) {
            forrige = forrige.neste;
        }

        if (forrige == tmp) { // hvis posisjon index 0
            Node ny = new Node(x);
            start = ny;
            start.neste = tmp.neste;
        } else {
            Node ny = new Node(x);
            ny.neste = tmp.neste;
            forrige.neste = ny;
        }

    }

    public T hent(int pos) throws UgyldigListeIndeks { // returnerer element paa angitt index posisjon
        if (erTom() || pos >= stoerrelse() || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }
        Node tmp = start;

        for (int i = 0; i < pos; i++) { // setter tmp som element i posisjon
            tmp = tmp.neste;
        }
        return tmp.data;
    }

    public T fjern(int pos) throws UgyldigListeIndeks { // fjerner element paa angitt index posisjon, og returnerer
                                                        // denne
        if (erTom() || pos >= stoerrelse() || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }
        Node tmp = start;
        Node forrige = start;

        for (int i = 0; i < pos; i++) {
            tmp = tmp.neste;
        }
        for (int i = 0; i < pos - 1; i++) {
            forrige = forrige.neste;
        }

        if (forrige == tmp) { // hvis posisjon index 0
            start = start.neste;
        }
        forrige.neste = tmp.neste;
        return tmp.data;
    }

    public T fjern() throws UgyldigListeIndeks { // fjerner element fremst i lenkeliste koen, og returnerer denne
        if (erTom()) {
            throw new UgyldigListeIndeks(0);
        }
        Node tmp = start;
        start = start.neste;
        return tmp.data;
    }

    public boolean erTom() { // returnerer true/false avhengig av om lenkeliste er tom eller ikke
        return start == null;
    }

    @Override
    public String toString() { // overskriver toString metode til Object, returnerer leselig strenge for
                               // lenkeliste
        String strenge = "";
        Node tmp = start;
        for (int i = 0; i < stoerrelse(); i++) {
            strenge += tmp.data + "\n";
            tmp = tmp.neste;
            strenge += "\n";
        }
        return strenge;
    }
}
