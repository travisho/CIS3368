package com.cis3368.projectfinal.controllers;

import com.cis3368.projectfinal.repositories.CustomerRepo;
import com.cis3368.projectfinal.repositories.EmployeeRepo;
import com.cis3368.projectfinal.repositories.InventoryRepo;
import com.cis3368.projectfinal.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    CustomerRepo cr;
    @Autowired
    EmployeeRepo er;
    @Autowired
    InventoryRepo ir;
    @Autowired
    OrderRepo or;

    @RequestMapping("/")
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("/customers")
    public ModelAndView customersPage(){
        ModelAndView mav = new ModelAndView("customers");
        mav.addObject("customerList", cr.findAll());
        return mav;
    }

    @RequestMapping("/orders")
    public ModelAndView ordersPage(){
        ModelAndView mav = new ModelAndView("orders");
        mav.addObject("orderList", or.findAll());
        mav.addObject("inventoryList", ir.findAll());
        mav.addObject("customerList", cr.findAll());
        mav.addObject("employeeList", er.findAll());
        return mav;
    }

    @RequestMapping("/employees")
    public ModelAndView employeesPage(){
        ModelAndView mav = new ModelAndView("employees");
        mav.addObject("employeeList", er.findAll());
        return mav;
    }

    @RequestMapping("/inventory")
    public ModelAndView inventoryPage(){
        ModelAndView mav = new ModelAndView("inventory");
        mav.addObject("inventoryList", ir.findAll());
        return mav;
    }

}
