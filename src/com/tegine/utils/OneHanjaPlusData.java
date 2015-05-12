package com.tegine.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class OneHanjaPlusData {
	List<DataValueOfPlusObject> mList = new ArrayList<DataValueOfPlusObject>();
	public OneHanjaPlusData(Context context) {

		AssetManager am =  context.getAssets();
		InputStream source = null;
		try {
			source = am.open("busu-data.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson g = new Gson();
		Reader reader = new InputStreamReader(source);

		DataValueOfPlusObject p = g.fromJson(reader, DataValueOfPlusObject.class);
		mList.add(p);
		System.out.println(p.mBusu);
	}
}
