package jarras;

import gfutria.SearchStateSpaces;
import java.util.ArrayList;
import mundo.Jarra;

public class InterfazApp {

    public static void main(String[] args) {
        Jarra jarra;
        SearchStateSpaces sss;
        ArrayList lst;
        jarra = new Jarra(0, 0);

        lst = new ArrayList();

        sss = new SearchStateSpaces("0 4", jarra, 8);

        lst = sss.solve();
        int i = 0;
        System.out.println("Pasos: " + sss.steps());
        while (i < lst.size()) {
            System.out.println(lst.get(i));
            i++;
        }

    }

}
