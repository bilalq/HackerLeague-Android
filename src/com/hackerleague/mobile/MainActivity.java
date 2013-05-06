package com.hackerleague.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		EditText password = (EditText) this.findViewById(R.id.passwordText);
		password.setOnKeyListener(new OnKeyListener(){
		    @Override
		    public boolean onKey(View v, int keyCode, KeyEvent event){
		        if(keyCode == event.KEYCODE_ENTER){
		            MainActivity.this.login(null);
		        	return true;
		        }
				return false;
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	public void login(View view) {
		Activity mainAct = this;

		//Login here
		String username = ((EditText)mainAct.findViewById(R.id.usernameText)).getText().toString();
		String password = ((EditText)mainAct.findViewById(R.id.passwordText)).getText().toString();


		if (username.equalsIgnoreCase("bilalq") && password.equals("password")) {
			//Success
			Intent myIntent = new Intent(this, HackathonsListActivity.class);
			myIntent.putExtra("username", username); //Optional parameters
			mainAct.startActivity(myIntent);
		} else {
			Context context = getApplicationContext();
			CharSequence text = "Invalid Username/Password";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

}
