package me.Totenfluch.other;

import java.net.Socket;

import me.Totenfluch.main.Main;

public class CheckServerThread_Dolphy implements Runnable{
	public void run() {
		if(hostAvailabilityCheck("188.194.11.106", 9976) == true){
			Main.isServerUpDolphy= 1;
		}else{
			Main.isServerUpDolphy = 2;
		}
    }

    public static void CheckServer(){
        (new Thread(new CheckServerThread_Dolphy())).start();
    }
    
	
	private boolean hostAvailabilityCheck(String ip, int port) { 
	    try (Socket s = new Socket(ip, port)) {
	        return true;
	    } catch (Exception ex) {
	        /* ignore */
	    }
	    return false;
	}

	
}
