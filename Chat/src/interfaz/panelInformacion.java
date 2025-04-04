
package interfaz;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class panelInformacion extends JPanel {

    private JTextField direccion;
    private JTextField nick;

    public panelInformacion() {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new FlowLayout());
        informacion();
    }

    private void informacion() {
        JLabel ip = new JLabel("IP:");
        add(ip);
        direccion = new JTextField(10);
        add(direccion);

        JLabel user = new JLabel("Nick:");
        add(user);
        nick = new JTextField(10);
        add(nick);
    }

    public String getDireccion() {
        return direccion.getText();
    }

    public String getNick() {
        return nick.getText();
    }

}
