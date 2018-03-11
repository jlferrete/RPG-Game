package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class HojaSprites implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Vamos a crear una array de int, para almacenar los pixeles de nuestra
	// imagen
	public final int ancho;
	public final int alto;
	public final int[] pixeles;

	// Coleccion de hojas de sprites
	public static HojaSprites cave = new HojaSprites("/texturas/cave.png", 320, 320);
	public static HojaSprites jugador = new HojaSprites("/texturas/jugador1.png", 320, 320);
	public static HojaSprites orco = new HojaSprites("/texturas/enemigo.png", 320, 320);
	public static HojaSprites objeto = new HojaSprites("/texturas/objetos.png", 320, 320);
	
	
	// Fin de la colección

	// Creamos el constructor de la hoja de sprites
	public HojaSprites(final String ruta, final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];

		// Hemos creado una imagen y le hemos atribuido el valor de una ruta
		BufferedImage imagen;
		try {
			imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
			// Usamos segundo metodo sugerido para imagen.getRGB
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int obtenAncho() {
		return ancho;
	}

}