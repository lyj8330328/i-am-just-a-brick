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
<head id="Head1" runat="server">
    <title>�ޱ���ҳ</title>
   <LINK href="../css/style.css" type="text/css" rel="stylesheet">
    <script src="../script/calendar.js"></script>
    <script src="../script/ajax.js"></script>
        <script language="javascript">
/*��֤�����˻���Ϣ��form*/
function checkSellBackInfoAddForm(sellBackInfoAddForm) {
    var Price = sellBackInfoAddForm.Price.value;
    var Number = sellBackInfoAddForm.Number.value;
    if(Price == "") {
    	alert("�۸���Ϊ��!");
    	sellBackInfoAddForm.Price.focus();
    	return false;
    }
    if(Number=="") {
    	alert("������������Ϊ��!");
    	sellBackInfoAddForm.Number.focus();
    	return false;
    }
	return true;
}

 function checkPrice(obj)    //��֤�������Ʒ�۸�
 {   
 	pta=/[^0123456789.]{1,}/;   
 	if(pta.exec(obj))
 		document.sellBackInfoAddForm.Price.value=obj.substr(0,obj.length-1);   
 }  
    </script>
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
    <form method="post" name=sellBackInfoAddForm action="SellBackInfoManage?action=add" onsubmit="return checkSellBackInfoAddForm(this);">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>��Ʒ���۹���--&gt;�˿��˻��Ǽ�</td>
        </tr>
            <tr><td>
                <br />
                ���۵��ݣ�
                <input type=text name=SellNo size=20>
                <br />
                <br />
                ��Ʒ��ţ�
                <input type=text name=GoodNo size=10>
                &nbsp;<input type=button onclick="javascript:sendRequest(document.sellBackInfoAddForm.GoodNo.value);" value="��ȡ��Ʒ��Ϣ"/><br />
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
                �˻����ۣ�
                <input type=text name=Price size=5 onpropertyChange="checkPrice(this.value);">
                Ԫ<br />
                <br />
                �˻���Ŀ��
                <input type=text name=Number size=5 onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" >
                <br /><br>
                �˻�ԭ��
                <input type=text name=SellBackReason size=20><br><br>
                ��Ʒ�Ƿ���ã�
                <select name=IsGood>
                  <option value="yes">���</option>
                  <option value="no">�ѻ�</option>
                </select>
                <br />
                <br />
                <input type=submit value="�˻��Ǽ�"/>
                </td></tr>
       &nbsp;&nbsp;&nbsp;
    </form>
</body>
</html>


