package juego.combate;

import java.util.Random;

public class personaje {
	
	protected int ataque;
	protected int defensa;
	protected int vidaMaxima;
	protected int vidaActual;
	protected int experiencia;
	protected int nivel;
	
	private Random rand = new Random(System.nanoTime());
	
	public int ataque_fisico(){
		
		Random rnd=new Random();
		int tirada, valor_max;
		valor_max= 20;
		tirada = 1;
		for (int i = 0; i < 1; i++)
		{
			rnd.setSeed(System.nanoTime());
			tirada= (int)(rnd.nextDouble()*valor_max);
		}
		return ataque*tirada;
		
	}
	
	public void daño(int impacto){
		this.vidaActual = vidaActual - impacto;
	}
	
	
		
	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public int getDefensa() {
		return defensa;
	}
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	public int getVidaMaxima() {
		return vidaMaxima;
	}
	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}
	public int getVidaActual() {
		return vidaActual;
	}
	public void setVidaActual(int vidaActual) {
		this.vidaActual = vidaActual;
	}
	public int getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	

}
