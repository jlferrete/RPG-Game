package juego;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ente.criaturas.Jugador;

public class MenuGameOver extends JDialog {

	private static final long serialVersionUID = 1L;

	private JFrame padre;
	private Jugador jugador;
	private Fondo fondo2;

	public static JButton bNewGame = new JButton("Nueva Partida");
	public static JButton bLoadGame = new JButton("Cargar Partida");
	public static JButton bSalir = new JButton("Salir");

	public MenuGameOver(JFrame padre, Jugador jugador) {

		super(padre, true);
		this.padre = padre;
		this.jugador = jugador;
		fondo2 = new Fondo();
		setUndecorated(true);

		if (jugador.getVidaActual() <= 0) {
			fondo2.setBackground("gameover.png");
		}

		if ((jugador.getVidaActual() > 0) && (jugador.getExperiencia() >= 1000)) {
			fondo2.setBackground("gamepass.png");
		}

		add(fondo2);
		setSize(640, 480);
		fondo2.add(bNewGame);
		fondo2.add(bLoadGame);
		fondo2.add(bSalir);
	}
}
