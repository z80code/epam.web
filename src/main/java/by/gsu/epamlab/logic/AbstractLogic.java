package by.gsu.epamlab.logic;

import by.gsu.epamlab.dao.CinemaDAO;

import java.sql.SQLException;

abstract class AbstractLogic {

	CinemaDAO cinemaService;

	public AbstractLogic(CinemaDAO cinemaService) throws ClassNotFoundException, SQLException {
		this.cinemaService = cinemaService;
	}

	public void close() throws SQLException {
		if (cinemaService != null) {
			cinemaService.close();
		}
	}
}
