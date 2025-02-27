package controlador;

import interfaz.panelBotones;
import interfaz.panelVidas;
import java.awt.Color;
import javax.swing.*;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import mundo.Verificar;

public class Controlador {

    private panelBotones panelBoton;
    private panelVidas panelVidas;
    private Verificar verificar;
    private final int columnas = 10;

    

    public List<Coordenada> obtenerPosicionesConX() {
        List<Coordenada> posiciones = new ArrayList<>();
        Component[] componentes = panelBoton.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JButton) {
                JButton boton = (JButton) componentes[i];
                if ("x".equals(boton.getText())) {
                    int fila = i / columnas;
                    int columna = i % columnas;
                    posiciones.add(new Coordenada(fila, columna));
                }
            }
        }
        return posiciones;
    }

    public List<Coordenada> obtenerPosicionesBlack() {
        List<Coordenada> posiciones = new ArrayList<>();
        Component[] componentes = panelBoton.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JButton) {
                JButton boton = (JButton) componentes[i];
                if (boton.getBackground().equals(Color.BLACK)) {
                    int fila = i / columnas;
                    int columna = i % columnas;
                    posiciones.add(new Coordenada(fila, columna));
                }
            }
        }
        return posiciones;
    }
    
    public void cambiarImagen(){
            panelVidas.perderVida(verificar.contador());
        }
    }

    public static class Coordenada{

        private int fila;
        private int columna;

        public Coordenada(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        public int getFila() {
            return fila;
        }

        public int getColumna() {
            return columna;
        }

        @Override
        public String toString() {
            return "(" + fila + ", " + columna + ")";
        }
    }

