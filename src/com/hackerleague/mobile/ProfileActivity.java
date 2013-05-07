package com.hackerleague.mobile;

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

public class ProfileActivity extends Activity {
  @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_profile);
    HackerLeagueRestClient client = null;
    try {
      client = new HackerLeagueRestClient();
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    JSONObject user = client.getUser("Bilal");
    ImageView profileImageView = (ImageView) this.findViewById(R.id.profile_image);
    TextView nameView = (TextView) this.findViewById(R.id.name);
    TextView usernameView = (TextView) this.findViewById(R.id.username);
    TextView bioView = (TextView) this.findViewById(R.id.bio);
    try {
      UrlImageViewHelper.setUrlDrawable(profileImageView, user.getString("image"));
      nameView.setText(user.getString("name"));
      usernameView.setText(user.getString("username"));
      bioView.setText(user.getString("bio"));
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
