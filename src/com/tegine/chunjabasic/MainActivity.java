package com.tegine.chunjabasic;

//구글플레이에서 내손의 서당을 검색하세요...
//뭐 이 코드에서도 뭔가 건진게 있으시다믄... 광고 클릭을 꼭 부탁 드리는 건 아니고요... 

import java.util.Timer;
import java.util.TimerTask;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
				new Timer().schedule(new TimerTask() {          
				    @Override
				    public void run() {
				    	Log.e(">>>>>>>>>>>>>>>>>>","click!!!");
//						Intent i = new Intent(getApplicationContext(), CreateActivity.class);
				    	Intent i = new Intent(getApplicationContext(), ViewPaperFragment.class);
						startActivity(i);
						finish();
				    }
				}, 3000);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
//	    getMenuInflater().inflate(R.menu.main, menu);
//
//	    MenuItem logoutMI= menu.add(0,1,0,"Logout");
//	    logoutMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//	    logoutMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
//
//	    MenuItem configMI= menu.add(0,2,1,"Configuration");
//	    configMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//	    configMI.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);

	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
