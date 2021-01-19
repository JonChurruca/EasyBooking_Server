package server.datanucleous;



import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class User {
	@PrimaryKey
	private String email; 
	private String defaultAirport; 
	private String authorizationSystem; 
	private String card; 
	
	//@Persistent(mappedBy="user", dependentElement="true")

	//@Join
	//private List<Reservation> reservations = new ArrayList<>();

	
	public User(String email, String defaultAirport, String authorizationSystem, String card) {
		this.email = email;
		this.defaultAirport = defaultAirport;
		this.authorizationSystem = authorizationSystem;
		this.card = card;
	}




	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDefaultAirport() {
		return defaultAirport;
	}


	public void setDefaultAirport(String defaultAirport) {
		this.defaultAirport = defaultAirport;
	}


	public String getAuthorizationSystem() {
		return authorizationSystem;
	}


	public void setAuthorizationSystem(String authorizationSystem) {
		this.authorizationSystem = authorizationSystem;
	}


	public String getCard() {
		return card;
	}


	public void setCard(String card) {
		this.card = card;
	}


	
	
	

}
