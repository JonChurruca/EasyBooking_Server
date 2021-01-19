package server.GatewayAirline;


import java.rmi.RemoteException;
import java.util.List; 

public class GatewaySingelton{
	
	private static GatewaySingelton gf = new GatewaySingelton(); 
	
	private static IAirlineGateway vuelingGateway; 
	private static IAirlineGateway iberiaGateway; 

	
	public static IAirlineGateway singelton(String airline) {
		
		if (airline.equals("Vueling")) {
			vuelingGateway = new VuelingGateway(); 
			
			return vuelingGateway; 
		}else {
			
			iberiaGateway = new IberiaGateway(); 
			return iberiaGateway; 
		}
		
	
	}



	

}
