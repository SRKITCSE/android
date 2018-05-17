<%@page import="osc.DAO" %>
<%@include file="CompanyHeader.jsp" %>
<div id="content">
<%  
  String  name=request.getParameter("name"); 
  String  gender=request.getParameter("gender"); 
  String  userid=request.getParameter("userid"); 
    String  password=request.getParameter("password");    
    String  address=request.getParameter("address");  
    String  email=request.getParameter("email");  
    String  phone=request.getParameter("phone");  
    String  cabno=request.getParameter("cabno");  
DAO dao=new DAO();
boolean b=dao.updateDriver(userid,name, address, email, phone,cabno);
if(b)
{
    out.println("<h2 align=center>UPDATED SUCCESSFULLY...</h2>");
}else{
    out.println("<h2 align=center>UPDATION FAILED...</h2>");
       }
%>
</div>
<%@include file="CommonFooter.jsp" %>