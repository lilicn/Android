package edu.vanderbilt.cs282.lili.threadeddownloads;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * MyHandles which extends Handler class can handle Messages related to
 * downloading image.
 * 
 * @author Li
 * 
 */
public class MyHandler extends Handler {
	// ThreadedDownloadActivity from UI thread
	private Activity activity_;
	// ImageView for bitmap from UI thread
	private ImageView iView_;
	// progress dialog in UI thread
	private ProgressDialog progress_;

	/**
	 * MyHandler constructor method
	 * 
	 * @param activity
	 *            ThreadedDownloadActivity from UI thread
	 * @param iView
	 *            ImageView for bitmap from UI thread
	 * @param progress
	 *            progress dialog in UI thread
	 */
	public MyHandler(Activity activity, ImageView iView, ProgressDialog progress) {
		this.activity_ = activity;
		this.iView_ = iView;
		this.progress_ = progress;
	}

	/**
	 * override method for handleMessage which can handle message related to
	 * show image and dismiss progress dialog in the UI thread.
	 */
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case Util.SET_BITMAP: {
			Util.showBitmap(activity_, iView_, (Bitmap) msg.obj);
			progress_.dismiss();
			break;
		}
		}
	}
}
