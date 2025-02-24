
package jarras;

import gfutria.SearchStateSpaces;
import java.util.ArrayList;
import mundo.Jarras;

/**
 *
 * @author SG701-06
 */
public class InterfazApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Jarras jarra ;
        SearchStateSpaces sss;
        ArrayList lst;
        jarra = new Jarras(3,7);
        /*
        lst = new ArrayList();
        
        sss = new SearchStateSpaces("0 4" , jarras, 8) ;
        
        lst = sss.solve();
        int i = 0;
        while(i < lst.size()){
            System.out.println(lst.get(i));
            i++;
        }
*/
            
        
        jarra.llenarJarra6(); 
        System.out.println(jarra.getJarra6() + " " + jarra.getJarra8());
        jarra.vaciar8en6();
        System.out.println(jarra.getJarra6() + " " + jarra.getJarra8());
        jarra.vaciar8en6();
        System.out.println(jarra.getJarra6() + " " + jarra.getJarra8());
        jarra.llenarJarra6();
        System.out.println(jarra.getJarra6() + " " + jarra.getJarra8());
        jarra.vaciar8en6();
        System.out.println(jarra.getJarra6() + " " + jarra.getJarra8());
        
        
    }
    
}
