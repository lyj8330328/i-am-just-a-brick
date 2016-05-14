package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.Admin;
import com.shuangyulin.javabean.Employee;
import com.shuangyulin.javabean.Good;

/*����Ա����Ϣ������ҵ�������*/
public class EmployeeDAO {
	/*errMessage����ҵ����Ĵ�����Ϣ*/
	private String errMessage;
	private final int  pageSize = 8; /*����ÿҳ��ʾ��Ա������*/
	private int recordCount = 0;	/*�����ѯ�����ܵļ�¼����*/
	private int totalPage = 0;		/*���ϲ�ѯ�������ܵ�ҳ��*/
	private int currentPage = 1; 	/*��ǰҪ��ʾ��ҳ��*/
	private String employeeNo = ""; /*��ѯԱ���ı�Źؼ���*/
	private String employeeName = ""; /*��ѯԱ���������ؼ���*/
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public int getPageSize() {
		return pageSize;
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

	public String getErrMessage() {
		return errMessage;
	}

	/*��֤Ա����½ϵͳ�ʺź��������ȷ��*/
	public static boolean checkLogin(Employee employee) {
		DB db = new DB();
		boolean flag = false;
		String sql = "select * from employeeInfo where employeeName='"
				+ employee.getEmployeeNo() + "' and employeePassword='"
				+ employee.getEmployeePassword() + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next())
				flag = true;
			else
				flag = false;
			
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return flag;
	}
	
