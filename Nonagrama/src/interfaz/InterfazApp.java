package interfaz;

import javax.swing.JFrame;

import controlador.Controlador;

public class InterfazApp extends JFrame {

    private panelBotones pnlBotones;
    private panelVidas pnlVidas;
    private panelPistas pnlPistas;

    public InterfazApp(Controlador controlador) {

        this.setLayout(null);

        pnlBotones = new panelBotones(controlador);
        pnlVidas = new panelVidas();
        pnlPistas = new panelPistas(controlador);

        controlador.setInstance(pnlVidas);
        this.setTitle("Nonograma");
        this.setSize(650, 650);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlBotones.setBounds(100, 150, 450, 450);
        add(pnlBotones);

        pnlVidas.setBounds(265, 10, 120, 40);
        pnlVidas.setBorder(null);

        add(pnlVidas);
        pnlPistas.setBounds(0, 50, 600, 600);
        pnlPistas.setBorder(null);
        add(pnlPistas);

    }

    public static void main(String[] args) {

        InterfazApp frmMain = new InterfazApp(new Controlador());

        frmMain.setVisible(true);
    }

}
