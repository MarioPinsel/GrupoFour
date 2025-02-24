
package mundo;

import gfutria.Logic;

/**
 *
 * @author POWER
 */
public class Movimientos extends Logic {

    private int misionerosI;
    private int canibalesI;
    private int misionerosD;
    private int canibalesD;
    private int lado;

    public Movimientos() {

    }

    public Movimientos(int misionerosI, int canibalesI, int lado, int misionerosD, int canibalesD) {
        this.misionerosI = misionerosI;
        this.canibalesI = canibalesI;
        this.lado = lado;
        this.misionerosD = misionerosD;
        this.canibalesD = canibalesD;
    }

    public void setCanibalesD(int canibalesD) {
        this.canibalesD = canibalesD;
    }

    public void setCanibalesI(int canibalesI) {
        this.canibalesI = canibalesI;
    }

    public void setLado(int lado) {
        this.lado = lado;
    }

    public void setMisionerosD(int misionerosD) {
        this.misionerosD = misionerosD;
    }

    public void setMisionerosI(int misionerosI) {
        this.misionerosI = misionerosI;
    }

    public int getCanibalesD() {
        return canibalesD;
    }

    public int getCanibalesI() {
        return canibalesI;
    }

    public int getLado() {
        return lado;
    }

    public int getMisionerosD() {
        return misionerosD;
    }

    public int getMisionerosI() {
        return misionerosI;
    }

    private boolean verificar() {
        if (lado == 1) {
            lado--;
            return true;
        } else {
            lado++;
            return false;
        }
    }

    public String otroLado() {
        int otroCan = 3 - canibales;
        int otroMis = 3 - misioneros;
        return otroCan + "" + otroMis;
    }

    public void unCanibal() {
        if (verificar())
            canibales--;

        else
            canibales++;

    }

    public void dosMisioneros() {
        if (verificar())
            misioneros -= 2;
        else
            misioneros += 2;

    }

    public void dosCanibales() {
        if (verificar())
            canibales -= 2;
        else
            canibales += 2;

    }

    public void unCanibalUnMisionero() {
        if (verificar()) {
            canibales--;
            misioneros--;
        } else {
            canibales++;
            misioneros++;
        }
    }

    public void unMisionero() {
        if (verificar())
            misioneros--;

        else
            misioneros++;

    }

    public String mostrarResultado() {
        return misioneros + "" + canibales + "1" + " " + otroLado() + "0";
    }

    @Override
    public void action(int arg0) {
        // TODO: Poner los otros metodos
        switch (arg0) {
            case 1:
                unCanibal();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    @Override
    public Logic cloneObject(Logic arg0) {
        Movimientos obj = (Movimientos) arg0;
        Movimientos clone = new Movimientos();

        clone.setCanibalesD(obj.getCanibalesD());
        clone.setCanibalesI(obj.getCanibalesI());
        clone.setMisionerosD(obj.getMisionerosD());
        clone.setMisionerosI(obj.getMisionerosI());
        clone.setLado(obj.getLado());

        return clone;
    }

    // 3 3 0 0 0 --- 0 0 1 3 3
    @Override
    public String state() {
        return misionerosI + " " + canibalesI + " " + lado + " " + misionerosD + " " + canibalesD;
    }

}
