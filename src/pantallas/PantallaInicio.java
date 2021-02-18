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

public class PantallaInicio implements Pantalla {
    private PanelJuego panelJuego;
    private Color colorIntro = Color.BLACK;
    private Font fuenteGrande;
    private BufferedImage fondo;
    private Image fondoRedimensionado;

    // fondo
    /*
     * private Image fondoRedimensionado; private BufferedImage fondo;
     */

    // constructor de la clase
    public PantallaInicio(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        fuenteGrande = new Font("Arial", Font.BOLD, 25);

    }

    /***
     * Inicializa mi pantalla con el fondo determinado
     */
    @Override
    public void inicalizarPantalla() {

        
        fondo = null;
        try {
            fondo = ImageIO.read(new File("Imagenes/ini.jpg"));
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
        g.setColor(Color.BLACK);
        /* g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
 */
        g.setColor(colorIntro);
        g.setFont(fuenteGrande);
        g.drawString("Bienvenidos a ASTEROIDES 2dam", panelJuego.getWidth() / 2 - 200, 100);
    }
    /**
     * Método para rellenar el fondo del componente.
     * 
     * @param g
     */
    /*
     * private void rellenarFondo(Graphics g){ g.drawImage(fondoRedimensionado ,0,
     * 0, null); }
     */

    /**
     * Este metodo se encarga de de la funcionalidad del hilo en esta pantalla
     */
    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(250);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // El color se va cambiando de tono
        colorIntro = colorIntro == Color.WHITE ? Color.LIGHT_GRAY : Color.WHITE;

    }

    /**
     * Cuando pulso el raton me manda a la pantalla de juego
     */
    @Override
    public void pulsarRaton(MouseEvent e) {
        panelJuego.cambiarPantalla(new PantallaJuego(panelJuego));

    }

    @Override
    public void moverRaton(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * Cada vez que se dredimensiona la pantalla la imagen coge toda la superficie
     */
    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        // redimensionarFondo();
    }

    @Override
    public void tocarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    private void rellenarFondo(Graphics g) {
        

        g.drawImage(fondoRedimensionado, 0, 0, null);

    }
    private void redimensionarFondo() {
        fondoRedimensionado = fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
                Image.SCALE_SMOOTH);
    }


}
