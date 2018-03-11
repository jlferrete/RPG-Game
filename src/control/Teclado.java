package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public final class Teclado implements KeyListener, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Esto hace referencia al valor de tecla m�s alto
	//que vamos a utilizar
	private final static int numeroTeclas=120;
	private boolean[] teclas = new boolean [numeroTeclas];
	
	public boolean arriba;
	public boolean abajo;
	public boolean izquierda;
	public boolean derecha;
	public boolean esc;
	
	public void actualizar(){

		arriba=teclas[KeyEvent.VK_W];
		abajo=teclas[KeyEvent.VK_S];
		izquierda=teclas[KeyEvent.VK_A];
		derecha=teclas[KeyEvent.VK_D];
		esc=teclas[KeyEvent.VK_ESCAPE];
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()]=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()]=false;
	}

	public void soltarTeclado(){
		for (int i=0; i< numeroTeclas; i++)
			teclas[i]=false;
	}
}
