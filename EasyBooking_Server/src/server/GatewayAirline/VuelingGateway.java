package server.GatewayAirline;

import java.rmi.RemoteException;
import java.util.List;

import VuelingServices.IVuelingService; 


public class VuelingGateway implements IAirlineGateway{

	
	// this one will be the one using RMI !!!

	//List<FlightDTO> flights;
	//List<Integer> flights;
	
	private String serverIP = "127.0.0.1"; 
	private int serverPort  = 1099;
	private String serverName = "Vueling" ; 
	private String flight; 
	
	public VuelingGateway() {
		super();
	
	}

	@Override
	public String getFlights(String depAirport, String arrivalAirport, String depDate)
			throws RemoteException {
		
		
		try {
			String name = "//" + serverIP + ":" + serverPort + "/" + serverName; 
		
			IVuelingService vuelingServ = (IVuelingService) java.rmi.Naming.lookup(name);
		
			// Parameters --> login, password
			flight = vuelingServ.getFlights(depAirport, arrivalAirport, depDate); 
		
			
		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
	
		
		return flight;
	}	
	

	/*
	@Override
	public List<FlightDTO> getFlights(String depAirport, String arrivalAirport, String depDate)
			throws RemoteException {
		
		
		try {
			String name = "//" + serverIP + ":" + serverPort + "/" + serverName; 
		
			IVuelingService vuelingServ = (IVuelingService) java.rmi.Naming.lookup(name);
		
			// Parameters --> login, password
			flights = vuelingServ.searchFlight(depAirport, arrivalAirport, depDate); 
		
			
		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}

		return flights;
	}	
	*/

}
