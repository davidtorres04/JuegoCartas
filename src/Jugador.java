import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN_SUPERIOR = 10;
    private final int MARGEN_IZQUIERDO = 10;
    private final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }

    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MARGEN_IZQUIERDO + DISTANCIA * (TOTAL_CARTAS - 1);
        for (Carta carta : cartas) {
            carta.mostrar(pnl, posicion, MARGEN_SUPERIOR);
            posicion -= DISTANCIA;
        }
        pnl.repaint();
    }

    public String getGrupos() {

        int[] contadores = new int[NombreCarta.values().length];

        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        String resultado = "";

        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                resultado += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
            }
        }

        for (int i = 0; i < TOTAL_CARTAS; i++) {

            Carta base = cartas[i];
            int valorBase = base.getNombre().ordinal();

            boolean esInicio = true;

            for (int j = 0; j < TOTAL_CARTAS; j++) {
                if (cartas[j].getPinta() == base.getPinta()
                        && cartas[j].getNombre().ordinal() == valorBase - 1) {
                    esInicio = false;
                }
            }

            if (!esInicio)
                continue;

            int cantidad = 1;
            int actual = valorBase;

            boolean encontrado = true;

            while (encontrado) {
                encontrado = false;

                for (int j = 0; j < TOTAL_CARTAS; j++) {

                    if (cartas[j].getPinta() == base.getPinta()) {

                        int valorJ = cartas[j].getNombre().ordinal();

                        if (valorJ == actual + 1 || (actual == 12 && valorJ == 0)) {
                            cantidad++;
                            actual = valorJ;
                            encontrado = true;
                        }
                    }
                }
            }

            if (cantidad >= 2) {
                resultado += Grupo.values()[cantidad] + " de " + base.getPinta()
                        + " de " + NombreCarta.values()[valorBase]
                        + " a " + NombreCarta.values()[actual] + "\n";
            }
        }

        int puntaje = 0;

        for (int i = 0; i < TOTAL_CARTAS; i++) {

            Carta actual = cartas[i];

            int iguales = 0;
            for (int j = 0; j < TOTAL_CARTAS; j++) {
                if (cartas[j].getNombre() == actual.getNombre()) {
                    iguales++;
                }
            }

            boolean enEscalera = false;

            for (int j = 0; j < TOTAL_CARTAS; j++) {

                if (i != j) {

                    Carta c1 = cartas[i];
                    Carta c2 = cartas[j];

                    if (c1.getPinta() == c2.getPinta()) {

                        int n1 = c1.getNombre().ordinal();
                        int n2 = c2.getNombre().ordinal();

                        if (Math.abs(n1 - n2) == 1) {
                            enEscalera = true;
                        }
                    }
                }
            }

            if (iguales < 2 && !enEscalera) {

                NombreCarta n = actual.getNombre();

                if (n == NombreCarta.AS || n == NombreCarta.JACK
                        || n == NombreCarta.QUEEN || n == NombreCarta.KING) {

                    puntaje += 10;

                } else {
                    puntaje += n.ordinal() + 1;
                }
            }
        }

        resultado += "\nPuntos:\n" + puntaje;

        return resultado;
    }

}
