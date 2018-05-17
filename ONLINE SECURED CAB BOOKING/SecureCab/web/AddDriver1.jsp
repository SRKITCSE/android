<%@page import="osc.DAO" %>
<%@include file="CompanyHeader.jsp" %>
<div id="content">
<%
  String  uploadimage=request.getParameter("uploadimage");  
  String  name=request.getParameter("name"); 
  String  gender=request.getParameter("gender"); 
  String  userid=request.getParameter("userid"); 
String  password=request.getParameter("password");    
String  address=request.getParameter("address");  
String  email=request.getParameter("email");  
String  phone=request.getParameter("phone");  
String  cabno=request.getParameter("cabno");  
DAO dao=new DAO();
boolean b=dao.registerDriver(uploadimage, name, gender, userid, password, address, email, phone,cabno);
if(b)
{
    out.println("REGISTERED SUCCESSFULLY...");
}else{
    out.println("REGISTRATION FAILED...");
       }
%>
</div>
<%@include file="CommonFooter.jsp" %>