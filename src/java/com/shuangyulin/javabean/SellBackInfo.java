package com.shuangyulin.javabean;

import java.sql.Timestamp;

/*��ʵ��ӳ�����ݿ��еĹ˿��˻���Ϣ��
CREATE TABLE [dbo].[sellBackInfo] (
	[sellBackId] [int] IDENTITY (1, 1) NOT NULL ,				//ϵͳ��¼���
	[sellNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//����СƱ��
	[goodNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//��Ʒ���
	[price] [float] NULL ,										//�˻�����
	[number] [int] NULL ,										//�˻�����
	[totalPrice] [float] NULL ,									//�˻��ܼ�
	[sellBackReason] [text] COLLATE Chinese_PRC_CI_AS NULL ,	//�˻�ԭ��
	[sellBackTime] [datetime] NULL 								//�˻�ʱ��
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY
*/
public class SellBackInfo {
	private int sellBackId;
	private String sellNo;
	private String goodNo;
	private String goodName; /*��Ʒ���ƣ���ͼ������*/
	private float price;
	private int number;
	private float totalPrice;
	private String sellBackReason;
	private java.sql.Timestamp sellBackTime;
	public SellBackInfo() {
		sellBackId = 0;
		sellNo = "";
		goodNo = "";
		price = 0.0f;
		number = 0;
		totalPrice = 0.0f;
		sellBackReason = "";
		java.util.Date nowDate = new java.util.Date();
		sellBackTime = new Timestamp(nowDate.getTime());
	}
	public String getGoodNo() {
		return goodNo;
	}
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getSellBackId() {
		return sellBackId;
	}
	public void setSellBackId(int sellBackId) {
		this.sellBackId = sellBackId;
	}
	public String getSellBackReason() {
		return sellBackReason;
	}
	public void setSellBackReason(String sellBackReason) {
		this.sellBackReason = sellBackReason;
	}
	public java.sql.Timestamp getSellBackTime() {
		return sellBackTime;
	}
	public void setSellBackTime(java.sql.Timestamp sellBackTime) {
		this.sellBackTime = sellBackTime;
	}
	public String getSellNo() {
		return sellNo;
	}
	public void setSellNo(String sellNo) {
		this.sellNo = sellNo;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
}
