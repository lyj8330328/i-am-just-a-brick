var XMLHttpReq;
 //����XMLHttpRequest����     
 function createXMLHttpRequest() {
      if(window.XMLHttpRequest) { //Mozilla �����
       XMLHttpReq = new XMLHttpRequest();
      }
      else if (window.ActiveXObject) { // IE�����
       try {
        XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
       } catch (e) {
        try {
          XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {}
       }
      }
 }
 //����������
 function sendRequest(goodNo) {
  createXMLHttpRequest();
  var url = "GetGoodInfo?goodNo=" + goodNo;
  XMLHttpReq.open("GET", url, true);
  XMLHttpReq.onreadystatechange = processResponse;//ָ����Ӧ����
  XMLHttpReq.send(null);  // ��������
 }
 // ��������Ϣ����
    function processResponse() {
     if (XMLHttpReq.readyState == 4) { // �ж϶���״̬
         if (XMLHttpReq.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
          DisplayGoodInfo();
          //setTimeout("sendRequest()", 1000);
         } else { //ҳ�治����
            window.alert("���������ҳ�����쳣��");
           }
         }
    }
    function DisplayGoodInfo() {
    
     var GoodName = XMLHttpReq.responseXML.getElementsByTagName("GoodName")[0].firstChild.nodeValue;
     var GoodModel = XMLHttpReq.responseXML.getElementsByTagName("GoodModel")[0].firstChild.nodeValue;
     var GoodSpecs = XMLHttpReq.responseXML.getElementsByTagName("GoodSpecs")[0].firstChild.nodeValue;
     var GoodPlace = XMLHttpReq.responseXML.getElementsByTagName("GoodPlace")[0].firstChild.nodeValue;
     
     document.getElementById("GoodName").innerHTML = GoodName;
     document.getElementById("GoodModel").innerHTML = GoodModel;
     document.getElementById("GoodSpecs").innerHTML = GoodSpecs;
     document.getElementById("GoodPlace").innerHTML = GoodPlace;
     document.getElementById("GoodInfo").style.display = "";
   }
