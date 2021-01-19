package server.datanucleous;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reservation {
	
	@PrimaryKey
	private Integer paymentID; 
	private Integer nSeats; 
	private String email; 
	private Integer flightID;
	
	public Reservation(Integer paymentID, Integer nSeats, String email, Integer flightID) {
		super();
		this.paymentID = paymentID;
		this.nSeats = nSeats;
		this.email = email;
		this.flightID = flightID;
	}
	public Integer getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(Integer paymentID) {
		this.paymentID = paymentID;
	}
	public Integer getnSeats() {
		return nSeats;
	}
	public void setnSeats(Integer nSeats) {
		this.nSeats = nSeats;
	}

	public String getEmail() {
		return email;
	}
	public void setUserEmail(String email) {
		this.email = email;
	}
	public Integer getFlightID() {
		return flightID;
	}
	public void setFlightID(Integer flightID) {
		this.flightID = flightID;
	} 
	
	

}