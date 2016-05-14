package com.shuangyulin.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.BuyInfoDAO;
import com.shuangyulin.javabean.BuyInfo;

public class BuyInfoManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		/*���������µĽ�����Ϣ*/
		if(action.equals("add")) {
			AddBuyInfo(request, response);
		} else if(action.equals("query")) {
			BuyInfoQuery(request, response);
		}
	}

	private void BuyInfoQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*ȡ�ò�ѯ�Ĳ�����Ϣ*/
		String GoodNo = request.getParameter("GoodNo");
		if(null == GoodNo) GoodNo = "";
		String method = (String)request.getParameter("method");
		if(null == method) method = "post";
		String GoodName = request.getParameter("GoodName");
		if(null == GoodName) GoodName = "";
		if(method.equals("get")) {
			GoodName = new String(GoodName.getBytes("ISO-8859-1"),"GBK"); 
		}
		int GoodClassId = 0;
		if(null != request.getParameter("GoodClassId"))
			GoodClassId = Integer.parseInt((String)request.getParameter("GoodClassId"));
		String StartDate = request.getParameter("StartDate");
		if(null == StartDate) StartDate = "";
		String EndDate = request.getParameter("EndDate");
		if(null == EndDate) EndDate = "";
		int currentPage = 1;
		if(request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		/*����ҵ����ѯ����ѯǰ�ȴ����ѯ�Ĳ�����Ϣ*/
		BuyInfoDAO buyInfoDAO = new BuyInfoDAO();
		buyInfoDAO.setGoodNo(GoodNo);
		buyInfoDAO.setGoodName(GoodName);
		buyInfoDAO.setGoodClassId(GoodClassId);
		buyInfoDAO.setStartDate(StartDate);
		buyInfoDAO.setEndDate(EndDate);
		buyInfoDAO.setCurrentPage(currentPage);
		ArrayList<BuyInfo> buyInfoList = buyInfoDAO.QueryBuyInfo();
		request.setAttribute("buyInfoList", buyInfoList);
		request.setAttribute("GoodNo", GoodNo);
		request.setAttribute("GoodName", GoodName);
		request.setAttribute("GoodClassId", GoodClassId);
		request.setAttribute("StartDate", StartDate);
		request.setAttribute("EndDate", EndDate);
		request.setAttribute("recordCount",buyInfoDAO.getRecordCount());
		request.setAttribute("pageSize", buyInfoDAO.getPageSize());
		request.setAttribute("totalPage",buyInfoDAO.getTotalPage());
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("TotalPrice",buyInfoDAO.getTotalPrice());
		RequestDispatcher rd = request.getRequestDispatcher("BuyInfoQuery.jsp");
		rd.forward(request, response);
	}

	private void AddBuyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*ȡ�Ľ���������Ϣ*/
		String SupplierName = request.getParameter("SupplierName"); /*��Ӧ��*/
		String GoodNo = request.getParameter("GoodNo"); /*��Ʒ���*/
		String Price = request.getParameter("Price"); /*�����۸�*/
		String Number = request.getParameter("Number"); /*��������*/
		String BuyDate = request.getParameter("BuyDate"); /*��������*/
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date BuyDate_Util = null;
		try {
			BuyDate_Util = dateFormat.parse(BuyDate);  //util����
		} catch (ParseException e) {
			System.out.println("ת�����ڴ���!");
			e.printStackTrace();
		}
		java.sql.Date BuyDate_Sql = new java.sql.Date(BuyDate_Util.getTime());//sql���� 
		BuyInfo buyInfo = new BuyInfo(); /*������Ʒ������Ϣ����*/
		buyInfo.setGoodNo(GoodNo);
		buyInfo.setSupplierName(SupplierName);
		buyInfo.setPrice(Float.parseFloat(Price));
		buyInfo.setNumber(Integer.parseInt(Number));
		buyInfo.setTotalPrice(Float.parseFloat(Price) * Integer.parseInt(Number));
		buyInfo.setBuyDate(BuyDate_Sql);
		/*����ҵ�����н�����Ϣ����Ӳ���*/
		BuyInfoDAO buyInfoDAO = new BuyInfoDAO();
		if(buyInfoDAO.AddBuyInfo(buyInfo))
			request.setAttribute("result", "��Ʒ������ӳɹ�!");
		else
			request.setAttribute("result", buyInfoDAO.getErrMessage());
		RequestDispatcher rd = request.getRequestDispatcher("BuyInfoAdd.jsp");
		rd.forward(request, response);
	}

}
