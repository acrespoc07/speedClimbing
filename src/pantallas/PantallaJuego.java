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
import java.util.ArrayList;

import javax.imageio.ImageIO;

import principal.PanelJuego;
import principal.Pantalla;
import principal.Sprite;

public class PantallaJuego implements Pantalla {

    private PanelJuego panelJuego;

    static ArrayList<Sprite> presas;

    private static final int NUMP = 17;
    private static final int ALTO_PRESA = 30;
    private static final int ANCHO_PRESA = 30;

    private static final int POSX_PRESA_1 = 430;
    private static final int POSY_PRESA_1 = 700;

    private static final int POSX_PRESA_2 = 440;
    private static final int POSY_PRESA_2 = 680;

    private static final int POSX_PRESA_3 = 410;
    private static final int POSY_PRESA_3 = 630;

    private static final int POSX_PRESA_4 = 360;
    private static final int POSY_PRESA_4 = 600;

    private static final int POSX_PRESA_5 = 410;
    private static final int POSY_PRESA_5 = 560;

    private static final int POSX_PRESA_6 = 430;
    private static final int POSY_PRESA_6 = 510;

    private static final int POSX_PRESA_7 = 380;
    private static final int POSY_PRESA_7 = 470;

    private static final int POSX_PRESA_8 = 430;
    private static final int POSY_PRESA_8 = 440;

    private static final int POSX_PRESA_9 = 360;
    private static final int POSY_PRESA_9 = 395;

    private static final int POSX_PRESA_10 = 380;
    private static final int POSY_PRESA_10 = 345;

    private static final int POSX_PRESA_11 = 350;
    private static final int POSY_PRESA_11 = 325;

    private static final int POSX_PRESA_12 = 390;
    private static final int POSY_PRESA_12 = 280;

    private static final int POSX_PRESA_13 = 370;
    private static final int POSY_PRESA_13 = 240;

    private static final int POSX_PRESA_14 = 360;
    private static final int POSY_PRESA_14 = 220;

    private static final int POSX_PRESA_15 = 400;
    private static final int POSY_PRESA_15 = 170;

    private static final int POSX_PRESA_16 = 430;
    private static final int POSY_PRESA_16 = 120;

    private static final int POSX_PRESA_17 = 390;
    private static final int POSY_PRESA_17 = 100;

    // Tiempo
    private double inicioTiempo;
    private static double tiempoTranscurrido;
    private DecimalFormat fomateador;

    // Fuentes
    private Font fuenteTiempo;

    // fondo
    private Image fondoRedimensionado;
    private BufferedImage fondo;

    private char letraActual;

    private boolean respuestaAcierto;
    private boolean empezarAContar;

