package interfaz;

import controlador.Controlador;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Esteban
 */
public class panelBotones extends JPanel {

    private JButton[][] botones;
    private boolean[][] ganar;
    private Controlador ctrl;
    private ImageIcon x;
    public panelBotones(Controlador controlador) {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new GridLayout(10, 10, 2, 2));
        ctrl = controlador;
        botones = new JButton[10][10];
        ganar = new boolean[10][10];
        botones();
    }

    public void botones() {
        x = new ImageIcon(getClass().getResource("/imagenes/X.png"));
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                final int filaActual = fila;
                final int columnaActual = columna;

                botones[fila][columna] = new JButton();
                botones[fila][columna].setBackground(Color.WHITE);
               
            int top = (fila % 5 == 0) ? 3 : 1;
                int left = (columna % 5 == 0) ? 3 : 1;
                int bottom = (fila % 5 == 4) ? 2 : 1;
                int right = (columna % 5 == 4) ? 2 : 1;

                botones[fila][columna].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

            botones[fila][columna].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (ganar[filaActual][columnaActual]) {
                        return;
                    }
                    ganar[filaActual][columnaActual] = true;
                    JButton btn = (JButton) e.getSource();

                    
                    if (perder()) {
                        JOptionPane.showMessageDialog(null, "Has perdido!!!", "Que mal", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (ctrl.revisarX(filaActual, columnaActual)) {
                            btn.setIcon(x);
                        } else {
                            btn.setBackground(Color.BLACK);
                        }
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        if (ctrl.revisar0(filaActual, columnaActual)) {
                            btn.setBackground(Color.BLACK);
                        } else {
                            btn.setIcon(x);
                        }

                    }
                    if (perder()) {
                        JOptionPane.showMessageDialog(null, "Has perdido!!!", "Que mal", JOptionPane.INFORMATION_MESSAGE);
                        return;
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

private boolean perder() {
        if (ctrl.getVerificar().getVidasRestantes() == 0) {
            return true;
        }
        return false;
    }

    private boolean esMatrizCompletaTrue(boolean[][] matriz) {
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
