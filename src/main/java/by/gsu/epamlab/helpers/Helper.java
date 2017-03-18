package by.gsu.epamlab.helpers;

import by.gsu.epamlab.contants.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

import static by.gsu.epamlab.contants.Constants.*;

public final class Helper {

	public interface Func<T1, T2> {
		T2 operation(T1 source);
	}

	private static <T1, T2> T2[] forEach(T1[] array, Func<T1, T2> func, Class<T2> t2Class) {
		List<Object> resultList = new ArrayList<>();
		for (T1 item : array) {
			T2 passedItem = func.operation(item);
			if(passedItem!=null){
				resultList.add(passedItem);
			}
		}
		return resultList.toArray((T2[])Array.newInstance(t2Class, resultList.size()) );
	}

	public static <T1, T2> List<T2> forEach(Collection<T1> collection, Func<T1, T2> func) {
		List<T2> resultList = new ArrayList<>();
		for (T1 item : collection) {
			T2 passedItem = func.operation(item);
			if(passedItem!=null){
				resultList.add(passedItem);
			}
		}
		return resultList;
	}

	public static Integer[] csvIndexesParser(String csvString) {
		final String DIVIDER = ";";
		String[] strArgs = csvString.split(DIVIDER);
		return Helper.forEach(strArgs, new Func<String, Integer>() {
			@Override
			public Integer operation(String source) {
				return Integer.parseInt(source);
			}
		}, Integer.class);
	}

	public static Date getCurrentDameTime(){
		// warning hardcode
		TimeZone.setDefault(TimeZone.getTimeZone(Constants.TIME_ZONE_MINSK));
		return new Date(new java.util.Date().getTime());
	}

	public static void setCookie(String cookieName, String userName, HttpServletResponse resp){
		Cookie cookie = new Cookie(cookieName, userName);
		cookie.setPath(CURRENT_URI);
		cookie.setMaxAge(EXPIRY_FOR_SESSION);
		resp.addCookie(cookie);
	}

	public static void deleteCookie(String cookieName, HttpServletResponse resp){
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(EXPIRY_DELETE);
		resp.addCookie(cookie);
	}
}
