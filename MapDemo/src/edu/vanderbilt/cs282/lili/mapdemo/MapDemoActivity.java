package edu.vanderbilt.cs282.lili.mapdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MapDemoActivity extends Activity {

	private EditText latitude_;
	private EditText longitude_;
	private String tag = "LifeCycleEvents";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_demo);

		latitude_ = (EditText) findViewById(R.id.latitude);
		longitude_ = (EditText) findViewById(R.id.longitude);
		Log.d(tag, "onCreate()");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_demo, menu);
		return true;
	}

	public void showLocation(View v) {
		String latitude = latitude_.getText().toString();
		String longitude = longitude_.getText().toString();

		if (Check.checkLocation(latitude, longitude)) {
			String geo = latitude + "," + longitude;
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"
					+ geo + "?q=" + geo));
			PackageManager packageManager = getPackageManager();
			if (Check.checkIntent(packageManager, intent)) {
				startActivity(intent);
			} else {
				intent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("http://maps.google.com?q=" + Uri.encode(geo)));
				packageManager = getPackageManager();
				if (Check.checkIntent(packageManager, intent)) {
					startActivity(intent);
				} else {
					Toast.makeText(this, "Please install a Map or a browser!",
							Toast.LENGTH_LONG).show();
				}
			}
		} else {
			Toast.makeText(this, "Please input a valid location!",
					Toast.LENGTH_LONG).show();
		}
	}

	public void onStart() {
		super.onStart();
		Log.d(tag, "onStart()");
	}

	public void onRestart() {
		super.onRestart();
		Log.d(tag, "onRestart()");
	}

	public void onResume() {
		super.onResume();
		Log.d(tag, "onResume()");
	}

	public void onPause() {
		super.onPause();
		Log.d(tag, "onPause()");
	}

	public void onStop() {
		super.onStop();
		Log.d(tag, "onStop()");
	}

	public void onDestroy() {
		super.onDestroy();
		Log.d(tag, "onDestroy()");
	}
}
