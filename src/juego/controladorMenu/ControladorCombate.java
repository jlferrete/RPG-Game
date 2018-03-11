package juego.controladorMenu;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import graficos.Sprite;
import juego.Juego;

public class ControladorCombate implements WindowListener {

	private Juego juego;

	public ControladorCombate(Juego juego) {
		this.juego = juego;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// cerramos y controlamos
		//this.juego.setHayCombate(false);

		if (this.juego.getEnemigo().isCombatiendo()) {

			this.juego.getEnemigo().setAlive(false);
			this.juego.getEnemigo().setSprite(Sprite.ORCO1);
			this.juego.getEnemigo().setCombatiendo(false);
			
		} else if (this.juego.getEnemigo1().isCombatiendo()) {
			this.juego.getEnemigo1().setAlive(false);
			this.juego.getEnemigo1().setSprite(Sprite.ORCO1);
			this.juego.getEnemigo1().setCombatiendo(false);
			
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
