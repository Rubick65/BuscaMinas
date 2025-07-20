package InterfacesBuscaMinas;

import FuncionesBuscaMinas.FuncionesBotonesBuscaMinas;
import FuncionesBuscaMinas.FuncionesBotonesSelector;

import javax.swing.*;
import java.awt.*;

public class InterfazBuscaMinas {

    private int num_casillas; // Número de casillas del buscaMinas;
    private JFrame buscaMinas; // Ventana del buscaMinas
    private JButton[][] botones;// Matriz de botones del buscaMinas
    private static final FuncionesBotonesBuscaMinas gestor = new FuncionesBotonesBuscaMinas(); // Gestor con las diferentes funciones del buscaMinas
    private static final FuncionesBotonesSelector gestorDificultades = new FuncionesBotonesSelector();
    private String[] dificultades = SelectorDificultadBuscaMinas.getDificultades();
    private JPanel gridBotones; // Panel principal del buscaMinas
    private JMenuBar menuBarra; // Barra del menú principal

    /**
     * Constructor que inizializa el buscaMinas
     */
    public InterfazBuscaMinas() {

        this.num_casillas = FuncionesBotonesSelector.getNumCasillas();
        botones = new JButton[num_casillas][num_casillas];
        inizializar();
    }

    /**
     * Método que crea todos los componentes del buscaMinas
     */
    private void inizializar() {
        // Llamamos a la función que crea la ventana principal
        crearVentanaBuscaMinas();

        crearMenuBarra();

        // Creamos el panel con los botones
        panelBotones();

        // Añadimos el panel de botones a la ventana
        buscaMinas.add(gridBotones);
        buscaMinas.setJMenuBar(menuBarra);

        buscaMinas.setVisible(true);

    }

    /**
     * Método que crea la ventana principal del buscaMinas y sus características
     */
    private void crearVentanaBuscaMinas() {
        buscaMinas = new JFrame("BuscaMinas");
        buscaMinas.setSize(new Dimension(700, 700));
        buscaMinas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buscaMinas.setResizable(false);
        buscaMinas.setLocationRelativeTo(null);
    }

    /**
     * Método que crear la barra con el menú de opciones
     */
    private void crearMenuBarra() {
        menuBarra = new JMenuBar();
        JMenu dificultad = new JMenu("Dificultad");
        JMenuItem[] menuDificultades = new JMenuItem[dificultades.length];
        for (int i = 0; i < dificultades.length; i++) {
            final int nivel = i;
            menuDificultades[i] = new JMenuItem(dificultades[i]);
            menuDificultades[i].addActionListener(e -> gestorDificultades.seleccionarDificultad(nivel, buscaMinas));
            dificultad.add(menuDificultades[i]);
        }
        menuBarra.add(dificultad);


    }

    /**
     * Método que crea el panel con los botones del buscaMinas
     */
    private void panelBotones() {
        // Creamos el grid de los botones
        gridBotones = new JPanel(new GridLayout(num_casillas, num_casillas));

        //Recorremos la matirz de botones
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < num_casillas; j++) {
                // Sacamos la posición de cada botón
                final int fila = i;
                final int columna = j;

                // Creamos y añadimos los botones
                botones[i][j] = new JButton();

                // Añadimos las funciones a los botones
                botones[i][j].addActionListener(e -> gestor.funcionSegundaPulsacion(gridBotones, buscaMinas, fila, columna));
                gestor.funcionBotonesClickDerecho(botones[i][j]);
                botones[i][j].addActionListener(e -> gestor.funcionPrimeraPulsacion(fila, columna));

                // Añadimos los botones a la grid principal
                gridBotones.add(botones[i][j]);

            }
        }
    }

}

