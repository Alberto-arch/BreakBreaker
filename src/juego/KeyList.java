package juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyList extends JFrame implements KeyListener {

	private int barra;
	public KeyList(int barrax) {
		barra=barrax;
	}
	
	public int getBarrax() {
		repaint();
		return barra;
		
	}

}
