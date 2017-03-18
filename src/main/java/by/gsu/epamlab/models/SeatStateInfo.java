package by.gsu.epamlab.models;

import by.gsu.epamlab.enums.SeatState;

public class SeatStateInfo {

		private int seatId;
		private SeatState seatState;

	public SeatStateInfo(int seatId, SeatState seatState) {
		this.seatId = seatId;
		this.seatState = seatState;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public SeatState getSeatState() {
		return seatState;
	}

	public void setSeatState(SeatState seatState) {
		this.seatState = seatState;
	}
}
