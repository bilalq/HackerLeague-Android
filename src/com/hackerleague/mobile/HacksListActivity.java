package com.hackerleague.mobile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hackerleague.adapters.HacksArrayAdapter;

public class HacksListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hack);
		Intent intent = this.getIntent();

		JSONArray temphacks;
		String hacksString = intent.getStringExtra("hacks");
		try {
			if (hacksString == null || hacksString.isEmpty()) {
				temphacks = new JSONArray();
			} else {
				temphacks = new JSONArray(hacksString);
			}
		} catch (JSONException e) {
			System.out.println("WAT");
		  temphacks = new JSONArray();
		}

		final JSONArray hacks = temphacks;
		System.out.println("HACKS: " + hacks.length());

		final JSONObject[] hackObjects = new JSONObject[hacks.length()];
		for (int i = 0; i < hacks.length(); i++) {
			try {
				hackObjects[i] = hacks.getJSONObject(i);
			} catch (JSONException e) { }
		}

		HacksArrayAdapter adapter = new HacksArrayAdapter(this, hackObjects);
		this.setListAdapter(adapter);

		final ListView list = getListView();

		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				JSONObject hack = null;
				try {
					hack = hacks.getJSONObject(arg2);
					if (hack != null) {
						Intent detailIntent = new Intent(HacksListActivity.this, HackDetailActivity.class);
						detailIntent.putExtra("hack", hack.toString());
						startActivity(detailIntent);
					}
				} catch (JSONException e) { e.printStackTrace(); }

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//
		switch (item.getItemId()) {
		case R.id.action_profile:
			Intent profileIntent = new Intent(this, ProfileActivity.class);
			startActivity(profileIntent);
			return true;
		case R.id.action_past:
			Intent pastIntent = new Intent(this, HackathonsListActivity.class);
			pastIntent.putExtra("time", "past");
			startActivity(pastIntent);
			return true;
		case R.id.action_happening:
			Intent happeningIntent = new Intent(this, HackathonsListActivity.class);
			happeningIntent.putExtra("time", "happening");
			startActivity(happeningIntent);
			return true;
		case R.id.action_upcoming:
			Intent upcomingIntent = new Intent(this, HackathonsListActivity.class);
			upcomingIntent.putExtra("time", "upcoming");
			startActivity(upcomingIntent);
			return true;
		case R.id.action_logout:
			Intent logoutIntent = new Intent(this, MainActivity.class);
			logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(logoutIntent);
			return true;
		default:
			return false;
		}
	}
}
