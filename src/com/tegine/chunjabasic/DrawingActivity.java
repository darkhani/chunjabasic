package com.tegine.chunjabasic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DrawingActivity extends Activity{
	String pHanjaString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawing);
		DrawHanjaView dhView = (DrawHanjaView)findViewById(R.id.hanja_draw_view); 
		Intent intent = getIntent(); 
		this.pHanjaString = intent.getStringExtra("hanjaName");
		Log.e(" >>>>>>>>>>>>>>>> this.pHanjaString >>>>>> ",this.pHanjaString);
		
	
		TextView tView = (TextView)findViewById(R.id.gray_hanja);
		tView.setText(this.pHanjaString);
		
	}
}
