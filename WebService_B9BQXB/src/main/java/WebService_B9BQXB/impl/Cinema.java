package WebService_B9BQXB.impl;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import seatreservation.*;

@WebService(
		name="CinemaService",
		portName="ICinema_HttpSoap11_Port",
		targetNamespace="http://www.iit.bme.hu/soi/hw/SeatReservation",
		endpointInterface="seatreservation.ICinema",
		wsdlLocation="WEB-INF/wsdl/SeatReservation.wsdl")
public class Cinema implements ICinema{
	String [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
	Init room = new Init();
	ArrayOfSeat arrayOfSeat = new ArrayOfSeat();
	List<Status> statuslist = new ArrayList<Status>();
	List<SeatStatusLock> seatstatuslocklist = new ArrayList<SeatStatusLock>();
	int id = 0;
	@Override
	public void init(int rows, int columns) throws ICinemaInitCinemaException {
		// TODO Auto-generated method stub
		if(rows < 1 || rows>26) {
			throw new ICinemaInitCinemaException("Invalid row number", new CinemaException());	
		}
		if(columns < 1 || columns>100) {
			throw new ICinemaInitCinemaException("Invalid column number", new CinemaException());	
		}
		room.setRows(rows);
		room.setColumns(columns);
		List<Seat> seatlist = arrayOfSeat.getSeat();
		seatlist.clear();
		for(int i = 0; i < rows; i++) {
			for(int j = 1; j <= columns; j++ ) {
				Seat seat=new Seat();
				seat.setRow(alphabet[i]);
				seat.setColumn(String.valueOf(j));
				seatlist.add(seat);
				Status status = new Status();
				status.setSeat(seat);
				status.setStatus(SeatStatus.FREE);
				statuslist.add(status);
			}
		} 
	}

	@Override
	public ArrayOfSeat getAllSeats() throws ICinemaGetAllSeatsCinemaException {
		GetAllSeatsResponse getallseats = new GetAllSeatsResponse();
		getallseats.setGetAllSeatsResult(arrayOfSeat);
		return getallseats.getGetAllSeatsResult();
	}

	@Override
	public SeatStatus getSeatStatus(Seat seat) throws ICinemaGetSeatStatusCinemaException {
		
		// TODO Auto-generated method stub
		SeatStatus status;
		GetSeatStatusResponse response = new GetSeatStatusResponse();
		boolean alert = true;
		for(Status s : statuslist) {
			if(seat.getRow().equals(s.getSeat().getRow()) && seat.getColumn().equals(s.getSeat().getColumn())) {
				status = s.getSeatStatus();
				response.setGetSeatStatusResult(status);
				alert=false;
			}
		}
		if (alert) {
			throw new ICinemaGetSeatStatusCinemaException("Bad seat number",new CinemaException());
		}
		return response.getGetSeatStatusResult();
	}

		@Override
		public String lock(Seat seat, int count) throws ICinemaLockCinemaException {
			// TODO Auto-generated method stub
			boolean ff = false;
			int temp = 0;
			if (Integer.parseInt(seat.getColumn()) - 1 + count > room.getColumns()) {
				throw new ICinemaLockCinemaException("Not enough seats", new CinemaException());
			}
			for(Status s : statuslist) {
				if(seat.getRow().equals(s.getSeat().getRow()) && seat.getColumn().equals(s.getSeat().getColumn())) {
					ff = true;
				}
				if(ff) {
					if(s.getSeatStatus().equals(SeatStatus.RESERVED)) {
						throw new ICinemaLockCinemaException("Seat is not free", new CinemaException());
					}
					if(s.getSeatStatus().equals(SeatStatus.SOLD)) {
						throw new ICinemaLockCinemaException("Seat is not free", new CinemaException());
					}
					if(s.getSeatStatus().equals(SeatStatus.LOCKED)) {
						throw new ICinemaLockCinemaException("Seat is not free", new CinemaException());
					}
					temp++;
					if(temp==count) {
						ff = false;
						temp = 0;
					}
				}
			}

			SeatStatusLock seatstatuslock = new SeatStatusLock();
			seatstatuslock.setStatusLock(seat, count);
			seatstatuslock.setLockId(id);
			id++;
			seatstatuslocklist.add(seatstatuslock);
			for(Status s : statuslist) {
				if(seat.getRow().equals(s.getSeat().getRow()) && seat.getColumn().equals(s.getSeat().getColumn())) {
					ff = true;
				}
				if(ff) {
					s.setStatus(SeatStatus.LOCKED);
					temp++;
					if(temp==count) {
						ff = false;
						temp = 0;
					}
				}
			}
			return "lock" + seatstatuslock.getLockId();
	}

	@Override
	public void unlock(String lockId) throws ICinemaUnlockCinemaException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(lockId.substring(lockId.length()-1));
		Seat seat = new Seat();
		int count = 0;
		for(SeatStatusLock s : seatstatuslocklist) {
			if (s.getLockId() == id) {
				seat = s.getSeat();
				count = s.getCount();
				break;
			}
		}
		boolean ff = false;
		int temp = 0;
		for(Status s : statuslist) {
			if(seat.getRow().equals(s.getSeat().getRow()) && seat.getColumn().equals(s.getSeat().getColumn())) {
				ff = true;
			}
			if(ff) {
				s.setStatus(SeatStatus.FREE);
				temp++;
				if(temp==count) {
					ff = false;
					temp = 0;
				}
			}
		}
		
	}

	@Override
	public void reserve(String lockId) throws ICinemaReserveCinemaException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(lockId.substring(lockId.length()-1));
		Seat seat = new Seat();
		int count = 0;
		for(SeatStatusLock s : seatstatuslocklist) {
			if (s.getLockId() == id) {
				seat = s.getSeat();
				count = s.getCount();
				break;
			}
		}
		boolean ff = false;
		int temp = 0;
		for(Status s : statuslist) {
			if(seat.getRow().equals(s.getSeat().getRow()) && seat.getColumn().equals(s.getSeat().getColumn())) {
				ff = true;
			}
			if(ff) {
				s.setStatus(SeatStatus.RESERVED);
				temp++;
				if(temp==count) {
					ff = false;
					temp = 0;
				}
			}
		}		
	}

	@Override
	public void buy(String lockId) throws ICinemaBuyCinemaException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(lockId.substring(lockId.length()-1));
		Seat seat = new Seat();
		int count = 0;
		for(SeatStatusLock s : seatstatuslocklist) {
			if (s.getLockId() == id) {
				seat = s.getSeat();
				count = s.getCount();
				break;
			}
		}
		boolean ff = false;
		int temp = 0;
		for(Status s : statuslist) {
			if(seat.getRow().equals(s.getSeat().getRow()) && seat.getColumn().equals(s.getSeat().getColumn())) {
				ff = true;
			}
			if(ff) {
				s.setStatus(SeatStatus.SOLD );
				temp++;
				if(temp==count) {
					ff = false;
					temp = 0;
				}
			}
		}
	}
	 
}
