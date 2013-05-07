package com.hackerleague.mobile;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class HackDetailActivity extends Activity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.hackathon_detail);
    Intent intent = this.getIntent();
    JSONObject hack;
    try {
      hack = new JSONObject(intent.getStringExtra("hack"));
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    this.getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
  
}
