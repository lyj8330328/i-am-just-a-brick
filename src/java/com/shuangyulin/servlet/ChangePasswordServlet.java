package com.shuangyulin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/*�޸�����Ŀ��Ʋ�*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.javabean.Admin;
import com.shuangyulin.javabean.Employee;
import com.shuangyulin.dao.*;

public class ChangePasswordServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identify = request.getParameter("identify");
		if(identify.equals("admin")) {
			Admin admin = new Admin();
			admin.setAdminUsername(request.getParameter("adminUsername"));
			admin.setAdminPassword(request.getParameter("NewPassword"));
			if(AdminDAO.ChangePassword(admin))
				request.setAttribute("result", "�����޸ĳɹ�!");
			else
				request.setAttribute("result", "�����޸�ʧ��!");
			RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
			rd.forward(request, response);
		} else if(identify.equals("employee")) {
			Employee employee = new Employee();
			employee.setEmployeeNo(request.getParameter("employeeNo"));
			employee.setEmployeePassword(request.getParameter("NewPassword"));
			if(EmployeeDAO.ChangePassword(employee))
				request.setAttribute("result", "�����޸ĳɹ�!");
			else
				request.setAttribute("result", "�����޸�ʧ��!");
			RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
			rd.forward(request, response);
		}
	}

}
