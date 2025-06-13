import javax.swing.*;
import java.awt.*;

public class InterfazBuscaMinas {

    private int numCasillas = 10;
    private JFrame buscaMinas; // Ventana del busca minas
    private JButton[][] botones = new JButton[numCasillas][numCasillas];
    private JPanel gridBotones;

    /**
     * Constructor que inizializa el busca minas
     */
    public InterfazBuscaMinas() {
        inizializar();
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
        buscaMinas.setResizable(false);
        buscaMinas.setLocationRelativeTo(null);
    }

    /**
     * Método que crea el panel con los botones del buscaminas
     */
    private void panelBotones() {
        gridBotones = new JPanel(new GridLayout(numCasillas, numCasillas));
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < numCasillas; j++) {
                botones[i][j] = new JButton();
                gridBotones.add(botones[i][j]);

            }
        }
    }

}

