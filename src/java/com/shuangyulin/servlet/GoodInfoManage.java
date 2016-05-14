package com.shuangyulin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.GoodDAO;
import com.shuangyulin.javabean.Good;

public class GoodInfoManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*����ȡ�����������Ϣ*/
		String action = request.getParameter("action");
		/*����ǽ�����Ʒ��Ϣ����Ӳ���*/
		if(action.equals("add")) {	
			AddGoodInfo(request, response);
		} else if(action.equals("query")) {
			QueryGoodInfo(request, response);
		} else if(action.equals("update")) {
			UpdateGoodInfo(request, response);
			
		}
		
	}

	private void UpdateGoodInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Good good = new Good();
		String goodNo = request.getParameter("goodNo");
		good.setGoodNo(goodNo);
		good.setGoodClassId(Integer.parseInt(request.getParameter("goodClassId")));
		good.setGoodName(request.getParameter("goodName"));
		good.setGoodUnit(request.getParameter("goodUnit"));
		good.setGoodModel(request.getParameter("goodModel"));
		good.setGoodSpecs(request.getParameter("goodSpecs"));
		good.setGoodPrice(Float.parseFloat(request.getParameter("goodPrice")));
		good.setGoodPlace(request.getParameter("goodPlace"));
		good.setGoodMemo(request.getParameter("goodMemo"));
		int result = GoodDAO.UpdateGoodInfo(good);
		if(result > 0) {
			request.setAttribute("result", "��Ʒ��Ϣ���³ɹ�!");
		} else {
			request.setAttribute("result", "��Ʒ��Ϣ����ʧ��!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("GoodInfoUpdate.jsp?goodNo=" + goodNo);
		rd.forward(request, response);
	}

	private void QueryGoodInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*����ȡ�ò�ѯ�ĸ���������Ϣ*/
		String GoodNo = request.getParameter("GoodNo");
		if(GoodNo == null) GoodNo = "";
		String GoodName = request.getParameter("GoodName");
		if(null == GoodName) GoodName = "";
		int GoodClassId = 0;
		if(request.getParameter("GoodClassId") != null)
			GoodClassId = Integer.parseInt(request.getParameter("GoodClassId"));
		int currentPage = 1;
		if(request.getParameter("currentPage") != null)
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		/*����ҵ���Է�����������Ʒ��Ϣ���в�ѯ*/
		GoodDAO goodDAO = new GoodDAO();
		/*���ò�ѯ����*/
		goodDAO.setGoodNo(GoodNo);
		goodDAO.setGoodName(GoodName);
		goodDAO.setGoodClassId(GoodClassId);
		goodDAO.setCurrentPage(currentPage);
		/*��ʼ����ѯ�������������ܵļ�¼�������ܵ�ҳ��*/
		ArrayList<Good> goodList = goodDAO.QueryGoodInfo();
		request.setAttribute("goodList", goodList);
		request.setAttribute("GoodNo", GoodNo);
		request.setAttribute("GoodName",GoodName);
		request.setAttribute("GoodClassId", GoodClassId);
		request.setAttribute("recordCount",goodDAO.getRecordCount());
		request.setAttribute("pageSize", goodDAO.getPageSize());
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("totalPage",goodDAO.getTotalPage());
		RequestDispatcher rd = request.getRequestDispatcher("GoodInfoManage.jsp");
		rd.forward(request, response);
	}

	private void AddGoodInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*�ռ���Ʒ����Ϣ��������Ʒģ��*/
		Good good = new Good();
		good.setGoodNo(request.getParameter("GoodNo"));
		good.setGoodClassId(Integer.parseInt(request.getParameter("GoodClassId")));
		good.setGoodName(request.getParameter("GoodName"));
		good.setGoodUnit(request.getParameter("GoodUnit"));
		good.setGoodModel(request.getParameter("GoodModel"));
		good.setGoodSpecs(request.getParameter("GoodSpecs"));
		try {
			good.setGoodPrice(Float.parseFloat(request.getParameter("GoodPrice")));
		} catch (Exception e) {
			good.setGoodPrice(0.0f);
		}
		good.setGoodPlace(request.getParameter("GoodPlace"));
		good.setGoodMemo(request.getParameter("GoodMemo"));
		java.util.Date now = new java.util.Date();
		//java.sql.Date goodAddTime = new java.sql.Date(now.getTime());
		java.sql.Timestamp goodAddTime = new java.sql.Timestamp(now.getTime());
		good.setGoodAddTime(goodAddTime);
		/*����ҵ��������Ʒ��Ϣ�ļ������*/
		GoodDAO goodDAO = new GoodDAO();
		boolean result = goodDAO.AddGoodInfo(good);
		if(result == true) {
			request.setAttribute("result", "��Ʒ��Ϣ��ӳɹ�!");
		} else {
			request.setAttribute("result",goodDAO.getErrMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("GoodInfoAdd.jsp");
		rd.forward(request, response);
	}

}
