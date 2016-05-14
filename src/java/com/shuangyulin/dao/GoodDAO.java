package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.Good;
import com.shuangyulin.javabean.GoodClass;

/*������Ʒ��Ϣ�����ҵ������*/
public class GoodDAO {
	private String errMessage; /* ����ҵ����ʱ�����Ĵ�����Ϣ */

	private final int pageSize = 8; /* ����ÿҳ��ʾ����Ʒ���� */

	private int recordCount = 0; /* �����ѯ�����ܵļ�¼���� */

	private int totalPage = 0; /* ���ϲ�ѯ�������ܵ�ҳ�� */

	private int currentPage = 1; /* ��ǰҪ��ʾ��ҳ�� */

	private String goodNo = ""; /* ��ѯ��Ʒ��Źؼ��� */

	private String goodName = ""; /* ��ѯ��Ʒ���ƹؼ��� */

	private int goodClassId = 0; /* ��ѯ����Ʒ��� */

	public GoodDAO() {
		this.errMessage = "";
	}

	public String getErrMessage() {
		return errMessage;
	}

	/* ������Ʒ��Ϣģ�Ͷ��󣬽�����Ʒ��Ϣ�����ҵ�� */
	public boolean AddGoodInfo(Good good) {
		/* ��֤��Ʒ������벻��Ϊ�� */
		if (good.getGoodNo().equals("")) {
			this.errMessage = "��Ʒ�����Ϣ���벻��Ϊ��!";
			return false;
		}
		/* ��֤��Ʒ�������벻��Ϊ�� */
		if (good.getGoodName().equals("")) {
			this.errMessage = "��Ʒ�������벻��Ϊ��!";
			return false;
		}
		/* ��������Ʒ������ϵͳ���Ƿ��Ѿ����� */
		String sqlString = "select count(*) from [goodInfo] where goodNo='"
				+ good.getGoodNo() + "'";
		DB db = new DB();
		try {
			if (db.getRecordCount(sqlString) != 0) {
				this.errMessage = "����Ʒ����Ѿ�������!";
				db.all_close();
				return false;

			}
			/* ���������֤ͨ����ִ��sql��������Ʒ��Ϣ */
			sqlString = "insert into [goodInfo] "
					+ "(goodNo,goodClassId,goodName,goodUnit,goodModel,goodSpecs,"
					+ "goodPrice,goodPlace,goodMemo,goodAddTime) values ('";
			sqlString += good.getGoodNo() + "',";
			sqlString += good.getGoodClassId() + ",'";
			sqlString += good.getGoodName() + "','";
			sqlString += good.getGoodUnit() + "','";
			sqlString += good.getGoodModel() + "','";
			sqlString += good.getGoodSpecs() + "',";
			sqlString += good.getGoodPrice() + ",'";
			sqlString += good.getGoodPlace() + "','";
			sqlString += good.getGoodMemo() + "','";
			sqlString += good.getGoodAddTime() + "')";
			/* Ȼ�����Ӹ���Ʒ�Ŀ����Ϊ0 */
			String sqlString2 = "insert into [goodStockInfo] (goodNo,goodCount) values ('"
					+ good.getGoodNo() + "',0)";
			String[] sqlStrings = new String[] { sqlString, sqlString2 };
			if (!db.excuteSqlStrings(sqlStrings)) {
				this.errMessage = "�����Ʒ��Ϣ�����˴���!";
				return false;
			}
			return true;
		} catch (Exception e) {
			this.errMessage = "���������ݴ���!";
			db.all_close();
			e.printStackTrace();
			return false;
		}
	}

	/* ��ѯ������������Ʒ��Ϣ */
	public ArrayList QueryGoodInfo() {
		this.PrepareQuery(); /* ���ȸ��ݲ�ѯ���������ܵļ�¼���ܵ�ҳ�� */
		ArrayList<Good> goodList = new ArrayList<Good>(); /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select * from [goodInfo] where 1=1";
		if (goodNo != "")
			sqlString += " and goodNo like '%" + goodNo + "%'";
		if (goodName != "")
			sqlString += " and goodName like '%" + goodName + "%'";
		if (goodClassId != 0)
			sqlString += " and goodClassId=" + goodClassId;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sqlString);
			if (this.currentPage < 1)
				this.currentPage = 1;
			if (this.currentPage > this.totalPage)
				this.currentPage = this.totalPage;
			/* ����Ҫ�ƶ����ټ�¼���ſ��Ե���Ҫ��ʾ��ҳ�� */
			int moveRecordCount = this.pageSize * (this.currentPage - 1);
			for (int i = 0; i < moveRecordCount; i++)
				rs.next();
			/* �ƶ�rs��¼ָ�뵽��Ŀ��ҳ��Ͷ�Ŀ��ҳ��ÿ����¼���д��� */
			for (int i = 0; i < this.pageSize; i++) {
				if (rs.next()) {
					Good good = new Good();
					good.setGoodNo(rs.getString("goodNo"));
					good.setGoodClassId(rs.getInt("goodClassId"));
					good.setGoodName(rs.getString("goodName"));
					good.setGoodUnit(rs.getString("goodUnit"));
					good.setGoodModel(rs.getString("goodModel"));
					good.setGoodSpecs(rs.getString("goodSpecs"));
					good.setGoodPrice(rs.getFloat("goodPrice"));
					good.setGoodPlace(rs.getString("goodPlace"));
					good.setGoodMemo(rs.getString("goodMemo"));
					good.setGoodAddTime(rs.getTimestamp("goodAddTime"));
					goodList.add(good);
				} else {
					break;
				}
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodList;
	}

