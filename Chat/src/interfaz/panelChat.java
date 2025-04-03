
package interfaz;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Esteban
 */
public class panelChat extends JPanel {
    
    private JTextArea chat;
    
    public panelChat() {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new BorderLayout());    
        chat();
    }
    
    private void chat() {
        chat = new JTextArea();
        chat.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chat);
        add(chatScrollPane, BorderLayout.CENTER);
    }
    
    public void agregarMensaje(String mensaje) {
        chat.append(mensaje + "\n");
    }
}


