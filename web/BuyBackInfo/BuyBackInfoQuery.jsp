<%@page contentType="text/html;charset=GBK" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList" %>
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
	String GoodName = (String)request.getAttribute("GoodName");
	if(null == GoodName) GoodName = "";
	int GoodClassId = (Integer)request.getAttribute("GoodClassId");
	String StartDate = (String)request.getAttribute("StartDate");
	if(null == StartDate) StartDate = "";
	String EndDate = (String)request.getAttribute("EndDate");
	if(null == EndDate) EndDate = "";
	float TotalPrice = (Float)request.getAttribute("TotalPrice");
	
	int recordCount = (Integer)request.getAttribute("recordCount");
	int currentPage = (Integer)request.getAttribute("currentPage");
	int totalPage = (Integer)request.getAttribute("totalPage");
	String pageFootStr = "";  
	int next, prev;  
	prev=currentPage-1;  
	next=currentPage+1; 
	String actionPath = "BuyBackInfoManage?action=query&method=get&GoodNo=" + GoodNo + "&GoodName=" + GoodName + "&GoodClassId=" + GoodClassId + "&StartDate=" + StartDate + "&EndDate=" + EndDate;
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
   <form method="post" name=buyBackInfoQueryForm action="BuyBackInfoManage?action=query">
      <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/list.gif" width=14px height=14px>��Ʒ�����˻�����--&gt;�����˻���Ϣ��ѯ</td>
        </tr>
        <tr>
          <td style="height: 37px">
        ��Ʒ���:<input type=text size=10 name=GoodNo>&nbsp;
       ��Ʒ����:<input type=text size=10 name=GoodName>&nbsp;
        ��Ʒ���:
        <select name=GoodClassId>
          <option value="0">��ѡ��</option>
        <%
           ArrayList goodClassList = (new GoodClassDAO()).QueryAllGoodClassInfo();
           for(int i=0;i<goodClassList.size();i++) {
        	   GoodClass goodClass = (GoodClass)goodClassList.get(i);
        %>
          <option value="<%=goodClass.getGoodClassId() %>"><%=goodClass.getGoodClassName() %></option>
        <%
           }
        %>
        </select>
        <br/>
          ��ʼ����:
          <input type=text name=StartDate size=10/>
          <input class="submit" name="Button" onclick="setDay(document.buyBackInfoQueryForm.StartDate);" style="width: 30px" type="button" value="ѡ��">
          ��������:
          <input type=text name=EndDate size=10/>
          <input class="submit" name="Button" onclick="setDay(document.buyBackInfoQueryForm.EndDate);" style="width: 30px" type="button" value="ѡ��">
       <input type=submit value="��ѯ"/>    
        </td>
      </tr>
    </table>
    </form>
    <table width=700 border=0 cellpadding=2 align="center">
         <tr class="title">
           <td>��Ʒ���</td><td>��Ʒ����</td><td>��Ʒ���</td><td>��Ӧ��</td><td>�˻�����</td><td>�˻�����</td><td>�˻��ܼ�</td><td>�˻�����</td><td>�˻�ԭ��</td>
         </tr>
         <%
            ArrayList<BuyBackInfo> buyBackInfoList = (ArrayList<BuyBackInfo>)request.getAttribute("buyBackInfoList");
            for(int i=0;i<buyBackInfoList.size();i++) {
            	BuyBackInfo buyBackInfo = buyBackInfoList.get(i);
            	Good good = (new GoodDAO()).GetGoodInfoByGoodNo(buyBackInfo.getGoodNo());
         %>
         <tr onmouseover="this.style.backgroundColor='#227711';" onmouseOut="this.style.backgroundColor='';">
           <td><%=buyBackInfo.getGoodNo() %></td>
           <td><%=good.getGoodName() %></td>
           <td><%=(new GoodClassDAO()).GetGoodClassNameById(good.getGoodClassId()) %></td>
           <td><%=buyBackInfo.getSupplierName() %></td>
           <td><%=buyBackInfo.getPrice() %></td>
           <td><%=buyBackInfo.getNumber() %></td>
           <td><%=buyBackInfo.getTotalPrice() %></td>
           <td><%=buyBackInfo.getBuyBackDate() %></td>
           <td><%=buyBackInfo.getBuyBackReason() %></td>
         </tr>
         <%
            }
         %>
         <tr><td colspan=8>
		<%=pageFootStr %>
		&nbsp;
		</td>
		</tr>
    </table>
    <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
    <tr>
    <td align=right style="height: 12px">
        ��ǰ��ѯ�������˻��ܽ��Ϊ��<%=TotalPrice %>Ԫ</td></tr>
    </table>
   
</body>
</html>
