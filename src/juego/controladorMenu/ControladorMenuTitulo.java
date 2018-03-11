package juego.controladorMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import ente.criaturas.Jugador;
import juego.Juego;
import juego.MenuTitulo;
import sonido.Bso;


public class ControladorMenuTitulo implements ActionListener {
	private MenuTitulo menu;
	private static Jugador jugador;
	private static final String FILENAME = "savedplayer.001";
	private Juego juego;
	
//	private final JTextField txVida;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == MenuTitulo.bNewGame) {
			new Bso();
			menu.dispose();

		}

		if (e.getSource() == MenuTitulo.bLoadGame) {
			try {

				Jugador data;
				FileInputStream fis = new FileInputStream(FILENAME);
				ObjectInputStream ois = new ObjectInputStream(fis);
				data = (Jugador) ois.readObject(); // El m�todo readObject()
													// recupera el objeto
				
				this.juego.setJugador(data);
				ois.close();
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						
			new Bso();
			menu.dispose();
		}

		if (e.getSource() == MenuTitulo.bSalir) {
			System.exit(0);
		}

	}

	public ControladorMenuTitulo(MenuTitulo menu) {
		this.menu = menu;
		menu.bNewGame.addActionListener(this);
		menu.bLoadGame.addActionListener(this);
		menu.bSalir.addActionListener(this);
	}

}
