package principal;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import pantallas.PantallaInicio;
import pantallas.PantallaJuego;

public class PanelJuego extends JPanel implements Runnable, MouseListener, MouseMotionListener, ComponentListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private Pantalla pantallaActual;

    /**
     * Constructor
     */
    public PanelJuego() {
        // incio como predeterminada la Pantalla de inciio
        pantallaActual = new PantallaInicio(this);
        this.addComponentListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        new Thread(this).start();
    }
    // Método que se llama automáticamente para pintar el componente.
    @Override
    public void paintComponent(Graphics g) {
        pantallaActual.pintarPantalla(g);
    }
    /**
     * El run del hilo, se estara ejecutando siempre
     */
    @Override
    public void run() {
        // antes de inciar el hilo se inicializa la pantalla
        pantallaActual.inicalizarPantalla();
        while (true) {
            pantallaActual.ejecutarFrame();
            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        pantallaActual.pulsarRaton(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        pantallaActual.redimensionarPantalla(e);
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    /**
     * hace el cambio de pantalla
     * 
     * @param nuevaPantalla
     */
    public void cambiarPantalla(Pantalla nuevaPantalla) {
        nuevaPantalla.inicalizarPantalla();
        pantallaActual = nuevaPantalla;
    }

    /**
     * esta clase hace de puente para enviar el tiempo de pantalla de juego a la de
     * victoria
     */
    public double enviarTiempo() {
        return PantallaJuego.darTiempo();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        pantallaActual.tocarTeclado(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

}
