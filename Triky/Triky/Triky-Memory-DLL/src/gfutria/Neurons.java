package gfutria;

import java.io.Serializable;
import java.util.ArrayList;

public class Neurons implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<String> neurons;

    public Neurons() {
        this.neurons = new ArrayList<>();
    }

    // Verifica si ya se ha almacenado una jugada
    public boolean verify(String key) {
        return neurons.contains(key);
    }

    // Guarda una jugada si no se ha registrado antes
    public void save(String key) {
        if (!verify(key)) {
            neurons.add(key);
        }
    }

    // Devuelve la lista de jugadas registradas
    public ArrayList<String> getNeurons() {
        return neurons;
    }
}