	/* �õ�����ĳ����ѯ�������ܵ�ҳ�� */
	public void PrepareQuery() {
		String sqlString = "select count(*) from [goodInfo] where 1=1";
		if (goodNo != "")
			sqlString += " and goodNo like '%" + goodNo + "%'";
		if (goodName != "")
			sqlString += " and goodName like '%" + goodName + "%'";
		if (goodClassId != 0)
			sqlString += " and goodClassId=" + goodClassId;
		DB db = new DB();
		try {
			/* �õ������������ܵļ�¼�� */
			recordCount = db.getRecordCount(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* �����ܵļ�¼����ÿҳ��ʾ�ļ�¼���������ܵ�ҳ�� */
		this.totalPage = (this.recordCount + pageSize - 1) / this.pageSize;
	}

	/* ������Ʒ��ŵõ�ĳ����Ʒ����ϸ��Ϣ */
	public static Good GetGoodInfoByGoodNo(String goodNo) {
		Good good = new Good();
		String sql = "select * from [goodInfo] where goodNo='" + goodNo + "'";
		DB db = new DB();
		ResultSet rs;
		try {
			rs = db.executeQuery(sql);
			if (rs.next()) {
				/* �����ݿ��еĸ���Ʒ��Ϣ��������Ʒ��Ϣģ�Ͷ����� */
				good.setGoodNo(rs.getString("goodNo"));
				good.setGoodClassId(rs.getInt("goodClassId"));
				good.setGoodName(rs.getString("goodName"));
				good.setGoodUnit(rs.getString("goodUnit"));
				good.setGoodModel(rs.getString("goodModel"));
				good.setGoodSpecs(rs.getString("goodSpecs"));
				good.setGoodPrice(rs.getFloat("goodPrice"));
				good.setGoodPlace(rs.getString("goodPlace"));
				good.setGoodMemo(rs.getString("goodMemo"));
				good.setGoodAddTime(rs.getTimestamp("goodAddTime"));
			}
		} catch (Exception e) {
			System.out.println("������Ʒ�����Ϣʱ�����˴���!");
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return good;
	}

	/* ������Ʒ��Ϣ */
	public static int UpdateGoodInfo(Good good) {
		int result = 0;
		String sql = "update [goodInfo] set goodClassId=";
		sql += good.getGoodClassId() + ",goodName='";
		sql += good.getGoodName() + "',goodUnit='";
		sql += good.getGoodUnit() + "',goodModel='";
		sql += good.getGoodModel() + "',goodSpecs='";
		sql += good.getGoodSpecs() + "',goodPrice=";
		sql += good.getGoodPrice() + ",goodPlace='";
		sql += good.getGoodPlace() + "',goodMemo='";
		sql += good.getGoodMemo() + "' where goodNo='" + good.getGoodNo() + "'";
		DB db = new DB();
		try {
			result = db.executeUpdate(sql);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<Good> QueryAllGoodInfo() {
		ArrayList<Good> goodList = new ArrayList<Good>();
		String sqlString = "select * from [goodInfo]";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Good good = new Good();
				good.setGoodNo(rs.getString("goodNo"));
				good.setGoodClassId(rs.getInt("goodClassId"));
				good.setGoodName(rs.getString("goodName"));
				good.setGoodUnit(rs.getString("goodUnit"));
				good.setGoodModel(rs.getString("goodModel"));
				good.setGoodSpecs(rs.getString("goodSpecs"));
				good.setGoodPrice(rs.getFloat("goodPrice"));
				good.setGoodPlace(rs.getString("goodPlace"));
				good.setGoodMemo(rs.getString("goodMemo"));
				good.setGoodAddTime(rs.getTimestamp("goodAddTime"));
				goodList.add(good);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getGoodClassId() {
		return goodClassId;
	}

	public void setGoodClassId(int goodClassId) {
		this.goodClassId = goodClassId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodNo() {
		return goodNo;
	}

	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}
}
