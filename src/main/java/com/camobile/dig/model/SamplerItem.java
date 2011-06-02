package com.camobile.dig.model;

import java.lang.reflect.Field;

public class SamplerItem {

	private String id;
	private String start_time;
	private String gain;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getGain() {
		return gain;
	}
	public void setGain(String gain) {
		this.gain = gain;
	}
	
    public String toString() {
        String str = "";
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (int i=0; i<fields.length; i++) {
                str += fields[i].getName() + " : " + fields[i].get(this) + ", \n";
            }
        } catch (Exception e) {/* なんもせん */}
        return str;
    }
}
