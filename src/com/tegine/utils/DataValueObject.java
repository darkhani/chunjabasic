package com.tegine.utils;

public class DataValueObject {
	String kHanja;
	String kMean;
	String kSound;
	boolean kMemory;
	DataValueOfPlusObject dvoPlus;
	
	long autoIdx;
	
	public long getAutoIdx(){
		return autoIdx;
	}
	public void setAutoIdx(long idx){
		this.autoIdx = idx;
	}
	
	public String getkHanja() {
		return kHanja;
	}
	public void setkHanja(String kHanja) {
		this.kHanja = kHanja;
	}
	public String getkMean() {
		return kMean;
	}
	public void setkMean(String kMean) {
		this.kMean = kMean;
	}
	public String getkSound() {
		return kSound;
	}
	public void setkSound(String kSound) {
		this.kSound = kSound;
	}
	public boolean iskMemory() {
		return kMemory;
	}
	public void setkIsMemory(boolean kIsMemory) {
		this.kMemory = kIsMemory;
	}
	
	public DataValueOfPlusObject getdvoPlus(){
		return dvoPlus;
	}
	public void setdvoPlus(DataValueOfPlusObject dvoPlus){
		this.dvoPlus = dvoPlus;
	}
}
