package server.GatewayPayment;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPaymentGateway extends Remote{
	  Integer proceedPayment(String userEmail)  throws RemoteException;


}
