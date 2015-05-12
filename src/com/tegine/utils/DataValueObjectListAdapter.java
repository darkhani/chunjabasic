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

public class DataValueObjectListAdapter extends ArrayAdapter<DataValueObject> {
	private  List<DataValueObject>data;
	private int layoutResourceId;
	private Context context;
	private Activity mActivity;
	
	 public DataValueObjectListAdapter(Activity act, Context context, int layoutResourceId, List<DataValueObject>data) {
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
	            holder.txtBusu = (TextView)row.findViewById(R.id.busu_tv);
	            holder.txtTotalHoik = (TextView)row.findViewById(R.id.total_hoik_tv);
	            holder.txtNanido = (TextView)row.findViewById(R.id.nanido_tv);
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
	        
	        holder.txtBusu.setTextColor(Color.rgb(0,205,0));
	        holder.txtBusu.setText(dao.dvoPlus.mBusu);
	        
	        holder.txtTotalHoik.setTextColor(Color.RED);
	        holder.txtTotalHoik.setText(dao.dvoPlus.mTotalHoik);
	        
	        holder.txtNanido.setTextColor(Color.rgb(0,205,205));
	        holder.txtNanido.setText(dao.dvoPlus.mNanido);
	        return row;
	    }
	 
	 static class WeatherHolder
	    {
	        TextView hanjaTV;
	        TextView meanTV;
	        TextView number;
	        TextView txtTitle3;
	        TextView txtBebe;
	        //Add more info.
	        TextView txtBusu;
	        TextView txtTotalHoik;	
	        TextView txtNanido;
	    }
}
