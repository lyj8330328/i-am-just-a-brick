package com.shuangyulin.javabean;

/*��ʵ����ӳ�����ݿ��е���Ʒ�����Ϣ��*
CREATE TABLE [dbo].[goodStockInfo] (
	[goodNo] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,	//��Ʒ���
	[goodCount] [int] NULL 											//��Ʒ����
) ON [PRIMARY]
*/
public class GoodStock {
	private String goodNo;
	private int goodCount;
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
