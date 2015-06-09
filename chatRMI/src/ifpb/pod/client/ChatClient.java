package ifpb.pod.client;

import ifpb.pod.server.ChatServerIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF {
	private static final long serialVersionUID = 1L;
	private String name = null;
	private static Scanner keyboard = new Scanner(System.in);
	
	public static ChatServerIF chatServer;
	

	protected ChatClient(String name) throws RemoteException {
		this.name = name;
		
		try {
			String chatServerURL = "rmi://10.0.4.70/RMIChatServer";
			chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void retrieveMessage(String message) throws RemoteException {
		System.out.println(message);
	}
	
		
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

<<<<<<< HEAD
	public static void main(String[] args) {
		
		try{
			System.out.println("Digite o nome do usuário:");
        	ChatClient cliente = new ChatClient(keyboard.nextLine());
        	while(true){
    			chatServer.menu(cliente);
    			String cmd = keyboard.nextLine();
    			switch (cmd){
    				case "1":
    					chatServer.broadcastMessage(message);
    					break;
    					
    			}
    			
    		}
		}catch (RemoteException ex) {
=======
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String chatServerURL = "rmi://10.0.4.70/RMIChatServer";
		ChatServerIF chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
		chatServer.registerChatClient(new ChatClient("Amanda"));
		chatServer.broadcastMessage("Enviei");
		
		while(true){
			
>>>>>>> branch 'master' of https://github.com/manu-andrade/chatRMI.git
		}
		
		
		
		
		
	
	
	}

	
}
