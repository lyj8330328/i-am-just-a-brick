<%@page contentType="text/html;charset=GBK" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>�����޸�</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script language="javascript">
      function checkForm() {
        var newPassword,newPassAgain;
        newPassword = document.changePasswordForm.NewPassword.value;
        newPassAgain = document.changePasswordForm.NewPassAgain.value;
        if( newPassword== "") {
          alert('�����벻��Ϊ��!');
          document.changePasswordForm.NewPassword.focus();
          return false;
        }
        if(newPassword != newPassAgain) {
          alert('�����������벻һ��!');
          document.changePasswordForm.NewPassword.value = "";
          document.changePasswordForm.NewPassAgain.value = "";
          document.changePasswordForm.NewPassword.focus();
          return false;
        }
        return true;
      }
    </script>
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
<form name="changePasswordForm" action="ChangePasswordServlet?identify=employee" onsubmit="return checkForm();" method="post">
   <table width="80%" border="0" cellpadding="0" cellspacing="2" align="center">
<tr> 
    <td height="21" bgcolor="magenta">&nbsp;<img src="../images/ico29.gif" width="32" height="32" hspace="2" vspace="2" align="absmiddle"><font size="+1"><strong>ϵͳ��ȫ��������</strong></font></td>
  </tr>
</table>
<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
	  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center" class="TableMenu">
	
      <tr>
        <td  class="a3">������������</td>
		    <td>
		      <input type=password name=NewPassword size=20/>
              
				</td>
      </tr>
		  <tr>
		    <td>�ٴ�ȷ��������:</td>
			  <td>
			    <input type=password name=NewPassAgain size=20/>
                </td>
			</tr>
      <tr bgcolor="#ffffff">
        <td height="30" colspan="4" align="center">
            <input type=hidden name=employeeNo value='<%=session.getAttribute("employeeNo").toString() %>'/>
            &nbsp;<input type=submit value="�޸�����"/>
            <br /></td>
      </tr> 
	  </table>
	</td>
  </tr>	  	    
</table>
 </form>
</body>
</html>
