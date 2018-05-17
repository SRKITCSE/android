<%@page import="osc.ImageToMail"%>
<%@include file="CompanyHeader.jsp" %>
<%@page import="java.sql.*" %>
<%@page import="osc.DAO"%>
<%
    String customerid=request.getParameter("customerid");
    String date=request.getParameter("date");
    
     //out.println("CustomerId:"+customerid+"<br>");
     //out.println("Date:"+date+"<br>");
     
     DAO dao=new DAO();
     //dao.allotDriver(customerid,date);
     ResultSet rs=dao.getDriverDetails();
     
%>
<br><br><br><center>
<h3>Driver Allotment</h3>
<form method="post" action="#" >
    <table>
        <tr><td>CustomerId:</td><td><input type="text" name="customerid" value="<%=customerid %>" ></td></tr>
        <tr><td>Date:</td><td><input type="text" name="date" value="<%=date %>" ></td></tr>
        <tr><td>Driver</td>
            <td>
                <select  name="driver">
                    <%  while(rs.next())     
                        {out.println("<option>"+rs.getString("userid")+"</option>");
                    }%>
                </select>
            </td>
        </tr>
        <tr><td><input type="submit" value="Allot" ></td></tr>
    </table>
</form>
</center>           
<%
    String method=request.getMethod();
    if(method.equalsIgnoreCase("post"))
    {   
        String cd=request.getParameter("customerid");
        String email=dao.getCustomerEmail(cd);
        if(email!=null){
        String dt=request.getParameter("date");
        String driver=request.getParameter("driver");
        rs=dao.getDriverDetails(driver);
        if(rs.next()){
        String carno=rs.getString("cabno");
        String image=rs.getString("image");
        String content="Customer: "+customerid+"\n";
        content+="Date: "+new java.text.SimpleDateFormat("dd-MMM-yyyy").format(new java.util.Date(Long.parseLong(dt)))+"\n";
        content+="Driver: "+driver+"\n";
        content+="Cab/Car No: "+carno+"\n";
        ImageToMail mail=new ImageToMail();
        String imagePath=request.getRealPath("/")+"databaseimages\\"+image;
        //out.println(request.getRealPath("/")+"databaseimages\\"+image);
        
        dao.updateAllotmentStatus(dt,customerid,driver,carno);
        out.println("Allotment Table is Updated<br>");
        mail.sendImage(email, content, imagePath);
        out.println("Email Sent Success fully....<br>");
        out.println("Cab is alloted to the customer<br>");
         }
        }else
        out.println("Invalid Email Id:");
    }
%>