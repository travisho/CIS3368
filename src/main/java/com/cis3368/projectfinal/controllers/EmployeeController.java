package com.cis3368.projectfinal.controllers;

import com.cis3368.projectfinal.models.Customer;
import com.cis3368.projectfinal.models.Employee;
import com.cis3368.projectfinal.repositories.EmployeeRepo;
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
public class EmployeeController {

    @Autowired
    EmployeeRepo er;

    @RequestMapping(value="/newemployee", method = RequestMethod.POST)
    public ModelAndView newEmployee(@RequestParam("first") String firstname, @RequestParam("last") String lastname, @RequestParam("phone") String phone, @RequestParam("email") String email){
        ModelAndView mav = new ModelAndView("employees");
        Boolean error = false;

        //Checking for duplicate emails
        Iterable<Employee> employeeList = er.findAll();
        for(Employee iter: employeeList){
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

        if(!error) {
            Employee ne = new Employee();
            UUID uuid = UUID.randomUUID();
            String employeeid = uuid.toString();
            ne.setEmployeeID(employeeid);
            ne.setFirstName(firstname);
            ne.setLastName(lastname);
            ne.setPhone(phone);
            ne.setEmail(email);
            er.save(ne);
        }

        mav.addObject("employeeList", er.findAll());

        return mav;
    }

    @RequestMapping(value = "/deleteemployee", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@RequestParam("id") String id){
        ModelAndView mav = new ModelAndView("employees");
        er.deleteById(id);
        mav.addObject("employeeList", er.findAll());
        return mav;
    }

    @RequestMapping("/editemployee")
    public ModelAndView editEmployee(@RequestParam("id") String id){
        ModelAndView mav = new ModelAndView("editemployee");
        mav.addObject("employeeList", er.findAll());
        mav.addObject("employeeID", id);
        return mav;
    }

    @RequestMapping(value="/editedemployee", method = RequestMethod.GET)
    public ModelAndView editedEmployee(@RequestParam(name="id") String id, @RequestParam("first") String firstname, @RequestParam("last") String lastname, @RequestParam("phone") String phone, @RequestParam("email") String email){
        ModelAndView mav = new ModelAndView("employees");
        Boolean error = false;

        //Checking for duplicate emails
        Iterable<Employee> employeeList = er.findAll();
        for(Employee iter: employeeList){
            if(iter.getEmail().equals(email)){
                String errormessage = "This email has already been taken. No changes were made.";
                mav.addObject("errormessage", errormessage);
                error = true;
            }
        }

        //Checking for filled fields
        if(firstname.equals("") || lastname.equals("") || phone.equals("") || email.equals("")){
            String errormessage = "Not all fields were filled. No changes were made.";
            mav.addObject("errormessage", errormessage);
            error = true;
        }

        if(!error) {

            Employee ne = er.findById(id).get();
            ne.setFirstName(firstname);
            ne.setLastName(lastname);
            ne.setPhone(phone);
            ne.setEmail(email);
            er.save(ne);

        }

        mav.addObject("employeeList", er.findAll());
        return mav;
    }

    @RequestMapping(value= "/filteremployee", method = RequestMethod.GET)
    public ModelAndView filterEmployee(@RequestParam("filterfirst") String first, @RequestParam("filterlast") String last, @RequestParam("filterphone") String phone, @RequestParam("filteremail") String email){
        ModelAndView mav = new ModelAndView("employees");

        ArrayList<Employee> all = new ArrayList<>();
        er.findAll().forEach(all::add);

        //Filtering first name
        if(!first.equals("")) {
            Iterator<Employee> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getFirstName();
                if(!first.equals(check)){
                    itr.remove();
                }
            }
        }

        //Filtering last name
        if(!last.equals("")) {
            Iterator<Employee> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getLastName();
                if(!last.equals(check)){
                    itr.remove();
                }
            }
        }

        //Filtering phone number
        if(!phone.equals("")) {
            Iterator<Employee> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getPhone();
                if(!phone.equals(check)){
                    itr.remove();
                }
            }
        }

        //Filtering email
        if(!email.equals("")) {
            Iterator<Employee> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getEmail();
                if(!email.equals(check)){
                    itr.remove();
                }
            }
        }

        ArrayList<String> filteredid = new ArrayList<>();
        for(Employee iter: all){
            filteredid.add(iter.getEmployeeID());
        }

        mav.addObject("employeeList", er.findAllById(filteredid));
        return mav;
    }
}
