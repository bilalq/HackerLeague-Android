package com.hackerleague.mobile;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.hackerleague.adapters.HackathonArrayAdapter;


public class HackathonsListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_hackathon);

		JSONObject[] hackathons = new JSONObject[10];


		try {
			for(int i = 0; i < hackathons.length; i++) {
				JSONObject hackathonHRU = new JSONObject();

				hackathonHRU.put("icon", "http://placekitten.com/50/50");
				hackathonHRU.put("title", "HackRU " + i);
				hackathonHRU.put("date", "February 12, 1991");

				hackathons[i] = hackathonHRU;
			}
		} catch (JSONException e) {
	    	Log.e("hackerleague", e.toString());
		}

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
