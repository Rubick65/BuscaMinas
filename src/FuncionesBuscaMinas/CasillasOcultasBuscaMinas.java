package FuncionesBuscaMinas;

import java.util.ArrayList;
import java.util.Arrays;

public class CasillasOcultasBuscaMinas {

    private static final int NUM_CASILLAS = NumeroCasillasBuscaMinas.getNumCasillas();// Cantidad de casillas ocultas del busa minas
    private int[][] casillasOcultas = new int[NUM_CASILLAS][NUM_CASILLAS]; // Matriz con la cantidad de casillas ocultas

    // Matriz con todas las posibles posiciones al rededor de una
    private static final int[][] POSICIONES = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    private ArrayList<int[]> listaDePosiconesNoValidas = new ArrayList<>(); // Lista de posiciones que no son válidas para colocar bombas
    private int bombas; // Contador que indica la cantidad de bombas que se irán añadiendo

    /**
     * Método que devuelve la cantidad de bombas de en la partida
     *
     * @return Cantidad de bombas
     */
    public int getBombas() {
        return bombas;
    }

    /**
     * Método que devuelve la matriz con las casillas ocultas
     *
     * @return Devuelve la matriz con las casillas ocultas
     */
    public int[][] getCasillasOcultas() {
        return casillasOcultas;
    }

    /**
     * Método que crear las casillas reales que están debajo de los botones
     */
    public void crearCasillasReales(int fila, int columna) {

        // Llamamos al método que crea la lista de posiciones sin bombas
        listaPosicionesSinBombas(fila, columna);

        // LLamamos al método que añade las bombas a las casillas
        anyadirBombas();


        // Añadimos los números que indican las bombas cercanas
        anyadirNumeros();


    }

    /**
     * Método que crea el array con las posiciones en las que no debe existir bombas al inicio de la partida
     *
     * @param fila    Fila de la primera posición
     * @param columna Columna de la primera posición
     */
    private void listaPosicionesSinBombas(int fila, int columna) {

        // Recorremos las posiciones 
        for (int[] dir : POSICIONES) {

            // Creamos una posición en la fila
            int nuevaFila = fila + dir[0];

            // Creamos una nueva posición en la columna
            int nuevaColumna = columna + dir[1];

            // Creamos un array con estas posiciones
            int[] array = {nuevaFila, nuevaColumna};

            // Las añadimos a la lista de posicones no válidas
            listaDePosiconesNoValidas.add(array);
        }

        // Por úlitmo añadimos la primera posición
        int[] posicionInicial = {fila, columna};

        // Añadimos la posición inicial a la lista
        listaDePosiconesNoValidas.add(posicionInicial);

    }

    /**
     * Método que añade las bombas en casillas aleatorias
     */
    private void anyadirBombas() {

        // VAriables que indican la cantidad máxima de bombas y las posiciones aleatorias donde se van a poner las bombas
        int bombasMaximas, posicionAleatoriaFila, posicionAleatoriaColumna;

        // Booleano que indica si la posición creado aleatoriamente es válida o no
        boolean posicionNoValida;

        // Calculamos el número de bombas máximas en función de la cantidad de casillas
        bombasMaximas = ((NUM_CASILLAS * NUM_CASILLAS) * 15) / 100;

        // Situamos el contador a cero
        bombas = 0;

        // Mientras que el contador de bombas no sea igual a la cantidad máxima de bombas
        while (bombas < bombasMaximas) {

            // Creamos números random para indicar las posiciones en las que se situaran las bombas
            posicionAleatoriaFila = (int) (Math.random() * (NUM_CASILLAS));
            posicionAleatoriaColumna = (int) (Math.random() * (NUM_CASILLAS));

            // Creamos un Array con las dos posciones aleatorias
            int[] posiconAleatoria = {posicionAleatoriaFila, posicionAleatoriaColumna};

            // Indicamos siempre que la posición será válida por defecto
            posicionNoValida = false;
            // En caso de que la lista de arrays contenga la posición aleatorio simplemente saltamos esa posición
            for (int[] posicionSinBomba : listaDePosiconesNoValidas) {
                // Si cualquier posición al rededor de la posición inicial coincide con alguna casilla con bomba
                if (Arrays.equals(posicionSinBomba, posiconAleatoria)) {
                    // Indicamos que la posición no es válida
                    posicionNoValida = true;
                    // Y salimos del bucle
                    break;
                }
            }

            // Si la posición no es valida entonces simplemente la saltamos y no ponemos bomba
            if (posicionNoValida) continue;

            // Solo ponemos la bomba en caso de que no exista ya una en esa posición
            if (casillasOcultas[posicionAleatoriaFila][posicionAleatoriaColumna] != 9) {

                // Aadimos la bomba a la posición
                casillasOcultas[posicionAleatoriaFila][posicionAleatoriaColumna] = 9;

                // Aumentamos en uno el contador
                bombas++;
            }
        }
    }

    /**
     * Método que añade los números que indican cuantas bombas existen al rededor
     */
    private void anyadirNumeros() {

        // Iteramos sobre la matriz
        for (int i = 0; i < casillasOcultas.length; i++) {
            for (int j = 0; j < NUM_CASILLAS; j++) {

                // En caso de que la posición actual no sea una bomba
                if (casillasOcultas[i][j] != 9) {
                    casillasOcultas[i][j] = sumarNumeros(i, j);
                }
            }
        }
    }

    /**
     * Método que suma la cantidad de bombas alrededor de una posición
     *
     * @param fila    Fila actual
     * @param columna Columna actual
     * @return Devuelve la cantidad de bombas alrededor de la posición actual
     */
    private int sumarNumeros(int fila, int columna) {

        // Variables para guardar las posiciones alrededor de la posción acutal y la cantidad de bombas en una posición
        int nuevaFila, nuevaColumna, bombasAlrededor = 0;

        // Iteramos sobre esas posiciones
        for (int[] dir : POSICIONES) {
            // Guardamos la posición en las variables
            nuevaFila = fila + dir[0];
            nuevaColumna = columna + dir[1];

            // En caso de que la posición no supere los límites
            if (nuevaFila >= 0 && nuevaFila < NUM_CASILLAS && nuevaColumna >= 0 && nuevaColumna < NUM_CASILLAS) {

                // Y de que la casilla actual sea una bomba
                if (casillasOcultas[nuevaFila][nuevaColumna] == 9) {
                    // Aumentamos en uno en caso de que existan bombas en cualquier posición circundante a la actual
                    bombasAlrededor++;
                }
            }
        }

        // Devolvemos la cantidad de bombas
        return bombasAlrededor;
    }
}
