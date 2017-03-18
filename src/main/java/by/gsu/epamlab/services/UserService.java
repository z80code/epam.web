package by.gsu.epamlab.services;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.entitys.UserRepository;
import by.gsu.epamlab.dao.models.User;

import java.sql.SQLException;

public class UserService {

	private UserRepository userRepository;

	public UserService(CinemaDAO cinemaDAO) throws SQLException, ClassNotFoundException {
		userRepository = cinemaDAO.getUserRepository();
	}

	public User getUserByLogin(String login) throws SQLException, ClassNotFoundException {
		return userRepository.getByLogin(login);
	}

	public boolean checkUserPassword(String login, String password) throws SQLException, ClassNotFoundException {

		User user = getUserByLogin(login);

		if(user==null) {
			return false;
		}
			return user.getPassword().equals(password);
	}

	public boolean addUser(User user) {
		return false;
	}
}
