package juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyList extends JFrame implements KeyListener {

	private int barra;
	public KeyList(int barrax) {
		barra=barrax;
	}
	public void left() {
		barra=barra-40;		
	}
	public void rigth() {
		barra=barra+40;		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			left();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rigth();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	public int getBarrax() {
		repaint();
		return barra;
		
	}

}
