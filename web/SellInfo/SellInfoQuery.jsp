<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<html>
<head>
    <title>�ޱ���ҳ</title>
<LINK href="../css/style.css" type="text/css" rel="stylesheet">
<script src="../script/calendar.js"></script>
</head>
<body>
<%
   String EmployeeNo = (String)request.getAttribute("EmployeeNo");
   if(null == EmployeeNo) EmployeeNo = "";
   String SellNo = (String)request.getAttribute("SellNo");
   if(null == SellNo) SellNo = "";
   String StartDate = (String)request.getAttribute("StartDate");
   if(null == StartDate) StartDate = "";
   String EndDate = (String)request.getAttribute("EndDate");
   if(null == EndDate) EndDate = "";
   
%>
     <form method="post" name=sellInfoQueryForm action="SellInfoManage?action=sellInfoQuery">
      <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px; width: 605px;">
          <img src="../images/list.gif" width=14px height=14px>��Ʒ���۹���--&gt;������Ϣ��ѯ</td>
        </tr>
        <tr>
          <td style="height: 37px; width: 605px;">
              Ա�����:<input type=text name=EmployeeNo value='<%=EmployeeNo %>' size=10/>
              ���ݺ�:<input type=text name=SellNo value='<%=SellNo %>' size=12/>
             <br />
              ��ʼ����: <input type=text name=StartDate value='<%=StartDate %>' size=10/>
          <input class="submit" name="Button" onclick="setDay(document.sellInfoQueryForm.StartDate);" style="width: 30px" type="button" value="ѡ��">
              ��������<input type=text name=EndDate value='<%=EndDate %>' size=10/>
          <input class="submit" name="Button" onclick="setDay(document.sellInfoQueryForm.EndDate);" style="width: 30px" type="button" value="ѡ��">
      <input type=submit value="��ѯ"/><br />
       <table width=700 border=0 cellpadding=2 align="center">
         <tr class="title">
           <td>���ݺ�</td><td>��Ʒ���</td><td>��Ʒ����</td><td>���ۼ۸�</td><td>��������</td><td>����ʱ��</td>
         </tr>
          <%
             float sellTotalPrice = 0.0f;
             ArrayList<SellInfo> sellInfoList = (ArrayList<SellInfo>)request.getAttribute("sellInfoList");
             if(null != sellInfoList) {
            	 for(int i=0;i<sellInfoList.size();i++) {
            		 SellInfo sellInfo = sellInfoList.get(i);
            		 sellTotalPrice += sellInfo.getTotalPrice();
            		 Good good = (new GoodDAO()).GetGoodInfoByGoodNo(sellInfo.getGoodNo());
          %>
          <tr>
          <td><%=sellInfo.getSellNo() %></td>
          <td><%=sellInfo.getGoodNo() %></td>
          <td><%=good.getGoodName() %></td>
          <td><%=sellInfo.getPrice() %></td>
          <td><%=sellInfo.getNumber() %></td>
          <td><%=sellInfo.getSellTime() %></td>
          </tr>
          <%
            	 }
             }
          %>    
        </table>
    <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
    <tr>
    <td align=right style="height: 12px">
        ��ǰ��ѯ�����������ܽ��Ϊ��<%=sellTotalPrice %>Ԫ</td></tr>
    </table>
  </form>
</body>
</html>
