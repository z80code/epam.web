package by.gsu.epamlab.exceptions;

public class UserExistException extends WebFilmsException {

    public UserExistException() {
        super("User is exist.");
    }
}
