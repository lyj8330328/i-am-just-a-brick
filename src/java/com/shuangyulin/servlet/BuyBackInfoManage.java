package com.shuangyulin.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.BuyBackInfoDAO;
import com.shuangyulin.dao.BuyInfoDAO;
import com.shuangyulin.javabean.BuyBackInfo;
import com.shuangyulin.javabean.BuyInfo;

/*��Ʒ�����˻���Ϣ�Ŀ��Ʋ���*/

public class BuyBackInfoManage extends HttpServlet {

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
			AddBuyBackInfo(request, response);
		} else if(action.equals("query")) {
			BuyBackInfoQuery(request, response);
		}
	}

	private void BuyBackInfoQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		BuyBackInfoDAO buyBackInfoDAO = new BuyBackInfoDAO();
		buyBackInfoDAO.setGoodNo(GoodNo);
		buyBackInfoDAO.setGoodName(GoodName);
		buyBackInfoDAO.setGoodClassId(GoodClassId);
		buyBackInfoDAO.setStartDate(StartDate);
		buyBackInfoDAO.setEndDate(EndDate);
		buyBackInfoDAO.setCurrentPage(currentPage);
		ArrayList<BuyBackInfo> buyBackInfoList = buyBackInfoDAO.QueryBuyBackInfo();
		request.setAttribute("buyBackInfoList", buyBackInfoList);
		request.setAttribute("GoodNo", GoodNo);
		request.setAttribute("GoodName", GoodName);
		request.setAttribute("GoodClassId", GoodClassId);
		request.setAttribute("StartDate", StartDate);
		request.setAttribute("EndDate", EndDate);
		request.setAttribute("recordCount",buyBackInfoDAO.getRecordCount());
		request.setAttribute("pageSize", buyBackInfoDAO.getPageSize());
		request.setAttribute("totalPage",buyBackInfoDAO.getTotalPage());
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("TotalPrice",buyBackInfoDAO.getTotalPrice());
		RequestDispatcher rd = request.getRequestDispatcher("BuyBackInfoQuery.jsp");
		rd.forward(request, response);
	}

	private void AddBuyBackInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*ȡ�Ľ���������Ϣ*/
		String SupplierName = request.getParameter("SupplierName"); /*��Ӧ��*/
		String GoodNo = request.getParameter("GoodNo"); /*��Ʒ���*/
		String Price = request.getParameter("Price"); /*�����˻��۸�*/
		String Number = request.getParameter("Number"); /*�����˻�����*/
		String BuyBackReason = request.getParameter("BuyBackReason"); /*�����˻�ԭ��*/
		String BuyBackDate = request.getParameter("BuyBackDate"); /*�����˻�����*/
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date BuyBackDate_Util = null;
		try {
			BuyBackDate_Util = dateFormat.parse(BuyBackDate);  //util����
		} catch (ParseException e) {
			System.out.println("ת�����ڴ���!");
			e.printStackTrace();
		}
		java.sql.Date BuyBackDate_Sql = new java.sql.Date(BuyBackDate_Util.getTime());//sql���� 
		BuyBackInfo buyBackInfo = new BuyBackInfo(); /*������Ʒ�����˻���Ϣ����*/
		buyBackInfo.setGoodNo(GoodNo);
		buyBackInfo.setSupplierName(SupplierName);
		buyBackInfo.setPrice(Float.parseFloat(Price));
		buyBackInfo.setNumber(Integer.parseInt(Number));
		buyBackInfo.setTotalPrice(Float.parseFloat(Price) * Integer.parseInt(Number));
		buyBackInfo.setBuyBackReason(BuyBackReason);
		buyBackInfo.setBuyBackDate(BuyBackDate_Sql);
		/*����ҵ�����н�����Ϣ����Ӳ���*/
		BuyBackInfoDAO buyBackInfoDAO = new BuyBackInfoDAO();
		if(buyBackInfoDAO.AddBuyBackInfo(buyBackInfo))
			request.setAttribute("result", "��Ʒ�����˻���ӳɹ�!");
		else
			request.setAttribute("result", buyBackInfoDAO.getErrMessage());
		RequestDispatcher rd = request.getRequestDispatcher("BuyBackInfoAdd.jsp");
		rd.forward(request, response);
	}

}
