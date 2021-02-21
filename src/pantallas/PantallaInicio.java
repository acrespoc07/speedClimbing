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
 * Ventana Pantalla Inicio
 * 
 * @author Alejandro Crespo Cobos
 */

public class PantallaInicio implements Pantalla {
    private PanelJuego panelJuego;
    private Color colorIntro = Color.RED;
    private Color colorNombre = Color.BLUE;
    private Font fuenteGrande;
    private Font fuentePeque;
    private BufferedImage fondo;
    private Image fondoRedimensionado;

    // fondo
    /*
     * private Image fondoRedimensionado; private BufferedImage fondo;
     */

    // constructor de la clase
    public PantallaInicio(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        fuenteGrande = new Font("Arial", Font.BOLD, 50);
        fuentePeque = new Font("Arial", Font.BOLD, 25);

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
        g.setColor(colorIntro);
        g.setFont(fuenteGrande);
        g.drawString("SPEED", 100, 250);
        g.drawString("CLIMBING", 100, 300);
        g.setFont(fuentePeque);
        g.setColor(colorNombre);
        g.drawString("Alejandro", 640, 700);
        g.drawString("Crespo", 640, 720);
        g.drawString("Cobos", 640, 740);
    }
    
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
  
    }

    /**
     * Cuando pulso el raton me manda a la pantalla de juego
     */
    @Override
    public void pulsarRaton(MouseEvent e) {
        panelJuego.cambiarPantalla(new PantallaJuego(panelJuego));

    }

    /**
     * Cada vez que se dredimensiona la pantalla la imagen coge toda la superficie
     */
    @Override
    public void redimensionarPantalla(ComponentEvent e) {}

    @Override
    public void tocarTeclado(KeyEvent e) {}

    private void rellenarFondo(Graphics g) {


        g.drawImage(fondoRedimensionado, 0, 0, null);

        
    }

    private void redimensionarFondo() {
        
        fondoRedimensionado = fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
                Image.SCALE_SMOOTH);
    } 


}
