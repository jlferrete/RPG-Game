package graficos;

import java.io.Serializable;

public final class Sprite implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int lado;

	private int x;
	private int y;

	public int[] pixeles;
	private HojaSprites hoja;

	// Coleccion de sprites del personaje (Ancho, columna, fila, transformacion_sprite, hojaSprite)
	public static final Sprite ABAJO0 = new Sprite(32, 0, 6, 0, HojaSprites.jugador);
	public static final Sprite ABAJO1 = new Sprite(32, 4, 6, 0, HojaSprites.jugador);
	public static final Sprite ABAJO2 = new Sprite(32, 8, 6, 0, HojaSprites.jugador);

	public static final Sprite ARRIBA0 = new Sprite(32, 0, 4, 0, HojaSprites.jugador);
	public static final Sprite ARRIBA1 = new Sprite(32, 3, 4, 0, HojaSprites.jugador);
	public static final Sprite ARRIBA2 = new Sprite(32, 8, 4, 0, HojaSprites.jugador);

	public static final Sprite DERECHA0 = new Sprite(32, 0, 7, 0, HojaSprites.jugador);
	public static final Sprite DERECHA1 = new Sprite(32, 4, 7, 0, HojaSprites.jugador);
	public static final Sprite DERECHA2 = new Sprite(32, 8, 7, 0, HojaSprites.jugador);

	public static final Sprite IZQUIERDA0 = new Sprite(32, 0, 5, 0, HojaSprites.jugador);
	public static final Sprite IZQUIERDA1 = new Sprite(32, 4, 5, 0, HojaSprites.jugador);
	public static final Sprite IZQUIERDA2 = new Sprite(32, 8, 5, 0, HojaSprites.jugador);

	// Fin de la coleccion

	// Coleccion de sprites de enemigos
	public static final Sprite ORCO0 = new Sprite(32, 0, 0, 0, HojaSprites.orco);
	public static final Sprite ORCO1 = new Sprite(32, 1, 0, 0, HojaSprites.orco);

	// Fin de la coleccion

	// Coleccion de Sprites de escenario
	public static final Sprite VACIO = new Sprite(32, 0);
	public static final Sprite SUELO = new Sprite(32, 0, 0, 0, HojaSprites.cave);
	public static final Sprite PARED = new Sprite(32, 1, 0, 0, HojaSprites.cave);
	public static final Sprite TELE = new Sprite(32, 2, 0, 0, HojaSprites.cave);

	// Fin de la coleccion

	// Coleccion de Sprites de objetos

	public static final Sprite ESPADA = new Sprite(32, 0, 0, 0, HojaSprites.objeto);
	public static final Sprite ROSA = new Sprite(32, 1, 0, 0, HojaSprites.objeto);
	public static final Sprite POCION = new Sprite(32, 2, 0, 0, HojaSprites.objeto);
	

	// Fin de la coleccion

	public Sprite(final int lado, final int columna, final int fila, int version, final HojaSprites hoja) {
		this.lado = lado;

		pixeles = new int[lado * lado];

		// Si queremos cargar la columna 1, empezaremos en el pixel 32.
		// Multiplicamos
		// por 32 el valor de la columna, lo mismo con la fila.

		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;

		cargarSprite(version);

		// Utilizaremos un bucle for para recorrer nuestra array. No usamos una
		// bidimensional
		// porque son mas lentas para trabajar con graficos

		// ANALIZAR ESTO A FONDO
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
			}

		}
	}

	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];

		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
	}

	public int obtenLado() {
		return lado;
	}

	private void cargarSprite(int version) {
		if (version == 0) {
			cargaNormal();
		} else {
			cargaManipulada(version);
		}
	}

	private void cargaNormal() {
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
			}
		}
	}

	// Estos métodos sirven para ofrecer versiones espejo de los tile a partir
	// de un unico tile
	private void cargaManipulada(int version) {
		int[] pixelesTemporales = iniciarPixelesTemporales();

		switch (version) {
		case 1:
			invertirX(pixelesTemporales);
			break;
		case 2:
			invertirY(pixelesTemporales);
			break;
		case 3:
			invertirXY(pixelesTemporales);
			break;
		case 4:
			rotar90I(pixelesTemporales);
			break;
		case 5:
			rotar90D(pixelesTemporales);
			break;

		case 6:
			rotarI90InvertirY(pixelesTemporales);
			break;
		case 7:
			rotarD90InvertirY(pixelesTemporales);
			break;

		default:
			cargaNormal();

		}
	}

	private int[] iniciarPixelesTemporales() {
		int[] pixelesTemporales = new int[lado * lado];
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixelesTemporales[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
			}
		}
		return pixelesTemporales;
	}

	// Caso 1
	private void invertirX(int[] pixelesTemporales) {
		int i = 0;
		for (int y = 0; y < lado; y++) {
			for (int x = lado - 1; x < -1; x--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}

	}

	// Caso 2
	private void invertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int y = lado - 1; y > -1; y--) {
			for (int x = 0; x < lado; x++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}

	}

	// Caso 3
	private void invertirXY(int[] pixelesTemporales) {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = pixelesTemporales[pixelesTemporales.length - 1 - i];
		}
	}

	// Caso 4
	private void rotar90I(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// Caso 5
	private void rotar90D(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = lado - 1; y > -1; y--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// Caso 6
	private void rotarI90InvertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// Caso 7
	private void rotarD90InvertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = lado - 1; y > -1; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}
}
