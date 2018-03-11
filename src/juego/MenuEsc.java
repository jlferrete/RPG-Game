package juego;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ente.criaturas.Jugador;

public class MenuEsc extends JDialog {

	private JFrame padre;
	private Jugador jugador;
	private Fondo fondo2;

	public static JButton bSaveGame = new JButton("Guardar Partida");
	public static JButton bReturnGame = new JButton("Volver a Partida");
	public static JButton bSalir = new JButton("Salir");

	public MenuEsc(JFrame padre, Jugador jugador) {

		super(padre, true);
		this.padre = padre;
		this.jugador = jugador;
		fondo2 = new Fondo();
		setUndecorated(true);
		fondo2.setBackground("pause.png");
		add(fondo2);
		setSize(640, 480);
		fondo2.add(bReturnGame);
		fondo2.add(bSaveGame);
		fondo2.add(bSalir);
	}

}