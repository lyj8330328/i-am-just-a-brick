<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%
if (session.getAttribute("employeeNo")==null || session.getAttribute("employeeNo")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>�ޱ���ҳ</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
    <script src="../script/ajax.js"></script>
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" name=sellInfoForm action="GoodCartManage?action=add">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>��Ʒ���۹���--&gt;��Ʒ����</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          <br />
          ��ã���ǰ����Ʒ������Ϣ���£�<br />
          <br />
            <table width=600 border=0 cellpadding=2 align="center">
             <tr class="title">
               <td>��Ʒ���</td><td>��Ʒ����</td><td>��Ʒ�ۼ�</td><td>��Ʒ����</td><td>����</td>
             </tr>
             <tr>
             <td colspan=5>
              &nbsp; �Բ���,�˹��ܱ�����,����Ҫ�����ѿ�����ϵ����
            <br />
            &nbsp; ����: ������ ����:1985��1��7��(ũ��)
            <br />
            &nbsp; ����: ˫����(����˫����) Ѫ��: 0
            <br />
            &nbsp; ��ҵѧУ: 2007���ڳɶ�����ѧ
            <br />
            &nbsp; �����ַ: �Ĵ�������Ϫ���ɽ��5��
            <br />
            &nbsp; ��ϵQQ: 287307421 ��ϵ�绰: 13558690869
            <br />
            &nbsp; ��ϵEmail: wangjianlin1985@126.com
            <br />
            &nbsp; ˫���ֵ��Թ������Ա���: <a href='http://shop34864101.taobao.com' target='_blank'>http://shop34864101.taobao.com</a>
            <br />
            &nbsp; ˫���ֵ��Թ��������ĵ�: <a href='http://287307421.paipai.com' target='_blank'>http://287307421.paipai.com</a>
            <br />
            &nbsp; ��������Ǿ����ҵ���ƻ�����,������ϵ�����,����ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver�ṩ����ָ��!
             
             </td>
             </tr>
             </table>
    </form>
</body>
</html>

