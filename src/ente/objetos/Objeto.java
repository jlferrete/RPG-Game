package ente.objetos;

import ente.Ente;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Objeto extends Ente {

	protected Sprite sprite;
	protected boolean isRecogiendo;

	public Objeto(Mapa mapa, Sprite sprite, int posicionX, int posicionY) {
		super();

		this.mapa = mapa;
		this.sprite = sprite;
		this.x = posicionX;
		this.y = posicionY;
		this.eliminado = false;
	}

	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarObjeto(x, y, this);
	}

	public Sprite obtenSprite() {
		return sprite;
	}

	public boolean isRecogiendo() {
		return isRecogiendo;
	}

	public void setRecogiendo(boolean isRecogiendo) {
		this.isRecogiendo = isRecogiendo;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}
