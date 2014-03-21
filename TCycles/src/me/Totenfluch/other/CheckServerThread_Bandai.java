package me.Totenfluch.other;

import java.net.Socket;

import me.Totenfluch.main.Main;

public class CheckServerThread_Bandai implements Runnable{
	public void run() {
		if(hostAvailabilityCheck(Main.RootIP, 9974) == true){
			Main.isServerUpBandai = 1;
		}else{
			Main.isServerUpBandai = 2;
		}
    }

    public static void CheckServer(){
        (new Thread(new CheckServerThread_Bandai())).start();
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
