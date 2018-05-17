<%@page import="java.sql.ResultSet"%>
<%@page import="osc.*"%>
<%@include file="CommonHeader.jsp" %>
<div class="body-wrapper" >
<br/>
<div class="body-middle" style="min-height: 400px;">    
<center>
<br/>
<h3> Login Check </h3><hr/>
<br/>
<%
        String userid=request.getParameter("userid");
        String password=request.getParameter("password");
        DAO d=new DAO();
        if(d.loginCheck(userid,password))
        {  session.setAttribute("userid",userid);
            response.sendRedirect("CompanyHome.jsp");
        }
        else
        {   out.println("Login Failed... Invalid userid/password");
        }
%>
</center>
</div>     
</div>
  <%@include file="CommonFooter.jsp" %>