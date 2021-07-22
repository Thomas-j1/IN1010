

class Stabel<T> extends Lenkeliste<T> {

    public void leggPaa(T x){ //legger til element overst  i stabel
        leggTil(x); //legger til element sist i koen lenkeliste
    }

    public T taAv(){ //fjerner element overst i stabel
        return fjern(stoerrelse()-1); //fjerner element sist i koen lenkeliste
    }

}
