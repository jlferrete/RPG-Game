package juego.combate;

import ente.criaturas.Enemigo;
import ente.criaturas.Jugador;

public class Combate {

	private Jugador jugador;
	private Enemigo enemigo;

	int aleatorio, impacto;
	boolean combatiente1;
	boolean combatiente2;

	public Combate(Jugador jugador, Enemigo actualEnemigo) {
		this.jugador = jugador;
		this.enemigo = actualEnemigo;
		combatiente1 = true;
		combatiente2 = true;
	}

	public void combatir(JDCombate refriega) {

		refriega.texto.setText("");
		refriega.texto.append("REPORTE DE COMBATE" + '\n');
		refriega.texto.append("" + '\n');
		refriega.texto.append("Jugador tiene una vida de: " + jugador.getVidaActual() + '\n');
		refriega.texto.append("Enemigo tiene una vida de: " + enemigo.getVidaActual() + '\n');
		refriega.texto.append("" + '\n');
		refriega.texto.append("Comienza el combate" + '\n');

		do {
			if (jugador.getVidaActual() > 0) {
				impacto = jugador.ataque_fisico();
				refriega.texto.append("Jugador ha realizado un ataque de: " + impacto + '\n');

				if ((impacto - enemigo.getDefensa()) > 0) {
					enemigo.daño(impacto - enemigo.getDefensa());
					refriega.texto
							.append("El daño producido por Jugador es de : " + (impacto - enemigo.getDefensa()) + '\n');
				} else {
					enemigo.daño(0);
					refriega.texto.append("El daño producido por Jugador es de : 0" + '\n');
				}
			}

			if (enemigo.getVidaActual() > 0) {
				impacto = enemigo.ataque_fisico();
				refriega.texto.append("Enemigo ha realizado un ataque de: " + impacto + '\n');

				if ((impacto - jugador.getDefensa()) > 0) {
					jugador.daño(impacto - jugador.getDefensa());
					refriega.texto
							.append("El daño producido por Enemigo es de : " + (impacto - jugador.getDefensa()) + '\n');
				} else {
					jugador.daño(0);
					refriega.texto.append("El daño producido por Enemigo es de : 0" + '\n');
				}

			}

			if (jugador.getVidaActual() <= 0) {
				combatiente1 = false;
				refriega.texto.append("El jugador ha sido derrotado" + '\n');
			}

			if (enemigo.getVidaActual() <= 0) {
				combatiente2 = false;
				refriega.texto.append("El Enemigo ha sido derrotado" + '\n');

				// TODO Controlar esta linea "Cálculo Experiencia"

				System.out.println("La experiencia era de:" + jugador.getExperiencia());
				jugador.setExperiencia(jugador.getExperiencia() + 100);

				System.out.println("La experiencia ahora es de:" + jugador.getExperiencia());

			}

			refriega.texto.append("" + '\n');
			refriega.texto.append("Jugador tiene una vida de: " + jugador.getVidaActual() + '\n');
			refriega.texto.append("Enemigo tiene una vida de: " + enemigo.getVidaActual() + '\n');
			refriega.texto.append("" + '\n');

		} while (combatiente1 && combatiente2);
	}
}