	/*����Ա����Ų�ѯϵͳ���Ƿ��Ѿ����ڸü�¼*/
	public boolean IsExistEmployee(String employeeNo) {
		boolean isExist = false;
		String sql = "select * from [employeeInfo] where employeeNo='" + employeeNo + "'";
		ResultSet rs = null;
		DB db = new DB();
		try {
			rs = db.executeQuery(sql);
			if(rs.next()) isExist = true;
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExist;
	}
	
	/*����Ա����Ϣbean��ִ�и�Ա����Ϣ�������ݿ�*/
	public boolean AddEmployeeInfo(Employee employee) {
		/*���Ȳ�ѯϵͳ�и�Ա������Ƿ��Ѿ�����*/
		if(IsExistEmployee(employee.getEmployeeNo())) {
			this.errMessage = "ϵͳ���Ѿ������˸�Ա�����!";
			return false;
		}
		String sql = "insert into [employeeInfo] " +
				"(employeeNo,employeeName,employeePassword,employeeSex," +
				"employeeBirthday,employeeEducationId,employeeHomeTel," +
				"employeeMobile,employeeCard,employeeEmail,employeeAddress) values ('";
		sql += employee.getEmployeeNo() + "','";
		sql += employee.getEmployeeName() + "','";
		sql += employee.getEmployeePassword() + "','";
		sql += employee.getEmployeeSex() + "','";
		sql += employee.getEmployeeBirthday() + "',";
		sql += employee.getEmployeeEducationId() + ",'";
		sql += employee.getEmployeeHomeTel() + "','";
		sql += employee.getEmployeeMobile() + "','";
		sql += employee.getEmployeeCard() + "','";
		sql += employee.getEmployeeEmail() + "','";
		sql += employee.getEmployeeAddress() + "')";
		
		boolean isSuccess = true;
		DB db = new DB();
		try {
			if(db.executeUpdate(sql) <= 0) {
				this.errMessage = "�������ʱ�����˴���!";
				isSuccess = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.errMessage = "�������ʱ�����˴���!";
			isSuccess = false;
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return isSuccess;
		
	}
	
	/*���ݲ�ѯ����ִ��Ա����Ϣ�Ĳ�ѯ*/
	public ArrayList QueryEmployeeInfo() {
		this.PrepareQuery();	/*���ȸ��ݲ�ѯ���������ܵļ�¼���ܵ�ҳ��*/
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sqlString = "select * from [employeeInfo] where 1=1";
		if(employeeNo != "")
			sqlString += " and employeeNo like '%" + employeeNo + "%'";
		if(employeeName != "")
			sqlString += " and employeeName like '%" + employeeName + "%'";
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
					Employee employee = new Employee();
					employee.setEmployeeNo(rs.getString("employeeNo"));
					employee.setEmployeeName(rs.getString("employeeName"));
					employee.setEmployeePassword(rs.getString("employeePassword"));
					employee.setEmployeeSex(rs.getString("employeeSex"));
					employee.setEmployeeBirthday(rs.getDate("employeeBirthday"));
					employee.setEmployeeEducationId(rs.getInt("employeeEducationId"));
					employee.setEmployeeHomeTel(rs.getString("employeeHomeTel"));
					employee.setEmployeeMobile(rs.getString("employeeMobile"));
					employee.setEmployeeCard(rs.getString("employeeCard"));
					employee.setEmployeeEmail(rs.getString("employeeEmail"));
					employee.setEmployeeAddress(rs.getString("employeeAddress"));
					employeeList.add(employee);
				} else {
					break;
				}
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeList;
	}

	private void PrepareQuery() {
		String sqlString = "select count(*) from [employeeInfo] where 1=1";
		if(employeeNo != "")
			sqlString += " and employeeNo like '%" + employeeNo + "%'";
		if(employeeName != "")
			sqlString += " and employeeName like '%" + employeeName + "%'";
		DB db = new DB();
		try {
			/*�õ������������ܵļ�¼��*/
			recordCount = db.getRecordCount(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*�����ܵļ�¼����ÿҳ��ʾ�ļ�¼���������ܵ�ҳ��*/
		this.totalPage = (this.recordCount + pageSize - 1) / this.pageSize;
		
	}
	
	/*����Ա����Ų�ѯ��Ա������ϸ��Ϣ������*/
	public static Employee GetEmployeeInfoByNo(String employeeNo) {
		Employee employee = null;
		String sql = "select * from [employeeInfo] where employeeNo='" + employeeNo + "'";
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sql);
			if(rs.next()) {
				employee = new Employee();
				employee.setEmployeeNo(employeeNo);
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setEmployeePassword(rs.getString("employeePassword"));
				employee.setEmployeeSex(rs.getString("employeeSex"));
				employee.setEmployeeBirthday(rs.getDate("employeeBirthday"));
				employee.setEmployeeEducationId(rs.getInt("employeeEducationId"));
				employee.setEmployeeHomeTel(rs.getString("employeeHomeTel"));
				employee.setEmployeeMobile(rs.getString("employeeMobile"));
				employee.setEmployeeCard(rs.getString("employeeCard"));
				employee.setEmployeeEmail(rs.getString("employeeEmail"));
				employee.setEmployeeAddress(rs.getString("employeeAddress"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	
	/*����Ա��ʵ��ģ�ͣ�ִ�и�Ա����Ϣ�ĸ��²���*/
	public int UpdateEmployeeInfo(Employee employee) {
		/*������µ�sql���*/
		String sql = "update [employeeInfo] set employeeName='";
		sql += employee.getEmployeeName() + "',employeeSex='";
		sql += employee.getEmployeeSex() + "',employeeEducationId=";
		sql += employee.getEmployeeEducationId() + ",employeeBirthday='";
		sql += employee.getEmployeeBirthday() + "',employeePassword='";
		sql += employee.getEmployeePassword() + "',employeeMobile='";
		sql += employee.getEmployeeMobile() + "',employeeCard='";
		sql += employee.getEmployeeCard() + "',employeeEmail='";
		sql += employee.getEmployeeEmail() + "',employeeAddress='";
		sql += employee.getEmployeeAddress() + "' where employeeNo='" + employee.getEmployeeNo() + "'";
		/*�������ݲ�ִ�и���*/
		DB db = new DB();
		int result = 0;
		try {
			result = db.executeUpdate(sql);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static boolean ChangePassword(Employee employee) {
		try {
			DB db = new DB();
			String sql = "update [employeeInfo] set employeePassword='" + employee.getEmployeePassword() + "' where employeeNo='" + employee.getEmployeeNo() + "'";
			int result = db.executeUpdate(sql);
			db.all_close();
			if( result > 0)
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
