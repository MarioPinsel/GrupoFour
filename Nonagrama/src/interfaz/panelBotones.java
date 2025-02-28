package interfaz;

import controlador.Controlador;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Esteban
 */
public class panelBotones extends JPanel {

    private JButton[][] botones;
    private boolean[][] ganar;
    private Controlador ctrl;

    public panelBotones(Controlador controlador) {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new GridLayout(10, 10, 2, 2));
        ctrl = controlador;
        botones = new JButton[10][10];
        ganar = new boolean[10][10];
        botones();
    }

    public void botones() {
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                final int filaActual = fila;
                final int columnaActual = columna;

                botones[fila][columna] = new JButton();
                botones[fila][columna].setBackground(Color.WHITE);

                botones[fila][columna].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {                      
                        ganar[filaActual][columnaActual] = true;
                        JButton btn = (JButton) e.getSource();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (ctrl.revisarX(filaActual, columnaActual)) {
                                btn.setText("X");
                            } else {
                                btn.setBackground(Color.BLACK);
                            }
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (ctrl.revisar0(filaActual, columnaActual)) {
                                btn.setBackground(Color.BLACK);
                            } else {
                                btn.setText("X");
                            }

                        }
                        if (esMatrizCompletaTrue(ganar)) {
                            JOptionPane.showMessageDialog(null, "Has ganado!!!", "Buena", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });

                add(botones[fila][columna]);
            }
        }
    }

    public boolean esMatrizCompletaTrue(boolean[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (!matriz[i][j]) { 
                    return false;
                }
            }
        }
        return true; 
    }

}
// VERIFICAR LO SIGUIENTE
/*
 * int grosor = 2;
 * int top = (fila % 5 == 0) ? grosor : 1;
 * int left = (columna % 5 == 0) ? grosor : 1;
 * int bottom = ((fila + 1) % 5 == 0) ? grosor : 1;
 * int right = ((columna + 1) % 5 == 0) ? grosor : 1;
 * execute.setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
 * //HASTA ANTES
 */
