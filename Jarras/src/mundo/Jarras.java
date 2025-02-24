/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import gfutria.Logic;

/**
 *
 * @author SG701-06
 */
public class Jarras extends Logic {

    private int jarra6, jarra8;

    public Jarras(int jarra6, int jarra8) {
        this.jarra6 = jarra6;
        this.jarra8 = jarra8;
    }

    public Jarras() {
    }
    

    public int llenarJarra6() {
        if (jarra6 < 6) {
            jarra6 = 6;
        }

        return jarra6;
    }

    public int llenarJarra8() {
        if (jarra8 < 8) {
            jarra8 = 8;
        }
        return jarra8;
    }

    public int descargar6() {
        if (jarra6 > 0) {
            jarra6 = 0;
        }
        return jarra6;
    }

    public int descargar8() {
        if (jarra8 > 0) {
            jarra8 = 0;
        }
        return jarra8;
    }

    public int llenar6en8() {
        int cantidad = 0;
        if (jarra8 > 0 && jarra6 < 6) {
            cantidad = 6 - jarra6;
            jarra6 += cantidad;
            jarra8 -= cantidad;
        }
        return jarra6;
    }

    public int llenar8en6() {
        int cantidad = 0;
        if (jarra6 > 0 && jarra8 < 8) {
            cantidad = 6 - jarra6;
            jarra8 += cantidad;
            jarra6 -= cantidad;
        }
        return jarra8;
    }
    public int vaciar6en8() {
        int cantidad = 0;
        if (jarra8 < 8 && jarra6 < 6) {
            cantidad = 8 - jarra8;
            jarra8 += cantidad;
            jarra6 -= cantidad;
        }
        return jarra6;
    }

    public int vaciar8en6() {
        int cantidad = 0;
        if (jarra6 < 6 && jarra8 > 0) {
            cantidad = 6 - jarra6;
            jarra8 -= cantidad;
            jarra6 += cantidad;
        }
        return jarra8;
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
        Jarras obj = (Jarras)logic;
        Jarras clone = new Jarras();
        
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
                llenarJarra6();
                break;
            case 2:
                llenarJarra8();
                break;
            case 3:
                descargar6();
                break;
            case 4:
                descargar8();
                break;
            case 5:    
                vaciar6en8();
                break;
            case 6:
                vaciar8en6();
                break;
            case 7:
                llenar6en8();
                break;
            case 8:
                llenar8en6();
                break;
        }

    }

}
