package com.shuangyulin.javabean;

/*��ʵ����ӳ�����ݿ��й��ﳵ��Ϣ��
CREATE TABLE [dbo].[goodCartInfo] (
	[goodCartId] [int] IDENTITY (1, 1) NOT NULL ,					//ϵͳ��¼���
	[employeeNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//Ա�����
	[goodNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//��Ʒ���
	[goodCount] [int] NULL 											//��Ʒ����
) ON [PRIMARY]
*/
public class GoodCart {
	private int goodCartId;
	private String employeeNo;
	private String goodNo;
	private int goodCount;
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public int getGoodCartId() {
		return goodCartId;
	}
	public void setGoodCartId(int goodCartId) {
		this.goodCartId = goodCartId;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public String getGoodNo() {
		return goodNo;
	}
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
	
}
