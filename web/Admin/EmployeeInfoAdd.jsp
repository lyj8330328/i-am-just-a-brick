<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<html>
<head>
    <title>�ޱ���ҳ</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
  <script src="../script/Vertify.js"></script>
  <script src="../script/calendar.js"></script>
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" name="empForm" onsubmit="return checkEmployeeAddForm(this);" action="EmployeeInfoManage?action=add">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>Ա����Ϣ����--&gt;Ա����Ϣ���</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          <br />
          Ա�����:<input type=text name="employeeNo" size=20 />&nbsp;&nbsp;
          &nbsp; &nbsp;&nbsp;
          Ա������:<input type=text name="employeeName" size=20/>&nbsp;&nbsp;
          &nbsp; &nbsp;&nbsp;<br />
          <br />
          �Ա�:<select name="employeeSex">
               <option value='��'>��</option>
               <option value='Ů'>Ů</option>
             </select>&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; 
          &nbsp; &nbsp; &nbsp;&nbsp; ѧ�����:
             <select name="educationId">
             <%
                ArrayList educationList = EducationDAO.QueryAllEducation();
                for(int i=0;i<educationList.size();i++) {
                	Education education = (Education)educationList.get(i);
             %>
                    <option value=<%=education.getEducationId() %>><%=education.getEducationName() %></option>
             <%
                }
             %>
             </select>
           &nbsp;<br />
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<br />
           ����:<input type=text name="employeeBirthday" size=12/>
          <input class="submit" name="Button" onclick="setDay(document.empForm.employeeBirthday);" style="width: 30px" type="button" value="ѡ��">
          &nbsp; &nbsp; &nbsp;&nbsp;
          ��½����:<input type=text name="employeePassword" size=20/><br/>
          <br />
          ��ͥ�绰:<input type=text name="employeeHomeTel" size=20/>
          &nbsp; &nbsp; �ֻ�:<input type=text name="employeeMobile" size=20/><br />
          <br />
          ���֤��:<input type=text name="employeeCard" size=30/>
          �ʼ���ַ:<input type=text name="employeeEmail" size=20/><br />
          <br />
          ��ס��ַ:<input type=text name="employeeAddress"/></td>
	</tr>
          <tr>
              <td style="height: 26px" align="center">
                  <input type=submit value="���" />&nbsp;
                  <input type=reset value="ȡ��" /></td>
          </tr>
         
      </table>
       &nbsp;&nbsp;
    </form>
</body>
</html>
