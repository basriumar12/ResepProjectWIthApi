package com.basbas.resepnew.model;

import com.google.gson.annotations.SerializedName;

public class ResponseData{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("kode")
	private int kode;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	@Override
 	public String toString(){
		return 
			"ResponseData{" + 
			"pesan = '" + pesan + '\'' + 
			",kode = '" + kode + '\'' + 
			"}";
		}
}