package interfaz;

import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class panelPistas extends JPanel {

    public panelPistas() {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(null); 
        subPanelNorte();
        subPanelOeste();
    }

    public void subPanelNorte() {
        JPanel panelNorte = new JPanel();        
        panelNorte.setLayout(new GridLayout(1,10));

        for (int i = 0; i < 10; i++) {
            JTextArea pistaV = new JTextArea("" + i + "\n" + i);
            pistaV.setEditable(false);
            pistaV.setOpaque(false);
            panelNorte.add(pistaV);
            
        }

        panelNorte.setBounds(110, 50, 290, 50); 
        add(panelNorte); 
    }
    
    public void subPanelOeste() {
        JPanel panelOeste = new JPanel();        
        panelOeste.setLayout(new GridLayout(10,1));

        for (int i = 0; i < 10; i++) {
            JTextArea pistaV = new JTextArea("" + i + " " + i);
            pistaV.setEditable(false);
            pistaV.setOpaque(false);
            panelOeste.add(pistaV);
            
        }

        panelOeste.setBounds(50, 90, 60, 290); 
        add(panelOeste); 
    }
    
    
    
    
}
