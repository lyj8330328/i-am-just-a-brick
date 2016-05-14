<%@page contentType="text/html;charset=GBK" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList;" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<%
  /*ȡ����Ʒ��ţ����ݸ���Ʒ��ŵ���ҵ���ȡ�ø���Ʒ����ϸ��Ϣ*/
  String goodNo = request.getParameter("goodNo");
  Good good = GoodDAO.GetGoodInfoByGoodNo(goodNo);
  /*����ҵ����ѯ���е���Ʒ�����Ϣ*/
  ArrayList goodClassList = GoodClassDAO.QueryAllGoodClassInfo();
%>
<html>
<head>
    <title>�ޱ���ҳ</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
 <c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" id="frmAnnounce" action="GoodInfoManage?action=update&goodNo=<%=goodNo %>">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px/>��Ʒ��Ϣ����--&gt;��Ʒ��Ϣ����</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          ��Ʒ���:
          <%=goodNo %>
          <br />
          <br />
          ��Ʒ���:
          <select name=goodClassId>
            <%
               for(int i=0;i<goodClassList.size();i++) {
            	   GoodClass goodClass = (GoodClass)goodClassList.get(i);
            	   if(goodClass.getGoodClassId() == good.getGoodClassId()) {
            %>
            		 <option selected value=<%=goodClass.getGoodClassId()%>><%=goodClass.getGoodClassName() %></option>
           <%
            	   } else {
           %>
                     <option value=<%=goodClass.getGoodClassId() %>><%=goodClass.getGoodClassName() %></option>
           <%
            	   }
           %>
            <%
               }
            %>
          </select>
          <br />
          <br />
          ��Ʒ����:
          <input type=text size=20 name=goodName value=<%=good.getGoodName() %>>
          <br />
          <br />
          ��Ʒ��λ:
          <input type=text size=4 name=goodUnit value=<%=good.getGoodUnit() %>>
          <br />
          <br />
          ��Ʒ�ͺ�:
          <input type=text size=20 name=goodModel value=<%=good.getGoodModel() %>>
          <br />
          <br />
          ��Ʒ���:
          <input type=text size=20 name=goodSpecs value=<%=good.getGoodSpecs() %>>
          <br />
          <br />
          ��Ʒ�ۼ�:
          <input type=text size=5 name=goodPrice value=<%=good.getGoodPrice() %>>
          Ԫ&nbsp;
          <br />
          <br />
          ��Ʒ����:
          <input type=text size=30 name=goodPlace value=<%=good.getGoodPlace() %>>
          <br />
          <br />
          ������Ϣ:
 		  <textarea name="goodMemo" cols=40 rows=5><%=good.getGoodMemo() %></textarea><br />
      </td>
	</tr>
          <tr>
              <td style="height: 25px">
                  <input type=submit value="����" />&nbsp;
                  <input type=button value='����' onclick="javascript:location.href='GoodInfoManage?action=query';" />
                  </td>
          </tr>
         
      </table>
       &nbsp;&nbsp;&nbsp;&nbsp;
    </form>
</body>
</html>
