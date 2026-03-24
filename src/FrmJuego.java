import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuego extends JFrame {

    private JPanel pnlJugador1, pnlJugador2;
    private Jugador jugador1 = new Jugador();
    private Jugador jugador2 = new Jugador();
    private JTabbedPane tpJugadores;

    // Metodo Constructor
    public FrmJuego() {
        setSize(500, 300);
        setTitle("Juego De Cartas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("REPARTIR");
        btnRepartir.setBounds(10, 10, 100, 25);
        add(btnRepartir);

        JButton btnVerificar = new JButton("VERIFICAR");
        btnVerificar.setBounds(120, 10, 100, 25);
        add(btnVerificar);

        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 50, 460, 200);
        add(tpJugadores);

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(0, 255, 0));
        pnlJugador1.setLayout(null);

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        tpJugadores.addTab("JUGADOR 1", pnlJugador1);
        tpJugadores.addTab("JUGADOR 2", pnlJugador2);

        btnRepartir.addActionListener(e -> {
            repartir();
        });

        btnVerificar.addActionListener(e -> {
            verificar();
        });

    }

    private void repartir() {
        jugador1.repartir();
        jugador2.repartir();
        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    private void verificar() {
        if (tpJugadores.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, jugador1.getGrupos());
        }else{
            JOptionPane.showMessageDialog(null, jugador2.getGrupos());
        }
    }

}
