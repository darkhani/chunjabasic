package com.tegine.chunjabasic;

//구글플레이에서 내손의 서당을 검색하세요...
//뭐 이 코드에서도 뭔가 건진게 있으시다믄... 광고 클릭을 꼭 부탁 드리는 건 아니고요... 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;
import com.fsn.cauly.CaulyAdViewListener;
import com.tegine.utils.DataValueObject;
import com.tegine.utils.DataValueObjectListAdapter;
import com.tegine.utils.DataValueOfPlusObject;
import com.tegine.utils.OneHanjaDataVO;
import com.tegine.utils.ScreenHelper;
import com.tegine.utils.StringDefinition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class OneHanjaModeActivity extends Fragment implements CaulyAdViewListener {

	List<DataValueObject> mList = new ArrayList<DataValueObject>();
	List<DataValueObject> mShowList = new ArrayList<DataValueObject>();
	List<DataValueObject> mListAll = new ArrayList<DataValueObject>(); 

	List<Integer>mMemoryList = new ArrayList<Integer>();

	DataValueObjectListAdapter adapter;
	Activity mDelegate;
	Context mContext;
	TextView memoryNumTV;

	boolean isHiddenMean;

	ListView kListView;

	int memNumber;
	//	private DBHelper dbAdapter;
	//	private static final String TAG = "DBHelper";

	TimerTask mTask;
	Timer mTimer;

	private CaulyAdView javaAdView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_one_mode, container, false);

		mDelegate = getActivity();
		mContext = v.getContext();

		isHiddenMean = true;

		settingDataFirst();		
		settingUI(v);

		mTask = new TimerTask() {
			@Override
			public void run() {
				mDelegate.runOnUiThread(new Runnable() {
					public void run() {
						//						memoryNumTV.setText("  "+ mMemoryList.size()+" / 1000개 외움\n꾹누르면 외운것");
					}
				});
			}
		};

		mTimer = new Timer();
		mTimer.schedule(mTask, 500, 1500);
		//getMaxY
		//	DisplayMetrics metrics = this.getResources().getDisplayMetrics();
		//	int width = metrics.widthPixels;
		//	int height = metrics.heightPixels;
		ScreenHelper sh = new ScreenHelper();
		int height = sh.getHeightThisDevice(mDelegate);

		LayoutParams lp = (LayoutParams) kListView.getLayoutParams();
