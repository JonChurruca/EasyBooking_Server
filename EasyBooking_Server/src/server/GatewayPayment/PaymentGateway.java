package server.GatewayPayment;

import PaypalServices.IPaymentServer; 

public class PaymentGateway implements IPaymentGateway{
	
	private String serverIP = "127.0.0.1"; 
	private int serverPort  = 1099;
	private String serverName = "Paypal" ; 

	@Override
	public Integer proceedPayment(String userEmail) {

		Integer paymentID = 0; 
		try {
			String name = "//" + serverIP + ":" + serverPort + "/" + serverName;
			
			IPaymentServer pServer = (IPaymentServer) java.rmi.Naming.lookup(name);
		
			// Parameters --> login, password
			paymentID = pServer.proceedPayment(userEmail); 
		
			
		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
	
		
		return paymentID;
	}
		
	}


