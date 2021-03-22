package WebService_B9BQXB.impl;

import seatreservation.Seat;
import seatreservation.SeatStatus;

public class Status {
	private Seat seat;
	private SeatStatus seatstatus;
	
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	public void setStatus(SeatStatus seatstatus) {
		this.seatstatus = seatstatus;
	}
	
	public Seat getSeat() {
		return seat;
	}
	
	public SeatStatus getSeatStatus() {
		return seatstatus;
	}
}
