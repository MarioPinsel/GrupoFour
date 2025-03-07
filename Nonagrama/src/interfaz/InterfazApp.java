package interfaz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import controlador.Controlador;

/**
 *
 * @author Esteban
 */
public class InterfazApp extends JFrame {

    private int selectedPuzzle = 0;
    panelBotones pnlB;
    panelVidas pnlV;
    panelPistas pnlP;
    private Controlador ctrl;

    public InterfazApp(Controlador controlador) {
        ctrl = new Controlador();
        this.setLayout(null);

        pnlB = new panelBotones(controlador);
        pnlV = new panelVidas();
        pnlP = new panelPistas(controlador);

        controlador.setInstance(pnlV);
        this.setTitle("Nonograma");
        this.setSize(650, 650);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlB.setBounds(100, 150, 450, 450);
        add(pnlB);

        pnlV.setBounds(265, 10, 120, 40);
        pnlV.setBorder(null);

        add(pnlV);
        pnlP.setBounds(0, 50, 600, 600);
        pnlP.setBorder(null);
        add(pnlP);

        elector();
    }

    public void elector() {
        JButton menuButton = new JButton("Dibujos");
        JPopupMenu menu = new JPopupMenu();

        JMenuItem openPuzzle1 = new JMenuItem("Puzzle 1");
        JMenuItem openPuzzle2 = new JMenuItem("Puzzle 2");

        menu.add(openPuzzle1);
        menu.add(openPuzzle2);
        menu.addSeparator();

        openPuzzle1.addActionListener(e -> {
            selectedPuzzle = 1;
            System.out.println("Seleccionaste: " + selectedPuzzle);
            ctrl.selector(selectedPuzzle);
        });

        openPuzzle2.addActionListener(e -> {
            selectedPuzzle = 2;
            System.out.println("Seleccionaste: " + selectedPuzzle);
            ctrl.selector(selectedPuzzle);
        });

        menuButton.addActionListener(e -> menu.show(menuButton, 0, menuButton.getHeight()));

        menuButton.setBounds(10, 10, 100, 50);
        add(menuButton);
    }

    public static void main(String[] args) {

        InterfazApp frmMain = new InterfazApp(new Controlador());

        frmMain.setVisible(true);
    }

}
