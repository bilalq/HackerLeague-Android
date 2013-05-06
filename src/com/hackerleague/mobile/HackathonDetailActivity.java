package com.hackerleague.mobile;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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

	    String location;
	    if (city.compareToIgnoreCase("Anywhere") == 0) {
	    	location = "Anywhere";
	    } else {
	    	location = city + ", " + "state" + ", " + country;
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
//	    }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
