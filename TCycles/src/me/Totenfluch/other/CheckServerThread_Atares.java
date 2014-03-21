package me.Totenfluch.other;

import java.net.Socket;

import me.Totenfluch.main.Main;

public class CheckServerThread_Atares implements Runnable{
	public void run() {
		if(hostAvailabilityCheck(Main.RootIP, 9977) == true){
			Main.isServerUpAtares = 1;
		}else{
			Main.isServerUpAtares = 2;
		}
    }

    public static void CheckServer(){
        (new Thread(new CheckServerThread_Atares())).start();
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
