package me.Totenfluch.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import me.Totenfluch.main.Main;

public class MainMenuWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private Object[] data = {"Atares", "Dolphy", "EnvyZ", "Bandai", "Connes"};
	@SuppressWarnings({ "rawtypes" })
	private JList serverbrowser;
	private JButton connect;
	
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
		
		connect = new JButton("Connect");
		connect.setBounds(550, 420, 180, 50);
		add(connect);
		
		
		
		thehandler handler = new thehandler();
		connect.addActionListener(handler);
		
		repaint();
	}
	
	private class thehandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == connect){
				System.out.println(serverbrowser.getSelectedValue().toString());
				if(serverbrowser.getSelectedValue().toString().equals("Atares")){
					Main.ConnectToLobbyServer("Atares");
					Main.closeMainMenuWindow();
				}
			}
			
		}
		
	}
	
	
	class Drawpane extends JPanel{
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g){
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 1300, 850);
			g.setColor(Color.GREEN);
			g.fillOval(735, 205, 25, 25);
			g.setColor(Color.RED);
			g.fillOval(735, 237, 25, 25);
			g.fillOval(735, 272, 25, 25);
			g.fillOval(735, 307, 25, 25);
			g.fillOval(735, 342, 25, 25);
		}
	}
}
