package server.GatewayAirline;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Destination;

public class IberiaGateway implements IAirlineGateway {

	// this one will be the one using the socket 
	
	
	private String serverIP = "0.0.0.0"; 
	private int serverPort = 35600; 
	private String flight; 

	
	//List<FlightDTO> flights; 

	
	@Override
	public String getFlights(String depAirport, String arrivalAirport, String depDate)
			throws RemoteException {
		
		
		//Declaration of the socket to send/receive information to/from the server (an IP and a Port are needed)
		try (Socket tcpSocket = new Socket(serverIP, serverPort);
			 
			//Streams to send and receive information are created from the Socket
		    DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())){
			
			String pattern = depAirport + "#" + arrivalAirport + "#" + depDate; 
			// This sends the string to the translator
			// The pattern is already created in the TVProgramManager where the instance of the class is created
			out.writeUTF(pattern);
	
			//Read response (a String) from the server
			flight = in.readUTF();
			
			
		
		} catch (UnknownHostException e) {
			System.err.println("# TCPSocketClient: Socket error: " + e.getMessage());
		} catch (EOFException e) {
			System.err.println("# TCPSocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# TCPSocketClient: IO error: " + e.getMessage());
		}
	
		return flight;
	}
	
	/*
	@Override
	public List<FlightDTO> getFlights(String depAirport, String arrivalAirport, String depDate)
			throws RemoteException {
		flights = new ArrayList<>(); 
		
		//Declaration of the socket to send/receive information to/from the server (an IP and a Port are needed)
		try (Socket tcpSocket = new Socket(serverIP, serverPort);
			 
			//Streams to send and receive information are created from the Socket
		    ObjectInputStream in = new ObjectInputStream(tcpSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())){
			
			String pattern = depAirport + "#" + arrivalAirport + "#" + depDate; 
			// This sends the string to the translator
			// The pattern is already created in the TVProgramManager where the instance of the class is created
			out.writeUTF(pattern);
			
	
			//Read response (a String) from the server
			flights = in.readObject();	
		
		} catch (UnknownHostException e) {
			System.err.println("# TCPSocketClient: Socket error: " + e.getMessage());
		} catch (EOFException e) {
			System.err.println("# TCPSocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# TCPSocketClient: IO error: " + e.getMessage());
		}
	
		return flights;
	}
	*/

}
