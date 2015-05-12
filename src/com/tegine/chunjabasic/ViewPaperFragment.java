package com.tegine.chunjabasic;

// 구글플레이에서 내손의 서당을 검색하세요...
// 뭐 이 코드에서도 뭔가 건진게 있으시다믄... 광고 클릭을 꼭 부탁 드리는 건 아니고요... 
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;

public class ViewPaperFragment extends FragmentActivity implements ActionBar.TabListener  {
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_paper_main);     

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

        
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

            case 0: return OneHanjaModeActivity.newInstance("1");
            case 1: return FourHanjaModeActivity.newInstance("2");
            case 2: return KeomjungModeActivity.newInstance("3");
            case 3: return GameActivity.newInstance("4");
            case 4: return DongMongSunSupActivity.newInstance("5");
            case 5: return MyungsimBogamActivity.newInstance("6");
            case 6: return MakerSayActivity.newInstance("7");
            default: return OneHanjaModeActivity.newInstance("8");
            }
        }

        @Override
        public int getCount() {
            return 7;
        }   
        
        @Override
        public CharSequence getPageTitle(int position) {
        	if (position == 0) {
        		return "천자문(1글자)";	
        	} else if (position == 1) {
        		return "천자문(4글자)";	
        	} else if (position == 2) {
        		return "검정용한자";	
        	} else if (position == 3) {
        		return "게임^^";	
        	} else if (position == 4) {
        		return "동몽선습";	
        	} else if (position == 5) {
        		return "명심보감";	
        	} else {
        		return "한마디~";
        	}
        }
    }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}