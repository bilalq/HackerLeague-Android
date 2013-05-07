package com.hackerleague.mobile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hackerleague.adapters.HacksArrayAdapter;

public class HacksListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hackslist);
		Intent intent = this.getIntent();

		final JSONArray hacks;
		String hacksString = intent.getStringExtra("hacks");
		try {
			if (hacksString == null || hacksString.isEmpty()) {
				hacks = new JSONArray();
			} else {
				hacks = new JSONArray(hacksString);
			}
		} catch (JSONException e) {
		}

		HacksArrayAdapter adapter = new HacksArrayAdapter(this, hacks);
		this.setListAdapter(adapter);

		final ListView list = getListView();

		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				JSONObject hack;
				try {
					hack = hacks.getJSONObject(arg2);
					if (hack != null) {
						Intent detailIntent = new Intent(HacksListActivity.this, HackDetailActivity.class);
						detailIntent.putExtra("hack", hack.toString());
						startActivity(detailIntent);
					}
				} catch (JSONException e) { e.printStackTrace(); }

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hacks_list, menu);
		return true;
	}

}
