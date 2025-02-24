
package mundo;

/**
 *
 * @author POWER
 */
public class Movimientos {

    private int canibales; 
    private int misioneros; 
    private int lado; 

    public Movimientos() {
        this.canibales = 3;
        this.misioneros= 3;
        this.lado = 1;
    }

    private boolean verificar(){
        if(lado == 1){
            lado--;
            return true;
        }else{
            lado++;
            return false;
        } 
    }

    public String otroLado(){
        int otroCan= 3 - canibales;
        int otroMis= 3- misioneros;
        return otroCan + "" + otroMis;
    } 

    public void unCanibal(){
        if(verificar())
            canibales --;
            
        else
            canibales++;

    }

    public void dosMisioneros(){
        if(verificar())
            misioneros-= 2; 
        else
            misioneros+= 2;

    }

    public void dosCanibales(){
        if(verificar())
            canibales-= 2; 
        else
            canibales+= 2;

    }

    public void unCanibalUnMisionero(){
        if(verificar()){
            canibales --;
            misioneros --;
        }else{
            canibales++;
            misioneros ++;
        }
    }

    public void unMisionero(){
        if(verificar())
            misioneros --;

        else
            misioneros++;

    }
 public String mostrarResultado(){
     return misioneros +""+ canibales +"1"+" "+ otroLado()+ "0";
 }
 
}
