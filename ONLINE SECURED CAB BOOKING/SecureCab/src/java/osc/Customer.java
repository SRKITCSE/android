package osc;

import com.google.gson.Gson;

public class Customer 
{ 
    String userid;
    String password;
    String address;
    String email;
    String phone1;
    String phone2;
    String phone3;
    String phone4;

    public Customer() {
    }

    public Customer(String userid, String password, String address, String email, String phone1, String phone2, String phone3, String phone4) {
        this.userid = userid;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.phone4 = phone4;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getPhone4() {
        return phone4;
    }

    public void setPhone4(String phone4) {
        this.phone4 = phone4;
    }

    @Override
    public String toString() {
        return "Customer{" + "userid=" + userid + ", password=" + password + ", address=" + address + ", email=" + email + ", phone1=" + phone1 + ", phone2=" + phone2 + ", phone3=" + phone3 + ", phone4=" + phone4 + '}';
    }
    public static void main(String[] args) {
 
	Customer c=new Customer("madhu", "123456", "123/23,vij", "madhu@gmail.com", "5454545454", "4343434344", "7878787878", "9948963307");
	Gson gson = new Gson();

	String json = gson.toJson(c);
 
	System.out.println(json);
 
    }
    
    
}
