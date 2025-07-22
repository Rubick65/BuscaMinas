package FuncionesBuscaMinas;

import InterfacesBuscaMinas.InterfazBuscaMinas;

import javax.swing.*;

public class FuncionesBotonesSelector {

    // Atributo que indica la cantidad de casillas por fila y columna del buscaMinas
    private static int numCasillas;

    // Getter del número de casillas
    public static int getNumCasillas() {
        return numCasillas;
    }

    // Setter del número de casillas
    private void setNumCasillas(int numCasillas) {
        FuncionesBotonesSelector.numCasillas = numCasillas;
    }

    /**
     * Método que cambia la dificultad en función del nivel seleccionado por el usuario
     *
     * @param nivel Nivel de dificultad
     */
    public void seleccionarDificultad(int nivel, JFrame ventana) {

        // En función del nivel seleccionado se indica un número de casillas en la partida
        switch (nivel) {
            case 0:
                setNumCasillas(9);
                break;
            case 1:
                setNumCasillas(12);
                break;
            case 2:
                setNumCasillas(15);
                break;
            default:
                break;
        }

        // Cerramos la ventana
        ventana.dispose();

        // Hacemos que la primera pulsación sea reiniciada
        FuncionesBotonesBuscaMinas.setPrimeraPulsacion(true);

        // Ejecutamos el juego del buscaMinas
        new InterfazBuscaMinas();
    }

}