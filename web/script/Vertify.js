  function checkEmployeeAddForm(empForm) {
       if(empForm.employeeNo.value == "") {
         alert('Ա��������벻��Ϊ��!');
         empForm.employeeNo.focus();
         return false;
       }
       if(empForm.employeeName.value == "") {
         alert('Ա���������벻��Ϊ��!');
         empForm.employeeName.focus();
         return false;
       }
       if(empForm.employeeBirthday.value == "") {
         alert('��ѡ��Ա��������!');
         empForm.employeeBirthday.focus();
         return false;
       }
       
       /*��֤��ͥ�绰*/
       var employeeHomeTel=empForm.employeeHomeTel.value;
       if(employeeHomeTel != "") {
         var p1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
         var me = false;
         if (p1.test(employeeHomeTel))me=true;
         if (!me){
           empForm.employeeHomeTel.value='';
           alert('�Բ���������ĵ绰�����д������ź͵绰����֮������-�ָ�');
           empForm.employeeHomeTel.focus();
           return false;
         }
       }
       
       /*��֤�ƶ��绰*/
       var employeeMobile = empForm.employeeMobile.value;
       if(employeeMobile != "") {
         var reg0 = /^13\d{5,9}$/;
         var reg1 = /^153\d{4,8}$/;
         var reg2 = /^159\d{4,8}$/;
         var reg3 = /^0\d{10,11}$/;
         var my = false;
         if (reg0.test(employeeMobile))my=true;
         if (reg1.test(employeeMobile))my=true;
         if (reg2.test(employeeMobile))my=true;
         if (reg3.test(employeeMobile))my=true;
         if (!my){
             empForm.employeeMobile.value='';
             alert('�Բ�����������ֻ���С��ͨ�����д���');
             empForm.employeeMobile.focus();
             return false;
         }
       }
       
       /*��֤�����ַ*/
       var employeeEmail = empForm.employeeEmail.value;
       if(employeeEmail != "") {
         if(!(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(employeeEmail))) {
           empForm.employeeEmail.select();
           empForm.employeeEmail.focus();
           alert('�������Email����ȷ!');
           return false;
         }
       }
       
        /*��֤���֤��*/
        var employeeCard = empForm.employeeCard.value; 
   	    var reg = /^([0-9]{15}|[0-9]{18})$/; 
        if(!reg.test(employeeCard)) {
          empForm.employeeCard.select();
          empForm.employeeCard.focus();
          alert('���֤��Ϣ���벻��ȷ!');
          return false;
        }
       
       return true;
 }
 
 
 
 /*��֤������Ϣ��form*/
function checkBuyInfoAddForm(buyInfoAddForm) {
    var Price = buyInfoAddForm.Price.value;
    var Number = buyInfoAddForm.Number.value;
    var BuyDate = buyInfoAddForm.BuyDate.value;
    if(Price == "") {
    	alert("�۸���Ϊ��!");
    	buyInfoAddForm.Price.focus();
    	return false;
    }
    if(Number=="") {
    	alert("������������Ϊ��!");
    	buyInfoAddForm.Number.focus();
    	return false;
    }
    if(BuyDate=="") {
    	alert("��ѡ���������!");
    	return false;
    }

    /*�жϼ۸�(��������)��������ʽ
    //var Price_Reg = /^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	//var Price_Reg = /^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/;
	var Price_Reg = /[^0123456789.]{1,}/;
	//var Number_Reg = /^[0-9]*[1-9][0-9]*$/; //�ж�����(������)��������ʽ
	var Number_Reg = /^[1-9]\d*$/ 
	if(!Price_Reg.test(Price)) {
	   alert("��������ȷ�Ľ����۸�!");
	   buyInfoAddForm.Price.select();
	   buyInfoAddForm.Price.focus();
	   return false;
	}
	if(!Number_Reg.test(Number)) {
	   alert("��������ȷ�Ľ�������!");
	   buyInfoAddForm.Number.select();
	   buyInfoAddForm.Number.focus();
	   return false;
	}
	Price = parseFloat(Price);
	Number = parsetInt(Number);
	var TotalPrice = Price * Number;
	buyInfoAddForm.TotalPrice.value = TotalPrice;*/
	return true;
}

/*������ܵļ۸�*/
function CalculateTotalPrice() {
	var Price = buyInfoAddForm.Price.value;
    var Number = buyInfoAddForm.Number.value;
    /*
    /*�жϼ۸�(��������)��������ʽ
   //var Price_Reg = /^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	//var Price_Reg = /^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/;
	var Price_Reg = /[^0123456789.]{1,}/;
	//var Number_Reg = /^[0-9]*[1-9][0-9]*$/; //�ж�����(������)��������ʽ
	var Number_Reg = /^[1-9]\d*$/ 
	if(!Price_Reg.test(Price)) {
	   alert("��������ȷ�Ľ����۸�!");
	   buyInfoAddForm.Price.select();
	   buyInfoAddForm.Price.focus();
	   return false;
	}
	if(!Number_Reg.test(Number)) {
	   alert("��������ȷ�Ľ�������!");
	   buyInfoAddForm.Number.select();
	   buyInfoAddForm.Number.focus();
	   return false;
	}*/
	//Price = parseFloat(Price);
	//Number = parsetInt(Number);
	var TotalPrice = Price * Number;
	buyInfoAddForm.TotalPrice.value = TotalPrice;
}

 function checkPrice(obj)    //��֤�������Ʒ�۸�
 {   
 	pta=/[^0123456789.]{1,}/;   
 	if(pta.exec(obj))
 		document.buyInfoAddForm.Price.value=obj.substr(0,obj.length-1);   
 }  
  
  