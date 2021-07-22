import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HvitRuteGUI extends JButton{
    private int rad;
    private int kolonne;
    private Labyrint labyrint;

    public HvitRuteGUI(int r, int k, Labyrint l){
        rad = r;
        kolonne = k;
        labyrint = l;
        initGUI(); //initialiserer instillinger for rute


    }

    // initialiserer instillinger for rute
    // setter utseende og event
    private void initGUI(){
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(10, 10));
        setOpaque(true);
        setBackground(Color.WHITE);
        addActionListener(new hvitRuteKlikket());
    }

    //kaller metode i labyrint som finner utvei fra rutes posisjon i rutenett
    //kaller paa metode i Oblig7 som viser disse paa GUI
    class hvitRuteKlikket implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<ArrayList<Tuppel>> utveier = labyrint.finnUtveiFra(kolonne, rad);
            Oblig7.Utvei(utveier);
            // gjoer om ruter til utvei liste til gule ved utmetode i oblig7
        }
    }

    public int hentKolonne() {
        return kolonne;
    }

    public int hentRad() {
        return rad;
    } 
}
