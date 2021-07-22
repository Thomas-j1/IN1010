
/*
importerer relevante java utvidelser.
*/
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

/**
 * Oppretter klassen Legesystem, som oppretter flere instanser av lenkelister,
 * som holder styr paa informasjon av Legemiddel, Resept, Lege og pasient.
 */
class Legesystem {
    Scanner in = new Scanner(System.in);

    Lenkeliste<Legemiddel> legemidler = new Lenkeliste<>();
    Lenkeliste<Resept> resepter = new Lenkeliste<>();
    Lenkeliste<Lege> leger = new Lenkeliste<>();
    Lenkeliste<Pasient> pasienter = new Lenkeliste<>();

    /**
     * Metoden lesFraFilleser inn innformasjonen paa en fil, og oppretter Objekter
     * (Legemiddel, Resept, Lege og Pasient) utifra den informasjon.
     */
    public void lesFraFil(String filnavn) {
        File fil = new File(filnavn);
        Scanner inf;
        try {
            inf = new Scanner(fil);
        } catch (FileNotFoundException exception) {
            System.out.println("Fant ikke filen");
            inf = new Scanner("");
        }

        /**
         * While loekken kjoerer saa lenge scanneren har en neste linje.
         */
        while (inf.hasNextLine()) {

            String linje = inf.nextLine();

            /**
             * Hvis linjen har ordet Pasienter i seg, saa skal den kjoere neste linje. While
             * den loekka ikke har en # i seg, skal den legge forskjellige data inn i et
             * array, og basert paa den dataen skal det opprettes et Pasient objekt som
             * legges inn i pasienter lenkelisten
             */

            if (linje.split(" ")[1].equals("Pasienter")) {
                String linjeInne = inf.nextLine();
                while (!(linjeInne.split(" ")[0].equals("#"))) {
                    String[] data = linjeInne.split(",");
                    String navn = data[0];
                    String fodselsnnr = data[1];
                    pasienter.leggTil(new Pasient(navn, fodselsnnr));
                    linjeInne = inf.nextLine();
                }
                // System.out.println("Sjekker for pasienter: ");
                // System.out.println(pasienter.toString());

                linje = linjeInne;
            }

            /**
             * Hvis linjen har ordet Legemidler i seg, saa skal den kjoere neste linje.
             * While den loekka ikke har en # i seg, skal den legge forskjellige data inn i
             * et array, basert paa hvilken type data[1] har skal den opprette enten et
             * Vanedannende, narkotisk eller vanlig objekt, som deretter legges inn i
             * legemidler lenkelisten.
             */
            if (linje.split(" ")[1].equals("Legemidler")) {
                String linjeInne = inf.nextLine();
                while (!(linjeInne.split(" ")[0].equals("#"))) {
                    try {
                        String[] data = linjeInne.split(",");
                        String navn = data[0];
                        String type = data[1];
                        double doublePris = Double.parseDouble(data[2]);
                        int pris = (int) doublePris;
                        double virkestoff = Double.parseDouble(data[3]);

                        if (type.equals("vanedannende")) {
                            int styrke = Integer.parseInt(data[4]);
                            legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
                        }
                        if (type.equals("narkotisk")) {
                            int styrke = Integer.parseInt(data[4]);
                            legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
                        }
                        if (type.equals("vanlig")) {
                            legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
                        }
                    } catch (Exception e) {
                        System.out.println("Hopper over linje" + e);
                    }

                    linjeInne = inf.nextLine();
                }
                // System.out.println("Sjekker for Legemidler: ");
                // System.out.println(legemidler.toString());
                linje = linjeInne;
            }

            /**
             * Hvis linjen har ordet Leger i seg, saa skal den kjoere neste linje. While den
             * loekka ikke har en # i seg, skal den legge forskjellige data inn i et array,
             * basert paa hvilken type data[1] har skal den opprette enten et Lege eller et
             * Spesialist objekt, som deretter legges inn i leger lenkelisten.
             */
            if (linje.split(" ")[1].equals("Leger")) {
                String linjeInne = inf.nextLine();
                while (!(linjeInne.split(" ")[0].equals("#"))) {
                    String[] data = linjeInne.split(",");
                    String navn = data[0];
                    String type = data[1];

                    if (type.equals("0")) {
                        leger.leggTil(new Lege(navn));
                    } else {
                        leger.leggTil(new Spesialist(navn, type));
                    }
                    linjeInne = inf.nextLine();
                }
                // System.out.println("Sjekker for Leger: ");
                // System.out.println(leger.toString());
                linje = linjeInne;
            }

            /**
             * Hvis linjen har ordet Resepter i seg, saa skal den kjoere neste linje. While
             * den loekka ikke har en # i seg, skal den legge forskjellige data inn i et
             * array, basert paa hvilken type data[3] har skal den opprette enten et
             * HvitResept, BlaaResept, PResept eller et Militaer objekt, som deretter legges
             * inn i resepter lenkelisten.
             */
            if (linje.split(" ")[1].equals("Resepter")) {
                String linjeInne = inf.nextLine();
                while (!(linjeInne.split(" ")[0].equals("#"))) {

                    String[] data = linjeInne.split(",");
                    int legemiddelNummer = Integer.parseInt(data[0]);
                    String legeNavn = data[1];
                    int pasientID = Integer.parseInt(data[2]);
                    String type = data[3];
                    Lege lege = null;
                    Legemiddel legemiddel = null;
                    Pasient pasient = null;

                    /**
                     * Finner lege basert paa legeNavn
                     */
                    for (Lege l : leger) {
                        if (l.hentNavn().equals(legeNavn)) {
                            lege = l;
                        }
                    }

                    /**
                     * Finner legemiddel basert paa ID
                     */
                    for (Legemiddel l : legemidler) {
                        if (l.hentId() == legemiddelNummer) {
                            legemiddel = l;
                        }
                    }

                    /**
                     * Finner pasient basert paa ID
                     */
                    for (Pasient p : pasienter) {
                        if (p.hentID() == pasientID) {
                            pasient = p;
                        }
                    }
                    if (legemiddel == null || pasient == null || lege == null) {
                        System.out.println("Hopper over linje, feil data");
                    } else {
                        try {
                            if (type.equals("hvit")) {
                                int reit = Integer.parseInt(data[4]);
                                resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                            }
                            if (type.equals("blaa")) {
                                int reit = Integer.parseInt(data[4]);
                                resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                            }
                            if (type.equals("p")) {
                                resepter.leggTil(lege.skrivPResept(legemiddel, pasient));
                            }

                            if (type.equals("militaer")) {
                                int reit = Integer.parseInt(data[4]);
                                resepter.leggTil(lege.skrivMilitaerResept(legemiddel, pasient, reit));
                            }
                        } catch (UlovligUtskrift e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (inf.hasNextLine()) {
                        linjeInne = inf.nextLine();
                    } else {
                        // System.out.println("Sjekker for Resepter: ");
                        // System.out.println(resepter.toString());
                        inf.close();
                        return;
                    }
                }
                linje = linjeInne;
                inf.close();
            }
        }
    }

    /*
     * Metode med en kommandolokke som gaar til bruker velger aa avslutte
     * programmet. Kommandolokken gir brukeren valgmulighetene: Skrive ut oversikt,
     * Opprette og legge til nye elementer (leger, resepter, legemidler..), Bruke en
     * gitt resept, Skrive ut statistikk, Skrive data til fil og Avslutte
     * programmet.
     */
    public void startKommando() {
        boolean fortsett = true;
        /*
         * While loekken gaar saa lenge vi ikke avslutter ved aa sette fortsett til
         * false (lenger ned i metoden). Utskriftene nedenfor gir bruker
         * valgmulighetene.
         */

        while (fortsett) {

            System.out.println("\n");
            System.out.println("Hva vil du gjore?");
            System.out.println("Skrive ut fullstendig oversikt (1)");
            System.out.println("Opprette og legge til nye elementer (2)");
            System.out.println("Bruke en gitt resept fra listen til en pasient (3)");
            System.out.println("Skrive ut forskjellige former for statistikk (4)");
            System.out.println("Skrive all data til fil (5)");
            System.out.println("Avslutt programmet (6)");

            String input = in.nextLine();
            System.out.println("\n");
            /**
             * Her avgjoer brukeren hvilken metode den vil ta i bruk basert paa aa skrive
             * inn et tall fra 1-6.
             */
            if (input.equals("1")) {
                skrivUt();
            }

            else if (input.equals("2")) {
                leggTil();
            }

            else if (input.equals("3")) {
                brukResept();
            }

            else if (input.equals("4")) {
                statistikk();
            }

            else if (input.equals("5")) {
                try {
                    skrivTilFil();
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (UnsupportedEncodingException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (input.equals("6")) {
                fortsett = false;
                System.out.println("Avslutter programmet");
                in.close();
            }

            else {
                System.out.println("Ugyldig input " + input + " er ikke en kommando i programmet.");
            }

        }

    }

    /**
     * Metoden skrivUt skriver ut en ryddig oversikt om alle elementene i
     * legesystemet, samt at Leger skriver ut i en ordnet rekkefoelge.
     */
    private void skrivUt() {
        System.out.println("Skriver ut all informasjon:\n");

        System.out.println("Skriver ut alle pasienter: \n");
        for (Pasient p : pasienter) {
            System.out.println(p.toString() + "\n");
        }

        System.out.println("\nSkriver ut alle legemidler: \n");
        for (Legemiddel l : legemidler) {
            System.out.println(l.toString());

        }

        System.out.println("\nSkriver ut alle leger: \n");
        /**
         * Bruker en sortertLenkeListe slik at vi sorterer Legene i en alfabetisk
         * rekkefoelge.
         */
        SortertLenkeliste<Lege> ordnetRekkefolge = new SortertLenkeliste<>();
        for (Lege l : leger) {
            ordnetRekkefolge.leggTil(l);
        }

        for (Lege l : ordnetRekkefolge) {
            System.out.print(l.toString() + "\n");
        }

        System.out.println("\nSkriver ut alle resepter: \n");
        for (Resept l : resepter) {
            System.out.println(l.toString());
        }

    }

    /**
     * Metoden leggTil er en funksjonalitet for aa la brukeren legge til en lege,
     * pasient, resept eller legemiddel.
     */
    private void leggTil() {
        System.out.println("Legg til lege (1), pasient(2), resept (3) eller legemiddel (4).");

        String input = in.nextLine();

        /**
         * Hvis brukeren velger lege er den noedt til aa oppgi om det skal opprettes et
         * lege eller et spesialist objekt. Utifra det maa det oppgis noedvendig
         * informasjon som trengs for aa opprette et slikt objekt. Deretter legges det
         * objektet til i leger lenkelisten.
         */
        if (input.equals("1")) {
            System.out.println("Instans av lege (1) eller Spesialist (2)");
            String type = in.nextLine();
            if (type.equals("1")) {
                System.out.println("Skriv lege navn: ");
                String navn = in.nextLine();
                Lege lege = new Lege(navn);
                leger.leggTil(lege);
            } else if (type.equals("2")) {
                System.out.println("Skriv spesialisten sin navn: ");
                String navn = in.nextLine();
                System.out.println("Skriv spesialisten sin kontrollid: ");
                String kontrollId = in.nextLine();
                Spesialist spesialist = new Spesialist(navn, kontrollId);
                leger.leggTil(spesialist);
            } else {
                System.out.println("Ugyldig input " + type + " er ikke en kommando i programmet. Linje 349");
            }
        }

        /**
         * Hvis brukeren velger pasient maa det oppgis noedvendig informasjon som trengs
         * for aa opprette et slikt objekt. Deretter legges det objektet til i pasienter
         * lenkelisten.
         */
        else if (input.equals("2")) {
            System.out.println("Skriv pasient navn: ");
            String navn = in.nextLine();
            System.out.println("Skriv inn foedselsnummer: ");
            String fodselsNummer = in.nextLine();
            pasienter.leggTil(new Pasient(navn, fodselsNummer));
        }

        /**
         * Hvis brukeren velger resept er den noedt til aa oppgi om det skal opprettes
         * et Hvit-resept, Blaa-resept, P-resept eller et Militaer-resept objekt. Utifra
         * det maa det oppgis noedvendig informasjon som trengs for aa opprette et slikt
         * objekt. Deretter legges det objektet til i resepter lenkelisten.
         */
        else if (input.equals("3")) {
            System.out.println("Velg resept type, Hvit-resept(1), Blaa-resept(2), P-resept(3), Militaer-resept(4):  ");
            String type = in.nextLine();
            if (leger.erTom()) {
                System.out.println("Vi har ingen legeObjekter forelopig");
                return;
            }
            if (type.equals("1")) {

                /**
                 * Den er nodt til aa gaa igjennom legemidler lenkelisten for aa finne det
                 * rikitge legemidlet.
                 */
                System.out.println("Skriv legemiddel nummer: ");
                int legemiddelNr = Integer.parseInt(in.nextLine());
                Legemiddel legemiddel = null;
                for (Legemiddel l : legemidler) {
                    if (legemiddelNr == l.hentId()) {
                        legemiddel = l;
                    }
                }
                if (legemiddel == null) {
                    System.out.println("Ikke noe legemiddel med denne ID-en");
                    return;
                }

                /**
                 * Den er nodt til aa gaa igjennom pasienter lenkelisten for aa finne den
                 * riktige pasienten
                 */
                System.out.println("Skriv pasient ID: ");
                int id = Integer.parseInt(in.nextLine());
                Pasient pasient = null;
                for (Pasient p : pasienter) {
                    if (id == p.hentID()) {
                        pasient = p;
                    }
                }
                if (pasient == null) {
                    System.out.println("Ingen pasient med denne ID-en");
                    return;
                }

                /**
                 * Den er nodt til aa gaa igjennom leger lenkelisten for aa finne den riktige
                 * legen
                 */
                System.out.println("Skriv legens navn: ");
                String legeNavn = in.nextLine();
                Lege lege = null;
                for (Lege l : leger) {
                    if (legeNavn.equals(l.hentNavn())) {
                        lege = l;
                    }
                }
                if (lege == null) {
                    System.out.println("Ingen lege med dette navnet");
                    return;
                }

                /**
                 * Den er nodt til aa oppgi antal reit
                 */
                System.out.println("Skriv inn antall reit: ");
                int reit = 0;
                try {
                    reit = Integer.parseInt(in.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Dette var ikke ett tall, yikes!");
                }

                /**
                 * Try-catch for aa sjekke om den klarer aa legge til
                 */
                try {
                    resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                } catch (UlovligUtskrift e) {
                    System.out.print(e.getMessage());
                }
            }
            if (type.equals("2")) {
                /**
                 * Den er nodt til aa gaa igjennom legemidler lenkelisten for aa finne det
                 * rikitge legemidlet.
                 */
                System.out.println("Skriv legemiddel nummer: ");
                int legemiddelNr = Integer.parseInt(in.nextLine());
                Legemiddel legemiddel = null;
                for (Legemiddel l : legemidler) {
                    if (legemiddelNr == l.hentId()) {
                        legemiddel = l;
                    }
                }
                if (legemiddel == null) {
                    System.out.println("Ikke noe legemiddel med denne ID-en");
                    return;
                }

                /**
                 * Den er nodt til aa gaa igjennom pasienter lenkelisten for aa finne den
                 * riktige pasienten
                 */
                System.out.println("Skriv pasient ID: ");
                int id = Integer.parseInt(in.nextLine());
                Pasient pasient = null;
                for (Pasient p : pasienter) {
                    if (id == p.hentID()) {
                        pasient = p;
                    }
                }
                if (pasient == null) {
                    System.out.println("Ingen pasient med denne ID-en");
                    return;
                }

                /**
                 * Den er nodt til aa gaa igjennom leger lenkelisten for aa finne den riktige
                 * legen
                 */
                System.out.println("Skriv legens navn: ");
                String legeNavn = in.nextLine();
                Lege lege = null;
                for (Lege l : leger) {
                    if (legeNavn.equals(l.hentNavn())) {
                        lege = l;
                    }
                }
                if (lege == null) {
                    System.out.println("Ingen lege med dette navnet");
                    return;
                }

                /**
                 * Den er noedt til aa oppgi antal reit
                 */
                System.out.println("Skriv inn antall reit: ");
                int reit = 0;
                try {
                    reit = Integer.parseInt(in.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Dette var ikke ett tall, yikes!");
                }

                /**
                 * Try-catch for aa sjekke om den klarer aa legge til
                 */
                try {
                    resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                } catch (UlovligUtskrift e) {
                    System.out.print(e.getMessage());
                }
            }
            if (type.equals("3")) {
                /**
                 * Den er noedt til aa gaa igjennom legemidler lenkelisten for aa finne det
                 * rikitge legemidlet.
                 */
                System.out.println("Skriv legemiddel nummer: ");

                int legemiddelNr = Integer.parseInt(in.nextLine());
                Legemiddel legemiddel = null;
                for (Legemiddel l : legemidler) {
                    if (legemiddelNr == l.hentId()) {
                        legemiddel = l;
                    }
                }
                if (legemiddel == null) {
                    System.out.println("Ikke noe legemiddel med denne ID-en");
                    return;
                }

                /**
                 * Den er noedt til aa gaa igjennom pasienter lenkelisten for aa finne den
                 * riktige pasienten
                 */
                System.out.println("Skriv pasient ID: ");
                int id = Integer.parseInt(in.nextLine());
                Pasient pasient = null;
                for (Pasient p : pasienter) {
                    if (id == p.hentID()) {
                        pasient = p;
                    }
                }
                if (pasient == null) {
                    System.out.println("Ingen pasient med denne ID-en");
                    return;
                }

                /**
                 * Den er noedt til aa gaa igjennom leger lenkelisten for aa finne den riktige
                 * legen
                 */
                System.out.println("Skriv legens navn: ");
                String legeNavn = in.nextLine();
                Lege lege = null;
                for (Lege l : leger) {
                    if (legeNavn.equals(l.hentNavn())) {
                        lege = l;
                    }
                }
                if (lege == null) {
                    System.out.println("Ingen lege med dette navnet");
                    return;
                }

                /**
                 * Try-catch for aa sjekke om den klarer aa legge til
                 */
                try {
                    resepter.leggTil(lege.skrivPResept(legemiddel, pasient));
                } catch (UlovligUtskrift e) {
                    System.out.print(e.getMessage());
                }

            }

            if (type.equals("4")) {
                /**
                 * Den er noedt til aa gaa igjennom legemidler lenkelisten for aa finne det
                 * rikitge legemidlet.
                 */
                System.out.println("Skriv legemiddel nummer: ");
                int legemiddelNr = Integer.parseInt(in.nextLine());
                Legemiddel legemiddel = null;
                for (Legemiddel l : legemidler) {
                    if (legemiddelNr == l.hentId()) {
                        legemiddel = l;
                    }
                }
                if (legemiddel == null) {
                    System.out.println("Ikke noe legemiddel med denne ID-en");
                    return;
                }

                /**
                 * Den er noedt til aa gaa igjennom pasienter lenkelisten for aa finne den
                 * riktige pasienten
                 */
                System.out.println("Skriv pasient ID: ");
                int id = Integer.parseInt(in.nextLine());
                Pasient pasient = null;
                for (Pasient p : pasienter) {
                    if (id == p.hentID()) {
                        pasient = p;
                    }
                }
                if (pasient == null) {
                    System.out.println("Ingen pasient med denne ID-en");
                    return;
                }

                /**
                 * Den er noedt til aa gaa igjennom leger lenkelisten for aa finne den riktige
                 * legen
                 */
                System.out.println("Skriv legens navn: ");
                String legeNavn = in.nextLine();
                Lege lege = null;
                for (Lege l : leger) {
                    if (legeNavn.equals(l.hentNavn())) {
                        lege = l;
                    }
                }
                if (lege == null) {
                    System.out.println("Ingen lege med dette navnet");
                    return;
                }

                /**
                 * Den er noedt til aa oppgi antal reit
                 */
                System.out.println("Skriv inn antall reit: ");
                int reit = 0;
                try {
                    reit = Integer.parseInt(in.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Dette var ikke ett tall, yikes!");
                }

                /**
                 * Try-catch for aa sjekke om den klarer aa legge til
                 */
                try {
                    resepter.leggTil(lege.skrivMilitaerResept(legemiddel, pasient, reit));
                } catch (UlovligUtskrift e) {
                    System.out.print(e.getMessage());
                }

            }
        }

        /**
         * Hvis brukeren velger legemiddelen er den noedt til aa oppgi om det skal
         * opprettes et Vanlig-legemiddel, Vanedannende-legemiddel eller et
         * Narkotisk-legemiddel. Utifra det maa det oppgis noedvendig informasjon som
         * trengs for aa opprette et slikt objekt. Deretter legges det objektet til i
         * resepter lenkelisten.zs
         */
        else if (input.equals("4")) {
            System.out.println("Velg legemiddel type, Vanlig(1), Vanedannende(2), Narkotisk(3): ");
            String type = in.nextLine();

            if (type.equals("1")) {
                System.out.println("Skriv legemiddel navn: ");
                String navn = in.nextLine();

                System.out.println("Skriv legemiddel pris: ");
                int pris = Integer.parseInt(in.nextLine());

                System.out.println("Skriv legemiddelets virkestoff: ");
                double virkestoff = Double.parseDouble(in.nextLine());

                legemidler.leggTil(new Vanlig(navn, pris, virkestoff));

            } else if (type.equals("2")) {
                System.out.println("Skriv legemiddel navn: ");
                String navn = in.nextLine();

                System.out.println("Skriv legemiddel pris: ");
                int pris = Integer.parseInt(in.nextLine());

                System.out.println("Skriv legemiddelets virkestoff: ");
                double virkestoff = Double.parseDouble(in.nextLine());

                System.out.println("Skriv legemiddelets styrke: ");
                int styrke = Integer.parseInt(in.nextLine());

                legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));

            } else if (type.equals("3")) {
                System.out.println("Skriv legemiddel navn: ");
                String navn = in.nextLine();

                System.out.println("Skriv legemiddel pris: ");
                int pris = Integer.parseInt(in.nextLine());

                System.out.println("Skriv legemiddelets virkestoff: ");
                double virkestoff = Double.parseDouble(in.nextLine());

                System.out.println("Skriv legemiddelets styrke: ");
                int styrke = Integer.parseInt(in.nextLine());

                legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
            } else {
                System.out.println("Ugyldig input " + input + " er ikke en kommando i programmet.");
            }

        } else {
            System.out.println("Ugyldig input " + input + " er ikke en kommando i programmet.");
        }
    }

    /**
     * Denne metoden soerger for at bruker skal faa mulighet til aa velge en resept
     * den vil se for en pasient. Brukeren vil faa en oversiktlig utskrift i
     * terminalen som viser stegene som skal tas for aa faa brukt en resept.
     */
    private void brukResept() {
        System.out.println("Hvilken pasient vil du se resepter for?");

        for (Pasient p : pasienter) {
            System.out.println(p.hentID() + ":" + p.toString() + "\n");
        }

        int pasientId = Integer.parseInt(in.nextLine());
        Pasient pasient = null;

        for (Pasient p : pasienter) {
            if (pasientId == p.hentID()) {
                pasient = p;
            }
        }
        System.out.println("Valgt pasient: " + pasient.toString() + "\n");
        System.out.println("Hvilken resept vil du bruke?");

        for (Resept r : resepter) {
            if (pasientId == r.hentPasientId()) {
                System.out
                        .println(r.hentId() + ": " + r.hentLegemiddel().hentNavn() + " (" + r.hentReit() + ")" + "\n");
            }
        }
        int reseptId = Integer.parseInt(in.nextLine());
        Resept resept = null;

        for (Resept r : resepter) {
            if (r.hentId() == reseptId) {
                resept = r;
            }
        }

        // Maa ta hensyn til om det ikke finnes noen resepter aa ta i bruk.
        if (resept == null) {
            System.out.println("Ingen gyldig resept");
            return;
        }
        resept.bruk();

        System.out.println("Brukte resept paa " + resept.hentLegemiddel().hentNavn() + " Antall gjenvaerende reit "
                + resept.hentReit());

    }

    /**
     * Denne metoden skriver ut en statistikk over: Totalt antall utskrevne resepter
     * på vanedannende legemidler. Totalt antall utskrevne resepter på narkotiske
     * legemidler. Statistikk over mulig misbruk av narkotika.
     */
    private void statistikk() {
        int antallVanedannende = 0;
        int antallNarkotisk = 0;

        /**
         * Finner antall Vanedannende resepter det finnes i lenkelisten resepter
         */
        for (Resept r : resepter) {
            if (r.hentLegemiddel() instanceof Vanedannende) {
                antallVanedannende++;
            }
        }

        /**
         * Finner antall Narkotisk resepter det finnes i lenkelisten resepter
         */
        for (Resept r : resepter) {
            if (r.hentLegemiddel() instanceof Narkotisk) {
                antallNarkotisk++;
            }
        }

        System.out.println("Total antall vanedannende resepter: " + antallVanedannende + "\n"
                + "Total antall Narkotiske reseper: " + antallNarkotisk + "\n");

        SortertLenkeliste<Lege> ordnetRekkefolge = new SortertLenkeliste<>();
        for (Lege l : leger) {
            ordnetRekkefolge.leggTil(l);
        }

        /**
         * Skriver ut leger med narkotiske resepter, og antall resepter som er skrevet
         * ut i dens navn.
         */
        System.out.println("Leger med narkotiske resepter: \n");
        for (Lege l : ordnetRekkefolge) {
            int antall = 0;
            Lenkeliste<Resept> resepter = l.hentUtskrevedeResepter();
            for (Resept r : resepter) {
                if (r.hentLegemiddel() instanceof Narkotisk) {
                    antall++;
                }
            }
            if (antall > 0) {
                System.out.println(l.toString() + "Antall Narkotiske resepter:  " + antall + "\n");
            }
        }

        /**
         * Skriver ut pasienter med narkotiske resepter, og antall resepter som er
         * skrevet ut i dens navn.
         */
        System.out.println("Pasienter med narkotiske resepter: \n");
        for (Pasient p : pasienter) {
            int antall = 0;
            for (Resept r : resepter) {
                if (r.hentLegemiddel() instanceof Narkotisk) {
                    int pasientId = r.hentId();
                    if (pasientId == p.hentID()) {
                        antall++;
                    }
                }
            }
            if (antall > 0) {
                System.out.println(p.toString() + " Antall Narkotiske resepter:  " + antall + "\n");
            }
        }
    }

    /**
     * Denne metoden sørger for at brukeren får skrevet ut alle elemenentene i det
     * nåværende systemet til fil. Formatert på samme måte som innfil-eksempelet fra
     * forrige deloppgave.
     */
    private void skrivTilFil() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("UtskrevetFil.txt", "UTF-8");

        /**
         * Skriver inn pasienter og informasjonen som trengs til filen.
         */
        writer.println("# Pasienter (navn, fnr)");
        for (Pasient p : pasienter) {
            writer.println(p.hentNavn() + "," + p.hentFodeNr());
        }

        /**
         * Skriver inn legemidler og informasjonen som trengs til filen.
         */
        writer.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
        for (Legemiddel l : legemidler) {
            String strenge = "";

            if (l instanceof Vanlig) {
                strenge += l.hentNavn() + "," + "vanlig" + "," + l.hentPris() + "," + l.hentVirkestoff();
            }
            if (l instanceof Vanedannende) {
                Vanedannende v = (Vanedannende) l;
                strenge += l.hentNavn() + "," + "vanedannende" + "," + l.hentPris() + "," + l.hentVirkestoff() + ","
                        + v.hentVanedannendeStyrke();
            }
            if (l instanceof Narkotisk) {
                Narkotisk n = (Narkotisk) l;
                strenge += l.hentNavn() + "," + "narkotisk" + "," + l.hentPris() + "," + l.hentVirkestoff() + ","
                        + n.hentNarkotiskStyrke();
            }

            writer.println(strenge);

        }

        /**
         * Skriver inn leger og informasjonen som trengs til filen.
         */
        writer.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
        for (Lege l : leger) {
            if (l instanceof Lege) {
                writer.println(l.hentNavn() + "," + "0");
            }
            if (l instanceof Spesialist) {
                Spesialist s = (Spesialist) l;
                writer.println(s.hentNavn() + "," + s.hentKontrollId());
            }
        }

        /**
         * Skriver inn resepter og informasjonen som trengs til filen.
         */
        writer.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
        for (Resept r : resepter) {
            if (r instanceof HvitResept) {
                HvitResept h = (HvitResept) r;
                writer.println(h.hentLegemiddel().hentId() + "," + h.hentLege().hentNavn() + "," + h.hentPasientId()
                        + "," + "hvit" + "," + h.hentReit());
            }

            if (r instanceof BlaaResept) {
                BlaaResept b = (BlaaResept) r;
                writer.println(b.hentLegemiddel().hentId() + "," + b.hentLege().hentNavn() + "," + b.hentPasientId()
                        + "," + "blaa" + "," + b.hentReit());
            }
            if (r instanceof MilitaerResept) {
                MilitaerResept m = (MilitaerResept) r;
                writer.println(m.hentLegemiddel().hentId() + "," + m.hentLege().hentNavn() + "," + m.hentPasientId()
                        + "," + "militaer" + "," + m.hentReit());
            }
            if (r instanceof PResept) {
                PResept h = (PResept) r;
                writer.println(h.hentLegemiddel().hentId() + "," + h.hentLege().hentNavn() + "," + h.hentPasientId()
                        + "," + "p");
            }
        }

        writer.close();
    }
}