package by.gsu.epamlab.models;

import by.gsu.epamlab.enums.UserRole;

public class ViewUser {
	private int userId;
	private String name;
	private String login;
	private UserRole role;

	public ViewUser(int userId, String name, String login, UserRole role) {
		this.userId = userId;
		this.name = name;
		this.login = login;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}

	public UserRole getRole() {
		return role;
	}
}
