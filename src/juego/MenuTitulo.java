package juego;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class MenuTitulo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JFrame padre;
	

	Fondo fondo1 = new Fondo();

	public static JButton bNewGame = new JButton("Nueva Partida");
	public static JButton bLoadGame = new JButton("Cargar Partida");
	public static JButton bSalir = new JButton("Salir");

	public MenuTitulo(JFrame padre) {
		super(padre,true);
		this.padre = padre;
				
		setUndecorated(true);
		fondo1.setBackground("titulo.png");
		add(fondo1);
		setSize(800, 700);
		fondo1.add(bNewGame);
		fondo1.add(bLoadGame);
		fondo1.add(bSalir);
		//this.addWindowListener(controlador);
		
	}

	

}
