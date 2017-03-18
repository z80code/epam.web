package by.gsu.epamlab.enums;

public enum RequestStatus {
	OK,
	AUTH_ERROR,
	ERROR;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
