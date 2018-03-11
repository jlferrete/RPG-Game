package ente;

import java.io.Serializable;

import mapa.Mapa;

public abstract class Ente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	protected boolean eliminado = false;
	protected Mapa mapa;

	public void actualizar() {

	}

	public void mostrar() {

	}

	public void eliminar() {
		eliminado = true;
	}

	public int obtenerPosicionX() {
		return x;
	}

	public void modificarPosicionX(int desplazamientoX) {
		x += desplazamientoX;
	}

	public int obtenerPosicionY() {
		return y;
	}

	public void modificarPosicionY(int desplazamientoY) {
		y += desplazamientoY;
	}

	public boolean estaEliminado() {
		return eliminado;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

}
