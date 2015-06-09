package ifpb.pod.server;

import ifpb.pod.client.ChatClientIF;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {
	private static final long serialVersionUID = 1L;
	private ArrayList<ChatClientIF> chatClients;

	protected ChatServer() throws RemoteException {
		chatClients = new ArrayList<ChatClientIF>();
	}

	@Override
	public synchronized void registerChatClient(ChatClientIF chatClient)
			throws RemoteException {
		chatClient.retrieveMessage("Aceita Galã");
		this.chatClients.add(chatClient);
	}

	@Override
	public synchronized void broadcastMessage(String message) throws RemoteException {
		int i = 0;
		
		while(i < chatClients.size()){
			chatClients.get(i++).retrieveMessage(message);
		}
	}
	
	public static void main(String[] args){
		try {
			 LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			Naming.rebind("RMIChatServer",new ChatServer());
			java.net.InetAddress inetAdress = java.net.InetAddress.getLocalHost();  
            String ip = inetAdress.getHostAddress();  
            System.out.println(ip);
		} catch (RemoteException | MalformedURLException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void menu(ChatClientIF chatClient) throws RemoteException {
		
		/*
		 * 
		 * ("\n	Bem vindo ao CHAT LINE "+nome+"! Menu:\n\n"
				1 : Envia mensagem para todos os participantes do grupo\n"
				2 : Envia mensagem para usuario específico\n"
				3 : Listar todos os participantes do grupo\n"
				4 : Altera seu username\n"
				5 : Sair do grupo\n\n");
		
		 */
	}

}
