public class CasillasOcultasBuscaMinas {

    private int numCasillas = 10;
    private int[][] casillasOcultas = new int[numCasillas][numCasillas];


    /**
     * Método que crear las casillas reales que están debajo de los botones
     */
    private void crearCasillasReales() {

        // Llamamos al método que pone todas las casillas a cero
        casillasIniciales();

        // LLamamos al método que añade las bombas a las casillas
        anyadirBombas();

        // Añadimos los números que indican las bombas cercanas
        casillasIniciales();

    }

    /**
     * Método que pone a cero todas las casillas ocultas
     */
    private void casillasIniciales() {
        // Iteramos sobre la matriz y vamos poniendo cada posición a cero
        for (int i = 0; i < casillasOcultas.length; i++) {
            for (int j = 0; j < numCasillas; j++) {
                casillasOcultas[i][j] = 0;
            }
        }
    }

    /**
     * Método que añade las bombas en casillas aleatorias
     */
    private void anyadirBombas() {

        int bombas = 0, bombasMaximas, posicionAleatoriaFila, posicionAleatoriaColumna;

        // Calculamos el número de bombas máximas en función de la cantidad de casillas
        bombasMaximas = ((numCasillas * numCasillas) * 15) / 100;

        // Situamos bombas en posiciones aleatorias de la matriz
        while (bombas < bombasMaximas) {

            // Creamos números random para indicar las posiciones en las que se situaran las bombas
            posicionAleatoriaFila = (int) (Math.random() * (numCasillas));
            posicionAleatoriaColumna = (int) (Math.random() * (numCasillas));

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

        // Iteramos sobre la matriz y vamos poniendo cada posición a cero
        for (int i = 0; i < casillasOcultas.length; i++) {
            for (int j = 0; j < numCasillas; j++) {
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
            if (nuevaFila >= 0 && nuevaFila < numCasillas && nuevaColumna >= 0 && nuevaColumna < numCasillas) {

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
