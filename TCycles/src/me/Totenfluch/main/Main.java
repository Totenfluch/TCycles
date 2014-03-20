package me.Totenfluch.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import me.Totenfluch.other.OtherStuff;
import me.Totenfluch.client.Client;
import me.Totenfluch.frames.LoadingWindow;
import me.Totenfluch.frames.LobbyWindow;
import me.Totenfluch.frames.MainGameWindow;
import me.Totenfluch.frames.MainMenuWindow;

import javax.swing.Timer;

public class Main {
	public static MainGameWindow gameframe = null;
	public static LobbyWindow lobbyframe = null;
	public static MainMenuWindow MainMenuframe = null;
	public static Client chatframe = null;
	public static Timer gametimer = null;
	public static Timer changeangelplus;
	public static Timer changeangelminus;
	public static Timer updateLobbyWindow = null;
	public static Timer RespawnTimer = null;
	public static Timer updateMainMenuWindow = null;
	public static int Player = 3;
	public static int AssignedPlayer = -1;
	public static boolean SlotTaken = false;
	public static InetAddress lComputerIP;
	public static String ComputerMac;
	public static String ComputerName;
	public static String ComputerIP;
	public static boolean isStarted = false;
	public static int TimeToRestart = 3;
	public static boolean isRespawning = false;
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
		gameframe = new MainGameWindow();
		MainMenuframe = new MainMenuWindow();
		initTimers();

		lobbyframe = new LobbyWindow();

	}

	public static void disconnectfromserver(){
		try {
			chatframe.din.close();
			chatframe.dout.close();
			chatframe.socket.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		chatframe.socket = null;
		chatframe.din = null;
		chatframe.dout = null;
		chatframe.thread = null;
		chatframe.running = false;
		chatframe.dispose();
		chatframe = null;
		openMainMenuWindow();
	}

	public static void closeMainMenuWindow(){
		MainMenuframe.setVisible(false);
		updateMainMenuWindow.stop();
	}

	public static void openMainMenuWindow(){
		MainMenuframe.setVisible(true);
		updateMainMenuWindow.start();
	}

	public static void ConnectToLobbyServer(String Name){
		String ip = null;
		if(Name.equals("Atares")){
			ip = "188.194.11.106";
		}else{
			return;
		}
		try{
			LoadingWindow lframe = new LoadingWindow();
			String host = ip;
			int port = Integer.parseInt("9977");
			chatframe = new Client(host, port);
			lframe.setVisible(false);
			openLobbyWindow();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void openLobbyWindow(){
		lobbyframe.setVisible(true);
		updateLobbyWindow.start();
	}

	public static void closeLobbyWindow(){
		lobbyframe.setVisible(false);
		updateLobbyWindow.stop();
	}

	public static void startgame(){
		if(isStarted == false){
			try{
				gameframe.initGame();
				gameframe.setVisible(true);
				gametimer.start();
				lobbyframe.setVisible(false);
				isStarted = true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public static void restartgame(){
		gameframe.RestartGame();
	}

	private static void initTimers(){
		gametimer = new Timer(35, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(gameframe.isDeath == false){
					gameframe.update();
				}else{
					gameframe.afterdeathrepaint();
				}
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

		updateMainMenuWindow = new Timer(50, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainMenuframe.repaint();
			}
		});

		RespawnTimer = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TimeToRestart--;
				if(TimeToRestart == 0){
					TimeToRestart = 3;
					RespawnTimer.stop();
					gameframe.revive();
					isRespawning = false;
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
