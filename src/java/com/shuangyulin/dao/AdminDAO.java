package com.shuangyulin.dao;

import java.sql.ResultSet;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.Admin;

/*���ڹ���Ա��Ϣ������ҵ������*/
public class AdminDAO {

	/* ��֤����Ա��ݵ�½ */
	public static boolean checkLogin(Admin admin) {
		DB db = new DB();
		boolean flag = false;
		String sql = "select * from admin where adminUsername='"
				+ admin.getAdminUsername() + "' and adminPassword='"
				+ admin.getAdminPassword() + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next())
				flag = true;
			else
				flag = false;
			db.all_close();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} 
		return flag;
	}
	
	public static boolean ChangePassword(Admin admin) {
		try {
			DB db = new DB();
			String sql = "update [admin] set adminPassword='" + admin.getAdminPassword() + "' where adminUsername='" + admin.getAdminUsername() + "'";
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
