package by.gsu.epamlab.services;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.entitys.RoleRepository;
import by.gsu.epamlab.dao.models.Role;

import java.sql.SQLException;

public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(CinemaDAO cinemaDAO) throws SQLException, ClassNotFoundException {
		roleRepository = cinemaDAO.getRoleRepository();
	}

	public Role getRoleById(int roleId) throws SQLException {
		return roleRepository.getById(roleId);
	}
}
