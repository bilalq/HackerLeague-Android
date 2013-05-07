package com.hackerleague.adapters;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackerleague.mobile.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class HacksArrayAdapter extends ArrayAdapter<JSONObject> {
	private final Context context;
	private final JSONObject[] values;

	public HacksArrayAdapter(Context context, JSONObject[] values) {
		super(context, R.layout.hackslist, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) this.context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.hackslist, parent, false);

	    JSONObject hack = this.values[position];

	    ImageView iconView = (ImageView) rowView.findViewById(R.id.screenshot);
	    TextView titleView = (TextView) rowView.findViewById(R.id.hack_title);

	    try {
	    	String url = hack.getJSONObject("urls").getString("screenshot");
	    	if (! (url == null || url.isEmpty())) {
		    	UrlImageViewHelper.setUrlDrawable(iconView, hack.getString("screenshot"));
	    	}

		    titleView.setText(hack.getString("name"));
	    } catch(Exception e) { Log.e("hackerleague", e.toString()); }

		return rowView;
	}
}
