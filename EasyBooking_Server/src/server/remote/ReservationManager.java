
package server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import server.GatewayAirline.GatewaySingelton;
import server.GatewayAirline.IAirlineGateway;
import server.GatewayAuthorization.IloginGateway;
import server.GatewayAuthorization.LoginGateway;
import server.GatewayPayment.IPaymentGateway;
import server.GatewayPayment.PaymentGateway;
import server.datanucleous.EasyBookingDAO;


import server.datanucleous.FlightDTO;
import server.datanucleous.FlightAssambler;
import server.datanucleous.Flight;

import server.datanucleous.IEasyBookingDAO;
import server.datanucleous.Reservation;
import server.datanucleous.User;



public class ReservationManager extends UnicastRemoteObject implements IReservationManager{

	private static final long serialVersionUID = 1L;
	
	private String airlines[] = {"Vueling", "Iberia"}; 

	private IEasyBookingDAO dao; 
	private IloginGateway loginGateway; 
	private IAirlineGateway airlineGateway; 
	private IPaymentGateway paymentGateway;
	
	private String serverName;
	
	private FlightAssambler flightAssambler = new FlightAssambler(); 

	
	
	
	// CONSTRUCTOR 
	public ReservationManager(String serverName ) throws RemoteException {
		super();
		this.serverName = serverName;
		dao = new EasyBookingDAO();
		
	}
	
	public boolean validateUser(String email, String password) throws RemoteException {
		boolean validation = false;
		loginGateway = new LoginGateway(); 
		
		validation = loginGateway.sendValidation(email, password); 
		
		return validation;
	}

	@Override
	public 	void registerUser(String email, String password, String cardNumber, String defaultAirport, String authorizationService) {
		// create an instance of the user so we can store it on our DB
		User user = new User( email,  defaultAirport,  authorizationService,  cardNumber); 
		// call the DAO and using the register user method store it
		try {
			dao.registerUser(user);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	/*
	@Override 
	public List<Integer> getFlights(String depAirport, String arrivalAirport, String depDate) throws RemoteException {
		
		
		List<Integer> flights = new ArrayList<>();
		
		for (String airline : airlines) {
			
		
			flights = (GatewaySingelton.singelton(airline).getFlights(depAirport, arrivalAirport, depDate)); 
		}
		
		return flights;
	}
*/
	@Override
	public void makeReservation(Integer flightID, Integer numSeats, String userEmail) throws RemoteException {
		
		paymentGateway = new PaymentGateway(); 
		
		Integer paymentID = paymentGateway.proceedPayment(userEmail); 
		
		Reservation reservation = new Reservation(paymentID, numSeats, userEmail, flightID); 
		
		dao.registerReservation(reservation); 
		
	}



	
	@Override 
	public List<FlightDTO> getFlights(String depAirport, String arrivalAirport, String depDate) throws RemoteException {
		
		String DELIMITER = "#"; 
		
		//List<FlightDTO> flightsDTO = new ArrayList<FlightDTO>();
		
		List<Flight> flights = new ArrayList<>(); 
		
		String airlineReturn; 
		
		for (String a : airlines) {
			
			System.out.println(a);
			airlineReturn = (GatewaySingelton.singelton(a).getFlights(depAirport, arrivalAirport, depDate)); 
			
			if (airlineReturn != null && !airlineReturn.trim().isEmpty()) {
				try {
					StringTokenizer tokenizer = new StringTokenizer(airlineReturn, DELIMITER);
				
					String flightIDString = tokenizer.nextToken(); 
					String airline = tokenizer.nextToken(); 
					String arrivalDate = tokenizer.nextToken(); 
					String departureDate = tokenizer.nextToken(); 
					String arrivalTime = tokenizer.nextToken(); 
					String depTime = tokenizer.nextToken(); 
					String departureAirport = tokenizer.nextToken(); 
					String arrivAirport = tokenizer.nextToken(); 
					
					
					int flightID = Integer.parseInt(flightIDString); 
					
					Flight f = new Flight(flightID, arrivalDate, departureDate, arrivalTime, depTime, departureAirport, arrivAirport, airline);					
					flights.add(f); 
					System.out.println(flights);

					}catch (Exception e) {
					System.err.println("   # API error:" + e.getMessage());
				}
			}
	
		
	}
		
		//System.out.println(flightAssambler.assemble(flights).get(0).toString());
		//List<FlightDTO>  flightsDTO = flightAssambler.assemble(flights); 
		//System.out.println("Sale del for");
		return flightAssambler.assemble(flights); 

	}
}
