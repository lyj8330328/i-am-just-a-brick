<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<html>
<head>
    <title>�ޱ���ҳ</title>
<LINK href="../css/style.css" type="text/css" rel="stylesheet">
<script src="../script/calendar.js"></script>
</head>
<body>
<%
   String StartDate = (String)request.getAttribute("StartDate");
   if(null == StartDate) StartDate = "";
   String EndDate = (String)request.getAttribute("EndDate");
   if(null == EndDate) EndDate = "";
%>
 <form method="post" name=employeeSellResultForm action="SellInfoManage?action=employeeSellResultQuery">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px" colspan=3>
          <img src="../images/edit.gif" width=14px height=14px>������Ϣ����--&gt;Ա������ҵ����ѯ</td>
        </tr>
        <tr><td colspan=3>
            ��ʼʱ��:<input type=text name=StartDate value='<%=StartDate %>' size=10/>
          <input class="submit" name="Button" onclick="setDay(document.employeeSellResultForm.StartDate);" style="width: 30px" type="button" value="ѡ��">
            ��������<input type=text name=EndDate value='<%=EndDate %>' size=10/>
          <input class="submit" name="Button" onclick="setDay(document.employeeSellResultForm.EndDate);" style="width: 30px" type="button" value="ѡ��">
          <input type="submit" value="��ѯ"/>
       </td></tr>
         </table><br />
         <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
            <tr style="color:red"><td>Ա������</td><td>����ҵ��</td></tr>
         <%
           ArrayList<EmployeeSellResult> employeeSellResultList = (ArrayList<EmployeeSellResult>)request.getAttribute("employeeSellResultList");
           if(null != employeeSellResultList) {
        	   	for(int i=0;i<employeeSellResultList.size();i++) {
        	   		EmployeeSellResult employeeSellResult = employeeSellResultList.get(i);
          %>
          <tr>
          <td><%=employeeSellResult.getEmployeeName() %></td>
          <td><%=employeeSellResult.getEmployeeSellMoney() %></td>
          </tr>
          <%
        	   	}
           }
         %>
         </table>
       &nbsp;&nbsp;&nbsp;
    </form>
</body>
</html>

