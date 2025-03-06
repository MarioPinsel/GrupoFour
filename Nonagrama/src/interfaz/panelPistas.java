package interfaz;

import controlador.Controlador;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class panelPistas extends JPanel {

    private Controlador ctrl;
    private String[] pistasNorte;
    private String[] pistasOeste;

    public panelPistas(Controlador controlador) {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(null);
        ctrl = controlador;
        cargarPistas(ctrl.getVerificar().getPistasColumna(),ctrl.getVerificar().getPistasFila());
        subPanelNorte();
        subPanelOeste();
    }

        public void subPanelNorte() {
            JTextArea pistaV;
            JPanel panelNorte = new JPanel();
            panelNorte.setLayout(new GridLayout(1, 10));

            for (int i = 0; i < 10; i++) {                
                pistaV = new JTextArea(pistasNorte[i]);                 
                pistaV.setEditable(false);
                pistaV.setOpaque(false);
                panelNorte.add(pistaV);

            }

            panelNorte.setBounds(120, 20, 450, 80);
            add(panelNorte);
        }

    public void subPanelOeste() {
        JTextArea pistaV;
        JPanel panelOeste = new JPanel();
        panelOeste.setLayout(new GridLayout(10, 1));

        for (int i = 0; i < 10; i++) {
            pistaV = new JTextArea(pistasOeste[i]);
            pistaV.setEditable(false);
            pistaV.setOpaque(false);
            panelOeste.add(pistaV);

        }

        panelOeste.setBounds(70, 110, 60, 450);
        add(panelOeste);
    }

    private void cargarPistas(String[] pistasN, String[] pistasO) {
        pistasNorte = new String[10];
        pistasOeste = new String[10];
        for (int i = 0; i < pistasNorte.length; i++) {
            pistasNorte[i] = pistasN[i];
        }
        for (int i = 0; i < pistasOeste.length; i++) {
            pistasOeste[i] = pistasO[i];
        }

    }
}
