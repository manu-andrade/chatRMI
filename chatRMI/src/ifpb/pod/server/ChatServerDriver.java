package ifpb.pod.server;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServerDriver {
	
	public static void main(String[] args){
		try {
			 LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			Naming.rebind("RMIChatServer",new ChatServer());
			java.net.InetAddress inetAdress = java.net.InetAddress.getLocalHost();  
            String ip = inetAdress.getHostAddress();  
            System.out.println("Ip do servidor = " + ip);
		} catch (RemoteException | MalformedURLException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
