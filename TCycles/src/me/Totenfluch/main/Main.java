package me.Totenfluch.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.Totenfluch.frames.MainGameWindow;

import javax.swing.Timer;

public class Main {
	public static MainGameWindow gameframe = null;
	public static Timer gametimer = null;
	public static Timer changeangelplus;
	public static Timer changeangelminus;
	public static void main(String[] args){
		gameframe = new MainGameWindow();
		initTimers();

	}

	private static void initTimers(){
		gametimer = new Timer(35, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gameframe.update();
			}
		});
		gametimer.start();

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


	}
}
