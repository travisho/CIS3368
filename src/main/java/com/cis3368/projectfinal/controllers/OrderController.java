package com.cis3368.projectfinal.controllers;

import com.cis3368.projectfinal.models.Customer;
import com.cis3368.projectfinal.models.Inventory;
import com.cis3368.projectfinal.models.Order;
import com.cis3368.projectfinal.repositories.CustomerRepo;
import com.cis3368.projectfinal.repositories.EmployeeRepo;
import com.cis3368.projectfinal.repositories.InventoryRepo;
import com.cis3368.projectfinal.repositories.OrderRepo;
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
public class OrderController {

    @Autowired
    CustomerRepo cr;
    @Autowired
    EmployeeRepo er;
    @Autowired
    InventoryRepo ir;
    @Autowired
    OrderRepo or;

    @RequestMapping(value="/neworder", method = RequestMethod.POST)
    public ModelAndView newOrder(@RequestParam(name="item") String itemid, @RequestParam(name="quantity") String quantity, @RequestParam(name="customerid") String customerid, @RequestParam(name="employeeid") String employeeid){
        ModelAndView mav = new ModelAndView("orders");

        if(quantity.equals("") || itemid.equals("") || customerid.equals("") || employeeid.equals("")){
            String errormessage = "Not all fields were filled.";
            mav.addObject("errormessage", errormessage);
        } else {
            Order no = new Order();
            UUID uuid = UUID.randomUUID();
            String orderid = uuid.toString();
            no.setOrderID(orderid);
            no.setItemID(itemid);
            no.setQuantity(Integer.parseInt(quantity));
            no.setCustomerID(customerid);
            no.setEmployeeID(employeeid);
            or.save(no);
        }

        mav.addObject("orderList", or.findAll());
        mav.addObject("inventoryList", ir.findAll());
        mav.addObject("customerList", cr.findAll());
        mav.addObject("employeeList", er.findAll());
        return mav;
    }

    @RequestMapping(value = "/deleteorder", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@RequestParam("id") String did){
        ModelAndView mav = new ModelAndView("orders");
        or.deleteById(did);
        mav.addObject("orderList", or.findAll());
        mav.addObject("inventoryList", ir.findAll());
        mav.addObject("customerList", cr.findAll());
        mav.addObject("employeeList", er.findAll());
        return mav;
    }

    @RequestMapping(value="/editorder")
    public ModelAndView editOrder(@RequestParam("id") String eid){
        ModelAndView mav = new ModelAndView("editorder");
        mav.addObject("orderList", or.findAll());
        mav.addObject("inventoryList", ir.findAll());
        mav.addObject("customerList", cr.findAll());
        mav.addObject("employeeList", er.findAll());

        Order thisorder = or.findById(eid).get();
        mav.addObject("orderID", thisorder);
        return mav;
    }

    @RequestMapping(value="/editedorder", method = RequestMethod.GET)
    public ModelAndView editedOrder(@RequestParam(name="other") String oid, @RequestParam(name="item") String itemid, @RequestParam(name="quantity") String quantity, @RequestParam(name="customerid") String customerid, @RequestParam(name="employeeid") String employeeid){
        ModelAndView mav = new ModelAndView("orders");

        if(quantity.equals("") || itemid.equals("") || customerid.equals("") || employeeid.equals("")){
            String errormessage = "Not all fields were filled. No changes were made.";
            mav.addObject("errormessage", errormessage);
        } else {
            Order no = or.findById(oid).get();
            no.setItemID(itemid);
            no.setQuantity(Integer.parseInt(quantity));
            no.setCustomerID(customerid);
            no.setEmployeeID(employeeid);
            or.save(no);
        }

        mav.addObject("orderList", or.findAll());
        mav.addObject("inventoryList", ir.findAll());
        mav.addObject("customerList", cr.findAll());
        mav.addObject("employeeList", er.findAll());
        return mav;
    }

    @RequestMapping(value= "/filterorder", method = RequestMethod.GET)
    public ModelAndView filterOrder(@RequestParam("item") String item, @RequestParam("quantity") String quantity, @RequestParam("customerid") String customerid, @RequestParam("employeeid") String employeeid){
        ModelAndView mav = new ModelAndView("orders");

        ArrayList<Order> all = new ArrayList<>();
        or.findAll().forEach(all::add);

        //Filtering item
        if(!item.equals("")) {
            Iterator<Order> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getItemID();
                if(!item.equals(check)){
                    itr.remove();
                }
            }
        }

        //Filtering quantity
        if(!quantity.equals("")) {
            Iterator<Order> itr = all.iterator();
            while(itr.hasNext()) {
                int check = itr.next().getQuantity();
                int iq = Integer.parseInt(quantity);
                if(iq!=check){
                    itr.remove();
                }
            }
        }

        //Filtering customer
        if(!customerid.equals("")) {
            Iterator<Order> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getCustomerID();
                if(!customerid.equals(check)){
                    itr.remove();
                }
            }
        }

        //Filtering employee
        if(!employeeid.equals("")) {
            Iterator<Order> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getEmployeeID();
                if(!employeeid.equals(check)){
                    itr.remove();
                }
            }
        }

        ArrayList<String> filteredid = new ArrayList<>();
        for(Order iter: all){
            filteredid.add(iter.getOrderID());
        }

        mav.addObject("orderList", or.findAllById(filteredid));
        mav.addObject("inventoryList", ir.findAll());
        mav.addObject("customerList", cr.findAll());
        mav.addObject("employeeList", er.findAll());
        return mav;
    }
}
