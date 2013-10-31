package edu.vanderbilt.cs282.lili.threadeddownloads;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Util class provides final static variables and static methods for other
 * classes
 * 
 * @author Li
 * 
 */
public class Util {
	// Regular expression for URL
	private final static String URLREGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	// Default image
	public final static int DEFAULTIMAGE = R.drawable.einstein;
	// toast message showing invalid url
	public final static String INVALIDURLTOAST = "Please input valid url";
	// toast message showing fail download
	public final static String FAILDOWNLOADTOAST = "Sorry, download image failed";
	// message for set bitmap
	public final static int SET_BITMAP = 0;
	// title of progress dialog
	public final static String PROGRESSTITLE = "Download";
	// message of handle & message for progress dialog
	public final static String MESSAGEMSG = "downloading via Handle & Message";
	// message of handle & runnable for progress dialog
	public final static String RUNNABLEMSG = "downloading via Handle & Runnable";
	// message of AsyncTask for progress dialog
	public final static String ASYNCTASKMSG = "downloading via AsyncTask";
	
	/**
	 * isValidURL method checks if the url is valid.
	 * 
	 * @param url
	 *            String URL from user input
	 * @return
	 */
	public static boolean isValidURL(String url) {
		if (url == null || url.equals(""))
			return false;
		return url.matches(URLREGEX);
	}

	/**
	 * downloadBitmap method download image by using the url.
	 * 
	 * @param url
	 *            String URL from user input
	 * @return
	 * @throws IOException
	 */
	public static Bitmap downloadBitmap(String url) throws IOException {
		Bitmap bitmap = null;
		try {
			// Download Image from URL
			InputStream input = new java.net.URL(url).openStream();
			// Decode Bitmap
			bitmap = BitmapFactory.decodeStream(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * showBitmap method set the imageview with a bitmap, and it will send a
	 * faildownload toast if the bitmap is null
	 * 
	 * @param activity
	 *            ThreadedDownloadActivity from UI thread
	 * @param iView
	 *            ImageView for bitmap from UI thread
	 * @param bm
	 *            Bitmap downloaded
	 */
	public static void showBitmap(Activity activity, ImageView iView, Bitmap bm) {
		if (bm != null)
			iView.setImageBitmap(bm);
		else
			Toast.makeText(activity, Util.FAILDOWNLOADTOAST, Toast.LENGTH_LONG)
					.show();
	}

	/**
	 * setProgressVisible method will set the progress dialog visible and
	 * initialize the title and message.
	 * 
	 * @param progress
	 *            Progress dialog in UI thread
	 */
	public static void setProgressVisible(ProgressDialog progress, String msg) {
		// Set progressdialog title
		progress.setTitle(PROGRESSTITLE);
		// Set progressdialog message
		progress.setMessage(msg);
		progress.setIndeterminate(false);
		// Show progressdialog
		progress.show();
	}
}
