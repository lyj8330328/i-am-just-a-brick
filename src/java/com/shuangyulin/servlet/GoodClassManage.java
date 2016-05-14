package com.shuangyulin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shuangyulin.javabean.GoodClass;
import com.shuangyulin.dao.GoodClassDAO;

public class GoodClassManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* ����ȡ��jspҳ�洫���Ĳ����������Ʒ�����Ϣ������Ϣ */
		String action = request.getParameter("action");
		/*�������ϵͳ������µ���Ʒ��Ϣ*/
		if(action.equals("add")) {
			String goodClassName = request.getParameter("goodClassName");
			GoodClass goodClass = new GoodClass();
			goodClass.setGoodClassName(goodClassName);
			GoodClassDAO goodClassDAO = new GoodClassDAO();
			/*����ҵ���ִ����Ʒ�����Ϣ����Ӳ���,�ɹ�����true*/
			boolean result = goodClassDAO.AddGoodClass(goodClass);
			if(result == true) {
				request.setAttribute("result", "��Ʒ�����Ϣ��ӳɹ�!");
			} else {
				request.setAttribute("result",goodClassDAO.getErrMessage());
			}
			RequestDispatcher rd = request.getRequestDispatcher("GoodClassManage.jsp");
		    rd.forward(request, response);
		} else if(action.equals("del")) {
			/*�����ɾ����Ʒ�������ȡ����Ʒ�����*/
			int goodClassId  = Integer.parseInt(request.getParameter("goodClassId"));
			GoodClassDAO goodClassDAO = new GoodClassDAO();
			/*����ҵ���ִ����Ʒ����ɾ�����ɹ�����true*/
			boolean result = goodClassDAO.DeleteGoodClassById(goodClassId);
			if(true == result)
				request.setAttribute("result","��Ʒ���ɾ���ɹ�!");
			else
				request.setAttribute("result",goodClassDAO.getErrMessage());
			RequestDispatcher rd = request.getRequestDispatcher("GoodClassManage.jsp");
			rd.forward(request, response);
			
		}
	}

}
