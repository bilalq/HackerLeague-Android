package com.hackerleague.mobile;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

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
  
}
