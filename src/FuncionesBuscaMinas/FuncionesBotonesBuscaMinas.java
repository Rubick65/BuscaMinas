package FuncionesBuscaMinas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import InterfacesBuscaMinas.InterfazBuscaMinas;


public class FuncionesBotonesBuscaMinas {

    private static final int NUM_CASILLAS = NumeroCasillasBuscaMinas.getNumCasillas(); // Número de casillas del busca minas
    private static int[][] casillasOcultas;// Casillas ocultas del busca minas
    private static boolean primeraPulsacion = true; // Variable que indica si es la primera vez que se pulsa un botón en la partida

    /**
     * Método que crea todas las casillas ocultas únicamente la primera vez que se pulsa algún botón
     */
    public void funcionPrimeraPulsacion() {

        // En caso de que sea la primera pulsación
        if (primeraPulsacion) {
            // Creamos una nueva instancia de casillas oculta
            CasillasOcultasBuscaMinas casillasOcultasGestor = new CasillasOcultasBuscaMinas(); // Gestor con los métodos de creación de las casillas ocultas

            // Creamos las casillas ocultas
            casillasOcultasGestor.crearCasillasReales();

            // Creamos las casillas ocultas y las guardamos
            casillasOcultas = casillasOcultasGestor.getCasillasOcultas();

            // Indicamos que ya hemos pulsado una vez
            primeraPulsacion = !primeraPulsacion;
        }
    }

    /**
     * Método que da la función que ocurrirá cuando los botones son pulsados por segunda vez
     */
    public void funcionSegundaPulsacion(JPanel gridBotones, JFrame buscaMinas, int fila, int columna) {

        // Actualizamos la posición en función de lo que se encuentre en la casilla oculta
        actualizarPosicion(fila, columna, gridBotones);

        switch (casillasOcultas[fila][columna]) {
            // En caso de que la posción sea una bomba
            case 9:
                // Ejectuamos el método de la bomba
                funcionBomba(buscaMinas, gridBotones);
                break;
            // En caso de que la posción sea un cero
            case 0:
                break;

            // En el resto de casos
            default:
                break;
        }
    }

    /**
     * Método que da función a lo que ocurrirá cuando el jugador pulse encima de una bomba(Perder)
     */
    private void funcionBomba(JFrame buscaMinas, JPanel gridBotones) {

        // LLamamos al método que revela todas las bombas existentes
        revelarBombas(gridBotones);

        // Esperamos 1 segundo (1000 ms) y luego ejecutamos el código
        Timer timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Mostramos una ventana cuando el usuario pierde para preguntarle si quiere reiniciar o no
                int respuesta = JOptionPane.showConfirmDialog(null, "Has perdido, ¿Quieres reintentar?", "Reintentar", JOptionPane.YES_NO_OPTION);

                // En caso de que si reinicie
                if (respuesta == JOptionPane.YES_OPTION) {
                    // Cerramos la ventana actual
                    buscaMinas.dispose();
                    primeraPulsacion = !primeraPulsacion;
                    // Abrimos una nueva instancia del buscaminas
                    new InterfazBuscaMinas();
                } else {
                    // En caso contrario simplemente cerramos la ventana
                    buscaMinas.dispose();
                }
            }
        });

        timer.setRepeats(false); // Solo se ejecuta una vez
        timer.start();
    }

    /**
     * Método que revela todas las bombas existentes en la partida
     *
     * @param gridBotones Panel principal donde se situan todos los elementos del buscaminas
     */
    private void revelarBombas(JPanel gridBotones) {

        // Iteramos sobre las casillas ocultas
        for (int fila = 0; fila < casillasOcultas.length; fila++) {// Filas
            for (int columna = 0; columna < NUM_CASILLAS; columna++) { // Columnas

                // En caso de que sea una bomba
                if (casillasOcultas[fila][columna] == 9) {
                    // Actualizamos la posición actual en la interfaz
                    actualizarPosicion(fila, columna, gridBotones);
                }
            }
        }
    }

    /**
     * Método que actualiza la posicion actual de la interfaz
     *
     * @param nuevaPosicion Componente que va a sustituir a la vieja posción
     * @param gridBotones   Panel donde se están situados todos los botones
     * @param indice        Indice de la posicón actual
     */
    private static void actualizarPosicion(int fila, int columna, JPanel gridBotones) {

        // Calculamos el indice de la posición actual
        int indice = fila * NUM_CASILLAS + (columna);

        // Sacamos el texto de la nueva posición
        JLabel nuevaPosicion = new JLabel(String.valueOf(casillasOcultas[fila][columna]), SwingConstants.CENTER);

        switch (casillasOcultas[fila][columna]) {
            case 9:
                // Cambiamos la posción por un icono
                nuevaPosicion.setText("💣");
                break;
            case 0:
                // En caso se ser un cero dejamos la posición vacia
                nuevaPosicion.setText("");
                break;
            default:
                break;
        }

        // Quitamos la posición actual
        gridBotones.remove(indice);
        // Añadimos la nueva posición
        gridBotones.add(nuevaPosicion, indice);

        // Refrescamos el panel
        gridBotones.revalidate();
        gridBotones.repaint();
    }


}
