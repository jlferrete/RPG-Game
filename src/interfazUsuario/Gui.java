package interfazUsuario;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import ente.criaturas.Criatura;

public class Gui extends Canvas {

	private static final long serialVersionUID = 1L;
	Criatura jugador;
//	private Rectangle areaGUI;
//	private Rectangle bordeGUI;

//	private Color grisOscuro;
//	private final BufferStrategy bf = null;

	public Gui(Criatura criatura) {
		this.jugador = criatura;
//		init(this.getGraphics());
	}

	// @Override
	// public void paint(Graphics g) {
	//
	// createBufferStrategy(2);
	// bf = this.getBufferStrategy();
	// // super.paint(g);
	// Graphics2D g2 = null;
	// try {
	// g2 = (Graphics2D) bf.getDrawGraphics();
	// paint(g2);
	// } finally {
	// g2.dispose();
	// }
	// bf.show();
	// }

	// public void paint(Graphics2D g) {
	
	
	public void paint(Graphics g) {

//		grisOscuro = new Color(23, 23, 23);
//		int altoInventario = 80;
//		areaGUI = new Rectangle(0, 0, 250, altoInventario);
//		bordeGUI = new Rectangle(areaGUI.x, areaGUI.y - 1, areaGUI.width, 1);
		

//		DibujoDebug.dibujarRectanguloRelleno(g, areaGUI, grisOscuro);
//		DibujoDebug.dibujarRectanguloRelleno(g, bordeGUI, Color.white);

		// si voy a pintar un ractangula de lado maximo 100 que representa la
		// vida maxima, entonces para pintar la vida actual, el lado sería =
		
		int ladoVida = this.jugador.getVidaActual() * 100 / this.jugador.getVidaMaxima();
		g.setColor(Color.red);
		g.drawString("VIDA", 20, 20);
		g.drawString(
				String.valueOf(this.jugador.getVidaActual()) + " / " + String.valueOf(this.jugador.getVidaMaxima()), 60,
				20);
		g.fillRect(120, 10, ladoVida, 10);

		int ladoExperiencia = this.jugador.getExperiencia();
		g.setColor(Color.blue);
		g.drawString("EXP", 20, 40);
		g.drawString(String.valueOf(this.jugador.getExperiencia()), 60, 40);
		//g.fillRect(120, 30, ladoExperiencia, 10);

		g.setColor(Color.green);
		g.drawString("NIVEL", 20, 60);
		g.drawString(String.valueOf(jugador.getNivel()), 60, 60);

	}

	// private Rectangle areaGUI;
	// private Rectangle bordeGUI;
	//
	// private Color grisOscuro;
	//
	// public MenuInferior(final Jugador jugador){
	//
	// int altoInventario = 100;
	// areaGUI = new Rectangle(0, 0, 200, altoInventario);
	// bordeGUI = new Rectangle(areaGUI.x, areaGUI.y - 1, areaGUI.width, 1);
	//
	// grisOscuro = new Color(23, 23, 23);
	// }
	//
	// public void dibujar(final Graphics g, final Jugador jugador){
	// dibujarAreaInventario(g);
	// }
	//
	// private void dibujarAreaInventario (final Graphics g){
	// DibujoDebug.dibujarRectanguloRelleno(g, areaInventario, grisOscuro);
	// DibujoDebug.dibujarRectanguloRelleno(g, bordeAreaInventario,
	// Color.white);
	//
	// }
}
