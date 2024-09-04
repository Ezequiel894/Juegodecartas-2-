import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
    private List<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};

        for (String palo : palos) {
            for (int i = 1; i <= 13; i++) {
                cartas.add(new Carta(palo, i));
            }
        }
        barajar();
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public List<Carta> repartir(int numeroDeCartas) {
        List<Carta> mano = new ArrayList<>();
        for (int i = 0; i < numeroDeCartas; i++) {
            if (!cartas.isEmpty()) {
                mano.add(cartas.remove(cartas.size() - 1));
            }
        }
        return mano;
    }
}
