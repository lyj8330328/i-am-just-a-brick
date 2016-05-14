<%@page contentType="text/html;charset=GBK" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList" %>
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
    <script src="../script/calendar.js"></script>
</head>
<body>
<%
   String GoodNo = (String)request.getAttribute("GoodNo");
   if(null == GoodNo) GoodNo = "";
   String SellNo = (String)request.getAttribute("SellNo");
   if(null == SellNo) SellNo = "";
   String StartDate = (String)request.getAttribute("StartDate");
   if(null == StartDate) StartDate = "";
   String EndDate = (String)request.getAttribute("EndDate");
   if(null == EndDate) EndDate = "";
   float TotalPrice = 0.0f;
   if(null != request.getAttribute("TotalPrice"))
	   TotalPrice = (Float)request.getAttribute("TotalPrice");
   
%>
   <form method="post" name=sellBackInfoQueryForm action="SellBackInfoManage?action=query">
      <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px; width: 605px;">
          <img src="../images/list.gif" width=14px height=14px>��Ʒ���۹���--&gt;�˻���Ϣ��ѯ</td>
        </tr>
        <tr>
          <td style="height: 37px; width: 605px;">
              ��Ʒ���:<input type=text name=GoodNo value='<%=GoodNo %>' size=10/>
              ���۵��ݺ�:<input type=text name=SellNo value='<%=SellNo %>' size=10/><br/>
              ��ʼʱ��:
              <input type=text name=StartDate value='<%=StartDate %>' size=10/>
          <input class="submit" name="Button" onclick="setDay(document.sellBackInfoQueryForm.StartDate);" style="width: 30px" type="button" value="ѡ��">
              ����ʱ��:<input type=text name=EndDate value='<%=EndDate %>' size=10/>
          <input class="submit" name="Button" onclick="setDay(document.sellBackInfoQueryForm.EndDate);" style="width: 30px" type="button" value="ѡ��">
       <input type=submit value='��ѯ'/><br />
        <table width=700 border=0 cellpadding=2 align="center">
         <tr class="title">
           <td>���ݺ�</td><td>��Ʒ���</td><td>��Ʒ����</td><td>�˻��۸�</td><td>�˻�����</td><td>�˻�ԭ��<td>�˻�ʱ��</td>
         </tr>
        <%
           ArrayList<SellBackInfo> sellBackInfoList = (ArrayList<SellBackInfo>)request.getAttribute("sellBackInfoList");
           if(null != sellBackInfoList) {
        	   for(int i= 0; i<sellBackInfoList.size();i++) {
        		   SellBackInfo sellBackInfo = sellBackInfoList.get(i);
        		   Good good = (new GoodDAO()).GetGoodInfoByGoodNo(sellBackInfo.getGoodNo());
        %>
        <tr>
          <td><%=sellBackInfo.getSellNo() %></td>
          <td><%=sellBackInfo.getGoodNo() %></td>
          <td><%=good.getGoodName() %></td>
          <td><%=sellBackInfo.getPrice() %></td>
          <td><%=sellBackInfo.getNumber() %></td>
          <td><%=sellBackInfo.getSellBackReason() %></td>
          <td><%=sellBackInfo.getSellBackTime() %></td>
          <td></td>
        </tr>
        <%
        	   }
           }
        %>
    </table>
    <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
    <tr>
    <td align=right style="height: 12px">
        ��ǰ��ѯ�������˻��ܽ��Ϊ��<%=TotalPrice %>Ԫ</td></tr>
    </table>
  </form>
</body>
</html>

