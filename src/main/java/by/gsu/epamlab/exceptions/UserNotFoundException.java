package by.gsu.epamlab.exceptions;

public class UserNotFoundException extends WebFilmsException {
    public UserNotFoundException() {
        super("User not found.");
    }
}
