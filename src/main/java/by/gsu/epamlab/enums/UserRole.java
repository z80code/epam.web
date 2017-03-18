package by.gsu.epamlab.enums;

public enum UserRole {
	USER,
	MODERATOR,
	ADMIN;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
