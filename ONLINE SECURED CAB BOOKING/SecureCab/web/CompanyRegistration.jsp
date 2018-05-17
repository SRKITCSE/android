<%@include file="CommonHeader.jsp" %>
<div id="content"> 
    <br><br><br>
    <h3  align="center" style="color:#009933 ">Registration Form </h3>
    <form action="CompanyRegistration1.jsp" name="frm" onsubmit="return validate();" method="post">
    <table align="center"  style="border: #ff6666 double thin;padding: 30px;" cellpadding="10px;">
             <tr><td>Company Name:</td><td><input type="text" name="companyName" required></td></tr>                          
             <tr><td>Password:</td><td><input type="password" name="password"  required></td></tr>
             <tr><td>Confirm Password:</td><td><input type="password" name="conpassword"  required></td></tr>
             <tr><td>Address:</td><td><textarea name="address" required="" ></textarea></td></tr>
             <tr><td>Email:</td><td><input type="text" name="email" required></td></tr>
             <tr><td>Land-Line:</td><td><input type="text" name="landline" required></td></tr>
             <tr><td>Phone:</td><td><input type="text" name="phone" required></td></tr>             
            <tr>
                <td><center><input type="submit" value="Register"></center></td>
                <td><center><input type="reset" name="reset"></center></td>
            </tr>
    </table>
</form>
<br><br>
</div>
<%@include file="CommonFooter.jsp" %>