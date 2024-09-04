import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private List<Jugador> jugadores;
    private Baraja baraja;
    private int rondas;

    public Juego(int numeroDeJugadores, int rondas) {
        this.rondas = rondas;
        jugadores = new ArrayList<>();
        baraja = new Baraja();

        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= numeroDeJugadores; i++) {
            System.out.print("Introduce el nombre del jugador " + i + ": ");
            String nombre = scanner.nextLine();
            jugadores.add(new Jugador(nombre));
        }
    }

    public void jugar() {
        for (int ronda = 1; ronda <= rondas; ronda++) {
            System.out.println("Ronda " + ronda);
            baraja.barajar();
            repartirCartas();
            jugarRonda();
        }
        mostrarGanadorFinal();
    }

    private void repartirCartas() {
        for (Jugador jugador : jugadores) {
            List<Carta> mano = baraja.repartir(1); // Repartir una carta a cada jugador
            jugador.recibirCartas(mano);
            jugador.mostrarCartas();
        }
    }

    private void jugarRonda() {
        Carta cartaGanadora = null;
        List<Jugador> ganadores = new ArrayList<>();

        for (Jugador jugador : jugadores) {
            Carta cartaJugada = jugador.jugarCarta();
            if (cartaJugada == null) continue;

            // Mostrar qué carta ha jugado el jugador
            System.out.println(jugador.getNombre() + " juega: " + cartaJugada);

            if (cartaGanadora == null || cartaJugada.getValor() > cartaGanadora.getValor()) {
                cartaGanadora = cartaJugada;
                ganadores.clear();
                ganadores.add(jugador);
            } else if (cartaJugada.getValor() == cartaGanadora.getValor()) {
                ganadores.add(jugador);
            }
        }

        if (ganadores.size() == 1) {
            Jugador ganador = ganadores.get(0);
            ganador.agregarPunto();
            System.out.println(ganador.getNombre() + " gana esta ronda.");
        } else {
            System.out.println("Empate en esta ronda.");
        }
    }

    private void mostrarGanadorFinal() {
        Jugador ganador = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntos() > ganador.getPuntos()) {
                ganador = jugador;
            }
        }
        System.out.println("El ganador final es " + ganador.getNombre() + " con " + ganador.getPuntos() + " puntos.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el número de jugadores: ");
        int numeroDeJugadores = scanner.nextInt();
        System.out.print("Introduce el número de rondas: ");
        int rondas = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Juego juego = new Juego(numeroDeJugadores, rondas);
        juego.jugar();
    }
}
