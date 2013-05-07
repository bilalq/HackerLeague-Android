package com.hackerleague.adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.hackerleague.mobile.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class HacksArrayAdapter extends ArrayAdapter<JSONArray> {
	private final Context context;
	private final JSONArray values;

	public HacksArrayAdapter(Context context, JSONArray values) {
		super(context, R.layout.hackslist);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) this.context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.hackslist, parent, false);

	    JSONObject hack = null;
		try {
			hack = this.values.getJSONObject(position);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	    try {
	    	UrlImageViewHelper.setUrlDrawable(iconView, hackathon.getString("logo"));
		    titleView.setText(hackathon.getString("name"));
	    } catch(Exception e) {
	    	Log.e("hackerleague", e.toString());
	    }

		return rowView;
	}
}
