package com.hackerleague.mobile;

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
	    SimpleDateFormat renderer = new SimpleDateFormat("dd/MM/yyyy");
	    start_time = renderer.format(parser.parse(start_time));
	    end_time = renderer.format(parser.parse(end_time));

	    ImageView logoView = (ImageView) this.findViewById(R.id.hackathonLogo);
	    TextView titleView = (TextView) this.findViewById(R.id.hackathonTitle);
	    TextView totalsView = (TextView) this.findViewById(R.id.hackathonTotals);
	    TextView totalsView = (TextView) this.findViewById(R.id.hackathonTotals);
	    TextView totalsView = (TextView) this.findViewById(R.id.hackathonTotals);
	    TextView totalsView = (TextView) this.findViewById(R.id.hackathonTotals);
	    try {
	    	UrlImageViewHelper.setUrlDrawable(logoView, logo);
		    titleView.setText(name);
		    dateView.setText(formattedDate);
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
