package com.shuangyulin.javabean;
/*��ʵ��ӳ�����ݿ��е�������Ϣ��
CREATE TABLE [dbo].[sellInfo] (
	[sellInfoId] [int] IDENTITY (1, 1) NOT NULL ,				//ϵͳ��¼���
	[sellNo] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,//���۵��ݱ��
	[goodNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//��Ʒ���
	[price] [float] NULL ,										//���۵���
	[number] [int] NULL ,										//��������
	[totalPrice] [float] NULL ,									//�����ܼ�
	[sellTime] [datetime] NULL ,								//����ʱ��
	[employeeNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL 	//���۵�Ա�����
) ON [PRIMARY]
*/
public class SellInfo {
	private int sellInfoId;
	private String sellNo;
	private String goodNo;
	private float price;
	private int number;
	private float totalPrice;
	private java.sql.Timestamp sellTime;
	private String employeeNo;
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
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
	public int getSellInfoId() {
		return sellInfoId;
	}
	public void setSellInfoId(int sellInfoId) {
		this.sellInfoId = sellInfoId;
	}
	public String getSellNo() {
		return sellNo;
	}
	public void setSellNo(String sellNo) {
		this.sellNo = sellNo;
	}
	public java.sql.Timestamp getSellTime() {
		return sellTime;
	}
	public void setSellTime(java.sql.Timestamp sellTime) {
		this.sellTime = sellTime;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public SellInfo() {
		sellInfoId = 0;
		sellNo = "";
		goodNo = "";
		price = 0.0f;
		number = 0;
		totalPrice = 0.0f;
		java.util.Date nowTime = new java.util.Date();
		sellTime = new java.sql.Timestamp(nowTime.getTime());
		employeeNo = "";
	}
}
