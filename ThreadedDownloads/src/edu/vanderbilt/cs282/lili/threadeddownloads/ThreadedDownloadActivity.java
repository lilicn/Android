package edu.vanderbilt.cs282.lili.threadeddownloads;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * ThreadedDownloadActivity class which extends LifecycleLoggingActivity class,
 * provides users three currency methods to download image from the input and
 * show it on the UI thread. It can also reset the image.
 * 
 * @author Li
 * 
 */
public class ThreadedDownloadActivity extends LifecycleLoggingActivity {
	// EditText for the user input URL
	private EditText editUrl_;
	// String URL from user input
	private String url_;
	// ImageView for bitmap
	private ImageView iView_;
	// progress dialog in UI thread
	private ProgressDialog progress_;
	// Handler object in UI thread
	private Handler handler_ = new Handler();

	/**
	 * overrided method for onCreate to provide user interface to input url and
	 * four buttons to set images.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_threaded_download);
		editUrl_ = (EditText) findViewById(R.id.editURL);
		iView_ = (ImageView) findViewById(R.id.imageDisplay);
		progress_ = new ProgressDialog(this);
	}

	/**
	 * overrided method for onCreateOptionsMenu.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.threaded_download, menu);
		return true;
	}

	/**
	 * runRunnable method will be triggered when the "Run Runnable" button is
	 * clicked, it provides Runnables and Handlers model to download and show
	 * image.
	 * 
	 * @param view
	 */
	public void runRunnable(View view) {
		url_ = editUrl_.getText().toString();
		if (Util.isValidURL(url_)) {
			// set progress dialog
			Util.setProgressVisible(progress_,Util.RUNNABLEMSG);
			new Thread(new MyRunnable(this, iView_, handler_, url_,progress_)).start();
		} else {
			// make toast if url is not valid
			Toast.makeText(this, Util.INVALIDURLTOAST, Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * runMessage method will be triggered when the "Run Messages" button is
	 * clicked, it provides Messages and Handlers model to download and show
	 * image.
	 * 
	 * @param view
	 */
	public void runMessages(View view) {
		url_ = editUrl_.getText().toString();
		if (Util.isValidURL(url_)) {
			// set progress dialog
			Util.setProgressVisible(progress_,Util.MESSAGEMSG);
			final Handler h = new MyHandler(this, iView_,progress_);
			new Thread(new MyRunnable(h, url_)).start();
		} else {
			// make toast if url is not valid
			Toast.makeText(this, Util.INVALIDURLTOAST, Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * runAsyncTask method will be triggered when the "Run Async" button is
	 * clicked, it provides AsyncTask model to download and show image.
	 * 
	 * @param view
	 */
	public void runAsyncTask(View view) {
		url_ = editUrl_.getText().toString();
		if (Util.isValidURL(url_)) {
			new MyAsyncTask(this, progress_, iView_).execute(url_);
		} else {
			// make toast if url is not valid
			Toast.makeText(this, Util.INVALIDURLTOAST, Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * resetImage method will be triggered when the "Reset Image" button is
	 * clicked, it can reset the image with the original one.
	 * 
	 * @param view
	 */
	public void resetImage(View view) {
		iView_.setImageResource(Util.DEFAULTIMAGE);
	}

}
