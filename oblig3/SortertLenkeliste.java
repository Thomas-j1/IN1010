

class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

    @Override
    public void leggTil(T x){ //legger til element i sortert rekkefolge,
        Node tmp = start;
        Node forrige = start;

        if (erTom()){ //hvis listen er tom
            start = new Node(x);
            return;
        }

        while (!(tmp == null)) { //gaar igjennom elementene
            //System.out.println(x);
            //System.out.println(tmp.data);
            //System.out.println(x.compareTo(tmp.data));
            //^debug
            if (x.compareTo(tmp.data) >= 0) { //hvis parameteret er storre, gaa til neste element i liste
                forrige = tmp;
                tmp = tmp.neste;

            }
            else { //legger til element i lenkelisten, foran tmp
                if (forrige == tmp){ //hvis det bare et ett element i listen
                    start = new Node(x);
                    start.neste = tmp;
                    return;
                }

                forrige.neste = new Node(x);
                Node nye = forrige.neste;
                nye.neste = tmp;
                return;
            }
        }

        forrige.neste = new Node(x); //hvis elementet er storre en alle elementer i liste, legg til paa slutten
    }

    @Override
    public T fjern()throws UgyldigListeIndeks{ //fjerner det storste/siste elementet i listen og returnerer dette
        if (erTom()) {throw new UgyldigListeIndeks(0);}
        Node siste = start;
        Node tmp = start;

        for (int i = 0; i < stoerrelse() - 2; i++){ //henter siste element og angir siste som dette
            siste = siste.neste;
        }
        if (siste == start){ //hvis lenkeliste inneholder 1 element
            start = null;
            return siste.data;
        }

        tmp = siste.neste;
        siste.neste = null;
        return tmp.data;

    }

    @Override
    public void leggTil(int pos, T x) throws UnsupportedOperationException{ //kaster unntak hvis man bruker den arvete metoden
        throw new UnsupportedOperationException();
    }

    @Override
    public void sett(int pos, T x)throws UnsupportedOperationException{ //kaster unntak hvis man bruker den arvete metoden
        throw new UnsupportedOperationException();
    }
}
