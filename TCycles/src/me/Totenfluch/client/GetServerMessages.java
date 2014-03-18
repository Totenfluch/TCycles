package me.Totenfluch.client;

import java.io.IOException;

import javax.swing.JOptionPane;

import me.Totenfluch.frames.MainGameWindow;
import me.Totenfluch.main.Main;
import me.Totenfluch.frames.LobbyWindow;;


public class GetServerMessages{
	public static String newestreply = null;


	public static void CheckServerMessages(String message){
		if(message.startsWith("/setPos")){
			String[] temp = message.split(" ");
			if(Integer.valueOf(temp[1]) != Main.Player){
				MainGameWindow.playerx[Integer.valueOf(temp[1])] = Integer.valueOf(temp[2]);
				MainGameWindow.playery[Integer.valueOf(temp[1])] = Integer.valueOf(temp[3]);
				if(Integer.valueOf(temp[4]) >= 5){
					MainGameWindow.WallsToDraw[Integer.valueOf(temp[1])]++;
					MainGameWindow.PlayerWalls[Integer.valueOf(temp[1])][0][MainGameWindow.WallsToDraw[Integer.valueOf(temp[1])]] = Integer.valueOf(temp[2]);
					MainGameWindow.PlayerWalls[Integer.valueOf(temp[1])][1][MainGameWindow.WallsToDraw[Integer.valueOf(temp[1])]] = Integer.valueOf(temp[3]);
				}
			}
		}
		if(message.startsWith("/forcestart")){
			Main.Player = Main.AssignedPlayer;
			Main.startgame();
		}
		else if(message.startsWith("/setReadyPlayers")){
			if(message.contains("0")){
				LobbyWindow.PlayerReady[0] = true;
			}
			if(message.contains("1")){
				LobbyWindow.PlayerReady[1] = true;
			}
			if(message.contains("2")){
				LobbyWindow.PlayerReady[2] = true;
			}
			if(message.contains("3")){
				LobbyWindow.PlayerReady[3] = true;
			}
		}
		else if(message.startsWith("/takeslot")){
			String temp[] = message.split(" ");
			Main.AssignedPlayer = Integer.parseInt(temp[1]);
			if(Integer.parseInt(temp[1]) == 0){
				LobbyWindow.User0.setText(temp[2]);
			}else if(Integer.parseInt(temp[1]) == 1){
				LobbyWindow.User1.setText(temp[2]);
			}else if(Integer.parseInt(temp[1]) == 2){
				LobbyWindow.User2.setText(temp[2]);
			}else if(Integer.parseInt(temp[1]) == 3){
				LobbyWindow.User3.setText(temp[2]);
			}
		}
		else if(message.startsWith("/updateSlots")){
			try{
				String temp[] = message.split(" ");

				if(temp[1].equals("true")){
					LobbyWindow.Join0.setEnabled(false);
				}else{
					LobbyWindow.Join0.setEnabled(true);
				}

				if(temp[3].equals("true")){
					LobbyWindow.Join1.setEnabled(false);
				}else{
					LobbyWindow.Join1.setEnabled(true);
				}

				if(temp[5].equals("true")){
					LobbyWindow.Join2.setEnabled(false);
				}else{
					LobbyWindow.Join2.setEnabled(true);
				}

				if(temp[7].equals("true")){
					LobbyWindow.Join3.setEnabled(false);
				}else{
					LobbyWindow.Join3.setEnabled(true);
				}

				if(!temp[2].equals("null")){
					LobbyWindow.User0.setText(temp[2]);
				}else{
					LobbyWindow.User0.setText("- Empty Slot -");
				}

				if(!temp[4].equals("null")){
					LobbyWindow.User1.setText(temp[4]);
				}else{
					LobbyWindow.User1.setText("- Empty Slot -");
				}

				if(!temp[6].equals("null")){
					LobbyWindow.User2.setText(temp[6]);
				}else{
					LobbyWindow.User2.setText("- Empty Slot -");
				}

				if(!temp[8].equals("null")){
					LobbyWindow.User3.setText(temp[8]);
				}else{
					LobbyWindow.User3.setText("- Empty Slot -");
				}
			}catch(Exception e){
				e.printStackTrace();
			}


			//updateSlots true Christian-PC false null false null false null
			// 0			1		2		3      4    5     6      7    8
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
