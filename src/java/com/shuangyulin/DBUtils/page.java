package com.shuangyulin.DBUtils;

import com.shuangyulin.DBUtils.DB;

import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;

public class page {

	int ShowPage = 1; // ������Ҫ��ʾ��ҳ��

	int RowCount = 0; // ResultSet�ļ�¼��Ŀ ��ʼֵ

	int PageCount = 0; // ResultSet��ҳ�����ҳ�� ��ʼֵ

	int redundance = 0; // ���÷�ҳ���һҳ�ļ�¼�� ��ʼֵ

	public String HttpFile;

	DB db; // �������ݿ����

	public page() {
	}

	int Cint(String cint) {
		try {
			int n;
			n = Integer.parseInt(cint);
			return n;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public int getCount() throws Exception { // ��ȡ��������
		db = new DB();
		int count = 0;
		ResultSet rs = null;
		try {
			String sql = "select count(id) as count from liuyan_temp";
			rs = db.executeQuery(sql);
			if (rs.next())
				count = rs.getInt(1);
		} catch (Exception e) {
			throw e;
		} finally {
			db.all_close();
		}
		return count;
	}

	// ��Ҫ�������������������������ӱ��ж�ȡ��Ӧ�ļ�¼

	public ResultSet myQuery(HttpServletRequest request,
			HttpServletResponse response, int PageSize) throws Exception {
		HttpFile = request.getRequestURI();
		RowCount = this.getCount();
		// if(Rowcount==0)������
		redundance = RowCount % PageSize;
		if (redundance == 0) { // ����ҳ��
			PageCount = RowCount / PageSize;
		} else {
			PageCount = (RowCount - redundance) / PageSize;
			PageCount++;
		}
		String ToPage = request.getParameter("ToPage");
		if (ToPage == "")
			ToPage = "1";
		ShowPage = Cint(ToPage);
		if (ShowPage > PageCount)
			ShowPage = PageCount;
		if (ShowPage <= 0)
			ShowPage = 1;
		String sql = "select * from liuyan_temp order by id desc limit "
				+ (ShowPage - 1) * PageSize + "," + PageSize + "";
		db = new DB();
		ResultSet rs = db.executeQuery(sql);
		return rs;

	}

	// ��ʾ��ҳ��
	public int getTotalPages() {
		return PageCount;
	}

	// ��ʾ��ǰ����ҳ��
	public int getCurrenPages() {
		return ShowPage;
	}

	// ��ʾ��������
	public int getRowCount() {
		return RowCount;
	}

	public String PageFooter() // ��ʾ���·�ҳ
	{
		String str = "";
		int next, prev;
		prev = ShowPage - 1;
		next = ShowPage + 1;
		str += "<form aciont=" + HttpFile + ">";
		str += "��ѯ��<span>" + getRowCount() + "</span>����¼" + "    ��<span>"
				+ getTotalPages() + "</span>ҳ";
		str += " ��<span>" + getCurrenPages() + "</span>ҳ ";
		if (ShowPage > 1)
			str += " <A href=" + HttpFile + "?ToPage=1" + ">��ҳ</A> ";
		else
			str += " ��ҳ ";
		if (ShowPage > 1)
			str += " <A href=" + HttpFile + "?ToPage=" + prev + ">��һҳ</A> ";
		else
			str += " ��һҳ ";
		if (ShowPage < PageCount)
			str += " <A href=" + HttpFile + "?ToPage=" + next + ">��һҳ</A> ";
		else
			str += " ��һҳ ";
		if (PageCount > 1 && ShowPage != PageCount)
			str += " <A href=" + HttpFile + "?ToPage=" + PageCount + ">βҳ</A>";
		else
			str += " βҳ ";
		str += "��<input name=ToPage type=text size=2>ҳ <input type=submit value=GO></form>";
		return str;
	}

	public void all_close() {
		db.all_close();
	}
}
