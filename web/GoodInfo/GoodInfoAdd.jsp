<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.GoodClass" %>
<%@page import="com.shuangyulin.dao.GoodClassDAO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head id="Head1" runat="server">
    <title>�ޱ���ҳ</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" id="frmAnnounce" action="GoodInfoManage?action=add">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px/>��Ʒ��Ϣ����--&gt;��Ʒ��Ϣ���</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          ��Ʒ���:
          <input type="text" name="GoodNo" size=20 /><br/>
          ��Ʒ���:
           <select name="GoodClassId">
           <%
               /*����ҵ����ѯ���е���Ʒ�����Ϣ*/
               ArrayList goodClassList = GoodClassDAO.QueryAllGoodClassInfo();
               for(int i=0;i<goodClassList.size();i++) {
    		   GoodClass goodClass = (GoodClass)goodClassList.get(i);%>
    		   <option value="<%=goodClass.getGoodClassId() %>"><%=goodClass.getGoodClassName() %></option>
 		   <%} %>
 		   </select><br/>
          <!--  
          <sql:setDataSource driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" url="jdbc:sqlserver://127.0.0.1;databaseName=SuperMarketInfo;user=sa;password=123" var="db" scope="request"/>
          <sql:query var="query" dataSource="${db}">select goodClassName,goodClassId from goodClassInfo</sql:query>
          <select name="goodClassId">
            <c:forEach var="goodClass" items="${query.rows}">
              <option value="${goodClass.goodClassId}">${goodClass.goodClassName}</option>
            </c:forEach>
          </select><br/>
          -->
          ��Ʒ����:
          <input type=text name="GoodName" size=20/>
          <br/>
          ��Ʒ��λ:
          <input type=text name="GoodUnit" size=5/>
          <br />
          ��Ʒ�ͺ�:
          <input type=text name="GoodModel" size=10/>
          <br />
          ��Ʒ���:
          <input type="text" name="GoodSpecs" size=10/>
          <br />
          ��Ʒ�ۼ�:
          <input type="text" name="GoodPrice" size=5/>Ԫ&nbsp;
         <br />
          ��Ʒ����:
          <input type="text" name="GoodPlace" size=10/>
          <br />
          ������Ϣ:
          <textarea name="GoodMemo" cols=40 rows=5></textarea>
          <br />
      </td>
	</tr>
          <tr>
              <td style="height: 25px">
                   <input type=submit value='���'/>
                  <input type=button value='ȡ��' onclick="javascript:location.href='GoodInfoAdd.jsp';" />
                  </td>
          </tr>
         
      </table>
    </form>
</body>
</html>

