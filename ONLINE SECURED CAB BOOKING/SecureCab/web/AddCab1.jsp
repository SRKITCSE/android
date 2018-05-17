<%@page import="osc.DAO" %>
<%@include file="CompanyHeader.jsp" %>
<div id="content">
<%
  String  carno=request.getParameter("carno");   
  String status=request.getParameter("status"); 
    DAO dao=new DAO();
boolean b=dao.addCab(carno,status);
if(b)
{
    out.println("CAB ADDED SUCCESSFULLY...");
}else{
    out.println("CAB ADDING FAILED...");
       }
%>
</div>
<%@include file="CommonFooter.jsp" %>