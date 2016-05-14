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
    <script src="../script/ajax.js"></script>
    <script language="javascript">
/*��֤������Ϣ��form*/
function checkBuyBackInfoAddForm(buyBackInfoAddForm) {
    var Price = buyBackInfoAddForm.Price.value;
    var Number = buyBackInfoAddForm.Number.value;
    var BuyBackDate = buyBackInfoAddForm.BuyBackDate.value;
    if(Price == "") {
    	alert("�۸���Ϊ��!");
    	buyBackInfoAddForm.Price.focus();
    	return false;
    }
    if(Number=="") {
    	alert("������������Ϊ��!");
    	buyBackInfoAddForm.Number.focus();
    	return false;
    }
    if(BuyBackDate=="") {
    	alert("��ѡ���������!");
    	return false;
    }
	return true;
}

/*������ܵļ۸�*/
function CalculateTotalPrice() {
	var Price = buyBackInfoAddForm.Price.value;
    var Number = buyBackInfoAddForm.Number.value;
	var TotalPrice = Price * Number;
	buyBackInfoAddForm.TotalPrice.value = TotalPrice;
}

 function checkPrice(obj)    //��֤�������Ʒ�۸�
 {   
 	pta=/[^0123456789.]{1,}/;   
 	if(pta.exec(obj))
 		document.buyBackInfoAddForm.Price.value=obj.substr(0,obj.length-1);   
 }  
    </script>
</head>
<body onload="javascript:sendRequest(document.buyBackInfoAddForm.GoodNo.value);">
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" name=buyBackInfoAddForm action="BuyBackInfoManage?action=add" onsubmit="return checkBuyBackInfoAddForm(this);">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>��Ʒ��������--&gt;��Ʒ�˻��Ǽ�</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          ѡ��Ӧ��:
          <select name=SupplierName>
            <%
				ArrayList<Supplier> supplierList = SupplierDAO.QueryAllSupplier();
	  			for(int i=0;i<supplierList.size();i++) {
	  				Supplier supplier = supplierList.get(i);
	  	    %>
	  	         <option value=<%=supplier.getSupplierName() %>><%=supplier.getSupplierName() %></option>
	  	    <%
	  			}
            %>
          </select>
          <br />
          <br />
          ѡ����Ʒ:
          <select name=GoodNo onchange="javascript:sendRequest(this.value);">
          <%
            ArrayList<Good> goodList = GoodDAO.QueryAllGoodInfo();
            for(int i=0;i<goodList.size();i++) {
            	Good good = goodList.get(i);
          %>
              <option value=<%=good.getGoodNo() %>><%=good.getGoodNo() %>----<%=good.getGoodName() %></option>
          <%
            }
          %>
          </select>
         <br /><br/>
          <div id="GoodInfo" style="display:none;color:red">
              ��Ʒ����:
              <span id=GoodName></span> <br>
              ��Ʒ�ͺ�:
              <span id=GoodModel></span> <br>
              ��Ʒ���:
              <span id=GoodSpecs></span> <br>
              ��Ʒ����:
              <span id=GoodPlace></span> <br>
          </div>
          <br />
          �˻��۸�:
         <input type=text name=Price size=5 onpropertyChange="checkPrice(this.value);" onBlur="CalculateTotalPrice();">Ԫ
           &nbsp; &nbsp;&nbsp;�˻�����:
          <input type=text name=Number size=5  onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" onBlur="CalculateTotalPrice();"><br />
          <br />
          �˻��ܼ�:
         <input type=text name=TotalPrice readonly='true'  size=8>Ԫ
          <br />
          <br />
          �˻�����:
         <input type=text name="BuyBackDate" size=12 readOnly/>
          <input class="submit" name="Button" onclick="setDay(document.buyBackInfoAddForm.BuyBackDate);" style="width: 30px" type="button" value="ѡ��">
           <br />
          <br />
          �˻�ԭ��:
          <input type=text name="BuyBackReason" size=20>
          <br />
      </td>
	</tr>
          <tr>
              <td style="height: 24px">
                <br>
                <input type=submit value="�˻��Ǽ�"/>&nbsp;
                <input type=button value="ȡ��" onclick="javascript:location.href='BuyBackInfoAdd.jsp';"/>
              </td>
          </tr>
         
      </table>
    </form>
</body>
</html>

