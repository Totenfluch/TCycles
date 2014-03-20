package me.Totenfluch.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import me.Totenfluch.main.Main;

public class MainMenuWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private Object[] data = {"Atares", "Dolphy", "EnvyZ", "Bandai", "Connes"};
	@SuppressWarnings({ "rawtypes" })
	private JList serverbrowser;
	private JButton connect;
	private JTextPane Leaderboard, News;
	
	public StyledDocument Leaderboarddoc;
	public StyledDocument NewsDoc;
	
	public SimpleAttributeSet OwnScore;
	public SimpleAttributeSet OtherScores;
	public SimpleAttributeSet NewsStyle;
	private int JWidth = 1280;
	@SuppressWarnings("unused")
	private int JHight = 800;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MainMenuWindow(){
		setSize(1280, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(new Drawpane());
		setLayout(null);
		setVisible(true);

		serverbrowser = new JList(data);
		serverbrowser.setFont(new Font("Impact", Font.PLAIN, 25));
		serverbrowser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		serverbrowser.setLayoutOrientation(JList.VERTICAL);
		serverbrowser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		serverbrowser.setBounds(550, 200, 180, 175);
		serverbrowser.setSelectedIndex(0);
		add(serverbrowser);
		
		Leaderboard = new JTextPane();
		Leaderboard.setBounds(50, 100, 300, 600);
		Leaderboard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		Leaderboard.setEnabled(false);
		add(Leaderboard);
		Leaderboarddoc = Leaderboard.getStyledDocument();
		
		News = new JTextPane();
		News.setBounds(930, 100, 300, 600);
		News.setEditable(false);
		News.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		add(News);
		NewsDoc = News.getStyledDocument();

		connect = new JButton("Connect");
		connect.setBounds(550, 420, 180, 50);
		add(connect);

		OwnScore = new SimpleAttributeSet();
		StyleConstants.setForeground(OwnScore, Color.RED);
		StyleConstants.setBold(OwnScore, true);
		
		OtherScores = new SimpleAttributeSet();
		StyleConstants.setForeground(OtherScores, Color.BLACK);
		StyleConstants.setBold(OtherScores, false);
		
		NewsStyle = new SimpleAttributeSet();
		StyleConstants.setForeground(NewsStyle, Color.BLUE);
		StyleConstants.setBold(NewsStyle, false);
		
		
		thehandler handler = new thehandler();
		connect.addActionListener(handler);

		repaint();
	}
	
	private void DrawCenteredString(Graphics g, Color color, Font f, String s, int xPos, int yPos){
		FontMetrics fm = getFontMetrics(f);
		Rectangle2D textsize = fm.getStringBounds(s, g);
		xPos = (int) ((JWidth - textsize.getWidth()) / 2);
		g.setColor(color);
		g.drawString(s, xPos, yPos);
	}

	private class thehandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == connect){
				if(serverbrowser.getSelectedValue().toString().equals("Atares")){
					if(Main.ConnectToLobbyServer("Atares") == true){ 
						Main.closeMainMenuWindow();
					}else{
						JOptionPane.showMessageDialog(null, "This Server is not available.");
					}
				}
				if(serverbrowser.getSelectedValue().toString().equals("Dolphy")){
					if(Main.ConnectToLobbyServer("Dolphy") == true){ 
						Main.closeMainMenuWindow();
					}else{
						JOptionPane.showMessageDialog(null, "This Server is not available.");
					}
				}
				if(serverbrowser.getSelectedValue().toString().equals("EnvyZ")){
					if(Main.ConnectToLobbyServer("EnvyZ") == true){ 
						Main.closeMainMenuWindow();
					}else{
						JOptionPane.showMessageDialog(null, "This Server is not available.");
					}
				}
				if(serverbrowser.getSelectedValue().toString().equals("Bandai")){
					if(Main.ConnectToLobbyServer("Bandai") == true){ 
						Main.closeMainMenuWindow();
					}else{
						JOptionPane.showMessageDialog(null, "This Server is not available.");
					}
				}
				if(serverbrowser.getSelectedValue().toString().equals("Connes")){
					if(Main.ConnectToLobbyServer("Connes") == true){ 
						Main.closeMainMenuWindow();
					}else{
						JOptionPane.showMessageDialog(null, "This Server is not available.");
					}
				}
			}

		}

	}


	class Drawpane extends JPanel{
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g){
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 1300, 850);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Impact", Font.ITALIC, 45));
			g.drawString(Main.ActiveUser, 50, 750);
			
			DrawCenteredString(g, Color.BLACK, new Font("Impact", Font.ITALIC, 45), "TCycles "+ Main.devState + " v "+Main.Version, JWidth/2, 50);
			
			if(Main.isServerUpAtares == 0){
				g.setColor(Color.GRAY);
			}else if(Main.isServerUpAtares == 1){
				g.setColor(Color.GREEN);
			}else if(Main.isServerUpAtares == 2){
				g.setColor(Color.RED);
			}
			g.fillOval(735, 205, 25, 25);

			

			if(Main.isServerUpDolphy == 0){
				g.setColor(Color.GRAY);
			}else if(Main.isServerUpDolphy == 1){
				g.setColor(Color.GREEN);
			}else if(Main.isServerUpDolphy == 2){
				g.setColor(Color.RED);
			}
			g.fillOval(735, 237, 25, 25);
			
			
			
			if(Main.isServerUpEnvyZ == 0){
				g.setColor(Color.GRAY);
			}else if(Main.isServerUpEnvyZ == 1){
				g.setColor(Color.GREEN);
			}else if(Main.isServerUpEnvyZ == 2){
				g.setColor(Color.RED);
			}
			g.fillOval(735, 272, 25, 25);
			
			
			
			if(Main.isServerUpBandai == 0){
				g.setColor(Color.GRAY);
			}else if(Main.isServerUpBandai == 1){
				g.setColor(Color.GREEN);
			}else if(Main.isServerUpBandai == 2){
				g.setColor(Color.RED);
			}
			g.fillOval(735, 307, 25, 25);
			
			
			
			if(Main.isServerUpConnes == 0){
				g.setColor(Color.GRAY);
			}else if(Main.isServerUpConnes == 1){
				g.setColor(Color.GREEN);
			}else if(Main.isServerUpConnes == 2){
				g.setColor(Color.RED);
			}
			g.fillOval(735, 342, 25, 25);
		}
	}
}
