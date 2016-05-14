package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.BuyInfo;
import com.shuangyulin.javabean.Employee;

/*������Ʒ������Ϣ������ҵ�������*/
public class BuyInfoDAO {
	/* errMessage����ҵ����Ĵ�����Ϣ */
	private String errMessage;

	private final int pageSize = 3; /* ����ÿҳ��ʾ�Ľ�����¼���� */

	private int recordCount = 0; /* �����ѯ�����ܵļ�¼���� */

	private int totalPage = 0; /* ���ϲ�ѯ�������ܵ�ҳ�� */

	private int currentPage = 1; /* ��ǰҪ��ʾ��ҳ�� */
	
	private String goodNo;	/*��ѯ��Ʒ��ŵĹؼ���*/
	private String goodName; /*��ѯ��Ʒ���ƵĹؼ���*/
	private int goodClassId; /*��ѯ����Ʒ���*/
	private String startDate; /*��ѯ�Ŀ�ʼ����*/
	private String endDate; /*��ѯ�Ľ�������*/
	private float totalPrice = 0.0f; /*���������Ľ����ܼ�*/

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getGoodClassId() {
		return goodClassId;
	}

	public void setGoodClassId(int goodClassId) {
		this.goodClassId = goodClassId;
	}

	public String getGoodNo() {
		return goodNo;
	}

	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
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

	/* ִ����Ʒ������Ϣ�ĵǼǲ��� */
	public boolean AddBuyInfo(BuyInfo buyInfo) {
		try {
			/* ������֤����Ա�������Ʒ�����ϵͳ���Ƿ���� */
			String sqlString = "select * from [goodInfo] where goodNo='"
					+ buyInfo.getGoodNo() + "'";
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			if (!rs.next()) {
				this.errMessage = "����Ʒ������!";
				db.all_close();
				return false;
			}

			/* ����sql���ʵ�ֽ�����Ϣ�ļ������ */
			String insertString = "insert into [buyInfo] (goodNo,supplierName,price,number,totalPrice,buyDate,addTime) values ('";
			insertString += buyInfo.getGoodNo() + "','";
			insertString += buyInfo.getSupplierName() + "',";
			insertString += buyInfo.getPrice() + ",";
			insertString += buyInfo.getNumber() + ",";
			insertString += buyInfo.getTotalPrice() + ",'";
			insertString += buyInfo.getBuyDate() + "','";
			insertString += buyInfo.getAddTime() + "')";

			/* ���Ӷ�Ӧ��Ʒ�Ŀ���sql */
			String updateString = "update [goodStockInfo] set goodCount = goodCount + "
					+ buyInfo.getNumber()
					+ " where goodNo='"
					+ buyInfo.getGoodNo()
					+ "'";

			String sqlStrings[] = new String[] { insertString, updateString };
			boolean result = db.excuteSqlStrings(sqlStrings);
			if (!result) {
				this.errMessage = "�Ǽ���Ʒ������Ϣʱ�����˴���!";
				db.all_close();
				return false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*���ݲ�ѯ����ִ�н�����Ϣ�Ĳ�ѯ*/
	public ArrayList QueryBuyInfo() {
		this.PrepareQuery();	/*���ȸ��ݲ�ѯ���������ܵļ�¼�����ܵ�ҳ�����ܵļ۸�*/
		ArrayList<BuyInfo> buyInfoList = new ArrayList<BuyInfo>();
		String sqlString = "select * from [buyInfoView] where 1=1";
		if(!goodNo.equals(""))
			sqlString += " and goodNo like '%" +goodNo + "%'";
		if(!goodName.equals(""))
			sqlString += " and goodName like '%" + goodName + "%'";
		if(0 != goodClassId)
			sqlString += " and goodClassId=" + goodClassId;
		if(!startDate.equals(""))
			sqlString += " and buyDate>='" + startDate + "'";
		if(!endDate.equals(""))
			sqlString += " and buyDate<='" + endDate + "'";
		/*�������ݲ���в�ѯ*/
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sqlString);
			if(this.currentPage < 1) this.currentPage = 1;
			if(this.currentPage > this.totalPage) this.currentPage = this.totalPage;
			/*����Ҫ�ƶ����ټ�¼���ſ��Ե���Ҫ��ʾ��ҳ��*/
			int moveRecordCount = this.pageSize * (this.currentPage - 1);
			for(int i=0;i<moveRecordCount;i++) rs.next();
			/*�ƶ�rs��¼ָ�뵽��Ŀ��ҳ��Ͷ�Ŀ��ҳ��ÿ����¼���д���*/
			for(int i=0;i<this.pageSize;i++) {
				if(rs.next()) {
					BuyInfo buyInfo = new BuyInfo();
					buyInfo.setBuyId(rs.getInt("buyId"));
					buyInfo.setGoodNo(rs.getString("goodNo"));
					buyInfo.setSupplierName(rs.getString("supplierName"));
					buyInfo.setPrice(rs.getFloat("price"));
					buyInfo.setNumber(rs.getInt("number"));
					buyInfo.setTotalPrice(rs.getFloat("totalPrice"));
					buyInfo.setBuyDate(rs.getDate("buyDate"));
					buyInfo.setAddTime(rs.getTimestamp("addTime"));
					buyInfoList.add(buyInfo);
				} else {
					break;
				}
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buyInfoList;
	}

	private void PrepareQuery() {
		String sqlString = "select count(*) as recordCount,sum(totalPrice) as totalPrice from [buyInfoView] where 1=1";
		if(!goodNo.equals(""))
			sqlString += " and goodNo like '%" +goodNo + "%'";
		if(!goodName.equals(""))
			sqlString += " and goodName like '%" + goodName + "%'";
		if(0 != goodClassId)
			sqlString += " and goodClassId=" + goodClassId;
		if(!startDate.equals(""))
			sqlString += " and buyDate>='" + startDate + "'";
		if(!endDate.equals(""))
			sqlString += " and buyDate<='" + endDate + "'";
		DB db = new DB();
		try {
			/*�õ������������ܵļ�¼��*/
			ResultSet rs = db.executeQuery(sqlString);
			if(rs.next()) {
				recordCount = rs.getInt("recordCount");
				totalPrice = rs.getFloat("totalPrice");
			}
			
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*�����ܵļ�¼����ÿҳ��ʾ�ļ�¼���������ܵ�ҳ��*/
		this.totalPage = (this.recordCount + pageSize - 1) / this.pageSize;
		
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
}
