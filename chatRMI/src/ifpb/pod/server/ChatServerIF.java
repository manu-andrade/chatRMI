package ifpb.pod.server;

import ifpb.pod.client.ChatClientIF;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerIF extends Remote{
	public void registerChatClient(ChatClientIF chatClient) throws RemoteException;
	public void broadcastMessage(String message) throws RemoteException;
	public void menu(ChatClientIF chatClient) throws RemoteException;
}
