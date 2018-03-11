package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] pixeles;

	public MapaCargado(String ruta, int x, int y) {
		super(ruta, x, y);

	}

	protected void cargarMapa(String ruta) {
		try {
			BufferedImage imagen = ImageIO.read(MapaCargado.class.getResource(ruta));

			ancho = imagen.getWidth();
			alto = imagen.getHeight();

			cuadrosCatalogo = new Cuadro[ancho * alto];
			pixeles = new int[ancho * alto];

			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void generarMapa() {
		for (int i = 0; i < pixeles.length; i++) {
			switch (pixeles[i]) {
			// Ponemos ff en el color, para opacidad al maximo
			// del canal alpha
			case 0xff616173:
				cuadrosCatalogo[i] = Cuadro.SUELO;
				continue;
			case 0xff101098:
				cuadrosCatalogo[i] = Cuadro.PARED;
				continue;
			case 0xff336600:
				cuadrosCatalogo[i] = Cuadro.TELE;
				continue;

			default:
				cuadrosCatalogo[i] = Cuadro.VACIO;

			}
		}
	}

}
