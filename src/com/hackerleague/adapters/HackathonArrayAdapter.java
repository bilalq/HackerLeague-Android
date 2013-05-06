package com.hackerleague.adapters;

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

public class HackathonArrayAdapter extends ArrayAdapter<JSONObject> {
	private final Context context;
	  private final JSONObject[] values;

	  public HackathonArrayAdapter(Context context, JSONObject[] values) {
	    super(context, R.layout.hackathonsrowlayout, values);
	    this.context = context;
	    this.values = values;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) this.context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.hackathonsrowlayout, parent, false);
	    ImageView iconView = (ImageView) rowView.findViewById(R.id.icon);
	    TextView titleView = (TextView) rowView.findViewById(R.id.title);
	    TextView dateView = (TextView) rowView.findViewById(R.id.date);


	    JSONObject hackathon = this.values[position];

	    try {
	    	UrlImageViewHelper.setUrlDrawable(iconView, hackathon.getString("logo"));
		    titleView.setText(hackathon.getString("name"));
		    dateView.setText(hackathon.getString("start_time"));
	    } catch(Exception e) {
	    	Log.e("hackerleague", e.toString());
	    }

	    return rowView;
	  }
}