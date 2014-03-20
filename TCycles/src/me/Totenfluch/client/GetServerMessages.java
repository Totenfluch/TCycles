package me.Totenfluch.client;

import javax.swing.JOptionPane;

import me.Totenfluch.frames.MainGameWindow;
import me.Totenfluch.main.Main;


public class GetServerMessages{
	public static String newestreply = null;


	public static void CheckServerMessages(String message){
		if(message.startsWith("/setPos")){
			String[] temp = message.split(" ");
			if(Integer.valueOf(temp[1]) != Main.Player){
				if(Integer.valueOf(temp[4]) >= 5){
					MainGameWindow.WallsToDraw[Integer.valueOf(temp[1])]++;
					MainGameWindow.PlayerWalls[Integer.valueOf(temp[1])][0][MainGameWindow.WallsToDraw[Integer.valueOf(temp[1])]] = Integer.valueOf(temp[2]);
					MainGameWindow.PlayerWalls[Integer.valueOf(temp[1])][1][MainGameWindow.WallsToDraw[Integer.valueOf(temp[1])]] = Integer.valueOf(temp[3]);
					MainGameWindow.playerx[Integer.valueOf(temp[1])] = Integer.valueOf(temp[2]);
					MainGameWindow.playery[Integer.valueOf(temp[1])] = Integer.valueOf(temp[3]);
				}else{
					MainGameWindow.playerx[Integer.valueOf(temp[1])] = Integer.valueOf(temp[2]);
					MainGameWindow.playery[Integer.valueOf(temp[1])] = Integer.valueOf(temp[3]);
				}
			}
		}
		
		if(message.startsWith("/forceRestart")){
			if(Main.gameframe.isDeath == false){
				Main.gameframe.won = true;
			}
			Main.restartgame();
			Main.gameframe.isDeath = true;
			Main.isRespawning = true;
			String[] temp = message.split(" ");
			Main.gameframe.Round = Integer.parseInt(temp[1])+1;
		}
		else if(message.startsWith("/forceEndGame")){
			JOptionPane.showMessageDialog(null, "The Game is over! Thanks for Playing!");
			System.exit(0);
		}
		else if(message.startsWith("/forcestart")){
			if(Main.AssignedPlayer != -1){
				Main.Player = Main.AssignedPlayer;
				Main.startgame();
			}else{
				JOptionPane.showMessageDialog(null, "The game has started but you didn't pick a slot.");
			}
		}
		else if(message.startsWith("/setReadyPlayers")){
			if(message.contains("0")){
				Main.lobbyframe.PlayerReady[0] = true;
			}
			if(message.contains("1")){
				Main.lobbyframe.PlayerReady[1] = true;
			}
			if(message.contains("2")){
				Main.lobbyframe.PlayerReady[2] = true;
			}
			if(message.contains("3")){
				Main.lobbyframe.PlayerReady[3] = true;
			}
		}
		else if(message.startsWith("/takeslot")){
			String temp[] = message.split(" ");
			Main.AssignedPlayer = Integer.parseInt(temp[1]);
			if(Integer.parseInt(temp[1]) == 0){
				Main.lobbyframe.User0.setText(temp[2]);
			}else if(Integer.parseInt(temp[1]) == 1){
				Main.lobbyframe.User1.setText(temp[2]);
			}else if(Integer.parseInt(temp[1]) == 2){
				Main.lobbyframe.User2.setText(temp[2]);
			}else if(Integer.parseInt(temp[1]) == 3){
				Main.lobbyframe.User3.setText(temp[2]);
			}
		}
		else if(message.startsWith("/updateSlots")){
			try{
				String temp[] = message.split(" ");

				if(temp[1].equals("true")){
					Main.lobbyframe.Join0.setEnabled(false);
				}else{
					Main.lobbyframe.Join0.setEnabled(true);
				}

				if(temp[3].equals("true")){
					Main.lobbyframe.Join1.setEnabled(false);
				}else{
					Main.lobbyframe.Join1.setEnabled(true);
				}

				if(temp[5].equals("true")){
					Main.lobbyframe.Join2.setEnabled(false);
				}else{
					Main.lobbyframe.Join2.setEnabled(true);
				}

				if(temp[7].equals("true")){
					Main.lobbyframe.Join3.setEnabled(false);
				}else{
					Main.lobbyframe.Join3.setEnabled(true);
				}

				if(!temp[2].equals("null")){
					Main.lobbyframe.User0.setText(temp[2]);
				}else{
					Main.lobbyframe.User0.setText("- Empty Slot -");
				}

				if(!temp[4].equals("null")){
					Main.lobbyframe.User1.setText(temp[4]);
				}else{
					Main.lobbyframe.User1.setText("- Empty Slot -");
				}

				if(!temp[6].equals("null")){
					Main.lobbyframe.User2.setText(temp[6]);
				}else{
					Main.lobbyframe.User2.setText("- Empty Slot -");
				}

				if(!temp[8].equals("null")){
					Main.lobbyframe.User3.setText(temp[8]);
				}else{
					Main.lobbyframe.User3.setText("- Empty Slot -");
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

		else if(message.startsWith("You are connected to TCycle-Server-1")){
			Main.chatframe.IsConnectedToServer = true;
		}

		else if(message.startsWith("ServerIsShuttingDown")){
			Main.disconnectfromserver();
			Main.chatframe.disconnected = true;
			Main.chatframe.IsConnectedToServer = false;
		}

		else if(message.startsWith("forcelogin")){

		}

		else if(message.equals("Authorized")){

		}

		else if(message.startsWith("broadcast Account") && message.contains("created")){

		}

		else if(message.startsWith("broadcast")){
			JOptionPane.showMessageDialog(null, message);
		}
	}
}
