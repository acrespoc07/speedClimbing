package principal;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

//Interfaz 

public interface Pantalla {
    public void inicalizarPantalla();
    public void pintarPantalla(Graphics g);
    public void ejecutarFrame();
    public void pulsarRaton(MouseEvent e);
    public void redimensionarPantalla(ComponentEvent e);
	public void tocarTeclado(KeyEvent e);
}
