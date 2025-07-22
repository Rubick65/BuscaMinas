package FuncionesBuscaMinas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import InterfacesBuscaMinas.InterfazBuscaMinas;

/**
 * Clase que da función a los botones del buscaMinas
 *
 * @author Rubén Martín Andrade
 * @version 1.0
 */
public class FuncionesBotonesBuscaMinas {

    private int num_casillas; // Número de casillas del buscaMinas
    private int numBombasFijas, numBanderas, numCasillasSinBomba, numCasillasDespejadas; // Número de bombas fijas, número de banderas, número de casillas sin bomba y cantidad de casillas actuales ya despejadas
    private static final Color[] coloresNumeros = {new Color(34, 139, 34), new Color(70, 130, 180), new Color(184, 134, 11), new Color(139, 69, 19), new Color(178, 34, 34), new Color(75, 0, 130), new Color(25, 25, 112)}; // Lista de colores para los números del buscaMinas
    private static int[][] casillasOcultas;// Casillas ocultas del buscaMinas
    private static boolean primeraPulsacion; // Variable que indica si es la primera vez que se pulsa un botón en la partida

    /**
     * Método set que cambia el estado del booleano primera pulsación
     *
     * @param primeraPulsacion Nuevo estado de la primera pulsación
     */
    public static void setPrimeraPulsacion(boolean primeraPulsacion) {
        FuncionesBotonesBuscaMinas.primeraPulsacion = primeraPulsacion;
    }

    /**
     * Método que crea todas las casillas ocultas únicamente la primera vez que se pulsa algún botón
     */
    public void funcionPrimeraPulsacion(int fila, int columna) {
        // En caso de que sea la primera pulsación
        if (primeraPulsacion) {
            // Sacamos el número de casillas
            num_casillas = FuncionesBotonesSelector.getNumCasillas();

            // Creamos una nueva instancia de casillas oculta
            CasillasOcultasBuscaMinas casillasOcultasGestor = new CasillasOcultasBuscaMinas(fila, columna); // Gestor con los métodos de creación de las casillas ocultas

            // Sacamos el número de bombas
            numBombasFijas = casillasOcultasGestor.getBombas();

            // Sacamos el número de casillas que no contienen bombas
            numCasillasSinBomba = num_casillas * num_casillas - numBombasFijas;

            // Actualizamos las variables iniciales
            valoresPredeterminados();

            // Guardamos las casillas ocultas
            casillasOcultas = casillasOcultasGestor.getCasillasOcultas();
        }
    }

    /**
     * Método que se ejecutará cuando los botones sean pulsados sin importar la el momento en el que se haga
     */
    public void funcionSegundaPulsacion(JPanel gridBotones, JFrame buscaMinas, int fila, int columna) {
        // Sacamos el botón actual
        JButton boton = (JButton) gridBotones.getComponent(fila * num_casillas + (columna));

        // Si el texto del botón es una bandera restamos una bandera
        if (Objects.equals(boton.getText(), "🏁")) numBanderas--;

        // Actualizamos la posición en función de lo que se encuentre en la casilla oculta
        actualizarPosicion(fila, columna, gridBotones);

        switch (casillasOcultas[fila][columna]) {
            // En caso de que la posición sea una bomba
            case 9:
                // Ejecutamos el método de la bomba
                funcionBomba(buscaMinas, gridBotones);
                break;
            // En caso de que la posición sea un cero
            case 0:
                // En caso de ser un número indicador simplemente aumentamos las casillas despejadas
                numCasillasDespejadas++;
                funcionCasillaVacia(gridBotones, fila, columna);
                break;
            // En el resto de casos
            default:
                // En caso de ser un número indicador simplemente aumentamos las casillas despejadas
                numCasillasDespejadas++;
                break;
        }

        // En caso de que todas las casillas que no sean bombas ya hayan sido liberadas ejecutamos la pantalla de victoria
        if (numCasillasDespejadas == numCasillasSinBomba) ventanaFinalPartida(buscaMinas, false);
    }

    /**
     * Método que da función a lo que ocurrirá cuando el jugador pulse encima de una bomba(Perder)
     */
    private void funcionBomba(JFrame buscaMinas, JPanel gridBotones) {
        // LLamamos al método que revela todas las bombas existentes
        revelarBombas(gridBotones);

        // Esperamos 0.2 segundos (200 ms) y luego ejecutamos el código
        Timer timer = new Timer(200, e -> {
            // Función que hace aparecer la ventana del final de la partida
            ventanaFinalPartida(buscaMinas, true);
        });

        timer.setRepeats(false); // Solo se ejecuta una vez
        timer.start();
    }


    /**
     * Método que revela todas las bombas existentes en la partida
     *
     * @param gridBotones Panel principal donde se sitúan todos los elementos del buscaMinas
     */
    private void revelarBombas(JPanel gridBotones) {
        // Iteramos sobre las casillas ocultas
        for (int fila = 0; fila < casillasOcultas.length; fila++) {// Filas
            for (int columna = 0; columna < num_casillas; columna++) { // Columnas
                // En caso de que sea una bomba
                if (casillasOcultas[fila][columna] == 9) {
                    // Actualizamos la posición actual en la interfaz
                    actualizarPosicion(fila, columna, gridBotones);
                }
            }
        }
    }

