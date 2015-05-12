package com.tegine.chunjabasic;

//구글플레이에서 내손의 서당을 검색하세요...
//뭐 이 코드에서도 뭔가 건진게 있으시다믄... 광고 클릭을 꼭 부탁 드리는 건 아니고요... 

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tegine.utils.DataValueObject;
import com.tegine.utils.OneHanjaDataVO;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Fragment implements OnTouchListener {
	
	TextView mHanjaView;
	TextView mItemFirst;
	TextView mItemSecond;
	TextView mItemThird;
	TextView mItemFourth;
	TextView mItemTotalNumber;
	TextView mItemCorrectNumber;
	TextView mItemScore;
	
	ImageView mChaejumImg;

	Activity mDelegate;
	Context mContext;
	List<DataValueObject> mList = new ArrayList<DataValueObject>();
	
	int mDap;
	int mIdx;
	
	int mTotal;
	int mCorrect;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_game, container, false);

		mDelegate = getActivity();
		mContext = v.getContext();
		
//		if (Utils.isRooted()){
//			//메세지만 출력합니다. 
//			Toast.makeText(mContext, "기기가 루팅가능성이 있어 보입니다.",Toast.LENGTH_LONG).show();
//		}
		
		settingDataFirst();		
		settingUI(v);
		
		return v;
	}

	private void settingUI(View v) {
	//문제판을 셋팅합니다. 
		
		mChaejumImg = (ImageView)v.findViewById(R.id.chaejum_image);
		Typeface font = Typeface.createFromAsset(mContext.getAssets(), "gungseo.ttf"); 
				
		mHanjaView = (TextView)v.findViewById(R.id.show_hanja_tv);
		mHanjaView.setTypeface(font);
		
		mItemFirst =  (TextView)v.findViewById(R.id.title_item_one);
		mItemSecond =  (TextView)v.findViewById(R.id.title_item_two);
		mItemThird =  (TextView)v.findViewById(R.id.title_item_three);
		mItemFourth =  (TextView)v.findViewById(R.id.title_item_four);
		
		mItemFirst.setOnTouchListener(mTouchEvent2);
		mItemSecond.setOnTouchListener(mTouchEvent2);
		mItemThird.setOnTouchListener(mTouchEvent2);
		mItemFourth.setOnTouchListener(mTouchEvent2);
		
		mItemTotalNumber = (TextView)v.findViewById(R.id.total_number);
		mItemCorrectNumber = (TextView)v.findViewById(R.id.jungdap_number);
		mItemScore = (TextView)v.findViewById(R.id.score_number);
		
		loadData(v);
	}

	private void loadData(View v) {
		
		Random oRandom = new Random();
		mDap = oRandom.nextInt(4) + 1;
		
	    int i = oRandom.nextInt(999) + 1;
	    mIdx = i;
	    
	    mHanjaView.setText(mList.get(mIdx).getkHanja());
	    
	    mItemTotalNumber.setText(mTotal+"");
	    mItemCorrectNumber.setText(mCorrect+"");
	    if ( mTotal == 0){
	    	mItemScore.setText("0");
	    } else{
	    	mItemScore.setText(((mCorrect*100/mTotal))+"");
	    }
	    showDap();
	    showNoDap();
	}
	
	private void showNoDap() {
		
		Random oRandom = new Random();
		int i=0,j=0,k=0;
		
		while (i==j||j==k||k==i||mDap==i||mDap==j||mDap==k) {
			i = oRandom.nextInt(350) + 1;
			j = oRandom.nextInt(350) + 1;
			k = oRandom.nextInt(350) + 1;	
		}
		
		if (mDap == 1){
			mItemSecond.setText("  2. "+mList.get(i).getkMean() + "  " + mList.get(i).getkSound());
			mItemThird.setText("  3. "+mList.get(j).getkMean() + "  " + mList.get(j).getkSound());
			mItemFourth.setText("  4. "+mList.get(k).getkMean() + "  " + mList.get(k).getkSound());
		} else if (mDap == 2){
			mItemFirst.setText("  1. "+mList.get(i).getkMean() + "  " + mList.get(i).getkSound());
			mItemThird.setText("  3. "+mList.get(j).getkMean() + "  " + mList.get(j).getkSound());
			mItemFourth.setText("  4. "+mList.get(k).getkMean() + "  " + mList.get(k).getkSound());
		} else if (mDap == 3){
			mItemFirst.setText("  1. "+mList.get(i).getkMean() + "  " + mList.get(i).getkSound());
			mItemSecond.setText("  2. "+mList.get(j).getkMean() + "  " + mList.get(j).getkSound());
			mItemFourth.setText("  4. "+mList.get(k).getkMean() + "  " + mList.get(k).getkSound());
		} else if (mDap == 4){
			mItemFirst.setText("  1. "+mList.get(i).getkMean() + "  " + mList.get(i).getkSound());
			mItemSecond.setText("  2. "+mList.get(j).getkMean() + "  " + mList.get(j).getkSound());
			mItemThird.setText("  3. "+mList.get(k).getkMean() + "  " + mList.get(k).getkSound());
		}		
	}

	private void showDap() {
		if (mDap == 1){
			mItemFirst.setText("  1. "+mList.get(mIdx).getkMean() + "  " + mList.get(mIdx).getkSound());
		} else if (mDap == 2){
			mItemSecond.setText("  2. "+mList.get(mIdx).getkMean() + "  " + mList.get(mIdx).getkSound());
		} else if (mDap == 3){
			mItemThird.setText("  3. "+mList.get(mIdx).getkMean() + "  " + mList.get(mIdx).getkSound());
		} else if (mDap == 4){
			mItemFourth.setText("  4. "+mList.get(mIdx).getkMean() + "  " + mList.get(mIdx).getkSound());
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
		
		for (int idx=0;idx<tempList.size();idx++){
			if (idx%2==0){
				DataValueObject dvo = new DataValueObject();
				dvo.setkHanja(tempList.get(idx));
				String[]tempStr = tempList.get(idx+1).split("\\t");
				dvo.setkMean(tempStr[0]);
				dvo.setkSound(tempStr[1]);
				dvo.setkIsMemory(false);
				mList.add(dvo);
			}
		}

	}
	public static Fragment newInstance(String text) {
		// TODO Auto-generated method stub
		GameActivity s = new GameActivity();
		Bundle b = new Bundle();
		b.putString("msg", text);

		s.setArguments(b);

		return s;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private  OnTouchListener mTouchEvent2 = new OnTouchListener()
    {
        public boolean onTouch(View v, MotionEvent event)
        {
            
            int action=event.getAction();
            
            if(action==MotionEvent.ACTION_DOWN)
            {
//            	Log.e(" >>>> ","click>>>>");
            	if (v == mItemFirst && mDap == 1 || v == mItemSecond && mDap == 2 || v == mItemThird && mDap == 3 || v == mItemFourth && mDap == 4){
            		mTotal ++;
            		mCorrect ++;
            		mChaejumImg.setImageResource(R.drawable.correct_img);
            		loadData(v);
            	} else if (v == mItemFirst && mDap != 1 || v == mItemSecond && mDap != 2 || v == mItemThird && mDap != 3 || v == mItemFourth && mDap != 4){
            		mTotal ++;
            		mChaejumImg.setImageResource(R.drawable.odap_img);
            		loadData(v);
            	} 
            }
            return true;
        }
    };
}
