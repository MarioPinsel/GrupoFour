package interfaz;

import controlador.Controlador;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Esteban
 */
public class panelMensaje extends JPanel {

    private Controlador ctrl;
    private JTextArea msg;
    private JButton enviar;

    public panelMensaje(Controlador controlador) {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new FlowLayout());
        ctrl = controlador;
        mensaje();
    }
    
    private void mensaje() {
        msg = new JTextArea(2, 20);
        add(msg);
        
        enviar = new JButton("send");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = msg.getText();
                ctrl.obtenerTodaInformacion(texto);
                msg.setText("");
            }
        });
        add(enviar);
    }

}
