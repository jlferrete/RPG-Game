package juego;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.Teclado;
import ente.criaturas.Enemigo;
import ente.criaturas.Jugador;
import ente.objetos.Objeto;
import graficos.Pantalla;
import graficos.Sprite;
import juego.combate.Combate;
import juego.combate.JDCombate;
import juego.controladorMenu.ControladorCombate;
import juego.controladorMenu.ControladorEsc;
import juego.controladorMenu.ControladorMenuGameOver;
import juego.controladorMenu.ControladorMenuTitulo;
import mapa.Mapa;
import mapa.MapaCargado;

//Utilizaremos la clase Juego para generar la ventana. Emplearemos un cavas, ya 
//que permite dibujar muy r�pido con pocos recursos.

public class Juego extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	private static final int ANCHO = 800;
	private static final int ALTO = 600;

	// Usamos la palabra reservada volatile para evitar que pueda
	// ser usada simultaneamente por los dos threads.
	private static volatile boolean enFuncionamiento = false;

	private static final String NOMBRE = "Escape from the Dark Castle";

	private static int aps = 0;
	private static int fps = 0;

	private static JFrame ventana;
	// Debemos emplear los threads para poder delegar parte del trabajo.
	private static Thread thread;
	private static Teclado teclado;
	private static Pantalla pantalla;

	private static MenuTitulo menuTitulo;
	private static ControladorMenuTitulo controladorMenu;

	private static MenuGameOver menuGameOver;
	private static ControladorMenuGameOver controladorGameOver;

	private static MenuEsc menuEsc;
	private static ControladorEsc controladorEsc;

	private static Mapa mapa1;
	private static Mapa mapa2;
	private static Mapa mapaSeleccionado;
	private static Jugador jugador;
	private static Enemigo enemigo;
	private static Enemigo enemigo1;
	private static Objeto espada;
	private static Objeto pocion;
	// private static boolean hayCombate = false;
	// private static boolean hayObjeto = false;

	private static Combate combate;

	private static JDCombate refriega;
	private static ControladorCombate controladorCombate;

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();

	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

	private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono/icono.png"));
	private final JLabel lblVida = new JLabel("Vida");
	private final JTextField txVida = new JTextField();
	private final JLabel lblExperiencia = new JLabel("Experiencia");
	private final JTextField txExperiencia = new JTextField();
	private final JLabel lblNivel = new JLabel("Nivel");
	private final JTextField txNivel = new JTextField();

	private Juego() {
		
		lblExperiencia.setFocusable(false);
		lblNivel.setFocusable(false);
		lblVida.setFocusable(true);
		txExperiencia.setFocusable(false);
		txNivel.setFocusable(false);
		txVida.setFocusable(false);
		
		
		setBounds(-3, 80, 800, 600);

		// Establecemos las dimensiones de alto y ancho para nuestra aplicaci�n
		setPreferredSize(new Dimension(ANCHO, ALTO));

		// pantalla = new Pantalla(800, 600);
		pantalla = new Pantalla(ANCHO, ALTO);
		// mapa = new MapaGenerado(128, 128);
		teclado = new Teclado();
		addKeyListener(teclado);

		mapa1 = new MapaCargado("/mapas/mapa1.png", 28, 24);
		mapa2 = new MapaCargado("/mapas/mapa2.png", 63, 1336);

		// inicializaPartidaMapa1();
		mapaSeleccionado = mapa1;

		jugador = new Jugador(mapaSeleccionado, teclado, Sprite.ABAJO0, 95, 98);

		enemigo = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 150, 98, true);
		enemigo1 = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 130, 200, true);
		espada = new Objeto(mapaSeleccionado, Sprite.ESPADA, 35, 200);
		pocion = new Objeto(mapaSeleccionado, Sprite.POCION, 130, 130);

		combate = null;

		ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// No queremos que el usuario pueda alterar las dimensiones de la
		// ventana
		ventana.setResizable(false);
		ventana.setSize(800, 700);
		
		refriega = new JDCombate(ventana);
		refriega.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		refriega.setResizable(false);
		controladorCombate = new ControladorCombate(this);
		refriega.addWindowListener(controladorCombate);

		// METIENDO GRIDBAGLAYOUT

		txVida.setEditable(false);
		txVida.setColumns(10);
		txExperiencia.setEditable(false);
		txExperiencia.setColumns(10);
		txNivel.setEditable(false);
		txNivel.setColumns(10);

		ventana.getContentPane().add(panel2);
		panel2.setBounds(0, 100, 800, 600);
		panel2.setLayout(null);

		panel2.add(panel1);

		//
		panel1.setBounds(40, 4, 206, 70);
		panel1.setBackground(Color.black);
		panel2.setBackground(Color.black);
		lblVida.setForeground(Color.white);
		lblExperiencia.setForeground(Color.white);
		lblNivel.setForeground(Color.white);

		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[] { 120, 1, 0 };
		gbl_panel1.rowHeights = new int[] { 14, 0, 0, 0 };
		gbl_panel1.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel1.setLayout(gbl_panel1);

		GridBagConstraints gbc_lblVida = new GridBagConstraints();
		gbc_lblVida.insets = new Insets(0, 0, 5, 5);
		gbc_lblVida.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblVida.gridx = 0;
		gbc_lblVida.gridy = 0;
		panel1.add(lblVida, gbc_lblVida);

		GridBagConstraints gbc_txVida = new GridBagConstraints();
		gbc_txVida.insets = new Insets(0, 0, 5, 0);
		gbc_txVida.fill = GridBagConstraints.HORIZONTAL;
		gbc_txVida.gridx = 1;
		gbc_txVida.gridy = 0;
		panel1.add(txVida, gbc_txVida);

		GridBagConstraints gbc_lblExperiencia = new GridBagConstraints();
		gbc_lblExperiencia.anchor = GridBagConstraints.WEST;
		gbc_lblExperiencia.insets = new Insets(0, 0, 5, 5);
		gbc_lblExperiencia.gridx = 0;
		gbc_lblExperiencia.gridy = 1;
		panel1.add(lblExperiencia, gbc_lblExperiencia);

		GridBagConstraints gbc_txExperiencia = new GridBagConstraints();
		gbc_txExperiencia.insets = new Insets(0, 0, 5, 0);
		gbc_txExperiencia.fill = GridBagConstraints.HORIZONTAL;
		gbc_txExperiencia.gridx = 1;
		gbc_txExperiencia.gridy = 1;
		panel1.add(txExperiencia, gbc_txExperiencia);

		GridBagConstraints gbc_lblNivel = new GridBagConstraints();
		gbc_lblNivel.anchor = GridBagConstraints.WEST;
		gbc_lblNivel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNivel.gridx = 0;
		gbc_lblNivel.gridy = 2;
		panel1.add(lblNivel, gbc_lblNivel);

		GridBagConstraints gbc_txNivel = new GridBagConstraints();
		gbc_txNivel.fill = GridBagConstraints.HORIZONTAL;
		gbc_txNivel.gridx = 1;
		gbc_txNivel.gridy = 2;
		panel1.add(txNivel, gbc_txNivel);
		panel2.add(this);

		// ventana.setLayout(new BorderLayout());
		// ventana.add(this, BorderLayout.SOUTH);
		// ventana.add(menuSuperior, BorderLayout.NORTH);

		// FIN SEPARAR VENTANA

		// Este comando sirve para que el contenido se ajuste a nuestra ventana
		// ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		ventana.setIconImage(icono.getImage());

		menuTitulo = new MenuTitulo(ventana);
		controladorMenu = new ControladorMenuTitulo(menuTitulo);

		menuTitulo.setLocationRelativeTo(null);
		menuTitulo.setVisible(true);
		setFocusable(true);

	}

	public void inicializaPartidaMapa1() {
		// mapa1 = new MapaCargado("/mapas/mapa1.png", 28, 24);
		// mapaSeleccionado = new MapaCargado("/mapas/mapa1.png", 28, 24);
		mapaSeleccionado = mapa1;

		jugador = new Jugador(mapaSeleccionado, teclado, Sprite.ABAJO0, 95, 98);

		enemigo = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 150, 98, true);
		enemigo1 = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 130, 200, true);
		espada = new Objeto(mapaSeleccionado, Sprite.ESPADA, 35, 200);
		pocion = new Objeto(mapaSeleccionado, Sprite.ESPADA, 130, 130);
		
		teclado.soltarTeclado();
		
		txVida.setText(String.valueOf(String.valueOf(jugador.getVidaActual())));
		txExperiencia.setText(String.valueOf(jugador.getExperiencia()));
		txNivel.setText(String.valueOf(jugador.getNivel()));

	}

	public void inicializaPartidaMapa2() {
		// mapa2 = new MapaCargado("/mapas/mapa2.png", 63, 1336);
		// mapaSeleccionado = new MapaCargado("/mapas/mapa1.png", 28, 24);
		mapaSeleccionado = mapa2;

		jugador = new Jugador(mapaSeleccionado, teclado, Sprite.ABAJO0, 95, 98);

		enemigo = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 150, 98, true);
		enemigo1 = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 130, 200, true);
		espada = new Objeto(mapaSeleccionado, Sprite.ESPADA, 35, 200);
		pocion = new Objeto(mapaSeleccionado, Sprite.POCION, 130, 130);
		
		teclado.soltarTeclado();

	}

	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.iniciar();

	}

	// Synchronized se usa para evitar que iniciar y detener se solapen. No
	// pueden ejecutarse a la vez. O uno u otro.
	private synchronized void iniciar() {
		enFuncionamiento = true;
		
		txVida.setText(String.valueOf(String.valueOf(this.jugador.getVidaActual())));
		txExperiencia.setText(String.valueOf(this.jugador.getExperiencia()));
		txNivel.setText(String.valueOf(jugador.getNivel()));
		// Creamos el thread y le ponemos un nombre para poder identificarlo
		// posteriormente
		
		thread = new Thread(this, "Graficos");
		thread.start();
	}

	private synchronized void detener() {
		enFuncionamiento = false;
		try {
			// Usamos join para no cerrar el hilo de forma abrupta. Si usaramos
			// stop, podria ser perjudicial. Con Join, se terminan las acciones
			// y
			// luego se cierra.
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Pondremos todo lo necesario para actualizar las variables del juego
	private void actualizar() {
		aps++;
		teclado.actualizar();
		jugador.actualizar();

		if (hayCombate(jugador, enemigo)) {
			if (combate == null) {
				combate = new Combate(jugador, enemigo);
				combate.combatir(refriega);
				
				teclado.soltarTeclado();
				
				refriega.setVisible(true);
				combate = null;
				txVida.setText(String.valueOf(String.valueOf(jugador.getVidaActual())));
				txExperiencia.setText(String.valueOf(jugador.getExperiencia()));

				if (jugador.getExperiencia() < 500) {

					txNivel.setText(String.valueOf(jugador.getNivel()));

				} else if (jugador.getExperiencia() > 500) {

					txNivel.setText(String.valueOf(jugador.getNivel()));

				} else {
					jugador.setNivel(2);
					txNivel.setText(String.valueOf(jugador.getNivel()));
					JOptionPane.showMessageDialog(null, "¡Enhorabuena, has subido de nivel!");
					teclado.soltarTeclado();
				}

			}

		} else if (hayCombate(jugador, enemigo1)) {
			if (combate == null) {
				combate = new Combate(jugador, enemigo1);
				
				teclado.soltarTeclado();
				
				combate.combatir(refriega);
				refriega.setVisible(true);
				combate = null;
				txVida.setText(String.valueOf(String.valueOf(jugador.getVidaActual())));
				txExperiencia.setText(String.valueOf(jugador.getExperiencia()));

				if (jugador.getExperiencia() < 500) {

					txNivel.setText(String.valueOf(jugador.getNivel()));

				} else if (jugador.getExperiencia() > 500) {

					txNivel.setText(String.valueOf(jugador.getNivel()));

				} else {
					jugador.setNivel(2);
					txNivel.setText(String.valueOf(jugador.getNivel()));
					JOptionPane.showMessageDialog(null, "¡Enhorabuena, has subido de nivel!");
					teclado.soltarTeclado();
				}
			}

		}

		if (hayObjeto(jugador, espada)) {

			System.out.println("Recogiendo Objeto");
			teclado.soltarTeclado();
			// TODO VERIFICAR QUE SE APLICA CORRECTAMENTE INCREMENTO DE STATS POR ESPADA
			System.out.println("El ataque era de:" + jugador.getAtaque());
			if (jugador.getAtaque()<9999){
				JOptionPane.showMessageDialog(null, "Has aumentado tu ataque en 15 putos");
				teclado.soltarTeclado();
				jugador.setAtaque(jugador.getAtaque() + 15);
			} else {
				JOptionPane.showMessageDialog(null, "¡¡¡Your attack is over 9000!!! ");
				
			}
			
			pantalla.eliminar(espada);
			System.out.println("El ataque ahora es de:" + jugador.getAtaque());
			teclado.soltarTeclado();

		}
		
		if (hayObjeto(jugador, pocion)) {

			System.out.println("Recogiendo Objeto");
			teclado.soltarTeclado();
			// TODO VERIFICAR QUE SE APLICA CORRECTAMENTE INCREMENTO DE STATS POR ESPADA
			System.out.println("La vida es de:" + jugador.getVidaActual());
			if (jugador.getVidaActual()<jugador.getVidaMaxima()-100){
				jugador.setVidaActual(jugador.getVidaActual() + 100);
				
				txVida.setText(String.valueOf(jugador.getVidaActual()));
			} else {
				jugador.setVidaActual(jugador.getVidaMaxima());
				txVida.setText(String.valueOf(jugador.getVidaActual()));
				
			}
			
			pantalla.eliminar(pocion);
			System.out.println("La vida es de:" + jugador.getVidaActual());
			teclado.soltarTeclado();

		}

		if (hayTeleport()) {
			if (mapaSeleccionado.equals(mapa1)) {

				//TODO Cambié los metodos de inciar partida, ya que iniciaban un nuevo jugador y se perdian los px
				// inicializaPartidaMapa2();

				mapaSeleccionado = mapa2;
				teclado.soltarTeclado();

				enemigo = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 35, 200, true);
				enemigo1 = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 130, 200, true);
				espada = new Objeto(mapaSeleccionado, Sprite.ESPADA, 150, 98);
				pocion = new Objeto(mapaSeleccionado, Sprite.POCION, 130, 130);
				

			} else {

				// inicializaPartidaMapa1();

				mapaSeleccionado = mapa1;
				teclado.soltarTeclado();

				enemigo = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 150, 98, true);
				enemigo1 = new Enemigo(mapaSeleccionado, Sprite.ORCO0, 130, 200, true);
				espada = new Objeto(mapaSeleccionado, Sprite.ESPADA, 35, 200);
				pocion = new Objeto(mapaSeleccionado, Sprite.POCION, 130, 130);

			}

			jugador.setMapa(mapaSeleccionado);
			enemigo.setMapa(mapaSeleccionado);
			enemigo1.setMapa(mapaSeleccionado);
			panel1.setBounds(40, 4, 206, 70);

			jugador.actualizar();
			// ventana.pack();
		}

		if (acabarJuego()) {

			menuGameOver = new MenuGameOver(ventana, jugador);
			controladorGameOver = new ControladorMenuGameOver(menuGameOver, this);
			teclado.soltarTeclado();
			menuGameOver.setLocationRelativeTo(null);
			menuGameOver.setVisible(true);
		}

		if (teclado.esc) {
			System.out.println("Creando menu ESC");
			menuEsc = new MenuEsc(ventana, jugador);
			controladorEsc = new ControladorEsc(menuEsc, this);
			
			teclado.soltarTeclado();
			
			
			menuEsc.setLocationRelativeTo(null);
			menuEsc.setVisible(true);
			
		}

	}

	private boolean acabarJuego() {
		boolean acabar = false;

		if (jugador.getVidaActual() <= 0) {
			acabar = true;
		}

		if ((jugador.getExperiencia() >= 1000)) {
			acabar = true;
		}

		return acabar;
	}

	private boolean hayTeleport() {
		boolean hay = false;

		if ((Math.abs(jugador.obtenerPosicionX() - mapaSeleccionado.getxTeleport()) <= 16)
				&& (Math.abs(jugador.obtenerPosicionY() - mapaSeleccionado.getyTeleport()) <= 16))
			hay = true;
		return hay;
	}

	private boolean hayCombate(Jugador jug, Enemigo enem) {
		// System.out.println("Jugador: X:" + jugador2.obtenerPosicionX() + "
		// Y:" + jugador2.obtenerPosicionY());
		// System.out.println("Enemigo: X:" + enemigo2.obtenerPosicionX() + "
		// Y:" + enemigo2.obtenerPosicionY());
		boolean hayCombate = false;
		if ((Math.abs(jug.obtenerPosicionX() - enem.obtenerPosicionX()) <= 16)
				&& (Math.abs(jug.obtenerPosicionY() - enem.obtenerPosicionY()) <= 16) && enem.isAlive()) {
			hayCombate = true;
			enem.setCombatiendo(true);
			enem.setAlive(false);
		}

		return hayCombate;
	}

	private boolean hayObjeto(Jugador jug, Objeto obj) {
		boolean hayObjeto = false;
		if ((Math.abs(jug.obtenerPosicionX() - obj.obtenerPosicionX()) <= 16)
				&& (Math.abs(jug.obtenerPosicionY() - obj.obtenerPosicionY()) <= 16) && obj.estaEliminado() == false) {
			hayObjeto = true;
			obj.setRecogiendo(true);
			obj.eliminar();
			// Mensaje de aumento de características
			
//			if (jugador.getAtaque()<9999){
//			JOptionPane.showMessageDialog(null, "Has aumentado tu ataque en 15 putos");
//			teclado.soltarTeclado();
//			}
		}

		return hayObjeto;

	}

	// Pondremos todo lo necesario para actualizar los gr�ficos
	private void mostrar() {

		BufferStrategy estrategia = getBufferStrategy();

		if (estrategia == null) {
			createBufferStrategy(3);
			return;
		}

		mapaSeleccionado.mostrar(
				jugador.obtenerPosicionX() - pantalla.obtenAncho() / 2 + jugador.obtenSprite().obtenLado() / 2,
				jugador.obtenerPosicionY() - pantalla.obtenAlto() / 2 + jugador.obtenSprite().obtenLado() / 2,
				pantalla);

		jugador.mostrar(pantalla);
		enemigo.mostrar(pantalla);
		enemigo1.mostrar(pantalla);
		espada.mostrar(pantalla);
		pocion.mostrar(pantalla);

		// Es posible que esto este generando flicker, hay que usar metodo de
		// dobleBuffer
		// menuSuperior.paint(panel1.getGraphics());
		// menuSuperior.paint(this.getGraphics());

		// Para acabar con el parpadeo, me recomienda sacar menu superior del
		// metodo mostrar y ponerlo en un nuevo

		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

//		 for (int i = 0; i < pixeles.length; i++) {
//		 pixeles[i] = pantalla.pixeles[i];
//		 }

		Graphics g = estrategia.getDrawGraphics();

		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);

		// Limpiamos la memoria que utiliza g
		g.dispose();

		estrategia.show();

		fps++;

	}

	public void run() {
		/*
		 * Lo que escribamos aqui dentro, es lo que ejecutara el segundo thread
		 * Hay gente que usa System.currentTimeMilis(); pero no es correcto, ya
		 * que mide el tiempo transcurrido desde 1970. Depende del SO, como en
		 * windows7 o windows8, no se debe usar. Nosotros usaremos otro que toma
		 * como referencia los ciclos de reloj del procesador. El problema que
		 * tiene el nanoTime, aparecio en java 1.5, limita en parte la
		 * compatibilidad de nuestro programa.
		 */
		// NS son los nanosegundos por segundo
		final int NS_POR_SEGUNDO = 1000000000;
		// APS es Actualizaciones por segundo
		final byte APS_OBJETIVO = 60;
		// Descubrir cuantos nanosegundos tienen que transcurrir para actualizar
		// 60 veces. Usamos double para obtener
		// una medicion precisa.
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		// Delta es el tiempo que transcurre hasta una actualizacion y es
		// convenio en el mundo de videojuegos.
		double delta = 0;

		// Hacemos que el foco del teclado est� centrado en la aplicacion

		//TODO CONTROLAR EL FOCO DE LA APLICACIÓN
		requestFocus();

		while (enFuncionamiento) {
			// Debemos limitar la velocidad de estos metodos, para que el juego
			// sea jugable.
			// Esto seria como iniciar el cron�metro.
			final long inicioBucle = System.nanoTime();
			// Iremos sumando pequeñas cantidades de tiempo, hasta que el tiempo
			// sea el necesario para que se ejecute.
			// Aproximadamente, se actualizara unas 60 veces por segundo.
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;

			delta = delta + (tiempoTranscurrido / NS_POR_ACTUALIZACION);

			while (delta >= 1) {
				actualizar();
				delta--;
			}

			mostrar();

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				// Con esto mostramos en la cabecera de la ventana la
				// informacion
				ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps + " || Coord X: "
						+ jugador.obtenerPosicionX() + " || Coord Y: " + jugador.obtenerPosicionY());
				aps = 0;
				fps = 0;
				// Con esto reiniciamos el contador
				referenciaContador = System.nanoTime();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		

	}

	// public void setHayCombate(boolean hayCombate) {
	// Juego.hayCombate = hayCombate;
	// }

	public Enemigo getEnemigo() {
		return enemigo;
	}

	public Enemigo getEnemigo1() {
		return enemigo1;
	}

	public static Jugador getJugador() {
		return jugador;
	}

	public static void setJugador(Jugador jugador) {
		Juego.jugador = jugador;
	}

}