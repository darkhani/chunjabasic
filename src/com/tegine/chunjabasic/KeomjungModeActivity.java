package com.tegine.chunjabasic;

//구글플레이에서 내손의 서당을 검색하세요...
//뭐 이 코드에서도 뭔가 건진게 있으시다믄... 광고 클릭을 꼭 부탁 드리는 건 아니고요... 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.tegine.utils.KeomjungDataHanjaKey;
import com.tegine.utils.KeomjungDataValueObject;
import com.tegine.utils.KeomjungDataValueObjectListAdapter;
import com.tegine.utils.OneHanjaDataVO;

import android.app.Activity;
import android.app.SearchManager.OnCancelListener;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class KeomjungModeActivity extends Fragment {
	
	KeomjungDataValueObjectListAdapter adapter1;
	KeomjungDataValueObjectListAdapter adapter2;
	List<KeomjungDataValueObject> mList;
	
    private ArrayList<KeomjungDataValueObject> type_name_copy = new ArrayList<KeomjungDataValueObject>();
    
	Activity mDelegate;
	Context mContext;
	ListView kListView;
	EditText mSearch;
	
	boolean isSearchMode;
	boolean isFirstRun;
	
	int savePositionIdex;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isFirstRun = true;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = null;
		v = inflater.inflate(R.layout.activity_keomjung_mode, container, false);
		
		mDelegate = getActivity();
		mContext = v.getContext();
		settingDataFirst();		
		settingUI(v);
		return v;
	}
	
	private void settingDataFirst() {
		
		isFirstRun = false;
		isSearchMode = false;
		
		mList = new ArrayList<KeomjungDataValueObject>();
		JSONObject obj = null;
		
		AssetManager am = mContext.getAssets();
		InputStream is = null;
		
		KeomjungDataHanjaKey kdhk = new KeomjungDataHanjaKey();
		List<String> tempList = kdhk.getList();

		if ( mList.size() <= 0 ){
			try {
				is = am.open("hanja-2cha.json");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			StringBuilder total = new StringBuilder();
			String line;

			//		JSONParser parser = new JSONParser();

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
				KeomjungDataValueObject kdvo = new KeomjungDataValueObject();
				kdvo.setkHanja(tempList.get(idx));
				try {
					kdvo.setkHunUm(obj.getJSONObject(tempList.get(idx)).getString("훈음"));
					kdvo.setkBusu(obj.getJSONObject(tempList.get(idx)).getString("부수"));
					kdvo.setkTotalHoik(obj.getJSONObject(tempList.get(idx)).getString("총획"));
					kdvo.setkNanido(obj.getJSONObject(tempList.get(idx)).getString("난이도"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mList.add(kdvo);
			}
		}
	}
	
	private void settingUI(View v) {
		
		mSearch = (EditText) v.findViewById(R.id.search_word_ET);
        mSearch.setSingleLine(true);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            	isSearchMode = true;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            	isSearchMode = true;
            }

            @Override
            public void afterTextChanged(Editable theWatchedText) {
                ArrayList<KeomjungDataValueObject> type_name_filter = new ArrayList<KeomjungDataValueObject>();

                String text = theWatchedText.toString();

                for (int i = 0; i < mList.size(); i++) {

                	if ( mList.get(i).getkHunUm() == null ){
                		continue;
                	} else if ((mList.get(i).getkHunUm()).contains(text) ){
                        type_name_filter.add(mList.get(i));

                    }
                	
                	if ( mList.get(i).getkNanido() == null ){
                		continue;
                	} else if ((mList.get(i).getkNanido()).contains(text) ){
                        type_name_filter.add(mList.get(i));
                    }
                	
                	
                }

                if ( text.length() > 0 ){
                	type_name_copy = type_name_filter;
                	adapter2 = new KeomjungDataValueObjectListAdapter(mDelegate, mContext, R.layout.listview_row_item_keomjung, type_name_copy);
                	kListView.setAdapter(adapter2);
                	adapter2.notifyDataSetChanged();
                	isSearchMode = true;
                }else{
                	adapter1 = new KeomjungDataValueObjectListAdapter(mDelegate, mContext, R.layout.listview_row_item_keomjung, mList);
                	kListView.setAdapter(adapter1);
                	adapter1.notifyDataSetChanged();
                	isSearchMode = false;
                }
            }
            
        });
        
		kListView = (ListView)v.findViewById(R.id.keomjung_mode_table);
		adapter1 = new KeomjungDataValueObjectListAdapter(mDelegate, mContext, R.layout.listview_row_item_keomjung, mList);

		kListView.setAdapter(adapter1);
		kListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		kListView.setDivider(null);
		kListView.setDividerHeight(0);
		kListView.setBackgroundColor(Color.TRANSPARENT);
		kListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent i = new Intent(mContext, DrawingActivity.class);
				if ( isSearchMode == true){
					i.putExtra("hanjaName", type_name_copy.get(position).getkHanja());
				}else{
					i.putExtra("hanjaName", mList.get(position).getkHanja());
					savePositionIdex = position;
				}
				startActivity(i);
			}			
		});
		
		adapter1.notifyDataSetChanged();
	}

	public static Fragment newInstance(String text) {
		// TODO Auto-generated method stub
		KeomjungModeActivity s = new KeomjungModeActivity();
		Bundle b = new Bundle();
		b.putString("msg", text);

		s.setArguments(b);

		return s;
	}
}
