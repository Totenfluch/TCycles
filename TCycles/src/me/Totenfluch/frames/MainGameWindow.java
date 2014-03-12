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

import me.Totenfluch.main.Main;

public class MainGameWindow extends JFrame implements MouseMotionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	double playerx;
	private double playery;
	private double PlayerWalls[][] = new double[2][9999999];
	private int WallsToDraw = 0;
	public static int lookingdirection = 0;
	public static boolean blockx = false;
	public static boolean blocky = false;

	private int distancebetweenwalls = 5;
	private double playerspeed = 4.0;

	public MainGameWindow(){
		setSize(1280, 720);
		setContentPane(new DrawPane());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		playerx = 640;
		playery = 360;

		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}




	public void update(){
		if(distancebetweenwalls >= 5){
			PlayerWalls[0][WallsToDraw] = playerx;
			PlayerWalls[1][WallsToDraw] = playery;
			WallsToDraw++;
			distancebetweenwalls =0;
		}else{
			distancebetweenwalls++;
		}
		playerx = playerx + (playerspeed*Math.cos(Math.toRadians(lookingdirection - 90)));
		playery = playery + (playerspeed*Math.sin(Math.toRadians(lookingdirection - 90)));
		if(playerx > 1263 || playerx < 0 || playery < 0 || playery > 680){
			Forcedeath();
		}
		for(int i = 0; i<WallsToDraw-1 ; i++){
			if(((PlayerWalls[0][i] - playerx < 6 && PlayerWalls[0][i] - playerx > -6) && (PlayerWalls[1][i] - playery < 6 && PlayerWalls[1][i] - playery > -6 ))){
				Forcedeath();
			}
		}


		repaint();
	}

	private void Forcedeath(){
		JOptionPane.showMessageDialog(null, "You faggot died again lolololo nub");

		RestartGame();
	}

	private void RestartGame(){
		playerx = 640;
		playery = 360;
		lookingdirection = 0;
		distancebetweenwalls = 6;
		playerspeed = 4.0;
		WallsToDraw = 0;
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
			g.fillOval((int)playerx, (int)playery, 10, 10);
			for(int i = 0; i<WallsToDraw ; i++){
				g.setColor(Color.RED);
				g.fillOval((int)PlayerWalls[0][i], (int)PlayerWalls[1][i], 10, 10);
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
