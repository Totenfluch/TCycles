package me.Totenfluch.client;

import java.io.IOException;

import javax.swing.JOptionPane;

import me.Totenfluch.frames.MainGameWindow;
import me.Totenfluch.main.Main;


public class GetServerMessages{
	public static String newestreply = null;

	public static void CheckServerMessages(String message){
		if(message.startsWith("/setPos")){
			String[] temp = message.split(" ");
			if(Integer.valueOf(temp[1]) != Main.Player){
				MainGameWindow.playerx[Integer.valueOf(temp[1])] = Integer.valueOf(temp[2])+50;
				MainGameWindow.playery[Integer.valueOf(temp[1])] = Integer.valueOf(temp[3])+50;
			}
		}
		else if(message.startsWith("YouGotkickednr")){
			JOptionPane.showMessageDialog(null, "Kicked by Server");
			System.exit(0);
		}


		else if(message.startsWith("YouGotkicked")){
			String [] kickreason = message.split(" ");
			if(kickreason.length == 1){
				JOptionPane.showMessageDialog(null, "Kicked by Server");
			}else if(kickreason.length == 2){
				JOptionPane.showMessageDialog(null, "Kicked by Server. Reason: " + kickreason[1]);
			}else{
				JOptionPane.showMessageDialog(null, "Kicked by Server");
			}
			System.exit(0);
		}

		else if(message.startsWith("You are connected to GameZ-Server-1")){
			Client.IsConnectedToServer = true;
		}

		else if(message.startsWith("ServerIsShuttingDown")){
			try {
				Client.socket.close();
			} catch (IOException e){
				e.printStackTrace();
			}
			Client.disconnected = true;
			Client.IsConnectedToServer = false;
		}

		else if(message.startsWith("forcelogin")){

		}

		else if(message.equals("Authorized")){

		}

		else if(message.startsWith("broadcast Account") && message.contains("created")){

		}

		else if(message.startsWith("broadcast")){

		}
	}
}
