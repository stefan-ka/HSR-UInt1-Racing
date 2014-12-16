package ch.hsr.skapfere.uint1.racing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Racing extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1169331112688629681L;
	private Car raceCar;
	private int[] keys;
	private Dimension screenSize;
	private Timer timer;

	public Racing() {
		init();
	}

	private void init() {
		screenSize = new Dimension(600, 600);
		setPreferredSize(screenSize);

		raceCar = new Car();
		raceCar.move(300, 300);

		registerKeyListener();

		timer = new Timer(15, this);
		timer.start();
	}

	private void registerKeyListener() {
		keys = new int[256];
		Arrays.fill(keys, 0); // 0 = key is up

		KeyboardFocusManager kfm = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				switch (e.getID()) {
				case KeyEvent.KEY_PRESSED:
					keys[e.getKeyCode()] = 1;
					break;
				case KeyEvent.KEY_RELEASED:
					keys[e.getKeyCode()] = 0;
					break;
				default:
					break;
				}
				return false;
			}
		});

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		raceCar.paintComponent(g2d);
	}

	private void moveCar() {
		keyProcessing();
		raceCar.move();
		controlRaceCarPosition();
	}

	private void controlRaceCarPosition() {
		/*
		 * �berpr�fen Sie ob das Fahrzeug den sichtbaren Bereich des Bildschirm
		 * nicht verl�sst
		 */

		/*
		 * �berpr�fen ob sich das Fahrzueg noch auf der Strecke befindet
		 */

	}

	private void keyProcessing() {

		if (keys[KeyEvent.VK_UP] == 1) {
			raceCar.accelerate();
		}
		if (keys[KeyEvent.VK_DOWN] == 1) {
			raceCar.slowdown();
		}
		if (keys[KeyEvent.VK_RIGHT] == 1) {
			raceCar.turnRight();
		}
		if (keys[KeyEvent.VK_LEFT] == 1) {
			raceCar.turnLeft();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		moveCar();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public static void createAndShowGUI() {
		JFrame f = new JFrame("Racing");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Racing());
		f.pack();
		f.setVisible(true);
	}

}
