package me.Totenfluch.frames;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import me.Totenfluch.client.Client;
import me.Totenfluch.main.Main;

public class LobbyWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField Server;
	private JButton Ready;
	
	private Container Frame;
	
	public JTextField ID0, ID1, ID2, ID3;
	public JTextField User0, User1, User2, User3;
	public JButton Join0, Join1, Join2, Join3, back, ChatSend;
	public JTextPane Chat;
	public JTextField ChatInput;
	public JScrollPane ChatScrollPane;
	
	public StyledDocument Chatdoc;

	public boolean[] PlayerReady = new boolean[4];
	public SimpleAttributeSet ChatStyle;


	public LobbyWindow(){
		setLayout(null);
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(new DrawPane());
		setLayout(null);

		
		Frame = this.getContentPane();
		
		Server = new JTextField(Main.LobbyServerName, 10);
		Server.setFont(new Font("Impact", Font.PLAIN, 20));
		Server.setBounds(300, 25, 200, 50);
		Server.setBackground(Color.WHITE);
		Server.setEditable(false);
		add(Server);
		
		Chat = new JTextPane();
		Chat.setBounds(400, 100, 350, 225);
		Chat.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		Chat.setEditable(false);
		
		ChatScrollPane = new JScrollPane(Chat);
		ChatScrollPane.setBounds(400, 100, 375, 225);
		Frame.add(ChatScrollPane);
		
		
		Chatdoc = Chat.getStyledDocument();
		//add(Chat);
		
		ChatInput = new JTextField("");
		ChatInput.setBounds(400, 335, 290, 25);
		add(ChatInput);
		
		ChatSend = new JButton("Send");
		ChatSend.setFont(new Font("Serif", Font.PLAIN, 10));
		ChatSend.setBounds(695, 335, 55, 25);
		add(ChatSend);
		
		ID0 = new JTextField("0");
		ID0.setBounds(50, 100, 12, 25);
		ID0.setEditable(false);
		add(ID0);

		ID1 = new JTextField("1");
		ID1.setBounds(50, 130, 12, 25);
		ID1.setEditable(false);
		add(ID1);

		ID2 = new JTextField("2");
		ID2.setBounds(50, 160, 12, 25);
		ID2.setEditable(false);
		add(ID2);

		ID3 = new JTextField("3");
		ID3.setBounds(50, 190, 12, 25);
		ID3.setEditable(false);
		add(ID3);

		User0 = new JTextField("");
		User0.setBounds(75, 100, 200, 25);
		User0.setEditable(false);
		add(User0);

		User1 = new JTextField("");
		User1.setBounds(75, 130, 200, 25);
		User1.setEditable(false);
		add(User1);

		User2 = new JTextField("");
		User2.setBounds(75, 160, 200, 25);
		User2.setEditable(false);
		add(User2);

		User3 = new JTextField("");
		User3.setBounds(75, 190, 200, 25);
		User3.setEditable(false);
		add(User3);

		Join0 = new JButton("Join");
		Join0.setBounds(330, 100, 60, 25);
		add(Join0);

		Join1 = new JButton("Join");
		Join1.setBounds(330, 130, 60, 25);
		add(Join1);

		Join2 = new JButton("Join");
		Join2.setBounds(330, 160, 60, 25);
		add(Join2);

		Join3 = new JButton("Join");
		Join3.setBounds(330, 190, 60, 25);
		add(Join3);
		
		back = new JButton("Disconnect");
		back.setBounds(565, 400, 105, 35);
		add(back);

		Ready = new JButton("Ready");
		Ready.setBounds(680, 400, 70, 35);
		add(Ready);

		thehandler handler = new thehandler();
		Ready.addActionListener(handler);
		Join0.addActionListener(handler);
		Join1.addActionListener(handler);
		Join2.addActionListener(handler);
		Join3.addActionListener(handler);
		back.addActionListener(handler);
		ChatInput.addActionListener(handler);
		ChatSend.addActionListener(handler);


		setVisible(false);
		
		
		ChatStyle = new SimpleAttributeSet();
		StyleConstants.setForeground(ChatStyle, Color.BLUE);
		StyleConstants.setBold(ChatStyle, false);
	}
	

	
	public void AppendToDoc(StyledDocument doc, String s, SimpleAttributeSet Style){
		try
		{
		    doc.insertString(doc.getLength(), s+"\n", Style);
		    Chat.setCaretPosition(Chat.getDocument().getLength());
		}
		catch(Exception e) { System.out.println(e); }
	}
	

	class DrawPane extends JPanel{
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			g.setColor(Color.white);
			g.fillRect(0, 0, 1000, 1000);
			if(PlayerReady[0] == false){
				g.setColor(Color.RED);
				g.fillOval(300, 100, 20, 20);
			}else{
				g.setColor(Color.GREEN);
				g.fillOval(300, 100, 20, 20);
			}
			if(PlayerReady[1] == false){
				g.setColor(Color.RED);
				g.fillOval(300, 130, 20, 20);
			}else{
				g.setColor(Color.GREEN);
				g.fillOval(300, 130, 20, 20);
			}
			if(PlayerReady[2] == false){
				g.setColor(Color.RED);
				g.fillOval(300, 160, 20, 20);
			}else{
				g.setColor(Color.GREEN);
				g.fillOval(300, 160, 20, 20);
			}
			if(PlayerReady[3] == false){
				g.setColor(Color.RED);
				g.fillOval(300, 190, 20, 20);
			}else{
				g.setColor(Color.GREEN);
				g.fillOval(300, 190, 20, 20);
			}
		}
	}


	private class thehandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == back){
				Client.processMessage("/disconnect " + Main.SlotTaken + " " + Main.AssignedPlayer);
				setVisible(false);
				Main.disconnectfromserver();
				PlayerReady[0] = false;
				PlayerReady[1] = false;
				PlayerReady[2] = false;
				PlayerReady[3] = false;
			}
			if(e.getSource() == Ready && Main.SlotTaken == true){
				Client.processMessage("/readyup " + Main.AssignedPlayer);
			}
			if(e.getSource() == ChatInput || e.getSource() == ChatSend && !ChatInput.getText().equals(" ") && !ChatInput.getText().equals("")){
				String s = ChatInput.getText().replace(" ", "_");
				Client.processMessage("/chat " + Main.ActiveUser + " " + s);
				ChatInput.setText("");
			}
			if(Main.SlotTaken == false){
				if(e.getSource() == Join0){
					Client.processMessage("/joinslot " + "0");
					Main.SlotTaken = true;
					Join0.setEnabled(false);
				}
				if(e.getSource() == Join1){
					Client.processMessage("/joinslot " + "1");
					Main.SlotTaken = true;
					Join1.setEnabled(false);
				}
				if(e.getSource() == Join2){
					Client.processMessage("/joinslot " + "2");
					Main.SlotTaken = true;
					Join2.setEnabled(false);
				}
				if(e.getSource() == Join3){
					Client.processMessage("/joinslot " + "3");
					Main.SlotTaken = true;
					Join3.setEnabled(false);
				}
			}


		}

	}

}
