<%@include file="CommonHeader.jsp" %> 
 
<div class="body-wrapper" >      
 <br/>
<div class="body-middle" style="min-height: 400px;">
    <script language="JavaScript">
function validation()
{
            var a = document.form.userid.value;
            var b = document.form.password.value;
            if(a=="")
            {
            alert("Enter your UserId");
            document.form.userid.focus();
            return false;
            }
            if(b=="")
            {
            alert("Enter your Password");
            document.form.password.focus();
            return false;
            }
}
</script>
    <br/><br/>
    <center><h3> Login Here</h3></center>
    <form action="DoLogin.jsp" method="post" name="form" onsubmit="return validation();">
           <table align="center"  style="border: #ff6666 double thin;padding: 30px;" cellpadding="10px;">
	
	<tr>
	    <td>Login-ID</td>
	<td><input type="text" name="userid" id="s" size="15" required /></td>
	</tr>
	<tr>
	    <td>Password</td>
	<td><input type="password" name="password" id="s" size="15" required/></td>
	</tr>
        <tr>
            <td align="right"><input type="submit" name="submit" class="button" value="Login"></td>
            <td><input type="reset" name="reset" class="button" value="Clear"></td>
        </tr>
	</table>  
          </form>
</div>
        
  </div>
  <%@include file="CommonFooter.jsp" %>