    // constructor de la clase
    public PantallaJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        fuenteTiempo = new Font("Arial", Font.BOLD, 30);
        fomateador = new DecimalFormat("0.00");
        respuestaAcierto = true;
        empezarAContar = false;

    }

    public void anadirPresas(String ruta, int ancho, int alto, int posx, int posy){

        presas.add(new Sprite(ruta, ancho, alto, posx, posy));

    }

    /***
     * Inicializa mi pantalla con el fondo determinado
     */
    @Override
    public void inicalizarPantalla() {
        inicioTiempo = 0.00;
        // añado mis presas
        presas = new ArrayList<Sprite>();

        for (int i = 1; i < 22; i++) {
            if(i%2 == 0){
                anadirPresas("speedClimbing/Imagenes/presa1.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_1 , POSY_PRESA_1 -30 * i);
            }else{
                anadirPresas("speedClimbing/Imagenes/presa1.png", ANCHO_PRESA, ALTO_PRESA, 380 , POSY_PRESA_1 -30 * i);

            }
            
        }
         
 
       /*  presas.add(new Sprite("speedClimbing/Imagenes/presa1.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_1, POSY_PRESA_1));
        presas.add(new Sprite("speedClimbing/Imagenes/presa2.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_2, POSY_PRESA_2));
        presas.add(new Sprite("speedClimbing/Imagenes/presa3.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_3, POSY_PRESA_3));
        presas.add(new Sprite("speedClimbing/Imagenes/presa4.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_4, POSY_PRESA_4));
        presas.add( new Sprite("speedClimbing/Imagenes/presa5.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_5, POSY_PRESA_5));
        presas.add(new Sprite("speedClimbing/Imagenes/presa6.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_6, POSY_PRESA_6));
        presas.add(new Sprite("speedClimbing/Imagenes/presa7.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_7, POSY_PRESA_7));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa8.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_8, POSY_PRESA_8));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa9.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_9, POSY_PRESA_9));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa6.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_10, POSY_PRESA_10));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa4.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_11, POSY_PRESA_11));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa3.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_12, POSY_PRESA_12));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa5.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_13, POSY_PRESA_13));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa6.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_14, POSY_PRESA_14));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa3.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_15, POSY_PRESA_15));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa7.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_16, POSY_PRESA_16));
        presas.add(
                new Sprite("speedClimbing/Imagenes/presa6.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_17, POSY_PRESA_17)); */

        // Pongo en fondo en la pantalla
        fondo = null;
        try {
            fondo = ImageIO.read(new File("speedClimbing/Imagenes/muroP.png"));
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
        // pinto mis componentes
        for (Sprite presa : presas) {
            presa.estampar(g);
        }

        // le doy formato al tiempo
        g.setColor(Color.BLACK);
        g.setFont(fuenteTiempo);

        // Compruebo si ha acertado la letra para poder generar la siguiente
        if (respuestaAcierto) {
            int le= (char) letrasAleaorias();
            g.drawString("" + le, 240, 50);
            respuestaAcierto = false;

        } else {
            g.drawString("" + letraActual, 240, 50);
        }

        // pinto el tiempo
        g.drawString(fomateador.format(tiempoTranscurrido / 1e9), 360, 60);
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
     * Este metodo devuelve la veocidad para mis asteroides la velocidad mayor es de
     * valor 5 ya que si lo pongo alg mas alta la jugabilidad empeora
     * 
     * @return
     */
    public int letrasAleaorias() {

        int numAleatorio = (int) Math.floor(Math.random() * (122 - 97) + 97);

        letraActual = (char) numAleatorio;
        return letraActual;

    }

    @Override
    public void tocarTeclado(KeyEvent e) {

        if (letraActual == e.getKeyChar()) {
            empezarAContar = true;
            if (inicioTiempo == 0.00) {
                inicioTiempo = System.nanoTime();
            }

            presas.remove(0);
            respuestaAcierto = true;

        } else {
            panelJuego.cambiarPantalla(new PantallaDerrota(panelJuego));

        }

    }

    /**
     * cada vez que se ejcuta el hilo hace estas comprobaciones
     */
    @Override
    public void ejecutarFrame() {
        panelJuego.setFocusable(true);
        panelJuego.requestFocus();

        if (empezarAContar) {
            try {
                Thread.sleep(25);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ponerTiempo();

            // SI has eliminado a todos los asteroides, ganas
            if (presas.size() == 0) {
                panelJuego.cambiarPantalla(new PantallaVictoria(panelJuego));
            }

        }

    }

    /**
     * Calcula el tiempo transcurrido desde el ultimo refresco
     */
    public void ponerTiempo() {

        tiempoTranscurrido = System.nanoTime() - inicioTiempo;
    }

    /**
     * Si pulsas el raton pueden pasar dos cosas. 1-si pulsas el boton derecho
     * aparece la nave. 2.si pulsas el boton izquierdo cargas un disparo y lo lanzas
     */
    @Override
    public void pulsarRaton(MouseEvent e) {

    } 

    /**
     * Este metodo ajusta la posicion central de la nave a nuestro cursor para tener
     * mas precision a la hora de disparar
     */
     @Override
    public void moverRaton(MouseEvent e) {

    } 

    /**
     * Cada vez que se dredimensiona la pantalla la imagen coge toda la superficie
     */
    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        redimensionarFondo();
    }

    /**
     * escalo la imagen para que pille toda la superficie de la pantalla
     */
    private void redimensionarFondo() {
        fondoRedimensionado = fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
                Image.SCALE_SMOOTH);
    }

    /**
     * Este metodo es importante para pintar el tiempo en la pantalla de victoria,
     * es el encargado de retornar el tiempo cuando acabas con todos los asteroides
     * 
     * @return
     */
    public static double darTiempo() {
        return tiempoTranscurrido;
    }

    

}
