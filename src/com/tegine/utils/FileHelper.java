package com.tegine.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.tegine.utils.StringDefinition;

public class FileHelper {
	List <Integer> mList = new ArrayList<Integer>();
	
	public FileHelper(Context ctx) {
		
	}
	public FileHelper(Context ctx,List<Integer> l) {
		// TODO Auto-generated constructor stub
		this.mList = l;
	}

	public void writeFile(){
		String fileNameStr = StringDefinition.DB_TO_TEXT_FILE;
		Log.e(" >>>>>>>>>>> fileFolder >>>>>>>>>>> ",fileNameStr);
		File root = new File(Environment.getExternalStorageDirectory(), StringDefinition.PROJECT_NAME_FULL);
		if (!root.exists()) {
			root.mkdirs();
		}

		try {
			File fileName = new File(root, fileNameStr);
			Log.e(">>>>>>>>> Root >>> ",root.getPath().toString());
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//			bufferedWriter.write("memory number");
//			bufferedWriter.newLine();
			if ( mList.size()>0 ){
				for ( int idx=0;idx<mList.size();idx++){
					bufferedWriter.write(mList.get(idx));
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		}
		catch(IOException ex) {
			System.out.println(
					"Error writing to file '"
							+ fileNameStr + "'");
		}
	}
	
	public List <Integer> readFile(){
        String fileName = StringDefinition.DB_TO_TEXT_FILE;
        File root = new File(Environment.getExternalStorageDirectory(), "com.tegine.chunjabasic");
        List <Integer>retVO = new ArrayList<Integer>();
        
        BufferedReader br = null;
        String line;
        int idx=0;
        try {
			br = new BufferedReader(new FileReader(new File(root,fileName)));
			try {
				while ((line = br.readLine()) != null) {
					retVO.add(Integer.parseInt(line));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return retVO;
	}
}
