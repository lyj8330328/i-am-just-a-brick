package com.shuangyulin.javabean;

/*��ʵ��javabean��Ӧ���ݿ��е���Ʒ�����Ϣ��
CREATE TABLE [dbo].[goodClassInfo] (
	[goodClassId] [int] IDENTITY (1, 1) NOT NULL ,						//��Ʒ�����
	[goodClassName] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL 		//��Ʒ�������
) ON [PRIMARY]
 */
public class GoodClass {
	private int goodClassId;
	private String goodClassName;
	public int getGoodClassId() {
		return goodClassId;
	}
	public void setGoodClassId(int goodClassId) {
		this.goodClassId = goodClassId;
	}
	public String getGoodClassName() {
		return goodClassName;
	}
	public void setGoodClassName(String goodClassName) {
		this.goodClassName = goodClassName;
	}
	
}
