package com.shuangyulin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.SellDAO;
import com.shuangyulin.javabean.EmployeeSellResult;
import com.shuangyulin.javabean.SellInfo;

public class SellInfoManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equals("add"))
			AddSellInfo(request,response);
		else if(action.equals("sellInfoQuery")) /*��ѯ������Ϣ*/
			QuerySellInfo(request,response);
		else if(action.equals("employeeSellResultQuery")) /*��ѯԱ������ҵ��*/
			QueryEmployeeSellResult(request,response);
		else if(action.equals("selfSellInfoQuery")) /*��ѯ��������ҵ��*/
			QuerySelfSellInfo(request,response);
			
	}

	private void QuerySelfSellInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*��ȡ��ѯ�Ĳ���*/
		String EmployeeNo = request.getParameter("EmployeeNo");
		if(null == EmployeeNo) EmployeeNo = "";
		String SellNo = request.getParameter("SellNo");
		if(null == SellNo) SellNo = "";
		String StartDate = request.getParameter("StartDate");
		if(null == StartDate) StartDate = "";
		String EndDate = request.getParameter("EndDate");
		if(null == EndDate) EndDate = "";
		/*ע�������ҵ�����*/
		SellDAO sellDAO = new SellDAO();
		sellDAO.setEmployeeNo(EmployeeNo);
		sellDAO.setSellNo(SellNo);
		sellDAO.setStartDate(StartDate);
		sellDAO.setEndDate(EndDate);
		/*����ҵ���Ĳ�ѯ���ܻ�ȡ��ѯ���*/
		ArrayList<SellInfo> sellInfoList = (ArrayList<SellInfo>)sellDAO.QuerySellInfo();
		request.setAttribute("EmployeeNo", EmployeeNo);
		request.setAttribute("SellNo", SellNo);
		request.setAttribute("StartDate", StartDate);
		request.setAttribute("EndDate", EndDate);
		request.setAttribute("sellInfoList",sellInfoList);
		RequestDispatcher rd = request.getRequestDispatcher("SelfSellInfoQuery.jsp");
		rd.forward(request, response);
		
	}

	private void QueryEmployeeSellResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*��ȡ��ѯ����*/
		String StartDate = request.getParameter("StartDate");
		if(null == StartDate) StartDate = "";
		String EndDate = request.getParameter("EndDate");
		if(null == EndDate) EndDate = "";
		/*ע�������ҵ�����*/
		SellDAO sellDAO = new SellDAO();
		sellDAO.setStartDate(StartDate);
		sellDAO.setEndDate(EndDate);
		/*����ҵ���Ĳ�ѯ���ܻ�ȡ��ѯ���*/
		ArrayList<EmployeeSellResult> employeeSellResultList = (ArrayList<EmployeeSellResult>)sellDAO.QueryEmployeeSellResult();
		request.setAttribute("StartDate", StartDate);
		request.setAttribute("EndDate", EndDate);
		request.setAttribute("employeeSellResultList",employeeSellResultList);
		RequestDispatcher rd = request.getRequestDispatcher("EmployeeSellResult.jsp");
		rd.forward(request, response);
	}

	private void QuerySellInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*��ȡ��ѯ�Ĳ���*/
		String EmployeeNo = request.getParameter("EmployeeNo");
		if(null == EmployeeNo) EmployeeNo = "";
		String SellNo = request.getParameter("SellNo");
		if(null == SellNo) SellNo = "";
		String StartDate = request.getParameter("StartDate");
		if(null == StartDate) StartDate = "";
		String EndDate = request.getParameter("EndDate");
		if(null == EndDate) EndDate = "";
		/*ע�������ҵ�����*/
		SellDAO sellDAO = new SellDAO();
		sellDAO.setEmployeeNo(EmployeeNo);
		sellDAO.setSellNo(SellNo);
		sellDAO.setStartDate(StartDate);
		sellDAO.setEndDate(EndDate);
		/*����ҵ���Ĳ�ѯ���ܻ�ȡ��ѯ���*/
		ArrayList<SellInfo> sellInfoList = (ArrayList<SellInfo>)sellDAO.QuerySellInfo();
		request.setAttribute("EmployeeNo", EmployeeNo);
		request.setAttribute("SellNo", SellNo);
		request.setAttribute("StartDate", StartDate);
		request.setAttribute("EndDate", EndDate);
		request.setAttribute("sellInfoList",sellInfoList);
		RequestDispatcher rd = request.getRequestDispatcher("SellInfoQuery.jsp");
		rd.forward(request, response);
		
		
	}

	private void AddSellInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
