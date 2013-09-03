package edu.vanderbilt.cs282.lili.mapdemo;

import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.EditText;

public class Check {
	private static String numMatch = "[+-]?\\d+(\\.\\d+)?";

	public static boolean checkLatitude(String latitude) {
		if (latitude != null && !latitude.equals("") && latitude.matches(numMatch)) {
			double lat = Double.parseDouble(latitude);
			if (lat < -90 || lat > 90)
				return false;
			return true;
		}
		return false;
	}

	public static boolean checkLongitude(String longitude) {
		if (longitude != null && !longitude.equals("") && longitude.matches(numMatch)) {
			
			double lon = Double.parseDouble(longitude);
			if (lon < -180 || lon > 180)
				return false;
			return true;
		}
		return false;
	}

	public static boolean checkLocation(String latitude, String longitude) {
		return checkLatitude(latitude) && checkLongitude(longitude);
	}

	public static boolean checkIntent(PackageManager packageManager,
			Intent intent) {
		List<ResolveInfo> activities = packageManager.queryIntentActivities(
				intent, 0);
		return activities.size() > 0;
	}

}
