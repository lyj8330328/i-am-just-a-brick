package com.shuangyulin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.SellBackDAO;
import com.shuangyulin.javabean.SellBackInfo;
/*�����˻��Ŀ��Ʋ�*/
public class SellBackInfoManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equals("add")) /*��������˻���Ϣ*/
			AddSellBackInfo(request,response);
		else if(action.equals("query"))
			QuerySellBackInfo(request,response);
			
	}

	private void QuerySellBackInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*��ȡ��ѯ����*/
		String GoodNo = request.getParameter("GoodNo");
		String SellNo = request.getParameter("SellNo");
		String StartDate = request.getParameter("StartDate");
		String EndDate = request.getParameter("EndDate");
		/*����ҵ���ִ�й˿��˻���Ϣ�Ĳ�ѯ*/
		SellBackDAO sellBackDAO = new SellBackDAO();
		ArrayList<SellBackInfo> sellBackInfoList = sellBackDAO.QuerySellBackInfo(GoodNo, SellNo, StartDate, EndDate);
		request.setAttribute("StartDate", StartDate);
		request.setAttribute("EndDate", EndDate);
		request.setAttribute("GoodNo", GoodNo);
		request.setAttribute("SellNo", SellNo);
		request.setAttribute("TotalPrice", sellBackDAO.getTotalPrice());
		request.setAttribute("sellBackInfoList",sellBackInfoList);
		RequestDispatcher rd = request.getRequestDispatcher("SellBackInfoQuery.jsp");
		rd.forward(request, response);
	}

	private void AddSellBackInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*ȡ�ò���*/
		SellBackInfo sellBackInfo = new SellBackInfo();
		sellBackInfo.setSellNo(request.getParameter("SellNo"));
		sellBackInfo.setGoodNo(request.getParameter("GoodNo"));
		sellBackInfo.setPrice(Float.parseFloat(request.getParameter("Price")));
		sellBackInfo.setNumber(Integer.parseInt(request.getParameter("Number")));
		sellBackInfo.setTotalPrice(sellBackInfo.getPrice() * sellBackInfo.getNumber());
		sellBackInfo.setSellBackReason(request.getParameter("SellBackReason"));
		String IsGood = request.getParameter("IsGood");
		boolean isGoodFlag = false;
		if(IsGood.equals("yes")) isGoodFlag = true;
		/*����ҵ���ִ�й˿��˻�ҵ��*/
		SellBackDAO sellBackDAO = new SellBackDAO();
		boolean isSuccess = sellBackDAO.AddSellBackInfoAdd(sellBackInfo, isGoodFlag);
		if(isSuccess)
			request.setAttribute("result", "�˿Ͱ����˻��ɹ�!");
		else
			request.setAttribute("result", "ʧ��:" + sellBackDAO.getErrMessage());
		RequestDispatcher rd = request.getRequestDispatcher("SellBackInfoAdd.jsp");
		rd.forward(request, response);
		
	}

}
