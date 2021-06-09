package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BreakBreaker extends JFrame {
	private JPanel contentPane;
	int ancho;
	int alto;
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
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.setBackground(Color.black);
	}
	public void paint(Graphics g) {
        super.paint(g);
        g.setColor (Color.red);
        int x=0;
        int y=0;
        for(int i=0;i<contentPane.getWidth()/42;i++) {
    		i=i+1;
        	for(int j=0;j<contentPane.getHeight()/42;j++) {
        		j=j+1;
    			g.fillRect ((contentPane.getWidth()/2+7)-(contentPane.getWidth()/2-40*i),
    			(contentPane.getHeight()/2-50)-(contentPane.getHeight()/3-15*j),70,20);
        	}
        }
        g.setColor(Color.yellow);
        g.fillRect
	}
}
