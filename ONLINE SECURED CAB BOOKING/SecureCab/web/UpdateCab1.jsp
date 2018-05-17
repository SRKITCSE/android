<%@page import="java.sql.ResultSet"%>
<%@page import="osc.DAO"%>
<%@include file="CompanyHeader.jsp" %>
<head>
     <link rel="stylesheet" type="text/css" media="all" href="jq/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="jq/jsDatePick.min.1.3.js"></script>
            <script type="text/javascript">
            window.onload = function(){
		new JsDatePick({
			useMode:2,
			target:"dob",
			dateFormat:"%d-%M-%Y"
		});
	};
</script>
</head>
  <div class="body-wrapper" >
      
      <br/><br/><br/>
<div class="body-middle" style="min-height: 400px;">
<h3 align="center"> Update Cab Details </h3>
<center>
   

<%
                                DAO dao=new DAO();
                                //String driverUserid=(String)session.getAttribute("driveruserid");
                                String cabid=request.getParameter("cabid");
                                ResultSet rs=dao.getCabDetails(cabid);
                                //out.println(rs);
                                if(rs!=null && rs.next())
                                //while(rs.next())
                                {
                                    //out.println("name:\t"+rs.getString("name"));
                                    
 %>                               

<br/>
<form action="UpdateCab2.jsp" method="post">
<table align="center"  style="border: #ff6666  thin;padding: 10px;border: #ff6666 double thin" cellpadding="10px;" >    
    <tr><td>CabId</td><td><input type="text" value="<%=rs.getString("carno")%>" name="cabid" readonly></td></tr>
    <tr><td>Status:</td><td><input type="text" value="<%=rs.getString("status")%>" name="status"</td></tr>
    <tr><td colspan="2"><input type="submit" value="Update"></td></tr>
</table>
</form>


<%
    }
else{
    out.println("Details Not Found");}
%>
</center>    
    
</div>       
  </div>
<%@include file="CommonFooter.jsp" %>