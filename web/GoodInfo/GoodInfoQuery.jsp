<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shuangyulin.javabean.GoodClass"%>
<%@page import="com.shuangyulin.javabean.Good"%>
<%@page import="com.shuangyulin.dao.GoodClassDAO"%>
<html>
<head>
    <title>�ޱ���ҳ</title>
<LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
		<%
		String GoodNo = (String)request.getAttribute("GoodNo");
		String GoodName = (String)request.getAttribute("GoodName");
		int GoodClassId = (Integer)request.getAttribute("GoodClassId");
		int recordCount = (Integer)request.getAttribute("recordCount");
		//int pageSize = (Integer)request.getAttribute("pageSize");
		int currentPage = (Integer)request.getAttribute("currentPage");
		int totalPage = (Integer)request.getAttribute("totalPage");
		String pageFootStr = "";  
		int next, prev;  
		prev=currentPage-1;  
		next=currentPage+1; 
		String actionPath = "GoodInfoManage?action=query&GoodNo=" + GoodNo + "&GoodName=" + GoodName + "&GoodClassId=" + GoodClassId;
		pageFootStr+="<form aciont='" + actionPath + "' method=post>";
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
		<form method="post" id="frmAnnounce"
			action="GoodInfoManage?action=query">
			<table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
				<tr style="color:blue;font-size:14px;">
					<td style="height: 14px">
						<img src="../images/list.gif" width=14px height=14px />
						��Ʒ��Ϣ����--&gt;��Ʒ��Ϣ��ѯ
					</td>
				</tr>
				<tr>
					<td style="height: 37px">
						��Ʒ���:
						<input type=text name=GoodNo size=12 value='<%=GoodNo %>' />
						&nbsp; ��Ʒ����:
						<input type=text name=GoodName value='<%=GoodName %>' size=12 />
						&nbsp; ��Ʒ���:
						<select name="GoodClassId">
							<option value="0">
								��ѡ����Ʒ���
							</option>
							<%
											              /*����ҵ����ѯ���е���Ʒ�����Ϣ*/
											              ArrayList goodClassList = GoodClassDAO.QueryAllGoodClassInfo();
											              for(int i=0;i<goodClassList.size();i++) {
											   		      	GoodClass goodClass = (GoodClass)goodClassList.get(i);
											   		      	String selected = "";
											   		      	if(goodClass.getGoodClassId() == GoodClassId) selected = "selected";
							%>
							<option value="<%=goodClass.getGoodClassId()%>" <%=selected %>>
								<%=goodClass.getGoodClassName()%>
							</option>
							<%
							}
							%>
						</select>
						&nbsp;
						<input type=submit value="��ѯ" />
					</td>
				</tr>
			</table>
		</form>
		<table width=600 border=0 cellpadding=2 cellspacing=0 align="center">
			<tr class="title">
				<td>
					��Ʒ���
				</td>
				<td>
					��Ʒ����
				</td>
				<td>
					��Ʒ���
				</td>
				<td>
					��Ʒ��λ
				</td>
				<td>
					��Ʒ�ۼ�
				</td>
			</tr>
			<%
					          	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ʒ��Ϣ
					          	ArrayList GoodList = (ArrayList)request.getAttribute("goodList");
					          	/*����ÿ����Ʒ����Ϣ������ʾ*/
					            for(int i=0;i<GoodList.size();i++){
					            	Good good = (Good)GoodList.get(i);
			%>
			<tr onmouseover="this.style.backgroundColor='#227711';" onmouseOut="this.style.backgroundColor='';">
				<td>
					<%=good.getGoodNo()%>
				</td>
				<td>
					<%=good.getGoodName()%>
				</td>
				<td>
					<%=GoodClassDAO.GetGoodClassNameById(good.getGoodClassId())%>
				</td>
				<td>
					<%=good.getGoodUnit()%>
				</td>
				<td>
					<%=good.getGoodPrice()%>
				</td>
			</tr>
			<%
			}
			%>
			<tr><td colspan=6>
		<%=pageFootStr %>
		&nbsp;
		</td>
		</tr>
		</table>
	</body>
</html>
