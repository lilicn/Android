package edu.vanderbilt.cs282.lili.mapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * MapDemoActivity class is used to provide users a UI to input a location with
 * parameters of latitude and longitude, and show them the exact location on the
 * map.
 * 
 * @author Li
 * 
 */
public class MapDemoActivity extends LifecycleLoggingActivity {

	private EditText latitude_;
	private EditText longitude_;

	/**
	 * overrided method for onCreate.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_demo);

		latitude_ = (EditText) findViewById(R.id.latitude);
		longitude_ = (EditText) findViewById(R.id.longitude);
	}

	/**
	 * overrided method for onCreateOptionsMenu.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_demo, menu);
		return true;
	}

	/**
	 * showLocation method will be triggered when the "Show Location" button is
	 * clicked.
	 * 
	 * @param v
	 */
	public void showLocation(View v) {
		String latitude = latitude_.getText().toString();
		String longitude = longitude_.getText().toString();

		if (Util.isValidLocation(latitude, longitude)) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Util.getGMapsUri(
					latitude, longitude));
			if (Util.isValidIntent(getPackageManager(), intent)) {
				startActivity(intent);
			} else {
				intent = new Intent(Intent.ACTION_VIEW,
						Util.getBrowserGMapsUri(latitude, longitude));
				if (Util.isValidIntent(getPackageManager(), intent)) {
					startActivity(intent);
				} else {
					Toast.makeText(this, Util.NOAPPTOAST, Toast.LENGTH_LONG)
							.show();
				}
			}
		} else {
			Toast.makeText(this, Util.INVALIDINPUTTOAST, Toast.LENGTH_LONG)
					.show();
		}
	}

}
