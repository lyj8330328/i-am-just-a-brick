<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head runat="server">
    <title>�ޱ���ҳ</title>
<LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<%
		String EmployeeNo = (String)request.getAttribute("EmployeeeNo");
        if(EmployeeNo == null) EmployeeNo = "";
		String EmployeeName = (String)request.getAttribute("EmployeeName");
		if(EmployeeName == null) EmployeeName = "";
		int recordCount = (Integer)request.getAttribute("recordCount");
		//int pageSize = (Integer)request.getAttribute("pageSize");
		int currentPage = (Integer)request.getAttribute("currentPage");
		int totalPage = (Integer)request.getAttribute("totalPage");
		String pageFootStr = "";  
		int next, prev;  
		prev=currentPage-1;  
		next=currentPage+1; 
		String actionPath = "EmployeeInfoManage?action=query&method=get&EmployeeNo=" + EmployeeNo + "&EmployeeName=" + EmployeeName;
		pageFootStr+="<form action='" + actionPath + "' method=post>";
		pageFootStr += "��ѯ��<span>"+recordCount+"</span>����¼"+"    ��<span>"+totalPage+"</span>ҳ";  
		pageFootStr +=" ��<span>"+currentPage+"</span>ҳ ";  
		if(currentPage>1) 
			pageFootStr += " <A href=" + actionPath + "&currentPage=1"+">��ҳ</A> ";  
		else 
			pageFootStr += " ��ҳ ";  
		if(currentPage>1)
			pageFootStr += " <A href=" + actionPath + "&currentPage=" + prev + ">��һҳ</A> ";  
		else
			pageFootStr += " ��һҳ ";  
		if(currentPage<totalPage)  
			pageFootStr += " <A href=" + actionPath + "&currentPage=" + next + ">��һҳ</A> ";  
		else 
			pageFootStr += " ��һҳ ";  
		if(totalPage>1&&currentPage!=totalPage)  
			pageFootStr += " <A href=" + actionPath + "&currentPage=" + totalPage + ">βҳ</A>";  
		else
			pageFootStr += " βҳ ";  
		pageFootStr+="��<input name=currentPage type=text size=2>ҳ <input type=submit value=GO></form>" ;
	%>
    <form method="post" id="frmAnnounce" action="EmployeeInfoManage?action=query">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	      <td style="height: 14px">
            <img src="../images/list.gif" width=14px height=14px />Ա����Ϣ����--&gt;Ա����Ϣά��
          </td>
        </tr>
        <tr>
          <td style="height: 37px">
             Ա�����:<input type=text name="EmployeeNo" size=10/>
             Ա������:<input type=text name="EmployeeName" size=10>&nbsp;
            <input type="submit" value="��ѯ"/>
           </td>
         </tr>
       </table>
       <table width=600 border=0 cellpadding=2 align="center">
         <tr class="title">
           <td>Ա�����</td><td>Ա������</td><td>Ա���Ա�</td><td>Ա������</td><td>Ա���绰</td><td>�༭</td>
         </tr>
         <%
            ArrayList<Employee> employeeList = (ArrayList<Employee>)request.getAttribute("employeeList");
            for(int i=0;i<employeeList.size();i++) {
            	Employee employee = (Employee)employeeList.get(i);
           
         %>
         <tr onmouseover="this.style.backgroundColor='#227711';" onmouseOut="this.style.backgroundColor='';">
           <td><%=employee.getEmployeeNo() %></td>
           <td><%=employee.getEmployeeName() %></td>
           <td><%=employee.getEmployeeSex() %></td>
           <td><%=employee.getEmployeeBirthday() %></td>
           <td><%=employee.getEmployeeMobile() %></td>
           <td><a href="EmployeeInfoUpdate.jsp?EmployeeNo=<%=employee.getEmployeeNo() %>">�༭</a></td>
         </tr>
         <%
            }
         %>
         <tr><td colspan=6>
		<%=pageFootStr %>
		&nbsp;
		</td>
		</tr>
    </table>
  </form>
</body>
</html>
