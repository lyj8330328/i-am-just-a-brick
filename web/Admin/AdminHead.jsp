<%@page contentType="text/html;charset=GBK"%>
<%
  //String baseUrl = "http://" + request.getRemoteHost() + ":" + request.getRemotePort() + "/SuperMarket";
%>
<!--BEGIN HEADER MODULE-->
<script>
	var linksrcTop;
	var linksrcLeft;
	var linksrcH;
	var linksrcW;
	var isShow = false;
	var mArray;
	var mArrayURL;

	function showMenu(menu,tableW)
	{
		switch (menu)
		{
			case '1' :mArray = new Array('');mArrayURL = new Array('');break;
			case '2' :mArray = new Array('��Ʒ������','�����Ʒ��Ϣ','��Ʒ��Ϣά��');mArrayURL = new Array('../GoodInfo/GoodClassManage.jsp','../GoodInfo/GoodInfoAdd.jsp','../GoodInfo/GoodInfoManage?action=query');break;
			case '3' :mArray = new Array('���Ա����Ϣ','Ա����Ϣά��');mArrayURL = new Array('EmployeeInfoAdd.jsp','EmployeeInfoManage?action=query');break;
			case '4' :mArray = new Array('��Ʒ�����Ǽ�','��Ʒ������ѯ','�����˻��Ǽ�','�����˻���ѯ');mArrayURL = new Array('../BuyInfo/BuyInfoAdd.jsp','../BuyInfo/BuyInfoManage?action=query','../BuyBackInfo/BuyBackInfoAdd.jsp','../BuyBackInfo/BuyBackInfoManage?action=query');break;
			case '5' :mArray = new Array('��Ʒ���۲�ѯ','Ա��ҵ����ѯ','�˿��˻�����','��Ʒ�˻���ѯ');mArrayURL = new Array('../SellInfo/SellInfoQuery.jsp','../SellInfo/EmployeeSellResult.jsp','../SellBackInfo/SellBackInfoAdd.jsp','../SellBackInfo/SellBackInfoQuery.jsp');break;
			case '6' :mArray = new Array('��Ʒ����ѯ','��Ʒ��汨��');mArrayURL = new Array('../GoodStockInfo/GoodStockQuery.jsp','../GoodStockInfo/GoodStockManage?action=warning');break;
			case '7' :mArray = new Array('��Ӧ�̹���','�޸�����','����ϵͳ');mArrayURL = new Array('SupplierManage.jsp','changePassword.jsp','../about.html');break;
		}
		
		if( mArray !=null )
		{
			var linksrc = window.event.srcElement;
			linksrcTop = linksrc.offsetTop;
			linksrcLeft = linksrc.offsetLeft;
			linksrcH = linksrc.offsetHeight;
			linksrcW = linksrc.offsetWidth;
			linksrcParent = linksrc.offsetParent;
			while(linksrcParent.tagName.toUpperCase()!="BODY")
			{
				linksrcTop += parseInt(linksrcParent.offsetTop);
				linksrcLeft += parseInt(linksrcParent.offsetLeft);
				linksrcParent = linksrcParent.offsetParent;
			}
			mdiv = document.all.menuDiv;
			mdiv.style.left = linksrcLeft + 8;
			mdiv.style.top = linksrcTop + linksrcH; 

			text ="<table width='96' border='0' cellpadding='3' cellspacing='0' bgcolor='e8e8e8' class='tbl'>";
			for(var i=0;i<mArray.length;i++)
			{
				text += "<tr>";
				if(i==mArray.length-1)
				{
					text += "<td align='center'>";
				}
				else
				{
					text += "<td align='center' class='bottom'>";
				}
				if (menu == "7" && i == 1)
				{
					text+="<a href='#' onclick=MM_CenterWindow('" +  mArrayURL[i] + "',400,250)>" + mArray[i];
				}
				else
				{
					text+="<a href='" + mArrayURL[i] + "' target='ContentFrame'>" + mArray[i];
				}
				text+="</a></td></tr>";
			}
			text +="</table>"; 
			mdiv.innerHTML = text;
			mdiv.style.visibility="visible";
			isShow = true;
		}
	}

	function hiddenMenu()
	{
		if (isShow == true)
		{
			var mx = document.body.scrollLeft + window.event.clientX;
			var my = document.body.scrollTop + window.event.clientY;
			mdiv=document.all.menuDiv;
			mdivT = parseInt(mdiv.style.top);
			mdivL = parseInt(mdiv.style.left);
			mdivH = parseInt(mdiv.offsetHeight);
			mdivW = parseInt(mdiv.offsetWidth);
			
			if(mx < mdivL || mx > mdivL + mdivW || my < linksrcTop || my > linksrcTop + linksrcH + mdivH)
			{
				mdiv.style.visibility = "hidden";isShow = false;
			}							
		}
	}	    
</script>
<!--   �˵���   -->
<div id="menuDiv" style="VISIBILITY: hidden; POSITION: absolute">
</div>
<!--   �˵���   -->
<table class="tbl" id="Table2" cellSpacing="0" cellPadding="0" width="760" border="0">
	<tr>
		<td background="../images/titl_bg.jpg" height="26" align="right">
			<!--   ����������    --> &nbsp;&nbsp; 
			<A href="index.jsp"%><font color="#ffffff">��ҳ</font></A>&nbsp;<font color="#ffffff">��</font>
			<a onclick="showMenu('2',100)" style="COLOR: black" href="#"><font color="#ffffff">��Ʒ��Ϣ����</font></a>&nbsp;<font color="#ffffff">��</font>&nbsp; 
			<A onclick="showMenu('3',100)" style="COLOR: black" href="#"><font color="#ffffff">Ա����Ϣ����</font></A>&nbsp;<font color="#ffffff">��</font>&nbsp;
			<A onclick="showMenu('4',100)" style="COLOR: black" href="#"><font color="#ffffff">��Ʒ��������</font></A>&nbsp;<font color="#ffffff">��</font>&nbsp;
			<A onclick="showMenu('5',100)" style="COLOR: black" href="#"><font color="#ffffff">��Ʒ���۹���</font></A>&nbsp;<font color="#ffffff">��</font>&nbsp;
			<A onclick="showMenu('6',100)" style="COLOR: black" href="#"><font color="#ffffff">��Ʒ������</font></A>&nbsp;<font color="#ffffff">��</font>&nbsp;
			<A onclick="showMenu('7',100)" style="COLOR: black" href="#"><font color="#ffffff">ϵͳ����</font></A>&nbsp;<font color="#ffffff">��</font>&nbsp; 
			<A style="COLOR: black" href='../logout.jsp' onclick="javascript:return confirm('ȷ���˳���?');"><font color="#ffffff">�� ��</font></A> &nbsp;&nbsp; 
			<!--   //�������˵�   --></td>
	</tr>
	<tr>
		<td align="center"><IMG src='../images/title.jpg'></td>
	</tr>
</table>

