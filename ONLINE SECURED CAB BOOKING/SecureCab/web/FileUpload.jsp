<%@page  import="java.util.*,java.io.*,org.apache.tomcat.util.http.fileupload.disk.*,org.apache.tomcat.util.http.fileupload.servlet.*,org.apache.tomcat.util.http.fileupload.*,org.apache.tomcat.util.http.fileupload.util.Streams" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>        
<%@ page import="java.sql.*,java.io.*,java.util.*,com.oreilly.servlet.MultipartRequest"%>
<%
	String filename="";
	try
        {
                File ff=new File(request.getRealPath("/")+"databaseimages/");
                ff.mkdir();
                MultipartRequest multi=new MultipartRequest(request, request.getRealPath("/")+"databaseimages/",5*1024*1024*1024);
                Enumeration files=multi.getFileNames();
		File f=null;
		while(files.hasMoreElements())
		{       
                        String name=(String)files.nextElement();
                        f=multi.getFile(name);
			/*filename=multi.getFilesystemName(name);
			String type=multi.getContentType(name);*/
			System.out.println("The File is "+f);
		}
                
		InputStream is = new FileInputStream(f);
		byte b[]=new byte[is.available()];
                is.read(b);
		//out.println(f);

		//out.println(request.getRealPath("/")+"databaseimages/"+f.getName());
                out.println("databaseimages/"+f.getName());
		/*out.println("Hello:&nbsp;&nbsp;"+request.getRealPath("/")+""+filepath+"\\"+""+f.getName());
		FileOutputStream fos=new FileOutputStream(request.getRealPath("/")+""+filepath+"\\"+""+f.getName());

		fos.write(b);

		session.setAttribute("pname", f.getName());
		fos.close();
		response.sendRedirect(redirectfile);*/
		}catch(Exception e)
		{out.println("Tokkale:&nbsp;"+e);
		}

		//out.println("The Image is Added into Database"); 

%>
