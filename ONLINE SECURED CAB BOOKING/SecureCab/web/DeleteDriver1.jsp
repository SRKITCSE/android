<%@include file="CompanyHeader.jsp" %>
<%@page import="osc.DAO"%>
<div id="content">
    <br/><br/><br/>
<%
String method=request.getMethod();
String driverUserId=request.getParameter("driveruserid");
if(method.equalsIgnoreCase("post"))
{
    DAO dao=new DAO();
    boolean b=dao.deleteDriverByUserId(driverUserId);
    if(b)
     {
        out.println("<h2 align=center>The Driver with the UserId\t\t"+driverUserId+"\t\tis successfully Deleted.</h2>");
    }

else
{
 out.println("<h2 align=center>No Drivers Found with the Driver User ID:"+driverUserId+"</h2>");
}
       }
%>
</div>
<%@include file="CommonFooter.jsp" %>