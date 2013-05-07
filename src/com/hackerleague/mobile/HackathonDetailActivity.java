package com.hackerleague.mobile;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class HackathonDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.hackathon_detail);
		Intent intent = this.getIntent();

		String id = intent.getStringExtra("id");
		String name = intent.getStringExtra("name");
		String logo = intent.getStringExtra("logo");
		String description = intent.getStringExtra("description");
		String start_time = intent.getStringExtra("start_time");
		String end_time = intent.getStringExtra("end_time");
		String city = intent.getStringExtra("city");
		String state = intent.getStringExtra("state");
		String country = intent.getStringExtra("country");
		int total_hackers = intent.getIntExtra("total_hackers", 0);
		int total_hacks = intent.getIntExtra("total_hacks", 0);
		final String hacks = intent.getStringExtra("hacks");

		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		SimpleDateFormat renderer = new SimpleDateFormat("MM/dd/yyyy");
		try {
			start_time = renderer.format(parser.parse(start_time));
			end_time = renderer.format(parser.parse(end_time));
		} catch (ParseException e1) { e1.printStackTrace(); }

		ImageView logoView = (ImageView) this.findViewById(R.id.hackathonLogo);
		TextView titleView = (TextView) this.findViewById(R.id.hackathonTitle);
		TextView totalsView = (TextView) this.findViewById(R.id.hackathonTotals);
		TextView startView = (TextView) this.findViewById(R.id.hackathonStart);
		TextView endView = (TextView) this.findViewById(R.id.hackathonEnd);
		TextView descView = (TextView) this.findViewById(R.id.hackathonDesc);
		TextView locationView = (TextView) this.findViewById(R.id.hackathonLocation);

		Button hacksButton = (Button) this.findViewById(R.id.hacks_button);
		hacksButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent hacksIntent = new Intent(HackathonDetailActivity.this, HacksListActivity.class);
				hacksIntent.putExtra("hacks", hacks);
				startActivity(hacksIntent);
			}
		});

		String location;
		if (city.compareToIgnoreCase("Anywhere") == 0) {
			location = "Anywhere";
		} else {
			location = city + ", " + state + ", " + country;
		}

		try {
			UrlImageViewHelper.setUrlDrawable(logoView, logo);
			titleView.setText(name);
			totalsView.setText("Total Hackers: " + total_hackers + " | Total Hacks: " + total_hacks);
			startView.setText("Start Date: " + start_time);
			endView.setText("End Date: " + end_time);
			descView.setText(description);
			locationView.setText(location);
		} catch(Exception e) {
			Log.e("hackerleague", e.toString());
		}

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
