package com.camobile.dig.model;

import java.lang.reflect.Field;

public class SoundItem {

	private String id;
	private String start_time;
	private String end_time;
	private String fade_in_until;
	private String fade_out_from;

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
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getFade_in_until() {
		return fade_in_until;
	}
	public void setFade_in_until(String fade_in_until) {
		this.fade_in_until = fade_in_until;
	}
	public String getFade_out_from() {
		return fade_out_from;
	}
	public void setFade_out_from(String fade_out_from) {
		this.fade_out_from = fade_out_from;
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
