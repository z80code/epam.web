package by.gsu.epamlab.dao;

import by.gsu.epamlab.dao.entitys.*;
import by.gsu.epamlab.factories.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class CinemaDAO {

	private FilmRepository filmsDAO;
	private FilmGenreRepository filmGenreRepository;
	private GenreRepository genreRepository;
	private DirectorRepository directorRepository;
	private CastRepository castRepository;
	private ActorRepository actorRepository;
	private SessionRepository sessionRepository;
	private SoldPlaceRepository soldPlaceRepository;
	private PlaceRepository placeRepository;
	private TheaterRepository theaterRepository;
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	private CinemaDAO(Connection connection) throws ClassNotFoundException, SQLException {
		this.filmsDAO = new FilmRepository(connection);
		this.filmGenreRepository = new FilmGenreRepository(connection);
		this.genreRepository = new GenreRepository(connection);
		this.directorRepository = new DirectorRepository(connection);
		this.castRepository = new CastRepository(connection);
		this.actorRepository = new ActorRepository(connection);
		this.sessionRepository = new SessionRepository(connection);
		this.soldPlaceRepository = new SoldPlaceRepository(connection);
		this.placeRepository = new PlaceRepository(connection);
		this.theaterRepository = new TheaterRepository(connection);
		this.userRepository = new UserRepository(connection);
		this.roleRepository = new RoleRepository(connection);
	}

	public static CinemaDAO getInstance() throws SQLException, ClassNotFoundException {
		return new CinemaDAO(ConnectionFactory.getMySQLConnection());
	}

	public FilmRepository getFilmsRepository() {
		return filmsDAO;
	}

	public FilmGenreRepository getFilmGenreRepository() {
		return filmGenreRepository;
	}

	public GenreRepository getGenreRepository() {
		return genreRepository;
	}

	public DirectorRepository getDirectorRepository() {
		return directorRepository;
	}

	public CastRepository getCastRepository() {
		return castRepository;
	}

	public ActorRepository getActorRepository() {
		return actorRepository;
	}

	public SessionRepository getSessionRepository() {
		return sessionRepository;
	}

	public SoldPlaceRepository getSoldPlaceRepository() {
		return soldPlaceRepository;
	}

	public PlaceRepository getPlaceRepository() {
		return placeRepository;
	}

	public TheaterRepository getTheaterRepository() {
		return theaterRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void close() throws SQLException {
		if (filmsDAO != null) {
			filmsDAO.close();
		}
		if (filmGenreRepository != null) {
			filmGenreRepository.close();
		}
		if (genreRepository != null) {
			genreRepository.close();
			if (directorRepository != null) {
				directorRepository.close();
			}
			if (castRepository != null) {
				castRepository.close();
			}
			if (actorRepository != null) {
				actorRepository.close();
			}
			if (sessionRepository != null) {
				sessionRepository.close();
			}
			if (soldPlaceRepository != null) {
				soldPlaceRepository.close();
			}
			if (placeRepository != null) {
				placeRepository.close();
			}
			if (theaterRepository != null) {
				theaterRepository.close();
			}
			if (userRepository != null) {
				userRepository.close();
			}
			if (roleRepository != null) {
				roleRepository.close();
			}
		}
	}
}
