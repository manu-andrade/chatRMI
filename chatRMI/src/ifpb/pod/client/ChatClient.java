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
			String chatServerURL = "rmi://10.0.142.90/RMIChatServer";
			chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
//			while(!chatServer.registerChatClient(this)){
//            	this.name = keyboard.nextLine();
//            }
			
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

	public static void main(String[] args) {
		
		try{
			System.out.println("Digite o nome do usuário:");
        	ChatClient cliente = new ChatClient(keyboard.nextLine());
        	chatServer.registerChatClient(cliente);
        	while(true){
    			chatServer.displayMenu(cliente);
    			String cmd = keyboard.nextLine();
    			switch (cmd){
    				case "1":
    					chatServer.broadcastMessage(cliente, keyboard.nextLine());
    					break;
    				case "2":
    					System.out.print("Digite o nome do destinatario:");
    					String dest = keyboard.nextLine();
    					System.out.println("Mensagem:");
    					chatServer.sendPrivateMessage(dest , cliente, keyboard.nextLine());
    					break;
    				case "3":
    					System.out.println(chatServer.listUsers(cliente));
    					break;
    				case "4":
    					chatServer.renameUser(cliente, keyboard.nextLine());
    					break;
    				case "5":
    					chatServer.exit(cliente);
    					return;
    				default:
    					System.out.println("Comando Inválido!");
    					break;
    			}
    			
    		}
		}catch (RemoteException ex) {
		}
		
		
				
	
	
	}

	
}

