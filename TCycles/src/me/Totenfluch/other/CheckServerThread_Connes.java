package me.Totenfluch.other;

import java.net.Socket;

import me.Totenfluch.main.Main;

public class CheckServerThread_Connes implements Runnable{
	public void run() {
		if(hostAvailabilityCheck(Main.RootIP, 9973) == true){
			Main.isServerUpConnes = 1;
		}else{
			Main.isServerUpConnes = 2;
		}
    }

    public static void CheckServer(){
        (new Thread(new CheckServerThread_Connes())).start();
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
