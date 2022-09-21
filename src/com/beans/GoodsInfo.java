package com.beans;

public class GoodsInfo {
	private int id;
	private String goodsName;  
	private int bigCateId;  
	private int smallCateId;  
	private Float price;  
	private String des;  
	private String unit;  
	private String producter;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getBigCateId() {
		return bigCateId;
	}
	public void setBigCateId(int bigCateId) {
		this.bigCateId = bigCateId;
	}
	public int getSmallCateId() {
		return smallCateId;
	}
	public void setSmallCateId(int smallCateId) {
		this.smallCateId = smallCateId;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProducter() {
		return producter;
	}
	public void setProducter(String producter) {
		this.producter = producter;
	}  

} 
