package me.Totenfluch.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import me.Totenfluch.other.OtherStuff;
import me.Totenfluch.client.Client;
import me.Totenfluch.frames.LoadingWindow;
import me.Totenfluch.frames.LobbyWindow;
import me.Totenfluch.frames.MainGameWindow;

import javax.swing.Timer;

public class Main {
	public static MainGameWindow gameframe = null;
	public static LobbyWindow lobbyframe = null;
	public static Timer gametimer = null;
	public static Timer changeangelplus;
	public static Timer changeangelminus;
	public static Timer updateLobbyWindow = null;
	public static int Player = 3;
	public static int AssignedPlayer = -1;
	public static boolean SlotTaken = false;
	public static InetAddress lComputerIP;
	public static String ComputerMac;
	public static String ComputerName;
	public static String ComputerIP;
	public static boolean isStarted = false;
	public static String LobbyServerName = "TCycle-Server-1-Atares";
	public static void main(String[] args){

		try {
			lComputerIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		ComputerMac = OtherStuff.getMacAdress();
		ComputerName = lComputerIP.getHostName();
		ComputerIP = lComputerIP.getHostAddress();
		lobbyframe = new LobbyWindow();

		LoadingWindow lframe = new LoadingWindow();
		String host = "188.194.11.106";
		int port = Integer.parseInt("9977");
		@SuppressWarnings("unused")
		final Client chatframe = new Client(host, port);
		initTimers();
		lframe.setVisible(false);

		lobbyframe = new LobbyWindow();
		lobbyframe.setVisible(true);
		updateLobbyWindow.start();

	}

	public static void startgame(){
		if(isStarted == false){
			gameframe = new MainGameWindow();
			gametimer.start();
			lobbyframe.setVisible(false);
			isStarted = true;
		}
	}

	private static void initTimers(){
		gametimer = new Timer(35, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gameframe.update();
			}
		});

		changeangelplus = new Timer(10, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(MainGameWindow.blocky == false){
					if(MainGameWindow.lookingdirection < 359){
						MainGameWindow.lookingdirection = MainGameWindow.lookingdirection+2;
					}else{
						MainGameWindow.lookingdirection = 0;
					}
				}
			}
		});

		changeangelminus = new Timer(10, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(MainGameWindow.blockx == false){
					if(MainGameWindow.lookingdirection > 0){
						MainGameWindow.lookingdirection = MainGameWindow.lookingdirection-2;
					}else{
						MainGameWindow.lookingdirection = 359;
					}
				}
			}
		});

		updateLobbyWindow = new Timer(50, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(lobbyframe.isVisible() == true){
					lobbyframe.repaint();
				}
			}
		});

	}
}
