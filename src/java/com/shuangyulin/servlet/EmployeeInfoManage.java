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

import com.shuangyulin.dao.EmployeeDAO;
import com.shuangyulin.dao.GoodDAO;
import com.shuangyulin.javabean.Employee;
import com.shuangyulin.javabean.Good;

public class EmployeeInfoManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("add")) {
			AddEmployeeInfo(request, response);
		} else if(action.equals("query")) {
			QueryEmployeeInfo(request, response);
		} else if(action.equals("update")) {
			UpdateEmployeeInfo(request, response);
		}
		
	}

	private void UpdateEmployeeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*ȡ��Ա����Ϣ����*/
		String employeeNo = request.getParameter("EmployeeNo");
		String employeeName = request.getParameter("EmployeeName");
		String employeeSex = request.getParameter("EmployeeSex");
		int employeeEducationId = Integer.parseInt(request.getParameter("EmployeeEducationId"));
		/*���������ڽ���ת��*/
		String employeeBirthday = request.getParameter("EmployeeBirthday");
		java.util.Date EmployeeBirthdayDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(employeeBirthday);
			EmployeeBirthdayDate = dateFormat.parse(employeeBirthday);  //util����
		} catch (ParseException e) {
			System.out.println("ת�����ڴ���!");
			e.printStackTrace();
		}
		java.sql.Date employeeBirthday_SQLDATE = new java.sql.Date(EmployeeBirthdayDate.getTime());//sql���� 
		String employeePassword = request.getParameter("EmployeePassword");
		String employeeMobile = request.getParameter("EmployeeMobile");
		String employeeCard = request.getParameter("EmployeeCard");
		String employeeEmail = request.getParameter("EmployeeEmail");
		String employeeAddress = request.getParameter("EmployeeAddress");
		/*����Ա��ģ��*/
		Employee employee = new Employee();
		employee.setEmployeeNo(employeeNo);
		employee.setEmployeeName(employeeName);
		employee.setEmployeeSex(employeeSex);
		employee.setEmployeeEducationId(employeeEducationId);
		employee.setEmployeeBirthday(employeeBirthday_SQLDATE);
		employee.setEmployeePassword(employeePassword);
		employee.setEmployeeMobile(employeeMobile);
		employee.setEmployeeCard(employeeCard);
		employee.setEmployeeEmail(employeeEmail);
		employee.setEmployeeAddress(employeeAddress);
		/*����ҵ���ִ��Ա����Ϣ�ĸ���*/
		EmployeeDAO employeeDAO = new EmployeeDAO();
		int result = employeeDAO.UpdateEmployeeInfo(employee);
		if(result > 0) {
			request.setAttribute("result", "Ա����Ϣ���³ɹ�!");
		} else {
			request.setAttribute("result", "Ա����Ϣ����ʧ��!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("EmployeeInfoUpdate.jsp?EmployeeNo=" + employeeNo);
		rd.forward(request, response);
	}

	private void QueryEmployeeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*����ȡ�ò�ѯ�Ĺؼ���*/
		String EmployeeNo = request.getParameter("EmployeeNo");
		if(null == EmployeeNo) EmployeeNo = "";
		String EmployeeName = request.getParameter("EmployeeName");
		String method = request.getParameter("method");
		if(null == method) method = "post";
		if(method.equals("get"))
			EmployeeName = new String(EmployeeName.getBytes("ISO-8859-1"),"GBK"); 
		if(null == EmployeeName) EmployeeName = "";
		int currentPage = 1;
		if(request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		/*����ҵ���Է���������Ա����Ϣ���в�ѯ*/
		EmployeeDAO employeeDAO = new EmployeeDAO();
		/*���ò�ѯ����*/
		employeeDAO.setEmployeeNo(EmployeeNo);
		employeeDAO.setEmployeeName(EmployeeName);
		employeeDAO.setCurrentPage(currentPage);
		ArrayList<Employee> employeeList = employeeDAO.QueryEmployeeInfo();
		request.setAttribute("employeeList", employeeList);
		request.setAttribute("EmployeeNo", EmployeeNo);
		request.setAttribute("EmployeeName",EmployeeName);
		request.setAttribute("recordCount",employeeDAO.getRecordCount());
		request.setAttribute("pageSize", employeeDAO.getPageSize());
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("totalPage",employeeDAO.getTotalPage());
		RequestDispatcher rd = request.getRequestDispatcher("EmployeeManage.jsp");
		rd.forward(request, response);
	}

	private void AddEmployeeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*���ȴ���ͼ��ȡ�ø�Ա���������Ե���Ϣ��ת������*/
		String employeeNo = request.getParameter("employeeNo");
		String employeeName = request.getParameter("employeeName");
		String employeePassword = request.getParameter("employeePassword");
		String employeeSex = request.getParameter("employeeSex");
		int employeeEducationId = Integer.parseInt(request.getParameter("educationId"));
		String employeeHomeTel = request.getParameter("employeeHomeTel");
		String employeeMobile = request.getParameter("employeeMobile");
		String employeeCard = request.getParameter("employeeCard");
		String employeeEmail = request.getParameter("employeeEmail");
		String employeeAddress = request.getParameter("employeeAddress");
		String employeeBirthday = request.getParameter("employeeBirthday");
		java.util.Date employeeBirthdayDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(employeeBirthday);
			employeeBirthdayDate = dateFormat.parse(employeeBirthday);  //util����
		} catch (ParseException e) {
			System.out.println("ת�����ڴ���!");
			e.printStackTrace();
		}
		java.sql.Date employeeBirthdayTime = new java.sql.Date(employeeBirthdayDate.getTime());//sql���� 

		/*Ȼ��Ա����Ϣ������Ա����Ϣbean��*/
		Employee employee = new Employee();
		employee.setEmployeeNo(employeeNo);
		employee.setEmployeeName(employeeName);
		employee.setEmployeePassword(employeePassword);
		employee.setEmployeeSex(employeeSex);
		employee.setEmployeeBirthday(employeeBirthdayTime);
		employee.setEmployeeEducationId(employeeEducationId);
		employee.setEmployeeHomeTel(employeeHomeTel);
		employee.setEmployeeMobile(employeeMobile);
		employee.setEmployeeCard(employeeCard);
		employee.setEmployeeEmail(employeeEmail);
		employee.setEmployeeAddress(employeeAddress);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		if(employeeDAO.AddEmployeeInfo(employee)) {
			request.setAttribute("result", "Ա����Ϣ��ӳɹ�!");
		} else {
			request.setAttribute("result", employeeDAO.getErrMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("EmployeeInfoAdd.jsp");
		rd.forward(request, response);
	}

}
