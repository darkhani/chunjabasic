package com.tegine.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;

public class ScreenHelper {
	int mWidth;
	int mHeight;
	public float mRatio;
	public ScreenHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public int getHeightThisDevice(Activity mDelegate){
		
		DisplayMetrics metrics = mDelegate.getResources().getDisplayMetrics();//Activity 아닌경
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;
		Log.e("Screen X,Y = "," ["+width+"  x  "+height+"]");
		this.mWidth = width;
		this.mHeight = height;
		this.mRatio = height/width;
		return height;
	}
}
