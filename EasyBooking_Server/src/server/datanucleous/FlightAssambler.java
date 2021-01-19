package server.datanucleous;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import server.datanucleous.Flight;



public class FlightAssambler implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public List<FlightDTO> assemble(List<Flight> flights) {
		
		List<FlightDTO> flightsDTO = new ArrayList<>();
		
		for (Flight f : flights) {
			
			System.out.println(f.getFlightID());
			
			flightsDTO.add(new FlightDTO(f.getFlightID(), f.getArrivalDate(), f.getDepDate(), f.getArrivalDate(), f.getDepDate(), f.getDepAirport(),
					f.getArrivalAirport(), f.getAirline()));
			
			
		}
		
		//System.out.println(flightsDTO.get(0).toString()); 
		
		return flightsDTO;
	}

}
