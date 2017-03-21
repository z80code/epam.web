package by.gsu.epamlab.enums;

public enum SeatState {
	FREE,
	RESERVED,
	SOLD;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}