package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BreakBreaker extends JFrame implements KeyListener {
	private JPanel contentPane;
	private int ballx,bally;
	private int barray,barrax;
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
		//setSize(500,500);
		setExtendedState(MAXIMIZED_BOTH);
		
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.setBackground(Color.black);
		
		bally=getHeight();
		ballx=getWidth();
		
		barray=getHeight();
		barrax=getWidth()/2;

		addKeyListener(this);
	}
	public void paint(Graphics g) {
		//Fondo
        g.setColor(Color.black);
        g.fillRect(contentPane.getWidth()-contentPane.getWidth(),contentPane.getHeight()-contentPane.getHeight(), 
        		getWidth(),getHeight());
        
        g.setColor (Color.red);
        for(int i=0;i<contentPane.getWidth()/42;i++) {
    		i=i+1;
        	for(int j=0;j<contentPane.getHeight()/42;j++) {
        		j=j+1;
    			g.fillRect ((contentPane.getWidth()/2+7)-(contentPane.getWidth()/2-40*i),
    			(contentPane.getHeight()/2-50)-(contentPane.getHeight()/3-15*j),70,20);
        	}
        }
        g.setColor(Color.yellow);
        g.fillRect (barrax,
        barray-80,100,20);
        
        //System.out.println(barrax*2);
        
        g.setColor(Color.white);
        g.fillOval(ballx,bally,20,20);
        

	}
	public void left() {
		if(barrax>0) {
			barrax=barrax-10;
		}
		}
	public void right() {
		if(barrax<contentPane.getWidth()-100) {
			barrax=barrax+10;
		}		
		}
	public int randx() {
		return (int)(Math.random()*getWidth());
		
		}
	public int randy() {
		return (int)(Math.random()*getHeight());

		
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
			right();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			for(int i=0;i<10;i++) {
				ballx=randx();
				bally=randy();
				repaint();

			}
			}
		repaint();

	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
}
