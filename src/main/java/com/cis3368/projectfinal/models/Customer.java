package com.cis3368.projectfinal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerfirst() {
        return customerfirst;
    }

    public void setCustomerfirst(String customerfirst) {
        this.customerfirst = customerfirst;
    }

    public String getCustomerlast() {
        return customerlast;
    }

    public void setCustomerlast(String customerlast) {
        this.customerlast = customerlast;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @Column(name = "customerid")
    String customerID;

    @Column(name = "customerfirst")
    String customerfirst;

    @Column(name = "customerlast")
    String customerlast;

    @Column(name = "phone")
    String phone;

    @Column(name = "email")
    String email;

}
