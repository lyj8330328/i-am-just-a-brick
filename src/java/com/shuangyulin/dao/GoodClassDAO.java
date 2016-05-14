package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;


import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.GoodClass;

/*������Ʒ������ҵ�������*/

public class GoodClassDAO {
	/*����ҵ����Ĵ�����Ϣ*/
	private String errMessage;
	
	public static ArrayList QueryAllGoodClassInfo(){
		ArrayList goodClassList = new ArrayList();
		/*��ѯ��Ʒ�����Ϣ���е����е���Ʒ�����Ϣ*/
		String sql="select * from [goodClassInfo]";
		DB db=new DB();
		ResultSet rs;
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				/*�����ݿ��е�ÿ����Ʒ�����Ϣ��������Ʒ�����Ϣģ�Ͷ�����*/
				GoodClass goodClass = new GoodClass();
				goodClass.setGoodClassId(rs.getInt("goodClassId"));
				goodClass.setGoodClassName(rs.getString("goodClassName"));
				goodClassList.add(goodClass);
			}
		} catch (Exception e) {
			System.out.println("������Ʒ�����Ϣʱ�����˴���!");
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return goodClassList;
	}
	/*��ϵͳ�м����µ���Ʒ��Ϣ*/
	public boolean AddGoodClass(GoodClass goodClass){
		/*��ѯ��Ʒ�����Ϣ�����Ƿ��Ѿ����ڸ���Ʒ�����Ϣ*/
		String sql="select * from [goodClassInfo] where goodClassName='" + goodClass.getGoodClassName() + "'";
		DB db=new DB();
		boolean flag = true;
		ResultSet rs;
		try {
			rs = db.executeQuery(sql);
			if(rs.next()){
				flag = false;
				this.errMessage = "����Ʒ����Ѿ�������!";
				db.all_close();
			} else {
				sql = "insert into [goodClassInfo] (goodClassName) values ('" + goodClass.getGoodClassName() + "')";
				db.executeUpdate(sql);
				db.all_close();
			}
		} catch (Exception e) {
			flag = false;
			this.errMessage = "���ݲ��������˴���!";
		} 
		return flag;
	}

	/*������Ʒ�����ɾ����Ʒ�����Ϣ*/
	public boolean DeleteGoodClassById(int goodClassId) {
		boolean result = true;
		String sqlString = "select count(*) from [goodInfo] where goodClassId=" + goodClassId;
		DB db = new DB();
		try {
			if(db.getRecordCount(sqlString) != 0) {
				errMessage = "����Ʒ����»�������Ʒ��Ϣ������ɾ��!";
				result = false;
				db.all_close();
			} else {
				sqlString = "delete from [goodClassInfo] where goodClassId=" + goodClassId;
				db.executeUpdate(sqlString);
				db.all_close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errMessage = "���������ݴ���!";
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/*������Ʒ����ŵõ���Ʒ������ơ�*/
	public static String GetGoodClassNameById(int goodClassId) {
		String goodClassName = "";
		String sqlString = "select goodClassName from [goodClassInfo] where goodClassId=" + goodClassId;
		DB db = new DB();
		goodClassName = db.GetScalarString(sqlString);
		db.all_close();
		return goodClassName;
	}
	
	public String getErrMessage() {
		return errMessage;
	}

}
