public class CasillasOcultasBuscaMinas {

    private static final int NUM_CASILLAS = NumeroCasillasBuscaMinas.getNumCasillas();// Cantidad de casillas ocultas del busa minas
    private int[][] casillasOcultas = new int[NUM_CASILLAS][NUM_CASILLAS]; // Matriz con la cantidad de casillas ocultas

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
    public void crearCasillasReales() {

        // LLamamos al método que añade las bombas a las casillas
        anyadirBombas();

        // Añadimos los números que indican las bombas cercanas
        anyadirNumeros();

    }

    /**
     * Método que añade las bombas en casillas aleatorias
     */
    private void anyadirBombas() {

        int bombas = 0, bombasMaximas, posicionAleatoriaFila, posicionAleatoriaColumna;

        // Calculamos el número de bombas máximas en función de la cantidad de casillas
        bombasMaximas = ((NUM_CASILLAS * NUM_CASILLAS) * 15) / 100;

        // Situamos bombas en posiciones aleatorias de la matriz
        while (bombas < bombasMaximas) {
            // Creamos números random para indicar las posiciones en las que se situaran las bombas
            posicionAleatoriaFila = (int) (Math.random() * (NUM_CASILLAS));
            posicionAleatoriaColumna = (int) (Math.random() * (NUM_CASILLAS));

            // Solo ponemos la bomba en caso de que no exista ya una en esa posición
            if (casillasOcultas[posicionAleatoriaFila][posicionAleatoriaColumna] != 9) {
                casillasOcultas[posicionAleatoriaFila][posicionAleatoriaColumna] = 9;
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

        // Variables para guardar lsa posiciones alrededor de la posción acutal y la cantidad de bombas en una posición
        int nuevaFila, nuevaColumna, bombasAlrededor = 0;

        // Primero creamos una matriz con todas las posiciones alrededor de la posición actual
        int[][] posiciones = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        // Iteramos sobre esas posiciones
        for (int[] dir : posiciones) {
            // Guardamos la posición en las variables
            nuevaFila = fila + dir[0];
            nuevaColumna = columna + dir[1];

            // En caso de que la posición no supere los límites
            if (nuevaFila >= 0 && nuevaFila < NUM_CASILLAS && nuevaColumna >= 0 && nuevaColumna < NUM_CASILLAS) {

                // Y de que la casilla actual sea una bomba
                if (casillasOcultas[nuevaFila][nuevaColumna] == 9) {
                    // Sumamos una en caso de que existan bombas en cualquier posición circundante a la actual
                    bombasAlrededor++;
                }
            }
        }
        return bombasAlrededor;
    }
}