//		lp.height = (int) (height-((int)(260*sh.mRatio)/1.6)) ; // 1.6 = 260, 1.777 = x
		lp.height = (int) (height-300);
		kListView.setLayoutParams(lp);

		//광고 추가
		CaulyAdInfo adInfo = new CaulyAdInfoBuilder("여러분의코드를 넣으세요!"). effect("RightSlide").
				build();

		javaAdView = new CaulyAdView(this.mDelegate); 
		javaAdView.setAdInfo(adInfo); 
		javaAdView.setAdViewListener(this);
		RelativeLayout rootView = (RelativeLayout) v.findViewById(R.id.one_mode_cp_layout); // 예시 : 화면 하단에 배너 부착
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//		rootView.addView(javaAdView, params);

		return v;
	}

	private void settingUI(View v) {
		kListView = (ListView)v.findViewById(R.id.one_mode_table);

		adapter = new DataValueObjectListAdapter(mDelegate, mContext, R.layout.listview_row_item, mList);

		kListView.setAdapter(adapter);
		kListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		kListView.setDivider(null);
		kListView.setDividerHeight(0);
		kListView.setBackgroundColor(Color.TRANSPARENT);
		kListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent i = new Intent(mContext, DrawingActivity.class);
				i.putExtra("hanjaName", mList.get(position).getkHanja());
				startActivity(i);
			}
		});

		kListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				Toast.makeText(mContext, "외운글자로 저장하고 목록에서 제거 합니다.",Toast.LENGTH_SHORT).show();
				for (int idx=0;idx<StringDefinition.MAX_NUMBER;idx++){
					if (mList.get(position).getkHanja().equals(mListAll.get(idx).getkHanja())){
						mMemoryList.add(idx);
						break;
					}else{
						continue;
					}
				}
				mListAll.get(position).setkIsMemory(true);
				mList.remove(position);
				adapter.notifyDataSetChanged();
				return true;
			}
		});

		//	showListButton = (Button)v.findViewById(R.id.button_list);
		//
		//	showListButton.setOnClickListener(new OnClickListener() {
		//
		//		@Override
		//		public void onClick(View v) {
		//			mList.clear();
		//			for(int idx=0;idx<mListAll.size();idx++){
		//				mList.add(mListAll.get(idx));
		//			}
		//
		//			for (int idx=0;idx<mMemoryList.size();idx++){
		//				mList.remove(mListAll.get(mMemoryList.get(idx)));
		//			}
		//
		//			adapter.notifyDataSetChanged();
		//		}
		//	});
		//
		//	toFinishMemoryButton = (Button)v.findViewById(R.id.button_memory_all);
		//
		//	toFinishMemoryButton.setOnClickListener(new OnClickListener() {
		//
		//		@Override
		//		public void onClick(View v) {
		//			//우선 현재 리스트 저장...
		//			saveNowListLogic();
		//
		//			if (mMemoryList.size()>0){
		//				mList.clear();
		//				for (int idx=0;idx<mMemoryList.size();idx++){
		//					mList.add(mListAll.get(mMemoryList.get(idx)));
		//				}
		//			}else{
		//				Toast.makeText(mContext, "외운글자가 없습니다. 길게 눌러보세요. 저장되고 목록에서 지워집니다.",Toast.LENGTH_SHORT).show();	
		//			}
		//
		//			//파일로 저장해 둠.
		//			FileHelper fh = new FileHelper(mContext, mMemoryList);
		//			fh.writeFile();
		//			adapter.notifyDataSetChanged();
		//		}
		//	});
		//
		//	hiddenMeanButton = (Button)v.findViewById(R.id.button_hidden_mean);
		//
		//	hiddenMeanButton.setOnClickListener(new OnClickListener() {
		//
		//		@Override
		//		public void onClick(View v) {
		//			//우선 현재 리스트 저장...
		//			saveNowListLogic();
		//
		//			if (isHiddenMean){
		//				isHiddenMean=false;
		//				hiddenMeanButton.setText("음훈보기");
		//				for(int idx=0;idx<mList.size();idx++){
		//					DataValueObject dvo = mList.get(idx);
		//					dvo.setkMean("");
		//					dvo.setkSound("");
		//					mList.set(idx, dvo);
		//				}
		//			}else{
		//				isHiddenMean = true;
		//				hiddenMeanButton.setText("음훈안보기");
		//				settingDataFirst();
		//
		//				for (int idx=0;idx<mMemoryList.size();idx++){
		//					int forDeleteIdx = whereIsMyIndex(mListAll.get(mMemoryList.get(idx)),mList);
		//					mList.remove(forDeleteIdx);
		//				}
		//				
		//			}
		//			adapter.notifyDataSetChanged();
		//		}
		//	});
		//
		//
		//	resetButton = (Button)v.findViewById(R.id.button_reset);
		//
		//	resetButton.setOnClickListener(new OnClickListener() {
		//
		//		@Override
		//		public void onClick(View v) {
		//			saveNowListLogic();
		//			mList.clear();
		//			settingDataFirst();
		//			//				for (int idx = 0; idx<mListAll.size();idx++){
		//			//					mList.add(mListAll.get(idx));
		//			//				}
		//			mMemoryList.clear();
		//			adapter.notifyDataSetChanged();
		//		}
		//	});
		//
		//	memoryNumTV = (TextView)v.findViewById(R.id.memory_number);
	}

	int whereIsMyIndex(DataValueObject item, List<DataValueObject> thouList){ //orgList의 아이템,비교대상 원본(1000개) list
		int retIdx = 0;
		for (int idx=0;idx<thouList.size();idx++){
			if (item.getkHanja().equals(thouList.get(idx).getkHanja())){
				retIdx=idx;
				break;
			}else{
				continue;
			}
		}
		return retIdx;
	}

	void saveNowListLogic(){
		mShowList.clear();
		for(int idx=0;idx<mShowList.size();idx++){
			mShowList.add(mList.get(idx));
		}	
	}
	private void settingDataFirst() {
		//DB생성후 데이터 넣기
		OneHanjaDataVO ohdvo = new OneHanjaDataVO();
		List<String> tempList = ohdvo.getList();
		if (mList != null) {
			mList = null;
			mList = new ArrayList<DataValueObject>();
		}
		if (mListAll != null) {
			mListAll = null;
			mListAll = new ArrayList<DataValueObject>();
		}

		JSONObject obj = null;

		AssetManager am = mContext.getAssets();
		InputStream is = null;
		try {
			is = am.open("busu-data.json");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		BufferedReader r = new BufferedReader(new InputStreamReader(is));
		StringBuilder total = new StringBuilder();
		String line;
		try {
			while ((line = r.readLine()) != null) {
				total.append(line);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			obj=new JSONObject(total.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		for (int idx=0;idx<tempList.size();idx++){
			if (idx%2==0){
				DataValueObject dvo = new DataValueObject();
				dvo.setkHanja(tempList.get(idx));
				String[]tempStr = tempList.get(idx+1).split("\\t");
				dvo.setkMean(tempStr[0]);
				dvo.setkSound(tempStr[1]);
				dvo.setkIsMemory(false);

				DataValueOfPlusObject dvoPlus = new DataValueOfPlusObject();
				try {
					dvoPlus.setmBusu(obj.getJSONObject(dvo.getkHanja()).getString("부수"));
					dvoPlus.setmTotalHoik(obj.getJSONObject(dvo.getkHanja()).getString("총획"));
					dvoPlus.setmNanido(obj.getJSONObject(dvo.getkHanja()).getString("난이도"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				dvo.setdvoPlus(dvoPlus);

				mList.add(dvo);

				DataValueObject dvo2 = new DataValueObject();
				dvo2.setkHanja(tempList.get(idx));
				String[]tempStr2 = tempList.get(idx+1).split("\\t");
				dvo2.setkMean(tempStr2[0]);
				dvo2.setkSound(tempStr2[1]);
				dvo2.setkIsMemory(false);
				DataValueOfPlusObject dvoPlus2 = new DataValueOfPlusObject();
				dvo2.setdvoPlus(dvoPlus2);
				mListAll.add(dvo2);
			}
		}

		if (adapter != null){
			adapter = null;
		}
		adapter = new DataValueObjectListAdapter(mDelegate, mContext, R.layout.listview_row_item, mList);

		if (kListView != null){
			kListView.setAdapter(adapter);
			kListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}

	}

	public static Fragment newInstance(String text) {
		// TODO Auto-generated method stub
		OneHanjaModeActivity s = new OneHanjaModeActivity();
		Bundle b = new Bundle();
		b.putString("msg", text);

		s.setArguments(b);

		return s;
	}

	@Override
	public void onCloseLandingScreen(CaulyAdView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailedToReceiveAd(CaulyAdView arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceiveAd(CaulyAdView arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onShowLandingScreen(CaulyAdView arg0) {
		// TODO Auto-generated method stub

	}

}
