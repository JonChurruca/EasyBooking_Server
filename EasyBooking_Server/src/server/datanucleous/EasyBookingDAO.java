package server.datanucleous;


import java.rmi.RemoteException;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;



public class EasyBookingDAO implements IEasyBookingDAO{
	
	private PersistenceManagerFactory pmf = null;
	private PersistenceManager pm = null; 
	private Transaction tx = null; 
	
	public EasyBookingDAO(){
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	

	@Override
	public void registerUser(User user) {
		pm = pmf.getPersistenceManager();  
		tx = pm.currentTransaction();
		
		// INSERT user into DB 
		
		try {				
			
			tx.begin();
			
			pm.makePersistent(user);
			
			System.out.println("Inserting user into the database ....");
			tx.commit();
		} catch (Exception ex) {
			System.out.println("# Error storing user: " + ex.getMessage());				
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public void registerReservation(Reservation reservation) {
		pm = pmf.getPersistenceManager();  
		tx = pm.currentTransaction();
		
		try {						
			tx.begin();
			pm.makePersistent(reservation);
		
			
			System.out.println("Inserting reservations into the database ....");
			tx.commit();
		} catch (Exception ex) {
			System.out.println("# Error storing objects: " + ex.getMessage());				
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			
			pm.close();
		}
	}




	

}
