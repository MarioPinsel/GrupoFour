package interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

    public panelMensaje() {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new FlowLayout());
        mensaje();
        botoncito();
    }

    public void mensaje() {
        JTextArea msg = new JTextArea(2,20);
        //msg.setPreferredSize(new Dimension(300, 30));
        add(msg);

    }

    public void botoncito() {
       JButton enviar = new JButton("send");
       add(enviar);
    }

}
