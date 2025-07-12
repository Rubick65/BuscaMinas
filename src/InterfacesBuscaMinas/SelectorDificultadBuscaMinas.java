package InterfacesBuscaMinas;

import FuncionesBuscaMinas.FuncionesBotonesSelector;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que inicializa y crea todos los componentes necesarios del selector de dificultad
 *
 * @author Rubén Martín Andrade
 * @version 1.0
 */

public class SelectorDificultadBuscaMinas {

    private JFrame ventanaSelectorDificultad; // Ventana del selector
    private JLabel encabezado; // Texto de la cabecera de la ventana
    private static FuncionesBotonesSelector funcionSelecciona = new FuncionesBotonesSelector(); // Gestor de la función de dificultad
    private static final String[] dificultades = {"Fácil", "Normal", "Difícil"}; // Texto de las distintas dificultades
    private JPanel panelBotones; // Panel que almacena los botones de las dificultades

    /**
     * Constructor que inicializa el selector de dificultad
     */
    public SelectorDificultadBuscaMinas() {
        inicializarSelector();
    }

    /**
     * Método que inicializa todos los componentes del selector
     */
    private void inicializarSelector() {

        // Llamamos a la función que crea la ventana del selector
        crearVentanaSelector();

        //LLamamos a la función que crea la cabecera del selector
        crearCabeceraSelector();

        // Llamamos a la función que crea el panel con los botones del selector
        crearPanelBotonesSelector();

        ventanaSelectorDificultad.add(encabezado, BorderLayout.NORTH);
        ventanaSelectorDificultad.add(panelBotones, BorderLayout.CENTER, SwingConstants.CENTER);
        ventanaSelectorDificultad.setVisible(true);

    }

    /**
     * Método que crea la ventana del selector de dificultad
     */
    private void crearVentanaSelector() {
        ventanaSelectorDificultad = new JFrame("BuscaMinas");
        ventanaSelectorDificultad.setSize(new Dimension(400, 400));
        ventanaSelectorDificultad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaSelectorDificultad.setResizable(false);
        ventanaSelectorDificultad.setLocationRelativeTo(null);
    }

    /**
     * Método que crea la cabecera del selector de dificultad con el encabezado
     */
    private void crearCabeceraSelector() {
        encabezado = new JLabel("SELECCIONA UNA DIFICULTAD", SwingConstants.CENTER);
        encabezado.setFont(new Font("Segoe UI", Font.BOLD, 20));
        encabezado.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
    }

    /**
     * Método que crea el panel que contiene los botones del selector
     */
    private void crearPanelBotonesSelector() {
        //Creamos la lista de botones con las distintas dificultades
        JButton[] botonesDificultades = new JButton[dificultades.length];

        // Creamos el panel que contendrá los botones
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));

        //Iteramos sobre la lista de botones
        for (int i = 0; i < dificultades.length; i++) {
            final int nivel = i;
            // Creamos los botones y los añadimos a la lista
            botonesDificultades[i] = new JButton(dificultades[i]);
            botonesDificultades[i].setFont(new Font("Arial", Font.ITALIC, 17));
            botonesDificultades[i].setPreferredSize(new Dimension(150, 75));
            botonesDificultades[i].addActionListener(e -> funcionSelecciona.seleccionarDificultad(nivel, ventanaSelectorDificultad));
            panelBotones.add(botonesDificultades[i]);
        }

    }
}
