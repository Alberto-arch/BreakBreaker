package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BreakBreaker extends JFrame implements KeyListener {
	private JPanel contentPane;
	private int ballx,bally;
	private int barray;
	private int barrax;
	private Blockes brick;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() throws NullPointerException, NumberFormatException {
				try {
					BreakBreaker frame = new BreakBreaker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public BreakBreaker() {
		setTitle("BreakBreaker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setExtendedState(MAXIMIZED_BOTH);
		
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.setBackground(Color.black);
		
		bally=getHeight();
		ballx=getWidth();
		
		barrax=getWidth()/2;
		barray=getHeight();
		
		brick=new Blockes(20,10);
		addKeyListener(this);
		
	}
   
	public void paint(Graphics g) {
    	g.setColor(Color.black);
        g.fillRect(getWidth()-getWidth(),getHeight()-getHeight(),getWidth(),getHeight());
        
    	brick.paint((Graphics) g);
    	g.setColor(Color.yellow);
        g.fillRect (barrax,barray-80,100,20);
                
        g.setColor(Color.white);
        g.fillOval(ballx,bally,20,20);
        
        repaint();
    }
	public void left() {
		barrax=barrax-40;		
	}
	public void rigth() {
		barrax=barrax+40;		
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
	


	

}
