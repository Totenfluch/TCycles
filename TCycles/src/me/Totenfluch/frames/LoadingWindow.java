package me.Totenfluch.frames;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.Totenfluch.main.Main;
import me.Totenfluch.other.ResourceLoader;

public class LoadingWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	public LoadingWindow(){
		setContentPane(new DrawPane());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(350, 450);
		setVisible(true);
		repaint();
	}

	class DrawPane extends JPanel{
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g){
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 400, 500);
			g.setColor(Color.BLACK);
			g.drawString("Connecting to: " + Main.LobbyServerName, 50, 15);
			ImageIcon i = ResourceLoader.ImageIconLoad("/loading.gif");
			i.paintIcon(this, g, 0, 0);
		}
	}
}
