<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@ page session="true" %>
<%
if (session.getAttribute("employeeNo")==null || session.getAttribute("employeeNo")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<html>
<head>
    <title>�ޱ���ҳ</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
    <script language=javascript>
function printSellInfo() { 
bdhtml=window.document.body.innerHTML; 
sprnstr="<!--startprint-->"; 
eprnstr="<!--endprint-->"; 
prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); 
prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
window.document.body.innerHTML=prnhtml; 
window.print(); 
window.document.body.innerHTML=bdhtml; 
return true;
         }
 function Calculate() {
    if(document.sellFinishForm.RealGiveMoney.value == "") {
       alert('������ʵ�����');
       document.sellFinishForm.RealGiveMoney.focus();
       return ;
    }
    document.sellFinishForm.GiveBackMoney.value = document.sellFinishForm.RealGiveMoney.value - document.sellFinishForm.ShouldGiveMoney.value
 }
 
 function checkPrice(obj)    //��֤�������Ʒ�۸�
 {   
 	pta=/[^0123456789.]{1,}/;   
 	if(pta.exec(obj))
 		document.sellFinishForm.RealGiveMoney.value=obj.substr(0,obj.length-1);   
 }  
</script>
</head>
<body>

<%
   String employeeNo = session.getAttribute("employeeNo").toString();
   java.text.SimpleDateFormat bartDateFormat = new java.text.SimpleDateFormat("yyMMddHHmmSS");
   java.util.Date date = new java.util.Date();
   String nowTimeString = bartDateFormat.format(date);
   /*���ɵ��ݺ�: Ա�����+��ǰʱ��*/
   String SellNo = employeeNo + nowTimeString; 
   
%>
   <form method="post" name="sellFinishForm" action="GoodCartManage?action=finishSell">
   <!--startprint-->
   ����<table width=550 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>��Ʒ���۹���--&gt;��Ʒ���۽���</td>
        </tr>
        </table><br />
      <table width=550 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px;"><center><font size=4 color=red>��ӭ����˫���ֳ���</font></center><br />
          ���СƱ����<font color=red><%=SellNo %></font>,�����Ʊ��ܣ�7����ƾ�˰����˻�.</td>
        </tr><tr>
	  <td style="height: 26px; width: 426px;">
          <br />
         <table width=600 border=0 cellpadding=2 align="center">
           <tr>
           <td>
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
       </td>
       </tr>
       </table>
    </form>
</body>
</html>


