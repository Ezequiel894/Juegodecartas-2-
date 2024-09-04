import java.util.List;

public class Jugador {
    private String nombre;
    private List<Carta> cartas;
    private int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public Carta jugarCarta() {
        if (cartas == null || cartas.isEmpty()) return null;
        // Juega una carta aleatoria
        return cartas.remove((int) (Math.random() * cartas.size()));
    }

    public void agregarPunto() {
        puntos++;
    }

    public int getPuntos() {
        return puntos;
    }

    public void mostrarCartas() {
        System.out.println(nombre + " tiene las siguientes cartas:");
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }
}
