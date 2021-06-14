package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BreakBreaker extends JFrame implements KeyListener,ActionListener {
	private JPanel contentPane;
	private int ballx,bally;
	private int barray;
	private int barrax;
	private int ballxdir=-10;
	private int ballydir=-10;
	private boolean start;
	private Timer timer;
	private Blockes brick;
	private final BufferStrategy bf;
	private Graphics g2;
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
				
		barrax=getWidth()/2;
		barray=getHeight();
		
		brick=new Blockes(20,10);
		addKeyListener(this);
		
		timer=new Timer(50,this);
		timer.start();
		
		createBufferStrategy(2);
		bf=this.getBufferStrategy();
		
		g2=this.getGraphics();
		
	}

	public void paint(Graphics g) {
		Graphics2D g2=(Graphics2D)bf.getDrawGraphics();
    	g2.setColor(Color.black);
        g2.fillRect(getWidth()-getWidth(),getHeight()-getHeight(),getWidth(),getHeight());
        
        g2.setColor(Color.black);
        g2.drawRect(0,0, getWidth(),10);
        g2.drawRect(-10,0, 10,getHeight());
        g2.drawRect(getWidth()-1,10,10,getHeight());
        
        
    	brick.paint((Graphics) g2);
    	g2.setColor(Color.yellow);
        g2.fillRect (barrax,barray-80,100,20);
                
        g2.setColor(Color.white);
        g2.fillOval(ballx,bally,20,20);
        
      
        
        bf.show();
        
    }
	
	public void left() {
		if (!new Rectangle(barrax,barray-80,100,20).intersects(colLEFT())) {
			barrax=barrax-40;
		}		
	}
	public void rigth() {
		if (!new Rectangle(barrax,barray-80,100,20).intersects(colRIGTH())) {
			barrax=barrax+40;
		}		
	}
	
	public Rectangle colUP() {
		return new Rectangle(0,0, getWidth(),10);
	}
	public Rectangle colLEFT() {
		return new Rectangle(-10,0, 10,getHeight());
	}
	public Rectangle colRIGTH() {
		return new Rectangle(getWidth()-5,10, 10,getHeight());
	}
	
	public void blockes() {
		for(int i=0;i<brick.getCoordenadas().length;i++) {
			for(int j=0;j<brick.getCoordenadas()[i].length;j++) {
				if (new Rectangle(ballx, bally, 20, 20).intersects(new Rectangle(i*72+110,j*50/2+100,70,20))){
					g2.setColor(Color.black);
					ballydir=-ballydir;
                }
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(start) {
			blockes();
			if (new Rectangle(ballx, bally, 20, 20).intersects(barrax,barray-80,100,20)) {
                ballydir=-ballydir;
            }
			ballx=ballx+ballxdir;
			bally=bally+ballydir;
			
			if (new Rectangle(ballx, bally, 20, 20).intersects(colUP())) {
                ballydir=-ballydir;
            }
			if (new Rectangle(ballx, bally, 20, 20).intersects(colLEFT())) {
                ballxdir=-ballxdir;
            }
			if (new Rectangle(ballx, bally, 20, 20).intersects(colRIGTH())) {
                ballxdir=-ballxdir;
            }
		}
		repaint();
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
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				ballx=getWidth()/2;
				bally=getHeight()-100;
				ballydir=-10;
				ballxdir=-10;
				barrax=getWidth()/2;
				start=false;
			}
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(ballx==getWidth()/2) {
				start=true;
				}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
