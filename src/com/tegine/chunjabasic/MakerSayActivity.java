package com.tegine.chunjabasic;

import java.util.ArrayList;
import java.util.List;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;
import com.fsn.cauly.CaulyAdViewListener;
import com.tegine.utils.DataValueObject;
import com.tegine.utils.MyungsimVOAdapter;
import com.tegine.utils.ScreenHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class MakerSayActivity extends Fragment  {
	Activity mDelegate;
	Context mContext;
	List<String> mList = new ArrayList<String>();
	private CaulyAdView javaAdView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_maker_say, container, false);
		if (mList.size()>=13){
			mList.clear();
		}
		mDelegate = this.getActivity();
		
		mList.add(" == 제작자 한마디 ==");
		mList.add(" 1. 안녕하세요? 한인택입니다. ^^");
		mList.add(" 2. 내용신고, 앱조언은 darkhani@naver.com 으로 받습니다.");
		mList.add(" 3. 개인적인 기부는 아래 주소로 부탁드려요.. 굽실...");
		mList.add(" 3-1. 농협 213-01-126411");
		mList.add(" 3-2. **bitcoin wallet : 134xUDoqr26ut6yEpe3WNmSs5v1CuNvvKT");
		mList.add(" 3-3. **paypal : dark_hani@lycos.co.kr");
		mList.add(" == 개인적인 광고 ==");
		mList.add(" 1. 제작자 홈 : http://www.tegine.com");
		mList.add(" 2. 푸켓여행정보는 : http://cafe.daum.net/pphuket");
		mList.add(" 3. 비트코인거래소는? : https://bitwon.kr:43622/");
		mList.add(" 4. 드래곤볼 또다른 이야기가 궁금하다면?? http://tvhot2.egloos.com/");
		//패키지 매니저
        PackageManager pm = mDelegate.getPackageManager();
        try {
            //검색할 대상 패키지명
            pm.getApplicationInfo("com.tegine.myhandhakdang",PackageManager.GET_META_DATA);
        }catch (Exception e)
        {
            mList.add(" == 제작자의 다른 어플 ==");
            //패키지가 없을경우 실행할 내용
            mList.add("내손의학당");
        }
		mContext = this.getActivity();
		mDelegate = this.getActivity();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mList);
		
		
		ListView kListView = (ListView)v.findViewById(R.id.say_list);

		kListView.setAdapter(adapter);
		kListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		kListView.setDivider(null);
		kListView.setDividerHeight(0);
		kListView.setBackgroundColor(Color.TRANSPARENT);
		kListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//				Intent i = new Intent(mContext, DrawingActivity.class);
//				i.putExtra("hanjaName", mList.get(position).getkHanja());
//				startActivity(i);
				if ( position == 5 || position == 10 ){ // 비트코인 
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bitwon.kr:43622/"));
					startActivity(browserIntent);
				} else if ( position == 6) {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/kr/webapps/mpp/home"));
					startActivity(browserIntent);
				} else if ( position == 8) {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tegine.com"));
					startActivity(browserIntent);
				} else if ( position == 9) {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cafe.daum.net/pphuket"));
					startActivity(browserIntent);
				} else if ( position == 11) {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tvhot2.egloos.com/"));
					startActivity(browserIntent);
				}else if ( position == 13) {
//                  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.tegine.chunjabasic"));
                  Intent intent = new Intent(Intent.ACTION_VIEW);
                  intent.setData(Uri.parse("market://details?id=com.tegine.myhandhakdang"));
                  startActivity(intent);
              }

				
				Toast.makeText(mContext, position+" 번쨰 항목",Toast.LENGTH_SHORT).show();
				
			}
		});

		kListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

//				Toast.makeText(mContext, "외운글자로 저장하고 목록에서 제거 합니다.",Toast.LENGTH_SHORT).show();
//				for (int idx=0;idx<StringDefinition.MAX_NUMBER;idx++){
//					if (mList.get(position).getkHanja().equals(mListAll.get(idx).getkHanja())){
//						mMemoryList.add(idx);
//						break;
//					}else{
//						continue;
//					}
//				}
//				mListAll.get(position).setkIsMemory(true);
//				mList.remove(position);
//				adapter.notifyDataSetChanged();
				return true;
			}
		});
		
//		ScreenHelper sh = new ScreenHelper();
//		int height = sh.getHeightThisDevice(mDelegate);
//		
//		LayoutParams lp = (LayoutParams) kListView.getLayoutParams();
//	     lp.height = height-260;
//	     kListView.setLayoutParams(lp);
		return v;
	}
	
	public static Fragment newInstance(String text) {
		// TODO Auto-generated method stub
		MakerSayActivity s = new MakerSayActivity();
		Bundle b = new Bundle();
		b.putString("msg", text);

		s.setArguments(b);

		return s;
	}
}
