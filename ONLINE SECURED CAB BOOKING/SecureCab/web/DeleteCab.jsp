<%@page import="osc.DAO"%>
<%@include file="CompanyHeader.jsp" %>
<div id="content">
    <br/><br/><br/>
<h3 align="center">Delete Cab Details</h3>
<br/>
<form action="#" method="post">
    <table align="center"  style="border: #ff6666 double thin;padding: 30px;" cellpadding="10px;">
            <tr>
                <td>Enter Cab_id:</td><td><input type="text" name="cabid" required></td>
            </tr>
            <tr>
                <td><center><input type="submit" value="Delete"></center></td>
                <td><center><input type="reset" name="Clear"></center></td>
            </tr>
    </table>
</form>
</div>

<%
String method=request.getMethod();
String cabid=request.getParameter("cabid");
if(method.equalsIgnoreCase("post"))
{   
    DAO dao=new DAO();
    boolean b=dao.deleteCab(cabid);
    if(b)
    {
        out.println("<h2 align=center>The Cab with the Id&nbsp;&nbsp;"+cabid+"&nbsp;&nbsp;is successfully Deleted.</h2>");
    }
    else
    {   out.println("<h2 align=center>No Cab is Found with the Given ID:"+cabid+"</h2>");
    }
}
%>

<%@include file="CommonFooter.jsp" %>