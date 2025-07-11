package InterfacesBuscaMinas;

import FuncionesBuscaMinas.FuncionesBotonesBuscaMinas;
import FuncionesBuscaMinas.NumeroCasillasBuscaMinas;

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
        buscaMinas = new JFrame("BuscaMinas");
        buscaMinas.setSize(new Dimension(700, 700));
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

                // Creamos y añadimos los botones
                botones[i][j] = new JButton();

                // Añadimos las fucniones a los botones
                botones[i][j].addActionListener(e -> gestor.funcionSegundaPulsacion(gridBotones, buscaMinas, fila, columna));
                gestor.funcionBotonesClickDerecho(buscaMinas, botones[i][j], fila, columna);
                botones[i][j].addActionListener(e -> gestor.funcionPrimeraPulsacion(fila, columna));

                // Añadimos los botones a la grid principal
                gridBotones.add(botones[i][j]);

            }
        }
    }

}

