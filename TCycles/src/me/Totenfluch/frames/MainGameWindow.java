package me.Totenfluch.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
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
	public int Round = 0;
	private Color colorplayer[] = {Color.blue, Color.MAGENTA, Color.YELLOW, Color.darkGray}; 
	private Color colortail[] = {Color.CYAN, Color.PINK, Color.ORANGE, Color.lightGray};
	public boolean isDeath = false;
	public Font ftDefault = new Font("Impact", Font.BOLD, 25);
	public int JWidth = 1280;
	public int JHight = 720;
	public boolean won = false;

	private int distancebetweenwalls = 5;
	private double playerspeed = 4.0;

	public MainGameWindow(){
		setSize(1280, 720);
		setContentPane(new DrawPane());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void initGame(){

		for(int i = 0; i<4; i++){
			playerx[i] = 0;
			playery[i] = 0;
		}

		playerx[Main.Player] = 640+(Main.Player*20);
		playery[Main.Player] = 360;

		if(Main.Player == 0){
			lookingdirection = 0;
		}else if(Main.Player == 1){
			lookingdirection = 180;
		}else if(Main.Player == 2){
			lookingdirection = 0;
		}else if(Main.Player == 3){
			lookingdirection = 180;
		}

		for(int i=0; i<4; i++){
			WallsToDraw[i] = 0;
		}
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

		if(playerx[Main.Player] > 1263 || playerx[Main.Player] < 0 || playery[Main.Player] < 0 || playery[Main.Player] > 665){
			Forcedeath(2);
		}
		for(int c = 0; c<4; c++){
			if(c != Main.Player){
				if(((playerx[Main.Player] - playerx[c] < 6 && playerx[Main.Player] - playerx[c] > -6) && (playery[Main.Player] - playery[c] < 6 && playery[Main.Player] - playery[c] > -6 ))){
					Forcedeath(1);
				}
			}
			for(int i = 0; i<WallsToDraw[c]-1 ; i++){
				if(((PlayerWalls[c][0][i] - playerx[Main.Player] < 6 && PlayerWalls[c][0][i] - playerx[Main.Player] > -6) && (PlayerWalls[c][1][i] - playery[Main.Player] < 6 && PlayerWalls[c][1][i] - playery[Main.Player] > -6 ))){
					Forcedeath(0);
				}
			}
		}

		repaint();
	}

	public void afterdeathrepaint(){
		repaint();
	}

	private void Forcedeath(int reason){
		Client.processMessage("/confirmdeath " + Main.Player + " " + reason);
		isDeath = true;
	}

	public void RestartGame(){
		playerx[Main.Player] = 640+(Main.Player*20);
		playery[Main.Player] = 360;
		if(Main.Player == 0){
			lookingdirection = 0;
		}else if(Main.Player == 1){
			lookingdirection = 180;
		}else if(Main.Player == 2){
			lookingdirection = 0;
		}else if(Main.Player == 3){
			lookingdirection = 180;
		}
		WallsToDraw[0] = 0;
		WallsToDraw[1] = 0;
		WallsToDraw[2] = 0;
		WallsToDraw[3] = 0;
		lookingdirection = 0;
		distancebetweenwalls = 6;
		playerspeed = 4.0;
		WallsToDraw[Main.Player] = 0;
		blocky=true;
		Main.changeangelplus.stop();
		blockx=true;
		Main.changeangelminus.stop();
		Main.RespawnTimer.start();
		Client.processMessage("/updatePos " + Main.Player + " " + (int)Math.floor(playerx[Main.Player]) + " " + (int)Math.floor(playery[Main.Player]) + " "+ 1);
	}

	public void revive(){
		won = false;
		isDeath = false;
	}

	private void DrawCenteredString(Graphics g, Color color, String s, int xPos, int yPos){
		FontMetrics fm = getFontMetrics(ftDefault);
		Rectangle2D textsize = fm.getStringBounds(s, g);
		xPos = (int) ((JWidth - textsize.getWidth()) / 2);
		g.setColor(color);
		g.drawString(s, xPos, yPos);
	}

	class DrawPane extends JPanel{
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g){
			g.setColor(Color.black);
			g.fillRect(0, 0, 1280, 720);
			g.setColor(Color.RED);
			g.drawRect(0, 0, 1263, 680);
			g.setColor(Color.ORANGE);

			for(int i = 0; i<4; i++){
				g.setColor(colorplayer[i]);
				g.fillOval((int)playerx[i], (int)playery[i], 10, 10);
			}
			for(int c = 0; c<4; c++){
				for(int i = 0; i<WallsToDraw[c] ; i++){
					g.setColor(colortail[c]);
					g.fillOval((int)PlayerWalls[c][0][i], (int)PlayerWalls[c][1][i], 10, 10);
				}
			}
			if(isDeath == true){
				g.setColor(Color.RED);
				g.setFont(new Font("Impact", Font.BOLD, 25));
				if(won == false){
					g.drawString("You have died. Wait for the other Players to finish their Round.", 300, 300);
				}else{
					g.setColor(Color.GREEN);
					g.drawString("You Won! Congratulations!!!! :)) Round restart incoming :/o", 300, 300);
				}
			}
			if(isDeath == true && Main.isRespawning == true){
				DrawCenteredString(g, Color.ORANGE, Main.TimeToRestart+". . .", JWidth/2, 250);
				DrawCenteredString(g, Color.ORANGE, "Round: " + Round + "/10", JWidth/2, 350);
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(isDeath == false){
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				blockx=false;
				Main.changeangelminus.start();
			}

			if(e.getKeyCode() == KeyEvent.VK_RIGHT){	
				blocky=false;
				Main.changeangelplus.start();
			}
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
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
}
