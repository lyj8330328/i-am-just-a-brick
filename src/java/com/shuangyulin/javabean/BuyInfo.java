package com.shuangyulin.javabean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*��ʵ��ӳ�����ݿ��е���Ʒ������Ϣ��
CREATE TABLE [dbo].[buyInfo] (
	[buyId] [int] IDENTITY (1, 1) NOT NULL ,						//�������
	[goodNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//��Ʒ���
	[supplierName] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,	/��Ӧ�̹�˾����
	[price] [float] NULL ,											//��������
	[number] [int] NULL ,											//��������
	[totalPrice] [float] NULL ,										//�����ܼ۸�
	[buyDate] [datetime] NULL ,										//��������
	[addTime] [datetime] NULL 										//��Ϣ����ʱ��
) ON [PRIMARY] */
public class BuyInfo {
	private int buyId;
	private String goodNo;
	private String supplierName;
	private float price;
	private int number;
	private float totalPrice;
	private Date buyDate;
	private Timestamp addTime;
	
	/*���캯��*/
	public BuyInfo() {
		buyId = 0;
		goodNo = supplierName = "";
		price = 0.0f;
		number = 0;
		totalPrice = 0.0f;
		java.util.Date nowDate = new java.util.Date();
		buyDate = new Date(nowDate.getTime());
		addTime = new Timestamp(nowDate.getTime());
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public int getBuyId() {
		return buyId;
	}
	public void setBuyId(int buyId) {
		this.buyId = buyId;
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
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
