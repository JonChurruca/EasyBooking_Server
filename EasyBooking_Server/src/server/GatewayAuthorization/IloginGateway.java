package server.GatewayAuthorization;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IloginGateway extends Remote{
	  boolean sendValidation(String email, String password) throws RemoteException;
		
}
