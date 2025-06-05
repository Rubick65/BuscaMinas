import javax.swing.*;
import java.awt.*;

public class InterfazBuscaMinas {

    public InterfazBuscaMinas() {
        inizializar();
    }

    private void inizializar() {
        crearVentanaBuscaMinas();

    }

    private void crearVentanaBuscaMinas() {
        JFrame buscaMinas = new JFrame("Busca Minas");
        buscaMinas.setSize(new Dimension(500, 400));
        buscaMinas.setResizable(false);
        buscaMinas.setLocationRelativeTo(null);
        buscaMinas.setVisible(true);
    }
}
