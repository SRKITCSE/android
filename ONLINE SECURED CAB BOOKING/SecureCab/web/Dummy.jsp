<form action="CustomerRegister.jsp" name="frm"  method="post">
    <table align="center"  style="border: #ff6666 double thin;padding: 30px;" cellpadding="10px;">
             <tr><td>Customer Name:</td><td><input type="text" name="userid" required></td></tr>                          
             <tr><td>Password:</td><td><input type="password" name="password"  required></td></tr>
             <tr><td>Email:</td><td><input type="text" name="email" required></td></tr>
             <tr><td>Address:</td><td><textarea name="address" required="" ></textarea></td></tr>
             <tr><td>Phone1:</td><td><input type="text" name="phone1" required></td></tr>
             <tr><td>Phone2:</td><td><input type="text" name="phone2" required></td></tr>
             <tr><td>Phone3:</td><td><input type="text" name="phone3" required></td></tr>
             <tr><td>Phone4:</td><td><input type="text" name="phone4" required></td></tr>
            <tr>
                <td><center><input type="submit" value="Register"></center></td>
                <td><center><input type="reset" name="reset"></center></td>
            </tr>
    </table>
</form>