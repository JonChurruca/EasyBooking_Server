package server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import server.datanucleous.FlightDTO;

public interface IReservationManager extends Remote{
	
	boolean validateUser(String email, String password) throws RemoteException;
	void registerUser(String email, String password, String CardNumber, String DefaultAirport, String AuthorizationService)throws RemoteException; 
	List<FlightDTO> getFlights(String depAirport, String arrivalAirport, String depDate) throws RemoteException;
	//List<Integer> getFlights(String depAirport, String arrivalAirport, String depDate) throws RemoteException;
	void makeReservation(Integer flightID, Integer numSeats, String userEmail) throws RemoteException; 

}
