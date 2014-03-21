package me.Totenfluch.other;

import java.net.Socket;

import me.Totenfluch.main.Main;

public class CheckServerThread_EnvyZ implements Runnable{
	public void run() {
		if(hostAvailabilityCheck(Main.RootIP, 9975) == true){
			Main.isServerUpEnvyZ = 1;
		}else{
			Main.isServerUpEnvyZ = 2;
		}
    }

    public static void CheckServer(){
        (new Thread(new CheckServerThread_EnvyZ())).start();
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
