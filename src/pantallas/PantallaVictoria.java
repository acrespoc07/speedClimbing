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
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import principal.PanelJuego;
import principal.Pantalla;

public class PantallaVictoria implements Pantalla {

    private PanelJuego panelJuego;

    private Font fuenteVictoria;
    private Font fuenteVictoriaT;

    private Image fondoRedimensionado;
    private BufferedImage fondo;

    private double tiempoVictoria;
    private DecimalFormat fomateador;

    // constructor de la clase

    public PantallaVictoria(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        fuenteVictoria = new Font("Agency FB", Font.BOLD, 70);
        fuenteVictoriaT = new Font("Agency FB", Font.BOLD, 30);
        tiempoVictoria = panelJuego.enviarTiempo();
        fomateador = new DecimalFormat("0.00");

    }

    /***
     * Inicializa mi pantalla con el fondo determinado
     */
    @Override
    public void inicalizarPantalla() {

        fondo = null;
        try {
            fondo = ImageIO.read(new File("Imagenes/vic.jpg"));
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

        g.setColor(Color.BLUE);
        g.setFont(fuenteVictoria);
        g.drawString("¡Victoria!", 50, 190);
        g.setFont(fuenteVictoriaT);
        g.drawString(" Tiempo: " + fomateador.format(tiempoVictoria / 1e9), 50, 240);

    }

    @Override
    public void ejecutarFrame() {
        // TODO Auto-generated method stub

    }

    /**
     * Cuando pulso el raton me manda a la pantalla de inicio
     */
    @Override
    public void pulsarRaton(MouseEvent e) {
        panelJuego.cambiarPantalla(new PantallaInicio(panelJuego));

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
    public void tocarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    


    
}
