package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {
	
	protected int ancho;
	protected int alto;

	protected int[] cuadros;
	protected Cuadro[] cuadrosCatalogo;

	private int xTeleport;
	private int yTeleport;

	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		cuadros = new int[ancho * alto];
		generarMapa();
	}

	public Mapa(String ruta, int x, int y) {
		cargarMapa(ruta);
		generarMapa();
		this.xTeleport = x;
		this.yTeleport = y;
	}

	protected void generarMapa() {

	}

	protected void cargarMapa(String ruta) {

	}

	public void actualizar() {

	}

	public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla) {

		pantalla.estableceDiferencia(compensacionX, compensacionY);

		int o = compensacionX >> 5; // Bit shiftting (/32)
		int e = (compensacionX + pantalla.obtenAncho() + Cuadro.LADO) >> 5; // Bit
																			// shiftting
																			// (/32)
		int n = compensacionY >> 5; // Bit shiftting (/32)
		int s = (compensacionY + pantalla.obtenAlto() + Cuadro.LADO) >> 5;
		; // Bit shiftting (/32)

		for (int y = n; y < s; y++) {
			for (int x = o; x < e; x++) {
				// obtenCuadro(x,y).mostrar(x, y, pantalla);
				if (x < 0 || y < 0 || x >= ancho || y >= alto) {
					Cuadro.VACIO.mostrar(x, y, pantalla);
				} else {
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}

	public Cuadro obtenerCuadroCatalogo(int posicion) {
		return cuadrosCatalogo[posicion];
	}

	public int obtenerAncho() {
		return ancho;
	}

	public Cuadro obtenCuadro(final int x, final int y) {

		if (x < 0 || y < 0 || x >= ancho || y >= alto) {
			return Cuadro.VACIO;
		}
		switch (cuadros[x + y * ancho]) {
		case 0:
			return Cuadro.SUELO;
		case 1:
			return Cuadro.PARED;
		case 2:
			return Cuadro.TELE;

		default:
			return Cuadro.VACIO;
		}

	}

	public int getxTeleport() {
		return xTeleport;
	}

	public int getyTeleport() {
		return yTeleport;
	}

}
