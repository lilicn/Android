package com.example.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Map extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	public void showMap(View view){
		EditText addressText = (EditText) findViewById(R.id.location);
		String address = addressText.getText().toString();
		startActivity(new Intent(android.content.Intent.ACTION_VIEW,
		Uri.parse("geo:0,0?q=" + address)));
	}
}
