
class TestResepter{
    private static int teller = 1;
    public static void main(String[] args) {
        Lege lege = new Lege("Lege1navn");
        Vanlig vanlig = new Vanlig("Vanlig1", 100, 10.5);
        Vanedannende vane = new Vanedannende("Vanedannende1", 200, 20.5, 20);
        Narkotisk nark = new Narkotisk("Narkotisk1", 300, 30.5, 30);

        HvitResept hvitR = new HvitResept(vanlig, lege, 1, 2); //tester for hvit resept klassen
        intTest(hvitR.hentId(), 1); //Test1
        middelTest(hvitR.hentLegemiddel(), vanlig); //Test2
        legeTest(hvitR.hentLege(), lege); //Test3
        intTest(hvitR.hentPasientId(), 1); //Test4
        intTest(hvitR.hentReit(), 2); //Test5
        booleanTest(hvitR.bruk(), true); //Test6
        booleanTest(hvitR.bruk(), true); //Test7
        booleanTest(hvitR.bruk(), false); //Test8
        intTest(hvitR.hentReit(), 0); //Test9
        stringTest(hvitR.farge(), "hvit"); //Test10
        intTest(hvitR.prisAaBetale(), 100); //Test11
        System.out.println(hvitR.toString());

        BlaaResept blaaR = new BlaaResept(vane, lege, 2, 5); //tester for blaa resept klassen
        intTest(blaaR.hentId(), 2); //Test12
        middelTest(blaaR.hentLegemiddel(), vane); //Test13
        intTest(blaaR.hentPasientId(), 2); //Test14
        intTest(blaaR.hentReit(), 5); //Test15
        stringTest(blaaR.farge(), "blaa"); //Test16
        intTest(blaaR.prisAaBetale(), 50); //Test17
        System.out.println(blaaR.toString());

        MilitaerResept milR = new MilitaerResept(nark, lege, 3, 10); //tester for militaer resept klassen
        intTest(milR.hentId(), 3); //Test18
        middelTest(milR.hentLegemiddel(), nark); //Test19
        intTest(milR.hentPasientId(), 3); //Test20
        stringTest(milR.farge(), "hvit"); //Test21
        intTest(milR.prisAaBetale(), 0); //Test22
        System.out.println(milR.toString());

        PResept pR = new PResept(vanlig, lege, 4); //tester for p resept klassen
        intTest(pR.hentId(), 4); //Test23
        intTest(pR.hentPasientId(), 4); //Test24
        intTest(pR.hentReit(), 3); //Test25
        stringTest(pR.farge(), "hvit"); //Test26
        intTest(pR.prisAaBetale(), 0); //Test27
        System.out.println(pR.toString());

    }

    //tenker det sikkert hadde vært best aa ha disse metodene i en egen klasse
    //men var ikke sikker paa om dere ville ha noen ekstra filer

    //int test om forste parameter er lik andre parameter
    public static boolean intTest(int faktiskResultat, int forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    //String test om forste parameter er lik andre parameter
    public static boolean stringTest(String faktiskResultat, String forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    //double test om forste parameter er lik andre parameter
    public static boolean doubleTest(double faktiskResultat, double forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    //boolean test om forste parameter er lik andre parameter
    public static boolean booleanTest(boolean faktiskResultat, boolean forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    //Lege test om forste parameter er lik andre parameter
    public static boolean legeTest(Lege faktiskResultat, Lege forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    //Legemiddel test om forste parameter er lik andre parameter
    public static boolean middelTest(Legemiddel faktiskResultat, Legemiddel forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }


}
