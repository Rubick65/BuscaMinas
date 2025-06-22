package FuncionesBuscaMinas;

import InterfacesBuscaMinas.InterfazBuscaMinas;

import javax.swing.*;

public class FuncionesBotonesSelector {

    private static NumeroCasillasBuscaMinas numeroCasillas;

    /**
     * Método que cambia la dificultad en función del nivel seleccionado por el usuario
     *
     * @param nivel              Nivel de dificultad
     * @param selectorDificultad Ventana del selector de dificultad
     */
    public void seleccionarDificultad(int nivel, JFrame selectorDificultad) {

        // En función del nivel seleccionado se indica un número de casillas en la partida
        switch (nivel) {
            case 0:
                numeroCasillas = new NumeroCasillasBuscaMinas(4);
                break;
            case 1:
                numeroCasillas = new NumeroCasillasBuscaMinas(10);
                break;
            case 2:
                numeroCasillas = new NumeroCasillasBuscaMinas(15);
                break;
            default:
                break;
        }

        // Ejecutamos el juego del busca minas
        new InterfazBuscaMinas();

        // Cerramos la ventana
        selectorDificultad.dispose();

    }


}
