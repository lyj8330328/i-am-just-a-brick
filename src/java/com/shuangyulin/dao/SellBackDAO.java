package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.SellBackInfo;

public class SellBackDAO {
	private String errMessage; /*����ҵ���߼�����Ĵ���*/
	private String goodNo;		/*��ѯ����Ʒ���*/
	private String sellNo;		/*��ѯ�����۱��*/
	private String startDate;	/*��ѯ�Ŀ�ʼ����*/
	private String endDate;		/*��ѯ�Ľ�������*/
	private float totalPrice = 0.0f;	/*��ǰ��ѯ�����µ����������˻��ܼ۸�*/
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public String getGoodNo() {
		return goodNo;
	}
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
	public String getSellNo() {
		return sellNo;
	}
	public void setSellNo(String sellNo) {
		this.sellNo = sellNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/*������Ʒ�˻���Ϣģ�Ͷ���ʵ�ֹ˿��˻���ҵ�����*/
    public boolean AddSellBackInfoAdd(SellBackInfo sellBackInfo,boolean isGood)
    {
        try {
			/*��ѯ������Ϣ�����Ƿ���ڸ����۵��ݺ�*/
			String sqlString = "select COUNT(*) as count from [sellInfo] where sellNo='" + sellBackInfo.getSellNo() + "'";
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			int count = 0;
			if(rs.next())
				count = rs.getInt("count");
			if(0 == count) {
				 this.errMessage = "����������۵��ݺŲ�����!";
			     return false;
			}
			db.all_close();
			/*�жϸô��������Ƿ���ڸ���Ʒ����Ϣ*/
			sqlString = "select COUNT(*) as count from [sellInfo] where sellNo='" + sellBackInfo.getSellNo() + "' and goodNo='" + sellBackInfo.getGoodNo() + "'";
			rs = db.executeQuery(sqlString);
			count = 0 ;
			if(rs.next())
				count = rs.getInt("count");
			if(0 == count) {
				 this.errMessage = "�ô�����û�и���Ʒ����Ϣ��";
			     return false;
			}
			db.all_close();
			/*�ж��˻�����Ʒ�����Ƿ���ȷ*/
			sqlString = "select number from [sellInfo] where sellNo='" + sellBackInfo.getSellNo() + "' and goodNo='" + sellBackInfo.getGoodNo() + "'";
			int number = 0;
			rs = db.executeQuery(sqlString);
			if(rs.next())
				number = rs.getInt("number");
			if(sellBackInfo.getNumber() > number)
			{
			    this.errMessage = "����˻��������ܴ�������ʱ������!";
			    return false;
			}
			db.all_close();
			/*ͨ����֤��ִ����Ʒ�˻���Ϣ�ļ���*/
			sqlString = "insert into [sellBackInfo] (sellNo,goodNo,price,number,totalPrice,sellBackReason,sellBackTime) values ('";
			sqlString += sellBackInfo.getSellNo() + "','";
			sqlString += sellBackInfo.getGoodNo() + "',";
			sqlString += sellBackInfo.getPrice() + ",";
			sqlString += sellBackInfo.getNumber() + ",";
			sqlString += sellBackInfo.getTotalPrice() + ",'";
			sqlString += sellBackInfo.getSellBackReason() + "','";
			sqlString += sellBackInfo.getSellBackTime() + "')";
			db.executeUpdate(sqlString);
			db.all_close();
			/*����˻ص���Ʒ����õģ���Ҫ����Ʒ���*/
			if (isGood)
			{
			    sqlString = "update [goodStockInfo] set goodCount = goodCount + " + sellBackInfo.getNumber(); ;
			    db.executeUpdate(sqlString);
			    db.all_close();
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
    /*������Ʒ��ţ����۵��ݣ���ʼʱ��ͽ���ʱ���ѯ��Ʒ�˻���Ϣ*/
    public ArrayList<SellBackInfo> QuerySellBackInfo(String goodNo,String sellNo, String startDate, String endDate)
    {
    	ArrayList<SellBackInfo> sellBackInfoList = new ArrayList<SellBackInfo>();
    	/*���ݲ�ѯ��������sql���*/
        String sqlString = "select * from [sellBackInfo] where 1=1";
        if (goodNo != "")
            sqlString += " and goodNo like '%" + goodNo + "%'";
        if (sellNo != "")
            sqlString += " and sellNo like '%" + sellNo + "%'";
        if (startDate != "")
            sqlString += " and sellBackTime >= '" + startDate + "'";
        if (endDate != "")
            sqlString += " and sellBackTime <= '" + endDate + "'";
        try {
			/*�������ݲ�ִ�в�ѯ*/
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			while(rs.next()) {
				SellBackInfo sellBackInfo = new SellBackInfo();
				sellBackInfo.setSellBackId(rs.getInt("sellBackId"));
				sellBackInfo.setSellNo(rs.getString("sellNo"));
				sellBackInfo.setGoodNo(rs.getString("goodNo"));
				//sellBackInfo.setGoodName(rs.getString("goodName"));
				sellBackInfo.setPrice(rs.getFloat("price"));
				sellBackInfo.setNumber(rs.getInt("number"));
				sellBackInfo.setTotalPrice(rs.getFloat("totalPrice"));
				sellBackInfo.setSellBackReason(rs.getString("sellBackReason"));
				sellBackInfo.setSellBackTime(rs.getTimestamp("sellBackTime"));
				
				totalPrice += sellBackInfo.getTotalPrice();
				sellBackInfoList.add(sellBackInfo);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return sellBackInfoList;
       
    }
	
	
}