    /**
     * Método que hace aparecer la ventana con el mensaje de fin de partida
     *
     * @param buscaMinas Ventana actual
     * @param perder     Booleano que indica si él la partida ha sido perdida o no
     */
    private void ventanaFinalPartida(JFrame buscaMinas, boolean perder) {
        // Variable que guardará el valor de la respuesta del usuario
        int respuesta;

        // En caso de que la partida sea perdida
        if (perder) {
            // Mostramos una ventana cuando el usuario pierde para preguntarle si quiere reiniciar o no
            respuesta = JOptionPane.showConfirmDialog(null, "Has perdido, ¿Quieres reintentar?", "Reintentar", JOptionPane.YES_NO_OPTION);
        } else {
            // Mostramos una ventana cuando el usuario gana para preguntarle si quiere volver a jugar
            respuesta = JOptionPane.showConfirmDialog(null, "¡Has Ganado!, ¿Quieres volver a jugar?", "Volver a jugar", JOptionPane.YES_NO_OPTION);
        }

        // Dependiendo de la respuesta del usuario
        switch (respuesta) {
            case 0:
                // Cerramos la ventana actual
                buscaMinas.dispose();

                // Actualizamos las variables importantes
                valoresPredeterminados();

                // Abrimos una nueva instancia del buscaMinas
                new InterfazBuscaMinas();
                break;

            case 1:
                // En caso contrario simplemente cerramos la ventana
                buscaMinas.dispose();
                break;

        }
    }

    /**
     * Método recursivo que se encarga de liberar las casillas en caso de que la pulsada este vacía
     *
     * @param gridBotones Panel con la matriz de botones
     * @param fila        Fila actual
     * @param columna     Columna actual
     */
    private void funcionCasillaVacia(JPanel gridBotones, int fila, int columna) {

        int nuevaFila, nuevaColumna;
        // Primero creamos una matriz con todas las posiciones alrededor de la posición actual
        int[][] posiciones = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] posicion : posiciones) {
            nuevaFila = fila + posicion[0];
            nuevaColumna = columna + posicion[1];

            // En caso de que la posición no supere los límites
            if (nuevaFila >= 0 && nuevaFila < num_casillas && nuevaColumna >= 0 && nuevaColumna < num_casillas) {
                // Sacamos el índice actual
                int indice = calcularIndice(nuevaFila, nuevaColumna);

                // En caso de que la casilla oculta sea cero y que la posición sea un botón
                if (casillasOcultas[nuevaFila][nuevaColumna] != 9 && gridBotones.getComponent(indice) instanceof JButton) {

                    // Aumentamos el número de casillas despejadas
                    numCasillasDespejadas++;

                    // Actualizamos la posición para mostrarla
                    actualizarPosicion(nuevaFila, nuevaColumna, gridBotones);

                    // En caso de que la posición este vacía
                    if (casillasOcultas[nuevaFila][nuevaColumna] == 0) {
                        // Volvemos a ejecutar la función
                        funcionCasillaVacia(gridBotones, nuevaFila, nuevaColumna);
                    }

                }
            }
        }
    }

    /**
     * Método que actualiza la posición actual de la interfaz
     *
     * @param fila        Fila actual
     * @param columna     Columna actual
     * @param gridBotones Panel donde se están situados todos los botones
     */
    private void actualizarPosicion(int fila, int columna, JPanel gridBotones) {

        // Calculamos el índice de la posición actual
        int indice = calcularIndice(fila, columna);

        // Sacamos el texto de la nueva posición
        JLabel nuevaPosicion = new JLabel(String.valueOf(casillasOcultas[fila][columna]), SwingConstants.CENTER);

        switch (casillasOcultas[fila][columna]) {
            case 9:
                // Cambiamos la posición por un icono
                nuevaPosicion.setText("💣");
                break;
            case 0:
                // En caso de ser un cero dejamos la posición vacía
                nuevaPosicion.setText("");
                break;
            default:
                // En caso de ser cualquier otro número cambiamos su color
                nuevaPosicion.setForeground(coloresNumeros[casillasOcultas[fila][columna] - 1]);
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

    /**
     * Método que da función al click derecho (Bandera)
     *
     * @param boton Botón actual
     */
    public void funcionBotonesClickDerecho(JButton boton) {

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    // En caso de que no sea la primera pulsación
                    if (!primeraPulsacion) {
                        // Si el texto del botón es igual a la bandera
                        if (boton.getText().equals("🏁")) {
                            // Si el número de banderas es mayor o igual que cero
                            if (numBanderas >= 0) {
                                // Restamos una bandera
                                numBanderas--;

                                // Cambiamos el texto del botón
                                boton.setText("");

                            }
                        } else {
                            // Si el número de banderas es menor que la cantidad total de bombas
                            if (numBanderas < numBombasFijas) {
                                // Aumentamos una bandera
                                numBanderas++;

                                // Sacamos la altura del botón
                                int alturaBoton = boton.getHeight();

                                // Indicamos el tamaño de la fuente que queremos
                                int tamanyoFuente = Math.max(10, alturaBoton / 5);

                                // Sacamos la fuente original del botón
                                Font fuenteOriginalBoton = boton.getFont();

                                // Cambiamos el texto del botón
                                boton.setFont(new Font(fuenteOriginalBoton.getName(), fuenteOriginalBoton.getStyle(), tamanyoFuente));

                                // Ponemos la bandera en el botón
                                boton.setText("🏁");

                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Método que cambia el estado variables importantes
     */
    private void valoresPredeterminados() {
        numCasillasDespejadas = 0;
        numBanderas = 0;
        primeraPulsacion = !primeraPulsacion;
    }

    /**
     * Método que calcula el indice en función de la posición pasada cómo argumento
     *
     * @param fila    Fila actual
     * @param columna Columna actual
     * @return Devuelve el indice
     */
    private int calcularIndice(int fila, int columna) {
        return fila * num_casillas + (columna);
    }
}
