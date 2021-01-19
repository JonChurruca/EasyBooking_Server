package server;


import java.rmi.Naming;

import server.remote.IReservationManager;
import server.remote.ReservationManager;

public class EBServer{
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IReservationManager EasyBookingServer = new ReservationManager(args[2]);
			Naming.rebind(name, EasyBookingServer);
			System.out.println("- EasyBooking '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("$ EasyBooking exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}


