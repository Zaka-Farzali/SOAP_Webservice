package WebService_B9BQXB.impl;

import seatreservation.Seat;

public class SeatStatusLock {
	private Seat seat;
	private int lockId = -1;
	private int count;
	
	public void setStatusLock(Seat seat, int count) {
		this.seat = seat;
		this.count = count;
	}
	
	public Seat getSeat() {
		return seat;
	}

	public int getCount() {
		return count;
	}
	
	public void setLockId(int id) {
		lockId = id;
	}
	
	public int getLockId() {
		return lockId;
	}
}
