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

/**
 * Ventana Pantalla Juego
 * @author Alejnadro Crespo Cobos
 */

public class PantallaJuego implements Pantalla {

    private PanelJuego panelJuego;

    //lista donde meto las presas
    static ArrayList<Sprite> presas;

    //constantes necesarias para las presas
    private static final int NUMP = 22;
    private static final int ALTO_PRESA = 30;
    private static final int ANCHO_PRESA = 30;

    private static final int POSX_PRESA_1 = 430;
    private static final int POSY_PRESA_1 = 700;

    

    // Tiempo
    private double inicioTiempo;
    private static double tiempoTranscurrido;
    private DecimalFormat fomateador;

    // Fuentes
    private Font fuenteTiempo;

    // fondo
    private Image fondoRedimensionado;
    private BufferedImage fondo;

    //variable que ira mostrando la letra a pulsar
    private char letraActual;

    //Comprobadores
    private boolean respuestaAcierto;
    private boolean empezarAContar;


      String[] l  = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","u","v","w","x","y","z"};
    // constructor de la clase
    public PantallaJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        fuenteTiempo = new Font("Arial", Font.BOLD, 30);
        fomateador = new DecimalFormat("0.00");
        tiempoTranscurrido = 0.00;
        respuestaAcierto = true;
        empezarAContar = false;

    }

    /**
     * Este metodo ira añadiendo presas a la lista.
     * @param ruta
     * @param ancho
     * @param alto
     * @param posx
     * @param posy
     */
    public void anadirPresas(String ruta, int ancho, int alto, int posx, int posy){

        presas.add(new Sprite(ruta, ancho, alto, posx, posy));

    }

    /***
     * Inicializa mi pantalla con el fondo determinado
     */
    @Override
    public void inicalizarPantalla() {
        
       
        presas = new ArrayList<Sprite>();
      

        //me recorro todas las presas que voy a poner y las voy añadiendo a la lista y a su vez a la pantalla
        for (int i = 1; i < NUMP; i++) {
            if(i%2 == 0){
                anadirPresas("Imagenes/presa1.png", ANCHO_PRESA, ALTO_PRESA, POSX_PRESA_1 , POSY_PRESA_1 -30 * i);
            }else{
                anadirPresas("Imagenes/presa1.png", ANCHO_PRESA, ALTO_PRESA, 350 , POSY_PRESA_1 -30 * i);

            }
            
        }
         
 

        // Pongo en fondo en la pantalla
        fondo = null;
        try {
            fondo = ImageIO.read(new File("Imagenes/muroP.png"));
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
     * Este metodo devuelve la letra que va a ir apareciendo en nuestra pantalla
     * la tengo que convertir a char 
     * @return
     */
    public int letrasAleaorias() {

        int numAleatorio = (int) Math.floor(Math.random() * (122 - 97) + 97);

        letraActual = (char) numAleatorio;
        return letraActual;

    }

   public int posAleatoria(){
        int numAleatorio = (int) Math.floor(Math.random() * 26);
        return numAleatorio;
    } 

    /**
     * Este metodo va a capturar las entradas de teclado e ira borrando las presas de la lista 
     */
    @Override
    public void tocarTeclado(KeyEvent e) {

      
        if (letraActual == e.getKeyChar()) {
            empezarAContar = true;
            if (inicioTiempo == 0.00) {
                inicioTiempo = System.nanoTime();
            }

            presas.remove(0);
            respuestaAcierto = true;

        }  
        else {
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
