package ente.criaturas;

import java.util.Random;

import ente.Ente;
import graficos.Sprite;

public abstract class Criatura extends Ente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false;
	protected boolean isAlive;
	protected boolean isCombatiendo;

	protected int ataque;
	protected int defensa;
	protected int vidaMaxima;
	protected int vidaActual;
	protected int experiencia;
	protected int nivel;

	public Criatura() {
		ataque = 5;
		defensa = 5;
		vidaMaxima = 200;
		vidaActual = 200;
		experiencia = 100;
		nivel = 1;
		isCombatiendo = false;
	}

	private Random rand = new Random(System.nanoTime());

	public int ataque_fisico() {

		Random rnd = new Random();
		int tirada, valor_max;
		valor_max = 20;
		tirada = 1;
		for (int i = 0; i < 1 ; i++) {
			rnd.setSeed(System.nanoTime());
			tirada = (int) (rnd.nextDouble() * valor_max);
		}
		return ataque * tirada;

	}

	public void daño(int impacto) {
		this.vidaActual = vidaActual - impacto;
	}

	public void actualizar() {

	}

	public void mostrar() {

	}

	public void mover(int desplazamientoX, int desplazamientoY) {
		if (desplazamientoX > 0) {
			direccion = 'e';

		}

		if (desplazamientoX < 0) {
			direccion = 'o';

		}

		if (desplazamientoY > 0) {
			direccion = 's';
		}

		if (desplazamientoY < 0) {
			direccion = 'n';
		}

		if (!estaEliminado()) {
			if (!enColision(desplazamientoX, 0)) {
				modificarPosicionX(desplazamientoX);
			}
			if (!enColision(0, desplazamientoY)) {
				modificarPosicionY(desplazamientoY);
			}

		}
	}

	private boolean enColision(int desplazamientoX, int desplazamientoY) {

		boolean colision = false;

		int posicionX = x + desplazamientoX;
		int posicionY = y + desplazamientoY;

		// Los margenes que aplicaremos a la caja de colisiones para una correcta visualización
		int margenIzquierdo = -10;
		int margenDerecho = 21;

		int margenSuperior = -16;
		int margenInferior = 31;

		// Definimos los bordes que corforman la caja de colisiones del personaje
		int bordeIzquierdo = (posicionX + margenDerecho) / sprite.obtenLado();
		int bordeDerecho = (posicionX + margenDerecho + margenIzquierdo) / sprite.obtenLado();
		int bordeSuperior = (posicionY + margenInferior) / sprite.obtenLado();
		int bordeInferior = (posicionY + margenInferior + margenSuperior) / sprite.obtenLado();

		//Comprobamos las 4 esquinas de nuestra caja de colisiones para detectarlas
		if (mapa.obtenerCuadroCatalogo(bordeIzquierdo + bordeSuperior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}

		if (mapa.obtenerCuadroCatalogo(bordeIzquierdo + bordeInferior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}

		if (mapa.obtenerCuadroCatalogo(bordeDerecho + bordeSuperior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}

		if (mapa.obtenerCuadroCatalogo(bordeDerecho + bordeInferior * mapa.obtenerAncho()).esSolido()) {
			colision = true;
		}

		return colision;
	}

	public Sprite obtenSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
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

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isCombatiendo() {
		return isCombatiendo;
	}

	public void setCombatiendo(boolean isCombatiendo) {
		this.isCombatiendo = isCombatiendo;
	}

}
