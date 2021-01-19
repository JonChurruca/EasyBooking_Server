package server.GatewayAirline;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IAirlineGateway extends Remote {
	
   // List<FlightDTO> getFlights(String depAirport, String arrivalAirport, String depDate) throws RemoteException;    
	String getFlights(String depAirport, String arrivalAirport, String depDate) throws RemoteException;    


}
