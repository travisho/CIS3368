package com.cis3368.projectfinal.controllers;

import com.cis3368.projectfinal.models.Customer;
import com.cis3368.projectfinal.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

@Controller
public class CustomerController {

    @Autowired
    CustomerRepo cr;

    @RequestMapping(value="/newcustomer", method = RequestMethod.POST)
    public ModelAndView newCustomer(@RequestParam("first") String firstname, @RequestParam("last") String lastname, @RequestParam("phone") String phone, @RequestParam("email") String email){
        ModelAndView mav = new ModelAndView("customers");
        Boolean error = false;

        //Checking for duplicate emails
        Iterable<Customer> customerList = cr.findAll();
        for(Customer iter: customerList){
            if(iter.getEmail().equals(email)){
                String errormessage = "This email has already been taken.";
                mav.addObject("errormessage", errormessage);
                error = true;
            }
        }

        //Checking for filled fields
        if(firstname.equals("") || lastname.equals("") || phone.equals("") || email.equals("")){
            String errormessage = "Not all fields were filled.";
            mav.addObject("errormessage", errormessage);
            error = true;
        }

        //Checking for errors
        if(!error) {
            Customer nc = new Customer();
            UUID uuid = UUID.randomUUID();
            String customerid = uuid.toString();
            nc.setCustomerID(customerid);
            nc.setCustomerfirst(firstname);
            nc.setCustomerlast(lastname);
            nc.setPhone(phone);
            nc.setEmail(email);
            cr.save(nc);
        }

        mav.addObject("customerList", cr.findAll());

        return mav;
    }

    @RequestMapping(value = "/deletecustomer", method = RequestMethod.GET)
    public ModelAndView deleteCustomer(@RequestParam("id") String id){
        ModelAndView mav = new ModelAndView("customers");
        cr.deleteById(id);
        mav.addObject("customerList", cr.findAll());
        return mav;
    }

    @RequestMapping("/editcustomer")
    public ModelAndView editCustomer(@RequestParam("id") String id){
        ModelAndView mav = new ModelAndView("editcustomer");
        mav.addObject("customerList", cr.findAll());
        mav.addObject("customerID", id);
        return mav;
    }

    @RequestMapping(value="/editedcustomer", method = RequestMethod.GET)
    public ModelAndView editedCustomer(@RequestParam(name="id") String id, @RequestParam("first") String firstname, @RequestParam("last") String lastname, @RequestParam("phone") String phone, @RequestParam("email") String email){
        ModelAndView mav = new ModelAndView("customers");
        Boolean error = false;
        String errormessage = "";

        //Checking for duplicate emails
        Iterable<Customer> customerList = cr.findAll();
        for(Customer iter: customerList){
            if(iter.getEmail().equals(email)){
                errormessage = "This email has already been taken. No changes were made.";
                mav.addObject("errormessage", errormessage);
                error = true;
            }
        }

        //Checking for filled fields
        if(firstname.equals("") || lastname.equals("") || phone.equals("") || email.equals("")){
            errormessage = "Not all fields were filled. No changes were made.";
            mav.addObject("errormessage", errormessage);
            error = true;
        }

        if(!error){

            Customer nc = cr.findById(id).get();
            nc.setCustomerfirst(firstname);
            nc.setCustomerlast(lastname);
            nc.setPhone(phone);
            nc.setEmail(email);
            cr.save(nc);

        }

        mav.addObject("customerList", cr.findAll());
        return mav;
    }

    @RequestMapping(value= "/filtercustomer", method = RequestMethod.GET)
    public ModelAndView filterCustomer(@RequestParam("filterfirst") String first, @RequestParam("filterlast") String last, @RequestParam("filterphone") String phone, @RequestParam("filteremail") String email){
        ModelAndView mav = new ModelAndView("customers");

        ArrayList<Customer> all = new ArrayList<>();
        cr.findAll().forEach(all::add);

        //Filtering first name
        if(!first.equals("")) {
            Iterator<Customer> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getCustomerfirst();
                if(!first.equals(check)){
                    itr.remove();
                }
            }
        }

        //Filtering last name
        if(!last.equals("")) {
            Iterator<Customer> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getCustomerlast();
                if(!last.equals(check)){
                    itr.remove();
                }
            }
        }

        //Filtering phone number
        if(!phone.equals("")) {
            Iterator<Customer> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getPhone();
                if(!phone.equals(check)){
                    itr.remove();
                }
            }
        }

        //Filtering email
        if(!email.equals("")) {
            Iterator<Customer> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getEmail();
                if(!email.equals(check)){
                    itr.remove();
                }
            }
        }

        ArrayList<String> filteredid = new ArrayList<>();
        for(Customer iter: all){
            filteredid.add(iter.getCustomerID());
        }

        mav.addObject("customerList", cr.findAllById(filteredid));
        return mav;
    }
}
