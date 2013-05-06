package com.hackerleague.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.main, menu);
		MenuItem item = menu.findItem(R.id.action_profile);
		item.setVisible(false);
		return true;
	}

	public void login(View view) {
		Activity mainAct = this;

		//Login here
		String username = ((EditText)mainAct.findViewById(R.id.emailText)).getText().toString();
		String password = ((EditText)mainAct.findViewById(R.id.passwordText)).getText().toString();


		//Success
		Intent myIntent = new Intent(this, HackathonsListActivity.class);
		myIntent.putExtra("username", username); //Optional parameters
		mainAct.startActivity(myIntent);
	}

}
