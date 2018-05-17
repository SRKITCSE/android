<%@page import="osc.DAO" %>
<%@include file="CompanyHeader.jsp" %>
<div id="content">
<%  
  String  cabid=request.getParameter("cabid"); 
  String  status=request.getParameter("status"); 
    DAO dao=new DAO();
    boolean b=dao.updateCab(cabid,status);
    if(b)
    {
        out.println("<h2 align=center>UPDATED SUCCESSFULLY...</h2>");
    }else{
        out.println("<h2 align=center>UPDATION FAILED...</h2>");
    }
%>
</div>
<%@include file="CommonFooter.jsp" %>