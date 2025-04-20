package gfutria;

import java.awt.Component;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Memory {
    private int row;
    private int col;
    private String key;
    private String losingKey;
    private Neurons neuron;

    public Memory() {
        this.row = 0;
        this.col = 0;
        read(); // Carga el archivo de memoria si existe
    }

    public boolean remember(int[][] board) {
        generateKey(board);
        return true;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void gameOver() {
        neuron.save(losingKey); // Guarda la jugada perdedora
        save(); // Persiste la información
    }

    public void clear() {
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de borrar la memoria del parcero?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            resetMemory();
        }
    }

    public void resetMemory() {
        neuron = new Neurons();
        save();
        JOptionPane.showMessageDialog(null, "Memoria borrada exitosamente.", "Memory", JOptionPane.INFORMATION_MESSAGE);
    }

    public ArrayList<String> brain() {
        return neuron.getNeurons();
    }

    private void generateKey(int[][] board) {
        while (true) {
            // Buscar una posición vacía aleatoria
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);

            if (board[row][col] != 0) continue;

            // Simula la jugada
            board[row][col] = 2;

            // Genera la clave del tablero
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                builder.append(board[i][0]).append(board[i][1]).append(board[i][2]);
                if (i < 2) builder.append(":");
            }

            key = builder.toString();
            losingKey = key; // Se considera jugada perdedora

            // Si es una jugada nueva (ni ella ni sus rotaciones están en memoria)
            if (!neuron.verify(key) && !isRotationMatch(key)) {
                return;
            }

            // Si ya existía, deshacer jugada y probar otra
            board[row][col] = 0;
        }
    }

    // Revisa si alguna rotación de la clave ya está en memoria
    private boolean isRotationMatch(String originalKey) {
        String rotated = originalKey;
        for (int i = 0; i < 3; i++) {
            rotated = rotate90(rotated);
            if (neuron.verify(rotated)) return true;
        }
        return false;
    }

    // Gira el tablero 90 grados (representado como String) en sentido horario
    private String rotate90(String key) {
        String[] rows = key.split(":");
        int[][] matrix = new int[3][3];

        // Convertir String a matriz
        for (int i = 0; i < 3; i++) {
            matrix[i][0] = rows[i].charAt(0) - '0';
            matrix[i][1] = rows[i].charAt(1) - '0';
            matrix[i][2] = rows[i].charAt(2) - '0';
        }

        // Rotar 90°
        int[][] rotated = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                rotated[j][2 - i] = matrix[i][j];

        // Volver a generar la clave
        StringBuilder newKey = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            newKey.append(rotated[i][0]).append(rotated[i][1]).append(rotated[i][2]);
            if (i < 2) newKey.append(":");
        }

        return newKey.toString();
    }

    // Guarda el objeto `neuron` en archivo
    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/triky.dat"))) {
            out.writeObject(neuron);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error guardando la memoria.", "Memory", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carga el archivo de memoria si existe, o lo inicializa
    private void read() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/triky.dat"))) {
            neuron = (Neurons) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo. Se creará uno nuevo.", "Memory", JOptionPane.WARNING_MESSAGE);
            neuron = new Neurons();
            save();
        }
    }
}
