package mundo;

import gfutria.Logic;

public class Jarra extends Logic {

    private int jarra6, jarra8;

    public Jarra(int jarra6, int jarra8) {
        this.jarra6 = jarra6;
        this.jarra8 = jarra8;
    }

    public Jarra() {

    }

    public void llenarJarra8() {
        if (jarra8 < 8) {
            jarra8 = 8;
        }
    }

    public void llenarJarra6() {
        if (jarra6 < 6) {
            jarra6 = 6;
        }
    }

    public void vaciarJarra8() {
        if (jarra8 > 0) {
            jarra8 = 0;
        }
    }

    public void vaciarJarra6() {
        if (jarra6 > 0) {
            jarra6 = 0;
        }
    }

    public void vaciar8En6() {
        int espacioDisponible = 6 - jarra6;
        if (jarra6 < 6 && jarra8 > 0) {
            int cantidadATransferir = Math.min(jarra8, espacioDisponible);
            jarra8 -= cantidadATransferir;
            jarra6 += cantidadATransferir;
        }
    }

    public void vaciar6En8() {
        int espacioDisponible = 8 - jarra8;
        if (jarra8 < 8 && jarra6 > 0) {
            int cantidadATransferir = Math.min(jarra6, espacioDisponible);
            jarra6 -= cantidadATransferir;
            jarra8 += cantidadATransferir;
        }
    }

    public void llenar8Con6() {
        if (jarra8 < 8 && jarra6 > 0 && (jarra8 + jarra6) >= 8) {
            jarra6 -= (8 - jarra8);
            jarra8 = 8;
        }
    }

    public void llenar6Con8() {
        if (jarra6 < 6 && jarra8 > 0 && (jarra8 + jarra6) >= 6) {
            jarra8 -= (6 - jarra6);
            jarra6 = 6;
        }
    }

    public int getJarra6() {
        return jarra6;
    }

    public int getJarra8() {
        return jarra8;
    }

    public void setJarra6(int jarra6) {
        this.jarra6 = jarra6;
    }

    public void setJarra8(int jarra8) {
        this.jarra8 = jarra8;
    }

    @Override
    public Logic cloneObject(Logic logic) {
        Jarra obj = (Jarra) logic;
        Jarra clone = new Jarra();

        clone.setJarra6(obj.getJarra6());
        clone.setJarra8(obj.getJarra8());

        return clone;
    }

    @Override
    public String state() {
        return jarra6 + " " + jarra8;
    }

    @Override
    public void action(int i) {
        switch (i) {
            case 1:
                llenarJarra8();
                break;
            case 2:
                llenarJarra6();
                break;
            case 3:
                vaciarJarra8();
                break;
            case 4:
                vaciarJarra6();
                break;
            case 5:
                vaciar8En6();
                break;
            case 6:
                vaciar6En8();
                break;
            case 7:
                llenar8Con6();
                break;
            case 8:
                llenar6Con8();
                break;
        }

    }

}
