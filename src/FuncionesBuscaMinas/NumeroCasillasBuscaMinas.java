package FuncionesBuscaMinas;

public class NumeroCasillasBuscaMinas {
    // Atributo que indica la cantidad de casillas por fila y columna del busca minas
    private static int numCasillas = 10;

    /**
     * Contructor que da valor inicial a la cantidad de casillas
     *
     * @param numCasillas Número de casillas del busca minas
     */
    public NumeroCasillasBuscaMinas(int numCasillas) {
        setNumCasillas(numCasillas);
    }

    // Getter del número de casillas
    public static int getNumCasillas() {
        return numCasillas;
    }

    // Setter del número de casillas
    public static void setNumCasillas(int numCasillas) {
        NumeroCasillasBuscaMinas.numCasillas = numCasillas;
    }
}
