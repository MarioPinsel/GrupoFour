
package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import mundo.Rubik;

public class Panel3 extends JPanel {
    private Rubik rubik;
    private final Border border = BorderFactory.createLineBorder(Color.WHITE, 2);

    public Panel3(Rubik rubik) {
        this.rubik = rubik;
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new GridLayout(2, 2));
        actualizarColores(); 
    }

    public void actualizarColores() {
        removeAll(); 
        Color colorSubPanel1 = rubik.getRubik()[0][1][0].getDer();
        Color colorSubPanel2 = rubik.getRubik()[0][1][1].getDer();
        Color colorSubPanel3 = rubik.getRubik()[1][1][0].getDer();
        Color colorSubPanel4 = rubik.getRubik()[1][1][1].getDer();

       
        JPanel subPanel1 = new JPanel();
        subPanel1.setBackground(colorSubPanel1);
        subPanel1.setBorder(border);
        add(subPanel1);

        JPanel subPanel2 = new JPanel();
        subPanel2.setBackground(colorSubPanel2);
        subPanel2.setBorder(border);
        add(subPanel2);

        JPanel subPanel3 = new JPanel();
        subPanel3.setBackground(colorSubPanel3);
        subPanel3.setBorder(border);
        add(subPanel3);

        JPanel subPanel4 = new JPanel();
        subPanel4.setBackground(colorSubPanel4);
        subPanel4.setBorder(border);
        add(subPanel4);
        revalidate(); 
    }
}
