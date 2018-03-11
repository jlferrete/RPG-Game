package juego.controladorMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ente.criaturas.Jugador;
import juego.Juego;
import juego.MenuGameOver;

public class ControladorMenuGameOver implements ActionListener {

	private MenuGameOver gameOver;
	private Juego juego;
	private Jugador jugador;

	public ControladorMenuGameOver(MenuGameOver gameOver, Juego juego) {
		this.gameOver = gameOver;
		this.juego = juego;
		gameOver.bNewGame.addActionListener(this);
		gameOver.bLoadGame.addActionListener(this);
		gameOver.bSalir.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.gameOver.bNewGame) {
			juego.inicializaPartidaMapa1();
			
			
//			juego.txVida.setText(String.valueOf(String.valueOf(jugador.getVidaActual())));
//			juego.txExperiencia.setText(String.valueOf(jugador.getExperiencia()));
//			juego.txNivel.setText("1");
					
			gameOver.dispose();
		}

		if (e.getSource() == this.gameOver.bLoadGame) {
			gameOver.dispose();
		}

		if (e.getSource() == this.gameOver.bSalir) {
			System.exit(0);
		}

	}

}
