package juego.controladorMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import juego.Juego;
import juego.MenuEsc;

public class ControladorEsc implements ActionListener {

	private static final String FILENAME = "savedplayer.001";
	private MenuEsc menuEsc;
	private Juego juego;

	public ControladorEsc(MenuEsc esc, Juego juego) {
		this.menuEsc = esc;
		this.juego = juego;
		esc.bSaveGame.addActionListener(this);
		esc.bReturnGame.addActionListener(this);
		esc.bSalir.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.menuEsc.bSaveGame) {
			// Creamos el archivo (si exist�a anteriormente se borrar� y crear�
			// de nuevo)
			FileOutputStream fs;
			try {
				fs = new FileOutputStream(FILENAME);
				// Esta clase tiene el m�todo writeObject() que necesitamos
				ObjectOutputStream os = new ObjectOutputStream(fs);
				// El m�todo writeObject() serializa el objeto y lo escribe en
				// el archivo
				os.writeObject(juego.getJugador());
				// cerramos el stream
				os.close();
				JOptionPane.showMessageDialog(null, "Archivo creado correctamente");

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			menuEsc.setVisible(false);
			menuEsc.dispose();
			
		}

		if (e.getSource() == this.menuEsc.bReturnGame) {
			
			menuEsc.setVisible(false);
			menuEsc.dispose();
//			menuEsc.setFocusable(false);
			
//			try {
//
//				Jugador data;
//				FileInputStream fis = new FileInputStream(FILENAME);
//				ObjectInputStream ois = new ObjectInputStream(fis);
//				data = (Jugador) ois.readObject(); // El m�todo readObject()
//													// recupera el objeto
//				ois.close();
//				this.juego.setJugador(data);
//				
//				
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (ClassNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
		}

		if (e.getSource() == this.menuEsc.bSalir) {
			System.exit(0);
		}

	}

}
