package com.hackerleague.mobile;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.hackerleague.adapters.HackathonArrayAdapter;


public class HackathonsListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_hackathon);

		HackerLeagueRestClient client = null;
		try {
			client = new HackerLeagueRestClient();
		} catch (JSONException e1) { e1.printStackTrace(); }

		JSONObject[] hackathons = null;
		try {
			hackathons = client.getHackathons(0, -1);
		} catch (JSONException e1) { e1.printStackTrace(); }

		HackathonArrayAdapter adapter = new HackathonArrayAdapter(this, hackathons);

		this.setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
