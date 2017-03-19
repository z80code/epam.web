package by.gsu.epamlab.contants;

public class Constants {

	public final static String EMPTY_STRING = "";

	/**
	 * Gomel time zone
	 */
	public final static String TIME_ZONE_MINSK = "Europe/Minsk";

	/**
	 * it is RESPONSE format messages
	 */
	public static final String RESPONSE_APPLICATION_JSON = "application/json";
	public static final String RESPONSE_ENCODING_UTF_8 = "utf-8";



	/**
	 * user login data received from frontend
	 */
	public static final String USER_LOGIN = "userlogin";
	public static final String PASSWORD = "password";

	/**
	 * base urls
	 */
	public static final String CURRENT_URI = "/";
	public final static String ANY_SUB_PATH = "*";
	public static final String ANY_URL = CURRENT_URI + ANY_SUB_PATH;
	public static final String FORWARD_LOCATION = "../";
	public static final String EDIT_LOCATION = "/edit";

	/**
	 *
	 */
	public static final String USER = "user";
	public final static String USER_PAGE_URL = CURRENT_URI + USER;
	public static final String MODERATOR = "moderator";
	public final static String MODERATOR_PAGE_URL = CURRENT_URI + MODERATOR;
	public final static String MODERATOR_EDIT_URL = CURRENT_URI + MODERATOR + EDIT_LOCATION;
	public static final String ADMIN = "admin";

	/**
	 * login and logout urls
	 */
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";

	/**
	 * TODO delete it some later
	 */
	public static final String FORWARD_LOGOUT = "../logout";

	/**
	 * 	user urls
	 */
	public static final String USER_ANY = "/user/*";
	public static final String USER_RESERVING = "/user/reserving";
	public static final String USER_INDEX_HTML = "/user/index.html";
	public static final String USER_RECEIVE_HTML = "/user/receive.html";

	/**
	 * 	moderator urls
	 */
	public static final String MODERATOR_ANY = "/moderator/*";
	public static final String MODERATOR_RESERVING = "/moderator/reserving";
	public static final String MODERATOR_INDEX_HTML = "/moderator/index.html";
	public static final String MODERATOR_EDIT_HTML = "/moderator/moderator.html";
	public static final String MODERATOR_RECEIVE_HTML = "/moderator/receive.html";

	/**
	 * 	cookie EXPIRY mode
	 */
	public static final int EXPIRY_FOR_SESSION = -1;
	public static final int EXPIRY_DELETE = 0;

	/**
	 * cookie values
	 */
	public static final String SESSION_ID = "sessionId";

	/**
	 * api urls
	 */
	public final static String API_ANY_URL = "/api/*";
	public final static String API_RESERVE_URL = "/api/reserve/";
	public final static String API_FILM_URL = "/api/film/";
	public final static String API_SESSION_URL = "/api/session/";
	public final static String API_MODERATOR_URL = "/api/moderator/";


	/**
	 * date/time format string
	 */
	public final static String VIEW_DATETIME_FORMAT = "dd MMM yyyy kk:mm";

	/**
	 * Error messages
	 */
	public final static String BAD_PARAMETERS = "Parameters are incorrect.";
	public static final String NOT_AUTHORIZED_USER = "Not authorized user.";
	public static final String SOME_PLACES_ARE_BUSY_JET = "Some places are busy jet.";
}
