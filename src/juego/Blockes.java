package juego;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Blockes {
	//creamos matriz
	private int coordenadas[][];
	public Blockes(int x,int y) {
		//definimos tama�o matriz
		coordenadas=new int[x][y];
	
		
	}
	public void paint(Graphics g) {
		//creamos un bucle para la creacion de bloques
		for(int i=0;i<coordenadas.length;i++) {
			for(int j=0;j<coordenadas[i].length;j++) {
				//si el valor de la matriz es zero creamos los bloques
				if(coordenadas[i][j]==0) {
					g.setColor (Color.red);	
					g.fillRect (i*80+120,j*50/2+100,70,20);
				}
				
			}
		}
	}
	//definimos valor de la matriz
	public void setBricksValue(int value,int row,int col){
        coordenadas[row][col] = value;

    }
	//reseteamos los bloques borrados en pantalla
	public void setreset(){
		for(int i=0;i<coordenadas.length;i++) {
			for(int j=0;j<coordenadas[i].length;j++) {
				coordenadas[i][j] = 0;
			}
		}
    }
	//cremos getter para la llamada de la matriz
	public int[][] getCoordenadas() {
		return coordenadas;
	}
	
}
