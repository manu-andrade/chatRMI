package ifpb.pod.server;

import ifpb.pod.client.ChatClientIF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerIF extends Remote{
	public void registerChatClient(ChatClientIF chatClient) throws RemoteException;
	public void broadcastMessage(ChatClientIF chatClient, String message) throws RemoteException;
	public void sendPrivateMessage (String dest, ChatClientIF chatClient, String msg) throws RemoteException;
	public String listUsers(ChatClientIF chatClient) throws RemoteException;
	public void exit(ChatClientIF chatClient) throws RemoteException;
	public void renameUser(ChatClientIF chatClient, String newName) throws RemoteException;
	public String displayMenu() throws RemoteException;
	
}
