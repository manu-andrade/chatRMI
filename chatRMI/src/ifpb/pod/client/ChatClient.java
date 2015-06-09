package ifpb.pod.client;

import ifpb.pod.server.ChatServerIF;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF {
	private static final long serialVersionUID = 1L;
	private String name = null;
	

	protected ChatClient(String name) throws RemoteException {
		this.name = name;
		
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

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String chatServerURL = "rmi://10.0.4.70/RMIChatServer";
		ChatServerIF chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
		chatServer.registerChatClient(new ChatClient("Amanda"));
		chatServer.broadcastMessage("Enviei");
		
		while(true){
			
		}
		

	}
}
