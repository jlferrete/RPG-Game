package ente.criaturas;

import java.io.Serializable;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Jugador extends Criatura implements Serializable {

	private static final long serialVersionUID = 1L;

	private Teclado teclado;

	private int animacion;

	public Jugador(Mapa mapa, Teclado teclado, Sprite sprite) {
		super();
		this.mapa = mapa;
		this.teclado = teclado;
		this.sprite = sprite;

	}

	public Jugador(Mapa mapa, Teclado teclado, Sprite sprite, int posicionX, int posicionY) {
		super();
		this.mapa = mapa;
		this.teclado = teclado;
		this.sprite = sprite;
		this.x = posicionX;
		this.y = posicionY;
		inicializa();

	}

	public void inicializa() {
		ataque = 15;
		defensa = 5;
		vidaMaxima = 500;
		vidaActual = 500;
		experiencia = 0;
		nivel = 1;
		isCombatiendo = false;
	}

	public void actualizar() {

		int desplazamientoX = 0;
		int desplazamientoY = 0;

		// Control del numero de animaciones mostradas ya que int tiene un
		// tamaño maximo
		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}

		if (teclado.arriba) {
			desplazamientoY--;
		}

		if (teclado.abajo) {
			desplazamientoY++;
		}

		if (teclado.izquierda) {
			desplazamientoX--;
		}

		if (teclado.derecha) {
			desplazamientoX++;
		}

		if (teclado.esc) {
			// System.out.println("pulsando escape para mostrar menu");

		}

		if (desplazamientoX != 0 || desplazamientoY != 0) {
			mover(desplazamientoX, desplazamientoY);
			enMovimiento = true;

		} else {
			enMovimiento = false;

		}

		// TODO Revisar la animaciones de personaje

		// Para modificar la velocidad a la que se alternan las animaciones, es
		// recomendable
		// dividir entre un numero par y comprobar que su resto es mayor que la
		// mitad de dicho numero.
		// A mayor número, la animacion se hará mas lenta.

		// Si quisieramos meter mas animaciones, por ejemplo 3, necesitariamos
		// numeros divisibles entre 3.
//		if (enMovimiento) {
//			int resto = animacion % 30;
//			if (resto > 10 && resto <= 20) {
//				sprite = Sprite.ARRIBA1;
//			} else if (resto > 20) {
//				sprite = Sprite.ARRIBA2;
//			} else {
//				sprite = Sprite.ARRIBA3;
//			}
//		}

		if (direccion == 'n') {
			sprite = Sprite.ARRIBA0;
			if (enMovimiento) {
				if (animacion % 30 > 15) {
					sprite = Sprite.ARRIBA1;
				} else {
					sprite = Sprite.ARRIBA2;
				}
			}
		}

		if (direccion == 's') {
			sprite = Sprite.ABAJO0;
			if (enMovimiento) {
				if (animacion % 30 > 15) {
					sprite = Sprite.ABAJO1;
				} else {
					sprite = Sprite.ABAJO2;
				}
			}
		}

		if (direccion == 'o') {
			sprite = Sprite.IZQUIERDA0;
			if (enMovimiento) {
				if (animacion % 30 > 15) {
					sprite = Sprite.IZQUIERDA1;
				} else {
					sprite = Sprite.IZQUIERDA2;
				}
			}
		}

		if (direccion == 'e') {
			sprite = Sprite.DERECHA0;
			if (enMovimiento) {
				if (animacion % 30 > 15) {
					sprite = Sprite.DERECHA1;
				} else {
					sprite = Sprite.DERECHA2;
				}
			}
		}

	}

	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarJugador(x, y, this);
	}
}
