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
        if ((misionerosI > 0 && canibalesI > misionerosI)
                || (misionerosD > 0 && canibalesD > misionerosD)) {
            return true;
        }
        return false;
    }

    private void lado() {
        if (lado == 0) {
            lado = 1;
        } else {
            lado = 0;
        }
    }

    public void unCanibal() {
        if (verificar()) {
            return;
        } else {
            if (lado == 1) {
                if (canibalesI > 0) {

                    canibalesI--;
                    canibalesD++;
                    lado();
                }
            } else {
                if (canibalesD > 0) {

                    canibalesI++;
                    canibalesD--;
                    lado();
                }
            }

        }
    }

    public void dosMisioneros() {
        if (verificar()) {
            return;
        } else {

            if (lado == 1) {
                if (misionerosI > 1) {

                    misionerosI -= 2;
                    misionerosD += 2;
                    lado();
                }
            } else {
                if (misionerosD > 1) {

                    misionerosI += 2;
                    misionerosD -= 2;
                    lado();
                }
            }

        }
    }

    public void dosCanibales() {
        if (verificar()) {
            return;
        } else {

            if (lado == 1) {
                if (canibalesI > 1) {

                    canibalesI -= 2;
                    canibalesD += 2;
                    lado();
                }
            } else {
                if (canibalesD > 1) {

                    canibalesI += 2;
                    canibalesD -= 2;
                    lado();
                }
            }

        }
    }

    public void unCanibalUnMisionero() {
        if (verificar()) {
            return;
        } else {
            if (lado == 1) {
                if (canibalesI > 0 && misionerosI > 0) {

                    canibalesI--;
                    canibalesD++;
                    misionerosI--;
                    misionerosD++;
                    lado();
                }
            } else {
                if (canibalesD > 0 && misionerosD > 0) {

                    canibalesI++;
                    canibalesD--;
                    misionerosI++;
                    misionerosD--;
                    lado();
                }
            }

        }
    }

    public void unMisionero() {
        if (verificar()) {
            return;
        } else {
            if (lado == 1) {
                if (misionerosI > 0) {

                    misionerosI--;
                    misionerosD++;
                    lado();
                }
            } else {
                if (misionerosD > 0) {

                    misionerosI++;
                    misionerosD--;
                    lado();
                }
            }

        }
    }

    @Override
    public void action(int arg0) {
        switch (arg0) {
            case 1:
                unCanibal();
                break;
            case 2:
                dosMisioneros();
                break;
            case 3:
                dosCanibales();
                break;
            case 4:
                unCanibalUnMisionero();
                break;
            case 5:
                unMisionero();
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

    @Override
    public String state() {
        return misionerosI + " " + canibalesI + " " + lado + " " + misionerosD + " " + canibalesD;
    }

}
