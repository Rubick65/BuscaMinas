import javax.swing.*;
import java.awt.*;

public class InterfazBuscaMinas {

    private static final int NUM_CASILLAS = NumeroCasillasBuscaMinas.getNumCasillas(); // Número de casillas del busca minas;
    private JFrame buscaMinas; // Ventana del busca minas
    private JButton[][] botones = new JButton[NUM_CASILLAS][NUM_CASILLAS];// Matriz de botones del busca minas
    private static FuncionesBotonesBuscaMinas gestor = new FuncionesBotonesBuscaMinas();
    private JPanel gridBotones; // Panel principal del busca minas

    /**
     * Constructor que inizializa el busca minas
     */
    public InterfazBuscaMinas() {
        inizializar();
    }


    /**
     * Método que devuelve el valor del grid de botones
     *
     * @return Devuelve el grid de botones
     */
    public JPanel getGridBotones() {
        return gridBotones;
    }

    /**
     * Método que devuelve la matriz de botones
     *
     * @return Devuelve la matriz de botones
     */
    public JButton[][] getBotones() {
        return botones;
    }

    /**
     * Método que crea todos los componentes del busca minas
     */
    private void inizializar() {
        // Llamamos a la función que crea la ventana principal
        crearVentanaBuscaMinas();

        // Creamos el panel con los botones
        panelBotones();

        // Añadimos el panel de botones a la ventana
        buscaMinas.add(gridBotones);

        buscaMinas.setVisible(true);

    }

    /**
     * Método que crea la ventana principal del busca minas y sus características
     */
    private void crearVentanaBuscaMinas() {
        buscaMinas = new JFrame("Busca Minas");
        buscaMinas.setSize(new Dimension(600, 600));
        buscaMinas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buscaMinas.setResizable(false);
        buscaMinas.setLocationRelativeTo(null);
    }

    /**
     * Método que crea el panel con los botones del buscaminas
     */
    private void panelBotones() {
        gridBotones = new JPanel(new GridLayout(NUM_CASILLAS, NUM_CASILLAS));
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < NUM_CASILLAS; j++) {
                final int fila = i;
                final int columna = j;
                botones[i][j] = new JButton();
                botones[i][j].addActionListener(e -> gestor.funcionSegundaPulsacion(gridBotones, buscaMinas, fila, columna));
                botones[i][j].addActionListener(e -> gestor.funcionPrimeraPulsacion());
                gridBotones.add(botones[i][j]);

            }
        }
    }

}

