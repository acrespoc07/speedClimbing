package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

public class Sprite {

    private BufferedImage buffer;
   
    private Color color = Color.BLACK;

    private int ancho;
    private int alto;

    
    private int posX;
    private int posY;

    private int velX;
    private int velY;



    /**
     * Contrucor de un Sprite segun su color
     * @param color
     * @param ancho
     * @param alto
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     */
    public Sprite(Color color, int ancho, int alto, int posX, int posY, int velX, int velY) {
        
        this.color = color;
        
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velX= velX;
        this.velY= velY;


        inicializarBuffered();
    }
    /**
     *Contrucor de un Sprite segun su imagen
     * @param rutaImagen
     * @param ancho
     * @param alto
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     */
    public Sprite(String rutaImagen, int ancho, int alto, int posX, int posY, int velX, int velY) {
        
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velX= velX;
        this.velY= velY;

        inicializarBuffered(rutaImagen);
    } 

    public Sprite(String rutaImagen, int ancho, int alto, int posX, int posY) {
        
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        
        inicializarBuffered(rutaImagen);
    } 

    /**
     * Inciio mi primer lienzo
     */
    private void inicializarBuffered() {
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();

        g.setColor(color);
        g.fillRect(0, 0, ancho, alto);
        g.dispose();
    }

    /**
     * Incicio mi lienzo con una imagen 
     * @param ruta
     */
    private void inicializarBuffered(String ruta) {
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        BufferedImage imagenSprite = null;

        try {
            imagenSprite = ImageIO.read(new File(ruta));
            Graphics g = buffer.getGraphics();
            g.drawImage(imagenSprite.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);
       
            g.dispose();
        } catch (Exception e) {
           e.printStackTrace();
        }
        
    }
    
    
    /**
     * Metodo que hace que se muevan mis Sprites dentro ddel panel y que reboten en los limites
     * @param widthPanel
     * @param heightPanel
     */
    public void mover(int widthPanel, int heightPanel) {
        setPosX(this.getPosX() + getVelX());
        setPosY(this.getPosY() + getVelY());
        posY +=velY;
        posX += velX;
        //comprobar si choca con los bordes
        //por la derecha
        if (posX + ancho >= widthPanel) {
            velX = - Math.abs(velX);//forzar a que la velocidad sea negativa, valor absoluto

        }
        //por la izquieda
        if(posX < 0){
            velX = Math.abs(velX); //forzar a que la velociadad sea siempre positiva
        }

        //por arriba

        if (posY< 0 ) {
            velY = Math.abs(velY);
        }

        //por Abajo
        if (posY + alto >= heightPanel) {
            velY = - Math.abs(velY);
        }


       
    }

    /**
     * Metodo para mover los Sprites que quiero que desaparezcan del panel (los disparos)
     */
    public void moverSinBordes() {
        posX+= velX;
        posY += velY;
    }
    
    /**
     * Este metodo comprueba si hay colisiones entre mis sprites
     * @param otro
     * @return
     */
    public boolean colisiona(Sprite otro) {
        boolean colisionEjeX = false;
        if (posX < otro.getPosX()) {
            colisionEjeX = posX + ancho >= otro.posX;
        } else {
            colisionEjeX = otro.getPosX() + otro.getAncho() >= posX;
        }

        boolean colisionEjeY = false;
        if (posY < otro.getPosY()) {
            colisionEjeY = posY + alto >= otro.posY;
        } else {
            colisionEjeY = otro.getPosY() + otro.getAlto() >= posY;
        }

        return colisionEjeX && colisionEjeY;

    }
    
    

    /**
     * Aqui calculamos la velocidad aleatoria tambien para los asteoides cuando la nave choca con mi sprite que tiene un porcentaje que puede relentizar considerablemente a mis asteroides
     */
    public static int velocidadRelentizada(){
        int vel;
        Random r = new Random();
        Random rd = new Random();
        int porcentaje = r.nextInt(2);

        vel = rd.nextInt(1); 

        if (porcentaje == 0) {
            vel = vel * (-1);   
        }

        return vel;
        

    }
    
    /**
     * Pinta en mi panel lo que desee, sprites imagenes...
     * @param g
     */
    public void estampar(Graphics g){
        
        g.drawImage(buffer, posX, posY, null);
        

    }


    //GETTERS Y SETTERS
    public BufferedImage getBuffer() {
        return this.buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getAncho() {
        return this.ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }



    public int getVelX() {
        return this.velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }


    public int getVelY() {
        return this.velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }


	

	

}
