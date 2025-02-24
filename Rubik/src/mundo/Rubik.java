package mundo;

/**
 *
 * @author Esteban
 */
public class Rubik {
    private Cubo rubik[][][];
     
    public Rubik() {
        
        rubik = new Cubo [2][2][2];
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    rubik[i][j][k] = new Cubo( );
                }
            }
        }
    }
    
    public void Horizontal(int d) {
        Cubo vacio;
               
        vacio = rubik[d][0][0];
        rubik[d][0][0] = rubik[d][0][1];
        rubik[d][0][1] = rubik[d][1][1];
        rubik[d][1][1] = rubik[d][1][0];
        rubik[d][1][0] = vacio;
        
        rubik[d][0][0].Horizontal();
        rubik[d][1][0].Horizontal();
        rubik[d][1][1].Horizontal();
        rubik[d][0][1].Horizontal();
        
    }
    
    
    public void Vertical(int d) {
        Cubo vacio;
               
        vacio = rubik[0][d][0];
        rubik[0][d][0] = rubik[0][d][1];
        rubik[0][d][1] = rubik[1][d][1];
        rubik[1][d][1] = rubik[1][d][0];
        rubik[1][d][0] = vacio;
        
        rubik[0][d][0].Vertical();
        rubik[1][d][0].Vertical();
        rubik[1][d][1].Vertical();
        rubik[0][d][1].Vertical();
  
    }
    public void Transversal(int d){
        Cubo vacio;
               
        vacio = rubik[0][0][d];
        rubik[0][0][d] = rubik[1][0][d];
        rubik[1][0][d] = rubik[1][1][d];
        rubik[1][1][d] = rubik[0][1][d];
        rubik[0][1][d] = vacio;
        
        rubik[0][0][d].Transversal();
        rubik[1][0][d].Transversal();
        rubik[1][1][d].Transversal();
        rubik[0][1][d].Transversal();
    }

    public Cubo[][][] getRubik() {
        return rubik;
    }

    public void setRubik(Cubo[][][] rubik) {
        this.rubik = rubik;
    }
    
    
         
}
