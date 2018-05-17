<%@page import="osc.*"%>
<jsp:useBean  id="company"  scope="request" class="osc.Company">
    <jsp:setProperty name="company" property="*" />
</jsp:useBean>
<%@include file="CommonHeader.jsp" %>
  <div class="body-wrapper" >
      <br/>
<div class="body-middle" style="min-height: 400px;">
<center>
<br/>
<h3> Registration Form </h3><hr/>
<br/>
<%
        DAO d=new DAO();
        if(d.registerCompany(company))
        out.println("Registered Is successfully.. ");
        else
        out.println("Registration Is Failed");
%>
</center>    
    
</div>       
  </div>
  <%@include file="CommonFooter.jsp" %>