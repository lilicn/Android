package edu.vanderbilt.cs282.lili.threadeddownloads;

import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * MyAsyncTask class which extends AsycTask class, will download image in the
 * background and show the image in the UI thread.
 * 
 * @author Li
 * 
 */
public class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {
	// ImageView for bitmap from UI thread
	private ImageView iView_;
	// Progress dialog
	private ProgressDialog progress_;
	// ThreadedDownloadActivity from UI thread
	private Activity activity_;

	/**
	 * MyAsyncTask constructor method initialize several member variables.
	 * 
	 * @param activity
	 *            ThreadedDownloadActivity from UI thread
	 * @param progress
	 *            Progress dialog
	 * @param iView
	 *            ImageView for bitmap from UI thread
	 */
	protected MyAsyncTask(Activity activity, ProgressDialog progress,
			ImageView iView) {
		super();
		this.activity_ = activity;
		this.progress_ = progress;
		this.iView_ = iView;
	}

	/**
	 * overrided method for onPreExecute will initialize the progress dialog
	 */
	@Override
	protected void onPreExecute() {
		Util.setProgressVisible(progress_,Util.ASYNCTASKMSG);
	}

	/**
	 * overrided method for doInBackground will download the image using url.
	 */
	@Override
	protected Bitmap doInBackground(String... URL) {
		String imageURL = URL[0];
		Bitmap bitmap = null;
		try {
			// Download Image from URL
			InputStream input = new java.net.URL(imageURL).openStream();
			
			// Decode Bitmap
			bitmap = BitmapFactory.decodeStream(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * overrided method for onProgressupdate will set the progress value to the
	 * progress dialog.
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		progress_.setProgress(values[0]);
	}

	/**
	 * overrided method for onPostExecute will show the image in the UI thread
	 * and dismiss the progress dialog
	 */
	@Override
	protected void onPostExecute(Bitmap result) {
		Util.showBitmap(activity_, iView_, result);
		progress_.dismiss();
	}
}
