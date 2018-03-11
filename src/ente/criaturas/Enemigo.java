package ente.criaturas;

import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Enemigo extends Criatura {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Enemigo(Mapa mapa, Sprite sprite, int posicionX, int posicionY, boolean isAlive)  {
		super();
		
		this.mapa=mapa;
		this.sprite = sprite;
		this.x = posicionX;
		this.y = posicionY;
		this.isAlive = isAlive;
	}

	
	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarEnemigo(x, y, this);
	}
	
	
	
	

}
