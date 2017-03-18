package by.gsu.epamlab.enums;

public enum SeatState {
	FREE,
	RESERVE,
	SOLD;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}