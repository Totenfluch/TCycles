package me.Totenfluch.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import me.Totenfluch.other.CheckServerThread_Atares;
import me.Totenfluch.other.CheckServerThread_Bandai;
import me.Totenfluch.other.CheckServerThread_Connes;
import me.Totenfluch.other.CheckServerThread_Dolphy;
import me.Totenfluch.other.CheckServerThread_EnvyZ;
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
	public static int Player = -1;
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
	
	public static String ActiveUser = "";
	
	public static double Version = 1.1;
	public static String devState = "Beta";
	
	public static String RootIP = "188.194.8.110";
	
	public static int isServerUpAtares = 0;
	public static int isServerUpDolphy = 0;
	public static int isServerUpEnvyZ = 0;
	public static int isServerUpBandai = 0;
	public static int isServerUpConnes = 0;
	// 0 -> unchecked, 1 -> up , 2 -> down
	
	public static void main(String[] args){

		try {
			lComputerIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		ComputerMac = OtherStuff.getMacAdress();
		ComputerName = lComputerIP.getHostName();
		
		ActiveUser = ComputerName;
		
		ComputerIP = lComputerIP.getHostAddress();
		lobbyframe = new LobbyWindow();
		gameframe = new MainGameWindow();
		CheckServerThread_Atares.CheckServer();
		CheckServerThread_Dolphy.CheckServer();
		CheckServerThread_EnvyZ.CheckServer();
		CheckServerThread_Bandai.CheckServer();
		CheckServerThread_Connes.CheckServer();
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
		lobbyframe = null;
		AssignedPlayer = -1;
		Player = -1;
		SlotTaken = false;
		lobbyframe = new LobbyWindow();
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
	
	public static boolean ConnectToLobbyServer(String Name){
		String ip = null;
		int port = 0;
		if(Name.equals("Atares") && isServerUpAtares == 1){
			LobbyServerName = "TCycle-Server-1-Atares";
			ip = "188.194.8.110";
			port = 9977;
		}else if(Name.equals("Dolphy") && isServerUpDolphy == 1){
			LobbyServerName = "TCycle-Server-2-Dolphy";
			ip = "188.194.8.110";
			port = 9976;
		}else if(Name.equals("EnvyZ") && isServerUpEnvyZ == 1){
			LobbyServerName = "TCycle-Server-3-EnvyZ";
			ip = "188.194.8.110";
			port = 9975;
		}else if(Name.equals("Bandai") && isServerUpBandai == 1){
			LobbyServerName = "TCycle-Server-4-Bandai";
			ip = "188.194.8.110";
			port = 9974;
		}else if(Name.equals("Connes") && isServerUpConnes == 1){
			LobbyServerName = "TCycle-Server-5-Connes";
			ip = "188.194.8.110";
			port = 9973;
		}else{
			return false;
		}
		
		
		try{
			LoadingWindow lframe = new LoadingWindow();
			String host = ip;
			chatframe = new Client(host, port);
			lframe.setVisible(false);
			Client.processMessage("/joinlobby " + Main.ActiveUser);
			openLobbyWindow();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
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
