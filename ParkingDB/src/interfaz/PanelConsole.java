package interfaz;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class PanelConsole extends JPanel {
    private JTextArea consoleArea;

    public PanelConsole() {
        setBorder(BorderFactory.createTitledBorder("CONSOLE"));
        setLayout(new BorderLayout());

        consoleArea = new JTextArea(5, 30);
        consoleArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(consoleArea);
        add(scroll, BorderLayout.CENTER);
    }

    public void log(String message) {
        consoleArea.append(message + "\n");
    }
} 
