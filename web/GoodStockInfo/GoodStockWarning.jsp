<%@page contentType="text/html;charset=GBK" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
    <title>�ޱ���ҳ</title>
 <LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
      <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/list.gif" width=14px height=14px>��Ʒ������--&gt;��Ʒ��汨��</td>
        </tr>
        <tr>
          <td style="height: 37px">
              ��Ʒ��汨�����ܣ�����Ʒ������(��ϵͳ����Ϊ200)ʱ��<font color=yellow>��ɫ</font>������ʾ������(����20)ʱ��<font color=red>��ɫ</font>������ʾ��<br />
            <table width=700 border=0 cellpadding=2 align="center">
         <tr class="title">
           <td>��Ʒ���</td><td>��Ʒ����</td><td>��Ʒ���</td><td>��Ʒ�ͺ�</td><td>��Ʒ���<td>��Ʒ���</td><td>��Ʒ��λ</td>
         </tr> 
           <%
           ArrayList<GoodStock> goodStockList = (ArrayList<GoodStock>)request.getAttribute("goodStockList");
           if(null != goodStockList) {
        	   for(int i=0;i<goodStockList.size();i++) {
        		   GoodStock goodStock = goodStockList.get(i);
        		   Good good = (new GoodDAO()).GetGoodInfoByGoodNo(goodStock.getGoodNo());
        		   String goodClassName = GoodClassDAO.GetGoodClassNameById(good.getGoodClassId());
        		   int goodCount = goodStock.getGoodCount();
        		   String color = "yellow";
        		   if(goodCount < 20) color = "red";
        %>
        <tr>
          <td><%=goodStock.getGoodNo() %></td>
          <td><%=good.getGoodName() %></td>
          <td><%=goodClassName %>
          <td><%=good.getGoodModel() %></td>
          <td><%=good.getGoodSpecs() %></td>
          <td style='color:<%=color %>;'><%=goodCount %></td>
          <td><%=good.getGoodUnit() %></td>
        </tr>
        <%
        	   }
           }
        %>
    </table>  
        </td>
      </tr>
    </table>
</body>
</html>
