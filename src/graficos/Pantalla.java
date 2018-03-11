package graficos;

import ente.criaturas.Criatura;
import ente.objetos.Objeto;
import mapa.cuadro.Cuadro;

public class Pantalla {

	private final int ancho;
	private final int alto;

	private int diferenciaX;
	private int diferenciaY;

	public final int[] pixeles;

	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];
	}

	// Método para recorrer la pantalla pintandola en negro
	public void limpiar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;
		}
	}

	// TEMPORAL
	// public void mostrar(final int compensacionX, final int compensacionY){
	// //Doble bucle for para recorrer el mapa y pintarlo
	// //Añadimos condición para no pintar fuera de la pantalla
	//
	// for (int y = 0; y < alto; y++) {
	// int posicionY = y + compensacionY;
	// if (posicionY < 0 || posicionY >= alto ){
	// continue;
	// }
	//
	// for (int x = 0; x < ancho; x++){
	// int posicionX = x + compensacionX;
	// if (posicionX < 0 || posicionX >= ancho){
	// continue;
	// }
	//
	// //LOS ERRORES PUEDEN PROCEDER DE AQUI
	// //VIDEO 11 15:43
	// pixeles[posicionX + posicionY * ancho] = Sprite.CAVE.pixeles[(x &
	// MASCARA_SPRITE)
	// + (y & MASCARA_SPRITE) * LADO_SPRITE];
	//
	// }
	// }
	// }
	//
	// FIN TEMPORAL

	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
		compensacionX -= diferenciaX;
		// compensacionX = compensacionX - diferenciaX; es lo mismo que lo de
		// arriba
		compensacionY -= diferenciaY;

		for (int y = 0; y < cuadro.sprite.obtenLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < cuadro.sprite.obtenLado(); x++) {
				int posicionX = x + compensacionX;
				// Controlar que sea mayor o igual, para que no existan bugs
				// visuales
				if (posicionX < -cuadro.sprite.obtenLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;
				}

				// Para arreglar el bug de desaparición de sprites en la zona de
				// valores negativos
				if (posicionX < 0) {
					posicionX = 0;
				}
				pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.obtenLado()];

			}
		}
	}

	public void mostrarJugador(int compensacionX, int compensacionY, Criatura jugador) {
		compensacionX -= diferenciaX;
		// compensacionX = compensacionX - diferenciaX; es lo mismo que lo de
		// arriba
		compensacionY -= diferenciaY;

		for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
				int posicionX = x + compensacionX;
				// Controlar que sea mayor o igual, para que no existan bugs
				// visuales
				if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;
				}

				// Para arreglar el bug de desaparición de sprites en la zona de
				// valores negativos
				if (posicionX < 0) {
					posicionX = 0;
				}
				// pixeles[posicionX + posicionY * ancho] =
				// jugador.obtenSprite().pixeles[x
				// + y * jugador.obtenSprite().obtenLado()];

				// Estamos realizando un Chroma. Cargamos todos los colores
				// salvo el color del fondo

				int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
				if (colorPixelJugador != 0xffff00ff) {
					pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
				}
			}
		}
	}

	public void mostrarEnemigo(int compensacionX, int compensacionY, Criatura enemigo) {
		compensacionX -= diferenciaX;
		// compensacionX = compensacionX - diferenciaX; es lo mismo que lo de
		// arriba
		compensacionY -= diferenciaY;

		for (int y = 0; y < enemigo.obtenSprite().obtenLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < enemigo.obtenSprite().obtenLado(); x++) {
				int posicionX = x + compensacionX;
				// Controlar que sea mayor o igual, para que no existan bugs
				// visuales
				if (posicionX < -enemigo.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;
				}

				// Para arreglar el bug de desaparición de sprites en la zona de
				// valores negativos
				if (posicionX < 0) {
					posicionX = 0;
				}
				// pixeles[posicionX + posicionY * ancho] =
				// jugador.obtenSprite().pixeles[x
				// + y * jugador.obtenSprite().obtenLado()];

				// Estamos realizando un Chroma. Cargamos todos los colores
				// salvo el color del fondo

				int colorPixelEnemigo = enemigo.obtenSprite().pixeles[x + y * enemigo.obtenSprite().obtenLado()];
				if (colorPixelEnemigo != 0xffff00ff) {
					pixeles[posicionX + posicionY * ancho] = colorPixelEnemigo;
				}
			}
		}
	}

	public void mostrarObjeto(int compensacionX, int compensacionY, Objeto objeto) {
		compensacionX -= diferenciaX;
		// compensacionX = compensacionX - diferenciaX; es lo mismo que lo de
		// arriba
		compensacionY -= diferenciaY;

		for (int y = 0; y < objeto.obtenSprite().obtenLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < objeto.obtenSprite().obtenLado(); x++) {
				int posicionX = x + compensacionX;
				// Controlar que sea mayor o igual, para que no existan bugs
				// visuales
				if (posicionX < -objeto.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;
				}

				// Para arreglar el bug de desaparición de sprites en la zona de
				// valores negativos
				if (posicionX < 0) {
					posicionX = 0;
				}
				// pixeles[posicionX + posicionY * ancho] =
				// jugador.obtenSprite().pixeles[x
				// + y * jugador.obtenSprite().obtenLado()];

				// Estamos realizando un Chroma. Cargamos todos los colores
				// salvo el color del fondo

				int colorPixelObjeto = objeto.obtenSprite().pixeles[x + y * objeto.obtenSprite().obtenLado()];
				if (colorPixelObjeto != 0xffff00ff) {
					pixeles[posicionX + posicionY * ancho] = colorPixelObjeto;
				}
			}
		}
	}

	public void estableceDiferencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}

	public int obtenAncho() {
		return ancho;
	}

	public int obtenAlto() {
		return alto;
	}

	public void eliminar(Objeto obj) {
		obj.setSprite(Sprite.ROSA);
	}

}
