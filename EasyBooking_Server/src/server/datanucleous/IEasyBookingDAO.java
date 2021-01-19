package server.datanucleous;

import java.rmi.Remote;
import java.rmi.RemoteException;



public interface IEasyBookingDAO extends Remote {
	public void registerUser(User user) throws RemoteException; 
	public void registerReservation(Reservation reservation) throws RemoteException; 

}
