
<%@page import="osc.DAO"%>
<jsp:useBean  id="customer"  scope="request"  class="osc.Customer" >
    <jsp:setProperty name="customer" property="*" />
</jsp:useBean>
<%
    DAO dao=new DAO();
    if(dao.registerCustomer(customer))
    out.println("{\"success\":\"1\"}");
    else
    out.println("{\"success\":\"0\"}");
%>
