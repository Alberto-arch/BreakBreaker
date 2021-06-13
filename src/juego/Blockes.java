package juego;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Blockes {
	private int coordenadas[][];
	public Blockes(int x,int y) {
		coordenadas=new int[x][y];
	}
	public void paint(Graphics g) {
		
		for(int i=0;i<coordenadas.length;i++) {
			for(int j=0;j<coordenadas[i].length;j++) {
				g.setColor (Color.red);	
				g.fillRect (i*72+110,j*50/2+100,70,20);
			}
		}
	}
}
