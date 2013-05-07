package com.hackerleague.mobile;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class HackDetailActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_hackdetail);
    Intent intent = this.getIntent();
    JSONObject hack = null, urls = null;
    try {
      hack = new JSONObject(intent.getStringExtra("hack"));
      urls = hack.getJSONObject("urls");
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    SimpleDateFormat renderer = new SimpleDateFormat("MM/dd/yyyy");
    String submitted_time = null;
    try {
      submitted_time = renderer.format(parser.parse(hack.getString("submitted_at")));
    } catch (ParseException e1) { e1.printStackTrace(); }
      catch (JSONException e) {e.printStackTrace(); }
    ImageView logoView = (ImageView) this.findViewById(R.id.hackLogo);
    TextView titleView = (TextView) this.findViewById(R.id.hackTitle);
    TextView totalsView = (TextView) this.findViewById(R.id.hackPeople);
    TextView submittedView = (TextView) this.findViewById(R.id.hackSubmitted);
    TextView descView = (TextView) this.findViewById(R.id.hackDesc);
    try {
      UrlImageViewHelper.setUrlDrawable(logoView, urls.getString("screenshot"));
      titleView.setText(hack.getString("name"));
      totalsView.setText("Total hackers: " + hack.getString("total_hackers"));
      submittedView.setText("Submitted At:" + submitted_time);
      descView.setText(hack.getString("description"));
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
