import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Oblig7 {
    // arraylist med hvite GUI ruter(aapninger)
    private static ArrayList<HvitRuteGUI> hviteRuter = new ArrayList<>(); 
    // arraylist med ruter som er gjort gule(utveier)
    private static ArrayList<HvitRuteGUI> gjortGul = new ArrayList<>(); 
    private static JLabel antallUtveierTekst = new JLabel("Antall utveier: xx   ");
    //utveier fra gjeldende rute
    private static ArrayList<ArrayList<Tuppel>> gjeldendeUtveier;
    //tekst til gjeldende utvei index
    private static JLabel gjeldendeUtvei = new JLabel("Utvei: xx   ");

    public static void main(String[] args) {

        JFileChooser velger = new JFileChooser();
        int resultat = velger.showOpenDialog(null);
        File fil;

        if (resultat == JFileChooser.APPROVE_OPTION) {
            fil = velger.getSelectedFile();
        } else {
            System.out.println("Filfeil");
            return;
        }

        Labyrint l = null;
        try {
            l = new Labyrint(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", fil);
            System.exit(1);
        }
        //System.out.println(l.toString());

        JFrame vindu = new JFrame("Labyrint");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel wrapper = new JPanel();

        JPanel lab = new JPanel(); //rutenett
        lab.setLayout(new GridLayout(l.antallRader, l.antallKolonner));

        //itererer igjennom labyrint rutenett, og oppretter gui elementer
        //for rutenett elementene
        for (int rad = 0; rad<l.antallRader; rad++){
            for (int kolonne = 0; kolonne<l.antallKolonner; kolonne++){
                Rute rute = l.ruteArray[rad][kolonne];
                if(rute.tilTegn()=='#'){ //sort rute/vegg
                    JLabel sortRute = new JLabel();
                    sortRute.setBorder(BorderFactory.createLineBorder(Color.black));
                    sortRute.setPreferredSize(new Dimension(10, 10));
                    sortRute.setOpaque(true);
                    sortRute.setBackground(Color.BLACK);
                    lab.add(sortRute);
                }else if(rute.tilTegn()=='.'){ //hvit rute/aapning
                    //egen knapp klasse som holder paa posisjon
                    //og har eget onclick event som finner/viser utveier
                    HvitRuteGUI hvitRute = new HvitRuteGUI(rad, kolonne, l); 
                    lab.add(hvitRute);
                    hviteRuter.add(hvitRute);
                }else{
                    System.out.println("Rute feil!");
                }
            }
        }
        
        JPanel info = new JPanel();

        info.add(antallUtveierTekst); 
        info.add(gjeldendeUtvei);
        
        //knapp som henter og viser neste utvei og index i gjeldene utveier
        JButton nesteKnapp = new JButton("Neste"); 
        class NesteUtvei implements ActionListener {
            int i = 1; //index til utvei
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gjeldendeUtveier == null) return;

                if (!gjeldendeUtveier.isEmpty()){
                    tilbakestillUtveier(); //tilbakestiller gule ruter(utvei)

                    if (i >= gjeldendeUtveier.size()) {
                        i = 0;
                    }
                    visUtvei(gjeldendeUtveier.get(i)); //viser utvei i rutenett
                    gjeldendeUtvei.setText("Utvei " + (i+1));
                    i++;
                }
            }
        }
        nesteKnapp.addActionListener(new NesteUtvei());
        info.add(nesteKnapp);

        //knapp som henter og viser korteste utvei og index til utveien
        JButton minst = new JButton("MinsteRute");
        class MinsteUtvei implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (gjeldendeUtveier == null) return;

                int i = 0; //index til utvei
                if (!gjeldendeUtveier.isEmpty()) {
                    tilbakestillUtveier(); // tilbakestiller gule ruter(utvei)

                    // henter minste utvei fra gjeldende utveier
                    ArrayList<Tuppel> minsteUtvei = gjeldendeUtveier.get(0);
                    for (ArrayList<Tuppel> utvei: gjeldendeUtveier){
                        if (utvei.size()<minsteUtvei.size()){
                            minsteUtvei = utvei;
                            i = gjeldendeUtveier.indexOf(utvei);
                        }
                    }

                    gjeldendeUtvei.setText("Utvei " + (i + 1));
                    visUtvei(minsteUtvei); // viser utvei i rutenett
                }
            }
        }
        minst.addActionListener(new MinsteUtvei());
        info.add(minst);

        wrapper.add(lab);
        wrapper.add(info);
        vindu.add(wrapper);

        vindu.pack();
        vindu.setVisible(true);
    }

    //setter gjeldende utveier fra arraylist med utveier
    //viser forste utvei i liste, med index
    public static void Utvei(ArrayList<ArrayList<Tuppel>> utveier){
        //gjoer om ruter i utvei list til gule
        if (utveier.isEmpty()) return;
        tilbakestillUtveier(); // tilbakestiller gule ruter(utvei)

        gjeldendeUtveier = utveier;
        ArrayList<Tuppel> utvei = utveier.get(0);
        visUtvei(utvei);
        antallUtveierTekst.setText("Antall Utveier: " + utveier.size());
        gjeldendeUtvei.setText("Utvei " + 1);
    }

    //viser utvei paa rutenett med gule "bokser" fra sti/utvei
    //setter ruter gule og legger de til i liste over gule ruter
    public static void visUtvei(ArrayList<Tuppel> utvei){
        
        for (Tuppel rute: utvei){
            int kolonne = rute.hentKolonne();
            int rad = rute.hentRad();
            
            for (HvitRuteGUI labRute: hviteRuter){
                if(labRute.hentKolonne()== kolonne && labRute.hentRad() == rad){
                    labRute.setBackground(Color.YELLOW);
                    gjortGul.add(labRute);
                }
            }

        }
    }

    // tilbakestiller gule ruter(utvei)
    private static void tilbakestillUtveier(){
        int antallGanger = gjortGul.size();
        for (int j = 0; j<antallGanger; j++){
            gjortGul.remove(0).setBackground(Color.WHITE);

        }
    }
    
}