package server.GatewayAuthorization;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import AuthorizationSystemServices.IAuthorizationService; 


public class LoginGateway extends UnicastRemoteObject implements IloginGateway  {
	

	private static final long serialVersionUID = 1L;
	private String serverIP  = "127.0.0.1"; 
	private int serverPort = 1099;
	private String serverName = "AuthorizationSystem"; 
	
	public LoginGateway() throws RemoteException  {
		super();
	
	}

	@Override
	public boolean sendValidation(String email, String password) throws RemoteException  {
		
		
		boolean validation  = false; 
		try {
			String name = "//" + serverIP + ":" + serverPort + "/" + serverName;
			// change this 
			//String name = "//120.0.1:1099/Hello";
			IAuthorizationService authServer = (IAuthorizationService) java.rmi.Naming.lookup(name);
		
			// Parameters --> login, password
			validation = authServer.sendValidation(email, password); 
		
			
		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
	
		
		return validation;
	}
	

}
