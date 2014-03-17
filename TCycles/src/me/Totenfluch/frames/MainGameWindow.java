package me.Totenfluch.frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import me.Totenfluch.client.Client;
import me.Totenfluch.main.Main;

public class MainGameWindow extends JFrame implements MouseMotionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	public static double[] playerx = new double[4];
	public static double[] playery = new double[4];
	public static double PlayerWalls[][][] = new double[4][2][9999999];
	public static int[] WallsToDraw = new int[4];
	public static int lookingdirection = 0;
	public static boolean blockx = false;
	public static boolean blocky = false;
	private Color colorrng[] = {Color.blue, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.WHITE, Color.LIGHT_GRAY, Color.darkGray}; 

	private int distancebetweenwalls = 5;
	private double playerspeed = 4.0;

	public MainGameWindow(){
		setSize(1280, 720);
		setContentPane(new DrawPane());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		for(int i = 0; i<4; i++){
			playerx[i] = 0;
			playery[i] = 0;
		}

		playerx[Main.Player] = 640;
		playery[Main.Player] = 360;

		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}




	public void update(){
		if(distancebetweenwalls >= 5){
			PlayerWalls[Main.Player][0][WallsToDraw[Main.Player]] = playerx[Main.Player];
			PlayerWalls[Main.Player][1][WallsToDraw[Main.Player]] = playery[Main.Player];
			WallsToDraw[Main.Player]++;
			Client.processMessage("/updatePos " + Main.Player + " " + (int)Math.floor(playerx[Main.Player]) + " " + (int)Math.floor(playery[Main.Player]) + " "+ distancebetweenwalls);
			distancebetweenwalls = 0;
		}else{
			Client.processMessage("/updatePos " + Main.Player + " " + (int)Math.floor(playerx[Main.Player]) + " " + (int)Math.floor(playery[Main.Player]) + " "+ distancebetweenwalls);
			distancebetweenwalls++;
		}

		playerx[Main.Player] = playerx[Main.Player] + (playerspeed*Math.cos(Math.toRadians(lookingdirection - 90)));
		playery[Main.Player] = playery[Main.Player] + (playerspeed*Math.sin(Math.toRadians(lookingdirection - 90)));

		if(playerx[Main.Player] > 1263 || playerx[Main.Player] < 0 || playery[Main.Player] < 0 || playery[Main.Player] > 680){
			Forcedeath();
		}
		for(int c = 0; c<4; c++){
			for(int i = 0; i<WallsToDraw[c]-1 ; i++){
				if(((PlayerWalls[c][0][i] - playerx[c] < 6 && PlayerWalls[c][0][i] - playerx[c] > -6) && (PlayerWalls[c][1][i] - playery[c] < 6 && PlayerWalls[c][1][i] - playery[c] > -6 ))){
					Forcedeath();
				}
			}
		}

		repaint();
	}

	private void Forcedeath(){
		JOptionPane.showMessageDialog(null, "You faggot died again lolololo nub");

		RestartGame();
	}

	private void RestartGame(){
		playerx[Main.Player] = 640;
		playery[Main.Player] = 360;
		lookingdirection = 0;
		distancebetweenwalls = 6;
		playerspeed = 4.0;
		WallsToDraw[Main.Player] = 0;
		blocky=true;
		Main.changeangelplus.stop();
		blockx=true;
		Main.changeangelminus.stop();
	}

	class DrawPane extends JPanel{
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g){
			g.setColor(Color.black);
			g.fillRect(0, 0, 1280, 720);
			g.setColor(Color.RED);
			g.drawRect(0, 0, 1263, 680);
			g.setColor(Color.ORANGE);
			//g.fillOval((int)playerx[Main.Player], (int)playery[Main.Player], 10, 10);

			for(int i = 0; i<4; i++){
				g.setColor(colorrng[i]);
				g.fillOval((int)playerx[i], (int)playery[i], 10, 10);
			}
			for(int c = 0; c<4; c++){
				for(int i = 0; i<WallsToDraw[c] ; i++){
					g.setColor(Color.RED);
					g.fillOval((int)PlayerWalls[c][0][i], (int)PlayerWalls[c][1][i], 10, 10);
				}
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			blockx=false;
			Main.changeangelminus.start();
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT){	
			blocky=false;
			Main.changeangelplus.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			blockx=true;
			Main.changeangelminus.stop();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			blocky=true;
			Main.changeangelplus.stop();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
