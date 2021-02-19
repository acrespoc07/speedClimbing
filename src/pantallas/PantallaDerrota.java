package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import principal.PanelJuego;
import principal.Pantalla;

/**
 * Pantalla Deroota
 * @author Alejnadro Crespo Cobos
 */

public class PantallaDerrota implements Pantalla {

    private PanelJuego panelJuego;
    private Font fuenteDerrota;
    private Image fondoRedimensionado;
    private BufferedImage fondo;

    // constructor de la clase
    public PantallaDerrota(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        fuenteDerrota = new Font("Agency FB", Font.BOLD, 70);

    }

    /***
     * Inicializa mi pantalla con el fondo determinado
     */
    @Override
    public void inicalizarPantalla() {

        fondo = null;
        try {
            fondo = ImageIO.read(new File("speedClimbing/Imagenes/inicio.jpg"));
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        // Ajustar al tamaño actual
        redimensionarFondo();
    }

    /**
     * Pinta mis componentes en la pantalla
     */
    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);

        g.setColor(Color.RED);
        g.setFont(fuenteDerrota);
        g.drawString("¡Derrota! Te has resbalado", 50, 500);
    }

    @Override
    public void ejecutarFrame() {}

    /**
     * Cuando pulso el raton me manda a la pantalla de inicio
     */
    @Override
    public void pulsarRaton(MouseEvent e) {
        panelJuego.cambiarPantalla(new PantallaInicio(panelJuego));

    }

    /**
     * Cada vez que se dredimensiona la pantalla la imagen coge toda la superficie
     */
    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        redimensionarFondo();

    }

    /**
     * Método para rellenar el fondo del componente.
     * 
     * @param g
     */
    private void rellenarFondo(Graphics g) {

        g.drawImage(fondoRedimensionado, 0, 0, null);

    }

    /**
     * escalo la imagen para que pille toda la superficie de la pantalla
     */
    private void redimensionarFondo() {
        fondoRedimensionado = fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
                Image.SCALE_SMOOTH);
    }

    @Override
    public void tocarTeclado(KeyEvent e) {}
    
}
