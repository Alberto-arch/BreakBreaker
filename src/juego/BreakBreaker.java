package juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import agenda.Contacto;

public class BreakBreaker extends JFrame implements KeyListener,ActionListener {
	private JPanel contentPane;
	private int ballx,bally;
	private int barray;
	private int barrax;
	//velocidad de movimiento x i y
	private int ballxdir=-10;
	private int ballydir=-10;
	//Si start es true iniciamos juego
	private boolean start;
	//creamos timer para especificar el tiempo de refreco en el que vamos a repintar
	private Timer timer;
	//llamamos a la clase blockes
	private Blockes brick;
	//Total de blockes
	private int btotal=200;
	//creamos el buffer
	private final BufferStrategy bf;
	private Graphics2D g2;
	//Definimos contador de puntos
	private int puntos;
	private Point registre;
	private ArrayList<Point>registro= new ArrayList();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() throws NullPointerException, NumberFormatException {
				try {
					BreakBreaker frame = new BreakBreaker();
					//especificamos que no pueda redimensionarse la ventana
					frame.setResizable(false);
					//especifiamos que sea visible la ventana
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public BreakBreaker() {
		//Creamos ventana y especificamos que se inicie maximizado
		setTitle("BreakBreaker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		//Creamos el panel y lo añadimos a la ventana
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
	
	//definimos la localizacion de  la pala
		barrax=getWidth()/2;
		barray=getHeight();
		
	//LLamamos a la clase Blokes y introducimos por argumento el tamaño de la 
	//matriz para defir cuantos bloques
		brick=new Blockes(20,10);
		
	//Especificamos donde ejecutamos las keylistener
		addKeyListener(this);
		
	//Llamamos y ejecutamos a la clase Timer y especificamos cada cuando vamos a repintar
		timer=new Timer(20,this);
		timer.start();
		
	//Creamos un buffer para asegurarnos que solucionamos el problema del
	//parpadeo de pantall al repintar
		createBufferStrategy(2);
		bf=this.getBufferStrategy();
		
		registre=new Point();
		
	}
//Con paint crearemos los elementos del juego
	public void paint(Graphics g) {
		g2=(Graphics2D)bf.getDrawGraphics();
		
		//Establecemos el fondo negro
    	g2.setColor(Color.black);
        g2.fillRect(getWidth()-getWidth(),getHeight()-getHeight(),getWidth(),getHeight());
        
        //Crearemos los elementos que usaremos para especificar los limites de pantalla
        g2.setColor(Color.black);
        g2.drawRect(0,0, getWidth(),10);
        g2.drawRect(-10,0, 10,getHeight());
        g2.drawRect(getWidth()-1,10,10,getHeight());
        g2.drawRect(0,getHeight(),getWidth(),10);
        
        //Llamamos a el metodo paint de la clase brick
    	brick.paint((Graphics) g2);
    	
    	//creamos la raqueta
    	g2.setColor(Color.yellow);
        g2.fillRect (barrax,barray-80,100,20);
        
        //creamos la bola
        g2.setColor(Color.white);
        g2.fillOval(ballx,bally,20,20);
        
        //creamos contador de puntos
    	g2.setColor(Color.white);
        g2.setFont(new Font("Segoe Script", Font.BOLD + Font.ITALIC, 40));
        g2.drawString(String.valueOf(puntos)+"/"+"200",getWidth()/2,70);

        //mostramos los elementos del buffer
        bf.show();
        
    }
	//mover raqueta a la izquierda limitando movimiento raqueta
	public void left() {
		if (!new Rectangle(barrax,barray-80,100,20).intersects(colLEFT())) {
			barrax=barrax-40;
		}		
	}
	//mover raqueta a la derecha limitando movimiento raqueta

	public void rigth() {
		if (!new Rectangle(barrax,barray-80,100,20).intersects(colRIGTH())) {
			barrax=barrax+40;
		}		
	}
	//especificar limite de pantalla arriva
	public Rectangle colUP() {
		return new Rectangle(0,0, getWidth(),10);
	}
	//Especificar limite de pantalla a la izquierda
	public Rectangle colLEFT() {
		return new Rectangle(-10,0, 10,getHeight());
	}
	//Especificar limite de pantalla a la derecha
	public Rectangle colRIGTH() {
		return new Rectangle(getWidth()-5,10, 10,getHeight());
	}
	//Especificar limite de pantalla de abajo
	public Rectangle colDOWN() {
		return new Rectangle(0,getHeight(),getWidth(),10);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int total=0;
		//iniciamos intervalo de refresco con la clase timer
		timer.start();
		//Si star es verdadero ejecutamos juego
		if(start) {
			//especificamos si la bola toca la raqueta
			if (new Rectangle(ballx, bally, 20, 20).intersects(barrax,barray-80,100,20)) {
                ballydir=-ballydir;
            }
			//Eleminamos los bloques al colisionar con la bola
			for(int i=0;i<brick.getCoordenadas().length;i++) {
				total++;
				for(int j=0;j<brick.getCoordenadas()[0].length;j++) {
					//si toca un bloque desaparece
					if (new Rectangle(ballx, bally, 20, 20).intersects(new Rectangle(i*80+50,j*50/2+100,70,20))){
							if(registro.get(j)!=registre.getLocation() ) {
								brick.setBricksValue(1, i, j);
								ballydir=-ballydir;
							}
	                }
				}
			}
			//mover bola
			ballx=ballx+ballxdir;
			bally=bally+ballydir;
			//limites de movimiento la bola en pantalla
			if (new Rectangle(ballx, bally, 20, 20).intersects(colUP())) {
                ballydir=-ballydir;
            }
			if (new Rectangle(ballx, bally, 20, 20).intersects(colLEFT())) {
                ballxdir=-ballxdir;
            }
			if (new Rectangle(ballx, bally, 20, 20).intersects(colRIGTH())) {
                ballxdir=-ballxdir;
            }
			if (new Rectangle(ballx, bally, 20, 20).intersects(colDOWN())) {
				JOptionPane.showMessageDialog(null, "Has perdido");
            }
		}
		//recargar los elementos de pantalla
		repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	//Definimos las teclas
	@Override
	public void keyPressed(KeyEvent e) {
		//Presionar la tecla izquierda
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			//llamamos a el metodo left para mover a la izquierda
			left();
		}
		//Presionar la tecla derecha
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			//llamamos a el metodo rigth para mover a la derecha
			rigth();
		}
		//Presionar la tecla espacio
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			//juego nuevo cada vez que ejecutamos la barra espaciadora
				ballx=getWidth()/2;
				bally=getHeight()-100;
				ballydir=-10;
				ballxdir=-10;
				barrax=getWidth()/2;
				btotal=200;
				brick.setreset();
				puntos=0;
				start=true;
				
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
