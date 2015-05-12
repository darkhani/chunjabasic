package com.tegine.utils;

import java.util.List;
import com.tegine.chunjabasic.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataValueObjectListAdapterFourMode extends ArrayAdapter<DataValueObject> {
	private  List<DataValueObject>data;
	private int layoutResourceId;
	private Context context;
	private Activity mActivity;

	public DataValueObjectListAdapterFourMode(Activity act, Context context, int layoutResourceId, List<DataValueObject>data) {
		super(act,layoutResourceId,data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		this.mActivity = act;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		WeatherHolder holder = null;

		if(row == null)
		{
			LayoutInflater inflater = mActivity.getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new WeatherHolder();
			holder.hanjaTV = (TextView)row.findViewById(R.id.hanja_tv);
			holder.meanTV = (TextView)row.findViewById(R.id.mean_tv);
			holder.txtTitle3 = (TextView)row.findViewById(R.id.textView3);
			row.setTag(holder);
		}
		else
		{
			holder = (WeatherHolder)row.getTag();
		}
		DataValueObject dao = data.get(position);
		String tempDate = dao.getkHanja();
		holder.hanjaTV.setTextColor(Color.BLACK);
		holder.hanjaTV.setText(tempDate);
		holder.meanTV.setTextColor(Color.BLUE);
		holder.meanTV.setText(dao.getkMean());
		holder.txtTitle3.setTextColor(Color.MAGENTA);
		holder.txtTitle3.setText(dao.getkSound());
		return row;
	}

	static class WeatherHolder
	{
		TextView hanjaTV;
		TextView meanTV;
		TextView txtTitle3;

		TextView txtBebe;
	}
}
