package osc;

import java.io.Serializable;

public class Company implements Serializable
{
    String companyName;
    String password;
    String address;
    String email;
    String landline;
    String phone;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getLandline() {
        return landline;
    }
    public void setLandline(String landline) {
        this.landline = landline;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Company() {
    }
    public Company(String companyName, String password, String address, String email, String landline, String phone) {
        this.companyName = companyName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.landline = landline;
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "Company{" + "companyName=" + companyName + ", password=" + password + ", address=" + address + ", email=" + email + ", landline=" + landline + ", phone=" + phone + '}';
    }
}
