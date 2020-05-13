package com.basbas.resepnew.model;

import com.google.gson.annotations.SerializedName;

public class ResepItem{

	@SerializedName("nama_resep")
	private String namaResep;

	@SerializedName("id_resep")
	private String idResep;

	@SerializedName("detail")
	private String detail;

	@SerializedName("gambar")
	private String gambar;

	public void setNamaResep(String namaResep){
		this.namaResep = namaResep;
	}

	public String getNamaResep(){
		return namaResep;
	}

	public void setIdResep(String idResep){
		this.idResep = idResep;
	}

	public String getIdResep(){
		return idResep;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return detail;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	@Override
 	public String toString(){
		return 
			"ResepItem{" + 
			"nama_resep = '" + namaResep + '\'' + 
			",id_resep = '" + idResep + '\'' + 
			",detail = '" + detail + '\'' + 
			",gambar = '" + gambar + '\'' + 
			"}";
		}
}