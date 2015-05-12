package com.tegine.chunjabasic;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.tegine.utils.DataValueObject;
import com.tegine.utils.DataValueObjectListAdapterFourMode;
import com.tegine.utils.MyungsimBogamVO;
import com.tegine.utils.MyungsimVOAdapter;
import com.tegine.utils.OneHanjaDataVO;

public class MyungsimBogamActivity extends Fragment {
	Activity mDelegate;
	Context mContext;

	List<DataValueObject> mList = new ArrayList<DataValueObject>();
	List<DataValueObject> mListAll = new ArrayList<DataValueObject>();

	MyungsimVOAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_myungsim, container, false);

		mDelegate = getActivity();
		mContext = v.getContext();

		settingDataFirst();		
		settingUI(v);
		return v;
	}

	private void settingUI(View v) {
		ListView kListView = (ListView)v.findViewById(R.id.myungsim_list);

		adapter = new MyungsimVOAdapter(mDelegate, mContext, R.layout.listview_row_item_myungsim, mList);

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
	}

	private void settingDataFirst() {
		//DB생성후 데이터 넣기
		MyungsimBogamVO msbvo = new MyungsimBogamVO();
		List<String> tempList = msbvo.getList();
		mList.clear();
		for (int idx=0;idx<tempList.size();idx++){
//			if (idx%2==0){
				DataValueObject dvo = new DataValueObject();
				dvo.setkHanja(tempList.get(idx));
				dvo.setkMean("");
				dvo.setkSound("");
				mList.add(dvo);
				mListAll.add(dvo);
//			}
		}	
	}

	public static Fragment newInstance(String text) {
		// TODO Auto-generated method stub
		MyungsimBogamActivity s = new MyungsimBogamActivity();
		Bundle b = new Bundle();
		b.putString("msg", text);

		s.setArguments(b);

		return s;
	}

}
