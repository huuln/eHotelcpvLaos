package com.elcom.eodapp.media.common;

public class eItemOrder {
	private String itemCode;
	private String iQty;
	private String orderTime;
	private String orderDate;
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getiQty() {
		return iQty;
	}
	public void setiQty(String iQty) {
		this.iQty = iQty;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public String toString()
	{
		return "itemCode: " + itemCode + " | iQty: " + iQty + " | orderTime: " +  orderTime + " | orderDate: " + orderDate;
	}
	
}
