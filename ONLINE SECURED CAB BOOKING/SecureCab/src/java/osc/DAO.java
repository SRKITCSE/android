package osc;
import com.google.gson.Gson;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.lang.System.*;
import java.sql.SQLException;
import java.sql.Statement;
import javax.smartcardio.Card;
public class DAO {
    public static Connection con;  
    
    public DAO()
    {       
            try
            {
                if(con==null)
                {       Class.forName("com.mysql.jdbc.Driver");	
                        System.out.println("Loaded...");
                        //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/osc","root","");
                        con = DriverManager.getConnection("jdbc:mysql://mysql4461-securecab.ind-cloud.everdata.com/osc","root","MCOyxe61239");
                        System.out.println("Connected...");
                }
            }catch(Exception e)
            {   out.println(e);
            }
    }
    public boolean loginCheck(String userid,String password)
    {   boolean flag=false;
        try{
            PreparedStatement pstmt=con.prepareStatement("select * from companyinfo where companyname=? and password=?");
            pstmt.setString(1,userid);
            pstmt.setString(2,password);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next())
            {
               flag=true;
            }
            }catch(Exception e)
            {out.println(e);
            }
            return flag;
    }   
    public String customerLoginCheck(String userid,String password)
    {   StringBuffer str=null;
        Customer c=new Customer();
        boolean flag=false;
        try{
            PreparedStatement pstmt=con.prepareStatement("select * from customer where userid=? and password=?");
            pstmt.setString(1,userid);
            pstmt.setString(2,password);
            ResultSet rs=pstmt.executeQuery();    
            if(rs.next())
            {   c=new Customer(rs.getString("userid"), rs.getString("password"), rs.getString("address"), rs.getString("email"), rs.getString("phone1"), rs.getString("phone2"), rs.getString("phone3"), rs.getString("phone4"));
                out.println("c==========="+c);
                flag=true;
            }
            }catch(Exception e)
            {out.println(e);
            }
            Gson gson = new Gson();
            str = new StringBuffer(gson.toJson(c));
            if(flag)
            str.insert(1, "\"success\":1,");
            else
            str.insert(1, "\"success\":0,");
            return str.toString();
    }   
    public boolean registerCustomer(Customer customer)
    {    boolean flag=false;
         try{
               PreparedStatement pstmt=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?)");
                pstmt.setString(1,customer.getUserid());
                pstmt.setString(2,customer.getPassword());               
                pstmt.setString(3,customer.getAddress());
                pstmt.setString(4,customer.getEmail());
                pstmt.setString(5,customer.getPhone1());
                pstmt.setString(6,customer.getPhone2());               
                pstmt.setString(7,customer.getPhone3());
                pstmt.setString(8,customer.getPhone4());
                int i=pstmt.executeUpdate();
                if(i>0)
                {    
                     flag=true;
                }
            }catch(Exception e)
            {out.println(e);
            }
            return flag;
    }
    public boolean booking(String source,String dest,String sourceLat1,String sourceLong1,String destLat1,String destLong1,String customerId,String currentTime)throws Exception
    {    
         boolean flag=false;
         try{
               PreparedStatement pstmt=con.prepareStatement("insert into caballotment2 values(?,?,?,?,?,?,?,?,?,?,?)");
               pstmt.setString(1,"");
               pstmt.setString(2,"");               
               pstmt.setString(3,source);
               pstmt.setString(4,dest);
               pstmt.setString(5,sourceLat1);
               pstmt.setString(6,sourceLong1);
               pstmt.setString(7,destLat1);
               pstmt.setString(8,destLong1);
               pstmt.setString(9,customerId);
               pstmt.setString(10,currentTime);
               pstmt.setString(11,"NotAlloted");
               int i=pstmt.executeUpdate();
               if(i>0)
               {    
                     flag=true;
               }
            }catch(Exception e)
            {out.println(e);
            }
            return flag;
    }
    
    public boolean registerCompany(Company company)
    {  
         boolean flag=false;
         try{
                PreparedStatement pstmt=con.prepareStatement("insert into companyinfo values(?,?,?,?,?,?)");
                pstmt.setString(1,company.getCompanyName());
                pstmt.setString(2,company.getPassword());               
                pstmt.setString(3,company.getAddress());
                pstmt.setString(4,company.getEmail());
                pstmt.setString(5,company.getLandline());
                pstmt.setString(6,company.getPhone());
                int i=pstmt.executeUpdate();
                if(i>0)
                {    
                     flag=true;
                }
            }catch(Exception e)
            {out.println(e);
            }
            return flag;
     }
    public boolean registerDriver(String imgPath,String name,String gender,String userid,String password,String address,String email,String phone,String cabno)
    {  
         boolean flag=false;
         try{
                PreparedStatement pstmt=con.prepareStatement("insert into drivers values(?,?,?,?,?,?,?,?,?,?)");
                pstmt.setString(1,imgPath);
                pstmt.setString(2,name);               
                pstmt.setString(3,gender);
                pstmt.setString(4,userid);
                pstmt.setString(5,password);
                pstmt.setString(6,address);
                pstmt.setString(7,email);
                pstmt.setString(8,phone);
                pstmt.setString(9,cabno);
                pstmt.setInt(10,1);
                int i=pstmt.executeUpdate();
                if(i>0)
                {    
                     flag=true;
                }
            }catch(Exception e)
            {out.println(e);
            }
            return flag;
     }
    public String getCustomerEmail(String cd)
    {
         String email=null;
          try{
              PreparedStatement pstmt=con.prepareStatement("select email from customer where userid=?");
              pstmt.setString(1, cd);
              ResultSet rs=pstmt.executeQuery();
              if(rs.next())
              {
                  email=rs.getString(1);
              }
          }catch(Exception e)
          {out.println(e);
          }
          return email;
    }
    public ResultSet getDriverDetails(String driverUserid)
    {
      ResultSet rs=null;
          try{
              PreparedStatement pstmt=con.prepareStatement("select * from drivers where userid=?");
              pstmt.setString(1, driverUserid);
              rs=pstmt.executeQuery();
          }catch(Exception e)
          {out.println(e);
          }
          return rs;
    }
    public ResultSet getCabAllotDetails()
    {     ResultSet rs=null;
          try{
              PreparedStatement pstmt=con.prepareStatement("select * from caballotment2");
              rs=pstmt.executeQuery();
          }catch(Exception e)
          {out.println(e);
          }
          return rs;
    }
    
    public ResultSet getCabDetails(String cabid)
    {
         ResultSet rs=null;
          try{
              PreparedStatement pstmt=con.prepareStatement("select * from cabs");
              rs=pstmt.executeQuery();
          }catch(Exception e)
          {out.println(e);
          }
          return rs;
    }
    public ResultSet getDriverDetails()
    {
         ResultSet rs=null;
          try{
              PreparedStatement pstmt=con.prepareStatement("select * from drivers");
              rs=pstmt.executeQuery();
          }catch(Exception e)
          {out.println(e);
          }
          return rs;
    }
    
    public ResultSet getCabDetails()
    {
         ResultSet rs=null;
          try{
              PreparedStatement pstmt=con.prepareStatement("select * from cabs");
              rs=pstmt.executeQuery();
          }catch(Exception e)
          {out.println(e);
          }
          return rs;
    }
    public boolean updateAllotmentStatus(String dt,String customerid,String driver,String carno)throws Exception
    {
         boolean flag=false;        
            PreparedStatement pstmt=con.prepareStatement("update caballotment2 set carno=?,driveruserid=?,status=? where customerid=? and dateOfAllotment=?");
            pstmt.setString(1,carno);
            pstmt.setString(2,driver);
            pstmt.setString(3,"Alloted");
            pstmt.setString(4,customerid);
            pstmt.setString(5,dt);
              int i=pstmt.executeUpdate();
              if(i>0)
              {
              flag=true;
              }
            return flag;
    }
    public boolean updateCab(String cabid,String status)throws Exception
    {
            boolean flag=false;        
            PreparedStatement pstmt=con.prepareStatement("update cabs set status=? where carno=?");
            pstmt.setString(1,status);
            pstmt.setString(2,cabid);
              int i=pstmt.executeUpdate();
              if(i>0)
              {
              flag=true;
              }
            return flag;
    }
    public boolean updateDriver(String driverUserId,String  name,String  address,String  email,String  phone,String cabno)throws SQLException
    {
    boolean flag=false;        
              PreparedStatement pstmt=con.prepareStatement("update drivers set name=?,address=?,email=?,phone=?,cabno=? where userid=?");
              pstmt.setString(1,name);
              pstmt.setString(2,address);
              pstmt.setString(3,email);
              pstmt.setString(4,phone);              
              pstmt.setString(5,cabno);
              pstmt.setString(6,driverUserId);
              int i=pstmt.executeUpdate();
              if(i>0)
              {
              flag=true;
              }
          
   return flag;
    }
    
    
    public boolean deleteDriverByUserId(String driverUserId) throws SQLException
    {
    boolean flag=false;        
              PreparedStatement pstmt=con.prepareStatement("delete from drivers where userid=?");
              pstmt.setString(1,driverUserId);
              int i=pstmt.executeUpdate();
              if(i>0)
              {
              flag=true;
              }
          
   return flag;
    }
    
    
     public boolean deleteCab(String cabid) throws SQLException
    {
        boolean flag=false;        
              PreparedStatement pstmt=con.prepareStatement("delete from cabs where carno=?");
              pstmt.setString(1,cabid);
              int i=pstmt.executeUpdate();
              if(i>0)
              {
              flag=true;
              }
        return flag; 
    }
    
    
    public boolean addCab(String carno,String status)
    {
        boolean flag=false;
        
        try{
                PreparedStatement pstmt=con.prepareStatement("insert into cabs values(?,?)");
                pstmt.setString(1,carno);
                pstmt.setString(2,status);               
                int i=pstmt.executeUpdate();
                if(i>0)
                {    
                     flag=true;
                }
            }catch(Exception e)
            {out.println(e);
            }
        
        return flag;
    }
    
    
    
    
    
    /*public String getEducationalInstitutionId()throws Exception
    {
        String id="";
        ResultSet rs=null;
        int n=0;
        try{
             PreparedStatement pstmt=con.prepareStatement("select max(institution_id) from educationalinstitutions");
             rs=pstmt.executeQuery();
             while(rs.next())
                {
                    int no=rs.getInt(1);
                    if(no>n)
                        n=no;
                }
            id=(n+1)+"";
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return id;
    }
    
    public boolean addHospitalDetails(String id,String name,String address,String website,String phone,String email,String user)
     {  
         boolean flag=false;
         try{
                PreparedStatement pstmt=con.prepareStatement("insert into hospitals values(?,?,?,?,?,?,?)");
                pstmt.setString(1,id);
                pstmt.setString(2,name);
                pstmt.setString(3,address);
                pstmt.setString(4,website);
                pstmt.setString(5,phone);
                pstmt.setString(6,email);
                pstmt.setString(7,user);
                int i=pstmt.executeUpdate();
                if(i>0)
                {    
                     flag=true;
                }
            }catch(Exception e)
            {out.println(e);
            }
            return flag;
     }*/
}
