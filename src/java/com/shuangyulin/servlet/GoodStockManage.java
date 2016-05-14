package com.shuangyulin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.GoodStockDAO;
import com.shuangyulin.javabean.GoodStock;
/*��Ʒ������Ŀ��Ʋ�*/
public class GoodStockManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("query")) {
			/*��ȡ��ѯ����*/
			String GoodNo = request.getParameter("GoodNo");
			String GoodName = request.getParameter("GoodName");
			/*ע�������ҵ�����*/
			GoodStockDAO goodStockDAO = new GoodStockDAO();
			goodStockDAO.setGoodNo(GoodNo);
			goodStockDAO.setGoodName(GoodName);
			/*����ҵ���Ĳ�ѯ���ܻ�ȡ��ѯ���*/
			ArrayList<GoodStock> goodStockList = goodStockDAO.QueryGoodStockInfo();
			request.setAttribute("goodStockList",goodStockList);
			request.setAttribute("GoodNo",GoodNo);
			request.setAttribute("GoodName",GoodName);
			RequestDispatcher rd = request.getRequestDispatcher("GoodStockQuery.jsp");
			rd.forward(request, response);
		} else if(action.equals("warning")) {
			GoodStockDAO goodStockDAO = new GoodStockDAO();
			ArrayList<GoodStock> goodStockList = goodStockDAO.QueryGoodStockWarningInfo();
			request.setAttribute("goodStockList",goodStockList);
			RequestDispatcher rd = request.getRequestDispatcher("GoodStockWarning.jsp");
			rd.forward(request, response);
		}
	}

}
