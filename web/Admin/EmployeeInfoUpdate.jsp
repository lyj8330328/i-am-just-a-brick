<%@page contentType="text/html;charset=gb2312" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<%
   Boolean adminFlag = (Boolean)session.getAttribute("adminFlag");
   if(adminFlag == null || adminFlag != true) 
	   response.sendRedirect("../login.jsp");
   String EmployeeNo = request.getParameter("EmployeeNo");
   Employee employee = EmployeeDAO.GetEmployeeInfoByNo(EmployeeNo);
%>
<html>
<head runat="server">
    <title>�ޱ���ҳ</title>
 <LINK href="../css/style.css" type="text/css" rel="stylesheet">
  <script src="../script/calendar.js"></script>
</head>
<body>
 <c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
    <form method="post" name="empForm" action="EmployeeInfoManage?action=update">
       <input type=hidden name=EmployeeNo value=<%=EmployeeNo %>>
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/Edit.gif" width=14px height=14px>Ա����Ϣ����--&gt;Ա����Ϣ����</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          <br />
          Ա�����:<%=employee.getEmployeeNo() %>
          &nbsp; &nbsp;&nbsp;
          Ա������:<input type=text name=EmployeeName value=<%=employee.getEmployeeName() %>>&nbsp;&nbsp;
          &nbsp; &nbsp;&nbsp;<br />
          <br />
          �Ա�:
          <%
             String employeeSex = employee.getEmployeeSex();
             if(employeeSex.equals("��")) {
          %>
            <select name=EmployeeSex>
              <option value="��">��</option>
              <option value="Ů">Ů</option>
            </select>
          <% 
             } else {
          %>
            <select name=EmployeeSex>
              <option value="Ů">Ů</option>
              <option value="��">��</option>
            </select>
          <%
             }
          %>&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; 
          &nbsp; &nbsp; &nbsp;&nbsp;
          ѧ�����:
          <select name="EmployeeEducationId">
            <%
               ArrayList<Education> educationList = (ArrayList<Education>)EducationDAO.QueryAllEducation();
               for(int i=0;i<educationList.size();i++) {
            	   Education education = educationList.get(i);
            	   if(education.getEducationId() == employee.getEmployeeEducationId()) {
           %>
               <option selected value=<%=education.getEducationId() %>><%=education.getEducationName() %></option> 
           <% 
            	   } else {
           %>
           
              <option value=<%=education.getEducationId() %>><%=education.getEducationName() %></option>
           <%		   
            	   }  
               }
            %>
          </select>&nbsp;<br />
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<br />
          ����:<input type=text name="EmployeeBirthday" value='<%=employee.getEmployeeBirthday() %>' size=12 readOnly/>
          <input class="submit" name="Button" onclick="setDay(document.empForm.EmployeeBirthday);" style="width: 30px" type="button" value="ѡ��">
          &nbsp; &nbsp; &nbsp;&nbsp;
          ��½����:<input type=text name="EmployeePassword" value='<%=employee.getEmployeePassword() %>' size=20/><br />
          <br />
          ��ͥ�绰:<input type=text name="EmployeeHomeTel" value='<%=employee.getEmployeeHomeTel() %>' size=20/>
          &nbsp; &nbsp; �ֻ�:<input type=text name="EmployeeMobile" value='<%=employee.getEmployeeMobile() %>' size=20/><br />
          <br />
          ���֤��:<input type=text name="EmployeeCard" value='<%=employee.getEmployeeCard() %>' size=30/>
          �ʼ���ַ:<input type=text name="EmployeeEmail" value='<%=employee.getEmployeeEmail() %>' size=20/><br />
          <br />
          ��ס��ַ:<input type=text name="EmployeeAddress" value='<%=employee.getEmployeeAddress() %>'/></td>
	</tr>
          <tr>
              <td style="height: 26px" align="center">
                 <input type=submit value='����'/>
                 &nbsp;
                 <input type=button value="ȡ��" onclick="javascript:location.href='EmployeeInfoUpdate.jsp?EmployeeNo=<%=EmployeeNo %>';">
           </tr>
         
      </table>
        &nbsp;&nbsp;
    </form>
</body>
</html>
