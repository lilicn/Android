package com.example.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.firstapp.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Always call the superclass first
		setContentView(R.layout.activity_main);
	}
	
	/** Called when the user clicks the Send button */
	public void startResult(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	public void startCalulator(View view){
		Intent intent = new Intent(MainActivity.this, Calculator.class);
        startActivity(intent);
	}
	
	public void startMap(View view){
		Intent intent = new Intent(MainActivity.this, Map.class);
        startActivity(intent);
	}
}
