package com.hackerleague.mobile;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class HacksListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hackslist);
		Intent intent = this.getIntent();

		String hacksString = intent.getStringExtra("hacks");
		try {
			JSONArray hacks = new JSONArray(hacksString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hacks_list, menu);
		return true;
	}

}
