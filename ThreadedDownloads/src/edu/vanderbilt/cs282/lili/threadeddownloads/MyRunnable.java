package edu.vanderbilt.cs282.lili.threadeddownloads;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

/**
 * MyRunnable class which implements Runnable interface provides two kinds of
 * Runnable objects
 * 
 * @author Li
 * 
 */
public class MyRunnable implements Runnable {
	// Handler object if isPost_, otherwise MyHandler object
	private Handler handler_;
	// String url from user input
	private String url_;
	// ThreadedDownloadActivity from UI thread
	private Activity activity_;
	// ImageView for bitmap from UI thread
	private ImageView iView_;
	// true if it is for Handle & Runnable, false for Handler & Message
	private boolean isPost_;
	// progress dialog in UI thread
	private ProgressDialog progress_;

	/**
	 * MyRunnable constructor method to create a Runnable object for Runnables
	 * and Handlers model.
	 * 
	 * @param activity
	 *            ThreadedDownloadActivity from UI thread
	 * @param iView
	 *            ImageView for bitmap from UI thread
	 * @param handler
	 *            Handler object from UI thread
	 * @param url
	 *            String url from user input
	 * @param progress
	 *            progress dialog in UI thread
	 */
	public MyRunnable(Activity activity, ImageView iView, Handler handler,
			String url, ProgressDialog progress) {
		this.activity_ = activity;
		this.iView_ = iView;
		this.handler_ = handler;
		this.url_ = url;
		this.isPost_ = true;
		this.progress_ = progress;
	}

	/**
	 * MyRunnable constructor method to create a Runnable object for Messages
	 * and Handlers model.
	 * 
	 * @param handler
	 *            MyHandler object from UI thread
	 * @param url
	 *            String url from user input
	 */
	public MyRunnable(Handler handler, String url) {
		this.handler_ = handler;
		this.url_ = url;
		this.isPost_ = false;
	}

	/**
	 * override method for run which can either post runnable or send message.
	 */
	@Override
	public void run() {
		if (isPost_)
			runPost();
		else
			runSend();
	}

	/**
	 * runPost method to let handler in the UI thread post Runnable
	 */
	public void runPost() {
		try {
			final Bitmap bm = Util.downloadBitmap(url_);
			handler_.post(new Runnable() {
				public void run() {
					Util.showBitmap(activity_, iView_, bm);
					progress_.dismiss();
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * runSend method to make message and let handler send message to UI thread.
	 */
	public void runSend() {
		try {
			Bitmap bm = Util.downloadBitmap(url_);
			handler_.sendMessage(handler_.obtainMessage(Util.SET_BITMAP, bm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
