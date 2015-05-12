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

public class MyungsimVOAdapter extends ArrayAdapter<DataValueObject> {
	private  List<DataValueObject>data;
	private int layoutResourceId;
	private Context context;
	private Activity mActivity;

	public MyungsimVOAdapter(Activity act, Context context, int layoutResourceId, List<DataValueObject>data) {
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
			
			row.setTag(holder);
		}
		else
		{
			holder = (WeatherHolder)row.getTag();
		}
		DataValueObject dao = data.get(position);
		String tempDate = dao.getkHanja();
		if ( dao.getkHanja().matches("편>")){
			row.setBackgroundColor(Color.BLACK);
			holder.hanjaTV.setTextColor(Color.YELLOW);
			
		}else{
			if ( position%2==0){
				holder.hanjaTV.setTextColor(Color.parseColor("#EAEAEA"));
			}else{
				holder.hanjaTV.setTextColor(Color.parseColor("#B8F3B8"));
			}
		}
		holder.hanjaTV.setText(tempDate);
		holder.meanTV.setTextColor(Color.BLUE);
		holder.meanTV.setText(dao.getkMean());
		
		return row;
	}

	static class WeatherHolder
	{
		TextView hanjaTV;
		TextView meanTV;
	}
}

