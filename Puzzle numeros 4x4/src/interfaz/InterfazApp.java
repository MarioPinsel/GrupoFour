package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mundo.Movimiento;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author sg701-15
 */
public class InterfazApp extends JFrame {

    private PanelDatos pnlDatos;
    private Movimiento ctrl;
    private Timer timer;
    private JLabel timerLabel;

    public InterfazApp() {

        getContentPane().setLayout(null);

        //ctrl = new Movimiento(pnlDatos);
        pnlDatos = new PanelDatos();
        pnlDatos.setBounds(150, 50, 400, 400);
        getContentPane().add(pnlDatos);

        this.setTitle("Ejercicio 3.0");
        this.setSize(800, 800);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timerLabel = new JLabel("00:00:00");
        timerLabel.setBounds(10, 10, 100, 30);
        add(timerLabel);

        timer = new Timer(1000, new ActionListener() {
            int seconds = 0;
            int minutes = 0;
            int hours = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }
                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }
                timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }
        });

        timer.start();

    }

    public static void main(String[] args) {
        InterfazApp frmMain = new InterfazApp();
        frmMain.setVisible(true);
    }

}
