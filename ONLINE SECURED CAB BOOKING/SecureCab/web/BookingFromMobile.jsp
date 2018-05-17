<%@page import="osc.DAO" %>
<%  
    String method=request.getMethod();
    if(method.equalsIgnoreCase("post")){
    String sourceLat=request.getParameter("sourceLat");
    String sourceLong=request.getParameter("sourceLong");
    String destLat=request.getParameter("destLat");
    String destLong=request.getParameter("destLong");
    String currentTime=request.getParameter("currentTime");
    String customerId=request.getParameter("customerId");
    String sourceAddress=request.getParameter("sourceAddress");
    String destAddress=request.getParameter("destAddress");
    
    DAO dao=new DAO();
    if(dao.booking(sourceAddress,destAddress,sourceLat,sourceLong,destLat,destLong,customerId,currentTime))
    out.println("{\"success\":1}");
    else
    out.println("{\"success\":0}");
    }else
    out.println("{\"success\":0}");
    
%>