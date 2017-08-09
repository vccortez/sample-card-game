package com.tardigrade.utils;

import com.well.tardigrade.Deck;
import com.well.tardigrade.Pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile{
	private String fileName = null;
	
	private String [] header = null;
	
	private iCallback onStart;
	private iCallback onReading;
	private iCallback onFinish;
	private iCallback onFail;

	public ReadFile(String fileName){
		this.fileName = fileName;
		this.onStart = new NullCallback();
		this.onReading = new NullCallback();
		this.onFinish = new NullCallback();
		this.onFail = new NullCallback();
		
		this.header = null;
	}

	public void readAsCSV(){
		new Thread(){
			@SuppressWarnings("resource")
			@Override
			public void run() {
				List<String []> data = new ArrayList<String []>();
				onStart.callingBack(Pack.create(Flag.NOTIFY, null));
				try{
					InputStream file = Deck.mContext.getAssets().open(fileName);
					//InputStreamReader stream = new InputStreamReader(new FileInputStream(fileName), "UTF8");
					InputStreamReader stream = new InputStreamReader(file, "UTF8");
					BufferedReader buffer = new BufferedReader(stream);
				    String line = buffer.readLine();
				    while (line != null) {
				    	String [] row = line.split(";");
					    data.add(row);
						line = buffer.readLine();						
						if(header == null){
							header = row;
						}else{
							onReading.callingBack(Pack.create(Flag.NOTIFY, row));
						}
				    }
				} catch (IOException e) {
					e.printStackTrace();
					onFail.callingBack(Pack.create(Flag.NOTIFY, e));
				}
				onFinish.callingBack(Pack.create(Flag.NOTIFY, data));
			}
		}.run();
	}

	public String[] getHeader(){
		return header;
	}
	public void setOnStartListener(iCallback callback){
		this.onStart = callback;
	}
	public void setOnReadingListener(iCallback callback){
		this.onReading = callback;
	}
	public void setOnFinishListener(iCallback callback){
		this.onFinish = callback;
	}
	public void setOnFailListener(iCallback callback){
		this.onFail = callback;
	}
}