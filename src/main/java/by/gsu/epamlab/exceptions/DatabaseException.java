package by.gsu.epamlab.exceptions;

public class DatabaseException extends WebFilmsException {

	public DatabaseException() {
		super("Database error.");
	}

	public DatabaseException(Throwable cause) {
		super("Database error.", cause);
	}
}

