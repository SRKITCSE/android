<%@page import="osc.*" %>

<%
    String userid=request.getParameter("userid");
    String password=request.getParameter("password");
    DAO dao=new DAO();
    out.println(dao.customerLoginCheck(userid,password));    
    //out.println(dao.customerLoginCheck("admin","dsafdsaf"));    
%>