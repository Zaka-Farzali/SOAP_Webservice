package cinema;

import javax.xml.ws.BindingProvider;

import seatreservation.*;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url  = args[0];
		String row = args[1];
		String column = args[2];
		String task = args[3];
		// Create the proxy factory:
		CinemaService cinemaService = new CinemaService();
		// Create the hello proxy object:
		ICinema cinema = cinemaService.getICinemaHttpSoap11Port();
		// Cast it to a BindingProvider:
		BindingProvider bp = (BindingProvider)cinema;
		// Set the URL of the service:
		bp.getRequestContext().put(
					BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					url);
		// Call the service:
		
		switch(task) {
		case "Lock": {
			Seat s = new Seat();
			s.setRow(row);
			s.setColumn(column);
			try {
			String response = cinema.lock(s, 3);
			System.out.println(response.substring(response.length()-1));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case "Reserve": {
			Seat s = new Seat();
			s.setRow(row);
			s.setColumn(column);
			try {
			String response = cinema.lock(s, 3);
			cinema.reserve(response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case "Buy": {
			Seat s = new Seat();
			s.setRow(row);
			s.setColumn(column);
			try {
			String response = cinema.lock(s, 3);
			cinema.buy(response);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		}
	}

}
