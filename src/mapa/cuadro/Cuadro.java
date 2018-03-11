package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
	public int x;
	public int y;
	
	public Sprite sprite;
	
	private boolean solido;
	
	public static final int LADO = 32;
	
	//Coleccion de cuadros
	public static final Cuadro VACIO = new Cuadro(Sprite.VACIO, true);
	public static final Cuadro SUELO = new Cuadro(Sprite.SUELO);
	public static final Cuadro PARED = new Cuadro(Sprite.PARED, true);
	public static final Cuadro TELE = new Cuadro(Sprite.TELE);
	
	// Fin de la coleccion de cuadros
	public Cuadro(Sprite sprite){
		this.sprite = sprite;
		solido = false;
	}
	
	public Cuadro(Sprite sprite, boolean solido){
		this.sprite = sprite;
		this.solido = solido;
	}
	
	public void mostrar(int x, int y, Pantalla pantalla){
		pantalla.mostrarCuadro(x<<5, y<<5, this); //Bit strafing *32
	}
	
	public boolean esSolido(){
		return solido;
	}
}
