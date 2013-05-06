package com.example.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Calculator extends Activity {
	private StringBuffer sb = new StringBuffer();
	int current = 0;
	int symbol = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

	public void startButton1(View view){
		sb.append("1");
		show(sb.toString());
	}	
	public void startButton2(View view){
		sb.append("2");
		show(sb.toString());
	}
	public void startButton3(View view){
		sb.append("3");
		show(sb.toString());
	}
	public void startButton4(View view){
		sb.append("4");
		show(sb.toString());
	}
	public void startButton5(View view){
		sb.append("5");
		show(sb.toString());
	}
	public void startButton6(View view){
		sb.append("6");
		show(sb.toString());
	}
	public void startButton7(View view){
		sb.append("7");
		show(sb.toString());
	}
	public void startButton8(View view){
		sb.append("8");
		show(sb.toString());
	}
	public void startButton9(View view){
		sb.append("9");
		show(sb.toString());
	}
	public void startButton10(View view){
		sb.append("0");
		show(sb.toString());
	}
	public void startButton11(View view){
		updateCurr();
		symbol = 1;	
	}
	public void startButton12(View view){
		updateCurr();
		symbol = 2;
	}
	public void startButton13(View view){
		updateCurr();
		symbol = 3;
	}
	public void startButton14(View view){
		updateCurr();
		symbol = 4;
	}
	
	public void updateCurr(){
		if(sb.length()>0){
			if(symbol>0){
				switch (symbol){
				case 1:
					current = current+Integer.parseInt(sb.toString());
					break;
				case 2:
					current = current-Integer.parseInt(sb.toString());
					break;
				case 3:
					current = current*Integer.parseInt(sb.toString());
					break;
				case 4:
					current = current/Integer.parseInt(sb.toString());
					break;
				default:
					break;
				}
			}else{
				current = Integer.parseInt(sb.toString());				
			}
			show(current+"");
			sb = new StringBuffer();
		}
	}
	
	// get the result from the user_message
	public void startButton15(View view){
		if(sb.length()>0){
			switch (symbol){
			case 1:		
				current = current+Integer.parseInt(sb.toString());	
				break;
			case 2:
				current = current-Integer.parseInt(sb.toString());		
				break;
			case 3:
				current = current*Integer.parseInt(sb.toString());	
				break;
			case 4:
				current = current/Integer.parseInt(sb.toString());	
				break;
			default:
				current = Integer.parseInt(sb.toString());			
				break;
			}
		}
		symbol = 0;
		sb = new StringBuffer();	
		show(current+"");	
	}
	public void startButton16(View view){
		sb = new StringBuffer();
		current = 0;
		symbol = 0;
		show("0");
	}
	public void startButton17(View view){
		int len = sb.length();
		if(len>0){
			sb.deleteCharAt(len-1);
			show(sb.toString());
		}else{
			StringBuffer temp = new StringBuffer();
			temp.append(current);
			len = temp.length();
			if((current>0 && len>1) || (current<0 && len>2)){
				temp.deleteCharAt(len-1);
				
				current = Integer.parseInt(temp.toString());
				show(temp.toString());
			}else{
				current = 0;
				show("0");
			}
		}
		
	}
	
	public void show(String s){
		TextView textView = (TextView)findViewById(R.id.user);
        textView.setText(s);
	}
	
}
