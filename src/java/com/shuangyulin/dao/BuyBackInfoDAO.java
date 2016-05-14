package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.BuyBackInfo;

/*���ڽ����˻���Ϣ��ҵ�������*/
public class BuyBackInfoDAO{
	/* errMessage����ҵ����Ĵ�����Ϣ */
	private String errMessage;

	private final int pageSize = 3; /* ����ÿҳ��ʾ�Ľ����˻���¼���� */

	private int recordCount = 0; /* �����ѯ�����ܵļ�¼���� */

	private int totalPage = 0; /* ���ϲ�ѯ�������ܵ�ҳ�� */

	private int currentPage = 1; /* ��ǰҪ��ʾ��ҳ�� */
	
	private String goodNo;	/*��ѯ��Ʒ��ŵĹؼ���*/
	private String goodName; /*��ѯ��Ʒ���ƵĹؼ���*/
	private int goodClassId; /*��ѯ����Ʒ���*/
	private String startDate; /*��ѯ�Ŀ�ʼ����*/
	private String endDate; /*��ѯ�Ľ�������*/
	private float totalPrice = 0.0f; /*���������Ľ����˻��ܼ�*/

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
	public boolean AddBuyBackInfo(BuyBackInfo buyBackInfo) {
		try {
			/* ������֤����Ա�������Ʒ�����ϵͳ���Ƿ���� */
			String sqlString = "select * from [goodInfo] where goodNo='"
					+ buyBackInfo.getGoodNo() + "'";
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			if (!rs.next()) {
				this.errMessage = "����Ʒ������!";
				db.all_close();
				return false;
			}
			/*��ѯ����ƷĿǰ�Ŀ����*/
			int goodCount = 0;
            sqlString = "select goodCount from [goodStockInfo] where goodNo='" + buyBackInfo.getGoodNo() + "'";
            rs = db.executeQuery(sqlString);
            if(rs.next())
            	goodCount = rs.getInt("goodCount");
            if (buyBackInfo.getNumber() > goodCount)
            {
                this.errMessage = "������ĸ���Ʒ�������ܴ��ڿ����";
                return false;
            }
            db.all_close();
            /*������Ʒ�˻���Ϣ�Ǽ���ϵͳ��*/
            String insertString = "insert into buyBackInfo (goodNo,supplierName,price,number,totalPrice,buyBackDate,buyBackReason,buyBackAddTime) values ('";
            insertString += buyBackInfo.getGoodNo() + "','";
            insertString += buyBackInfo.getSupplierName() + "',";
            insertString += buyBackInfo.getPrice() + ",";
            insertString += buyBackInfo.getNumber() + ",";
            insertString += buyBackInfo.getTotalPrice() + ",'";
            insertString += buyBackInfo.getBuyBackDate() + "','";
            insertString += buyBackInfo.getBuyBackReason() + "','";
            insertString += buyBackInfo.getBuyBackAddTime() + "')";
            /* ���ٶ�Ӧ��Ʒ�Ŀ���sql */
			String updateString = "update [goodStockInfo] set goodCount = goodCount-"
					+ buyBackInfo.getNumber()
					+ " where goodNo='"
					+ buyBackInfo.getGoodNo()
					+ "'";
			String[] sqlStrings = new String[] {insertString,updateString};
            //db.executeUpdate(insertString);
            //db.executeUpdate(updateString);
            db.excuteSqlStrings(sqlStrings); 
            db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.errMessage = e.getMessage();
			return false;
		}
		return true;
	}
	
	/*���ݲ�ѯ����ִ�н����˻���Ϣ�Ĳ�ѯ*/
	public ArrayList QueryBuyBackInfo() {
		this.PrepareQuery();	/*���ȸ��ݲ�ѯ���������ܵļ�¼�����ܵ�ҳ�����ܵļ۸�*/
		ArrayList<BuyBackInfo> buyBackInfoList = new ArrayList<BuyBackInfo>();
		String sqlString = "select * from [buyBackInfoView] where 1=1";
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
					BuyBackInfo buyBackInfo = new BuyBackInfo();
					buyBackInfo.setBuyBackId(rs.getInt("buyBackId"));
					buyBackInfo.setGoodNo(rs.getString("goodNo"));
					buyBackInfo.setSupplierName(rs.getString("supplierName"));
					buyBackInfo.setPrice(rs.getFloat("price"));
					buyBackInfo.setNumber(rs.getInt("number"));
					buyBackInfo.setTotalPrice(rs.getFloat("totalPrice"));
					buyBackInfo.setBuyBackDate(rs.getDate("buyBackDate"));
					buyBackInfo.setBuyBackReason(rs.getString("buyBackReason"));
					buyBackInfo.setBuyBackAddTime(rs.getTimestamp("buyBackAddTime"));
					buyBackInfoList.add(buyBackInfo);
				} else {
					break;
				}
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buyBackInfoList;
	}

	private void PrepareQuery() {
		String sqlString = "select count(*) as recordCount,sum(totalPrice) as totalPrice from [buyBackInfoView] where 1=1";
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
