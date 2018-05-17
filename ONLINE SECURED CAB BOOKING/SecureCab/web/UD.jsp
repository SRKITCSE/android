<%@page import="osc.DAO"%>
<%@include file="CompanyHeader.jsp" %>
<div id="content">
    <br/><br/><br/>
<h3 align="center"> Update Driver Details </h3>
<br/>
<form action="UpdateDriver.jsp" method="post">
    <table align="center"  style="border: #ff6666 double thin;padding: 30px;" cellpadding="10px;">
            <tr>
                <td>Enter User-id of Driver:</td><td><input type="text" name="driveruserid" required></td>
            </tr>
            <tr>
                <td><center><input type="submit" value="View"></center></td>
            </tr>
    </table>
</form>

</div>
<%@include file="CommonFooter.jsp" %>