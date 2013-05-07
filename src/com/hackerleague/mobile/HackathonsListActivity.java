package com.hackerleague.mobile;

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
		  String time;
		  Intent self = this.getIntent();
		  if (self != null) {
		      time = this.getIntent().getStringExtra("time");
		  } else {
		      time = null;
		  }
		  if (time == null) {
		    hackathons = client.getHackathons();
		  } else if(time.equals("past")) {
		    hackathons = client.getPastHackathons();
		  } else if(time.equals("happening")) {
		    hackathons = client.getCurrentHackathons();
		  } else if(time.equals("upcoming")) {
		    hackathons = client.getFutureHackathons();
		  } else {
		    hackathons = client.getHackathons();
		  }
		} catch (JSONException e1) { e1.printStackTrace(); }

		HackathonArrayAdapter adapter = new HackathonArrayAdapter(this, hackathons);

		this.setListAdapter(adapter);

		final ListView list = getListView();
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				JSONObject obj = (JSONObject) list.getItemAtPosition(arg2);
				try {
					Intent detailIntent = new Intent(HackathonsListActivity.this, HackathonDetailActivity.class);
					detailIntent.putExtra("id", obj.getString("id"));
					detailIntent.putExtra("name", obj.getString("name"));
					detailIntent.putExtra("logo", obj.getString("logo"));
					detailIntent.putExtra("description", obj.getString("description"));
					detailIntent.putExtra("start_time", obj.getString("start_time"));
					detailIntent.putExtra("end_time", obj.getString("end_time"));
					detailIntent.putExtra("total_hackers", obj.getInt("total_hackers"));
					detailIntent.putExtra("total_hacks", obj.getInt("total_hacks"));
					detailIntent.putExtra("hacks", obj.getJSONArray("hacks").toString());
					try {
						JSONObject location = obj.getJSONObject("location");
						detailIntent.putExtra("city", location.getString("city"));
						detailIntent.putExtra("state", location.getString("state"));
						detailIntent.putExtra("country", location.getString("country"));
					} catch (JSONException e) { e.printStackTrace(); }

					startActivity(detailIntent);
				} catch (JSONException e) { e.printStackTrace(); }
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.main, menu);
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
