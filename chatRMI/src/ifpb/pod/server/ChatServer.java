package ifpb.pod.server;

import ifpb.pod.client.ChatClientIF;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ChatServer extends UnicastRemoteObject implements ChatServerIF {
	private static final long serialVersionUID = 1L;
	private ArrayList<ChatClientIF> chatClients;

	protected ChatServer() throws RemoteException {
		chatClients = new ArrayList<ChatClientIF>();
	}

	@Override
	public synchronized void registerChatClient(ChatClientIF chatClient)
			throws RemoteException {
		
		for(ChatClientIF cc : chatClients){
			if(cc.getName().equals(chatClient.getName())){
				chatClient.retrieveMessage("Oops! Esse nome ja existe! Try again >]");
			}
		}
		this.chatClients.add(chatClient);
		broadcastMessage(chatClient, chatClient.getName() + " entrou na sala.");
		//chatClient.retrieveMessage(chatClient.getName() + " entrou na sala.");
	}

	@Override
	public synchronized void broadcastMessage(ChatClientIF chatClient, String message) throws RemoteException {
			
		for(ChatClientIF client : chatClients){
			//if(!chatClient.getName().equals(client.getName())){
				sendPrivateMessage(client.getName(), chatClient, message);
			//}
		}
	}

	public void displayMenu(ChatClientIF chatClient) throws RemoteException{
		String msg = "---------------------------------------------------\n"
				    + "--------------BEM VINDOS AO CHAT RMI---------------\n"
				    + "---------------------------------------------------\n"
				    + "| 1 - Enviar Mensagem                             |\n"
				    + "| 2 - Enviar Mensagem Privada                     |\n"
				    + "| 3 - Listar Usuarios Online                      |\n"
				    + "| 4 - Renomear Usuario                            |\n"
				    + "| 5 - Sair                                        |\n"
				    + "---------------------------------------------------";
		
		chatClient.retrieveMessage(msg);
	}

	@Override
	public void sendPrivateMessage(String dest, ChatClientIF chatClient,
			String msg) throws RemoteException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		Date d = new Date();
		String date = dateFormat.format(d);
		int flag = 0;
		
		for(ChatClientIF cc : chatClients){
			if(cc.getName().equals(dest)){
				flag = 1;
				cc.retrieveMessage(chatClient.getName() +": " + msg + " < " + date + " >");
			}
		}
		
		if(flag == 0){
			chatClient.retrieveMessage("Usuario inexistente");
		}
		
	}

	public String listUsers(ChatClientIF chatClient) throws RemoteException {
		String list = "Lista de usuarios conectados: \n";
		for(ChatClientIF cc : chatClients){
			list += cc.getName() + "\n";
		}
		return list;
		
		
	}

	@Override
	public void exit(ChatClientIF chatClient) throws RemoteException {
		chatClients.remove(chatClient);
		chatClient.retrieveMessage(chatClient.getName() + " Saiu da sala.");
		
	}

	@Override
	public void renameUser(ChatClientIF chatClient, String newName)
			throws RemoteException {
		if((newName.length() == 0) || (newName.trim().equals(""))){
			chatClient.retrieveMessage("Nome invalido");
			return;
		}
		for(ChatClientIF cc : chatClients){
			if(cc.getName().equals(newName)){
				chatClient.retrieveMessage("Nome de usuario ja existente. Escolha outro!");
				return;
			}
		}
		chatClients.get(chatClients.indexOf(chatClient)).setName(newName);
		chatClient.retrieveMessage("Nome alterado para: " + newName);
		
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

}
