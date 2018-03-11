package juego.combate;

import java.awt.TextArea;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class JDCombate extends JDialog {

	private static final long serialVersionUID = 1L;
	// todos los componentes graficos
	public TextArea texto;
	
	
	public JDCombate(JFrame padre) {
		super(padre, true);
		
		// construyo los componentes
		 texto = new TextArea();
		 
		 inicializaVista();

		// poner aqui la interfaz grafica
	}

	private void inicializaVista() {
		
		// añado y distribuyo los componentes
		setBounds(200, 300, 300, 400);
		this.add(texto);
		texto.setEditable(false);
		
		
	}
}
