package ifpb.pod.client;

import ifpb.pod.server.ChatServerIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ChatClientDriver {
	private static Scanner keyboard = new Scanner(System.in);
	public static ChatServerIF chatServer;
	
	public static void main(String[] args) throws MalformedURLException, NotBoundException {
		
		try{
			
			String chatServerURL = "rmi://150.165.250.113/RMIChatServer";
			chatServer = (ChatServerIF) Naming.lookup(chatServerURL); 
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
			ex.printStackTrace();
		}
	
	}
}
