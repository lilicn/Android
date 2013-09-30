package edu.vanderbilt.cs282.lili.mapdemo;

import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

/**
 * Util class is used to provide some helpful functions such as check the input,
 * verify the availability of the intent, format transformation and some final
 * static public variables.
 * 
 * @author Li
 * 
 */
public class Util {
	private final static String NUMMATCH = "[+-]?\\d+(\\.\\d+)?";
	public final static String GMAPS = "http://maps.google.com?q=";
	public final static String NOAPPTOAST = "Please install a Map or a browser!";
	public final static String INVALIDINPUTTOAST = "Please input a valid location!";

	/**
	 * getGMapsUri method provides the intent Uri of google maps.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return Uri which parses the given encoded URI string
	 */
	public static Uri getGMapsUri(String latitude, String longitude) {
		return Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude
				+ "," + longitude);
	}

	/**
	 * getBrowserGMapsUri method provides the intent Uri of browser.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return Uri which parses the given encoded URI string
	 */
	public static Uri getBrowserGMapsUri(String latitude, String longitude) {
		return Uri.parse(GMAPS + Uri.encode(latitude + "," + longitude));
	}

	/**
	 * isValidIntent method is used to check the packageManager has the provied
	 * intent.
	 * 
	 * @param packageManager
	 * @param intent
	 * @return true if the it has the intent, otherwise false
	 */
	public static boolean isValidIntent(PackageManager packageManager,
			Intent intent) {
		List<ResolveInfo> activities = packageManager.queryIntentActivities(
				intent, 0);
		return activities.size() > 0;
	}
	
	/**
	 * isValidLocation method is used to check whether the input latitude and
	 * longitude are valid or not.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return true if they are valid, otherwise false
	 */
	public static boolean isValidLocation(String latitude, String longitude) {
		return isValidLatitude(latitude) && isValidLongitude(longitude);
	}

	/**
	 * isValidLatitude method is used to check whether the input latitude is valid
	 * or not.
	 * 
	 * @param latitude
	 * @return true if it is valid, otherwise false
	 */
	public static boolean isValidLatitude(String latitude) {
		if (latitude != null && !latitude.equals("")
				&& latitude.matches(NUMMATCH)) {
			double lat = Double.parseDouble(latitude);
			if (lat < -90 || lat > 90)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * isValidLongitude method is used to check whether the input longitude is
	 * valid or not.
	 * 
	 * @param longitude
	 * @return true if it is valid, otherwise false
	 */
	public static boolean isValidLongitude(String longitude) {
		if (longitude != null && !longitude.equals("")
				&& longitude.matches(NUMMATCH)) {
			double lon = Double.parseDouble(longitude);
			if (lon < -180 || lon > 180)
				return false;
			return true;
		}
		return false;
	}

}
