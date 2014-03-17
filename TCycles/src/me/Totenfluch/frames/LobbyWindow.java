package me.Totenfluch.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import me.Totenfluch.main.Main;

public class LobbyWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField Server;
	private JTextField Username;
	@SuppressWarnings("rawtypes")
	private JComboBox PlayerID;
	private String[] playerids = {"0", "1", "2", "3"};
	private JButton play;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LobbyWindow(){
		setLayout(new FlowLayout());
		setSize(200, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Server = new JTextField("TCycle-Server-1", 10);
		Server.setEditable(false);
		add(Server);
		
		Username = new JTextField("Username", 10);
		add(Username);
		
		PlayerID = new JComboBox(playerids);
		add(PlayerID);
		
		play = new JButton("Play");
		add(play);
		
		thehandler handler = new thehandler();
		play.addActionListener(handler);
		
		pack();
		setVisible(true);
	}
	
	private class thehandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == play){
				Main.Player = Integer.valueOf(PlayerID.getSelectedItem().toString());
				Main.startgame();
			}
			
		}
		
	}
	
}
