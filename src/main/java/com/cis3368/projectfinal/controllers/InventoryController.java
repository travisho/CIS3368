package com.cis3368.projectfinal.controllers;

import com.cis3368.projectfinal.models.Customer;
import com.cis3368.projectfinal.models.Employee;
import com.cis3368.projectfinal.models.Inventory;
import com.cis3368.projectfinal.repositories.EmployeeRepo;
import com.cis3368.projectfinal.repositories.InventoryRepo;
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
public class InventoryController {

    @Autowired
    InventoryRepo ir;

    @RequestMapping(value="/newinventory", method = RequestMethod.POST)
    public ModelAndView newInventory(@RequestParam("name") String name, @RequestParam("stock") String stock, @RequestParam("price") String price){
        ModelAndView mav = new ModelAndView("inventory");

        Inventory ni = new Inventory();
        UUID uuid = UUID.randomUUID();
        String inventoryid = uuid.toString();
        ni. setItemID(inventoryid);
        ni.setItemName(name);
        ni.setStock(Integer.parseInt(stock));
        ni.setPrice(Double.parseDouble(price));
        ir.save(ni);

        mav.addObject("inventoryList", ir.findAll());

        return mav;
    }

    @RequestMapping(value = "/deleteinventory", method = RequestMethod.GET)
    public ModelAndView deleteInventory(@RequestParam("id") String id){
        ModelAndView mav = new ModelAndView("inventory");
        ir.deleteById(id);
        mav.addObject("inventoryList", ir.findAll());
        return mav;
    }

    @RequestMapping("/editinventory")
    public ModelAndView editInventory(@RequestParam("id") String id){
        ModelAndView mav = new ModelAndView("editinventory");
        mav.addObject("inventoryList", ir.findAll());
        mav.addObject("inventoryID", id);
        return mav;
    }

    @RequestMapping(value="/editedinventory", method = RequestMethod.GET)
    public ModelAndView editedInventory(@RequestParam(name="id") String id, @RequestParam(name="name") String name, @RequestParam("stock") String stock, @RequestParam("price") String price){
        ModelAndView mav = new ModelAndView("inventory");

        if(name.equals("") || stock.equals("") || price.equals("")){
            String errormessage = "Not all fields were filled. No changes were made.";
            mav.addObject("errormessage", errormessage);
        } else {
            Inventory ni = ir.findById(id).get();
            ni.setItemName(name);
            ni.setStock(Integer.parseInt(stock));
            ni.setPrice(Double.parseDouble(price));
            ir.save(ni);
        }

        mav.addObject("inventoryList", ir.findAll());
        return mav;
    }

    @RequestMapping(value= "/filterinventory", method = RequestMethod.GET)
    public ModelAndView filterInventory(@RequestParam("filteritem") String item, @RequestParam("filterstock") String stock, @RequestParam("filterprice") String price){
        ModelAndView mav = new ModelAndView("inventory");

        ArrayList<Inventory> all = new ArrayList<>();
        ir.findAll().forEach(all::add);

        //Filtering item
        if(!item.equals("")) {
            Iterator<Inventory> itr = all.iterator();
            while(itr.hasNext()) {
                String check = itr.next().getItemName();
                if(!item.equals(check)){
                    itr.remove();
                }
            }
        }

        //Filtering stock
        if(!stock.equals("")) {
            Iterator<Inventory> itr = all.iterator();
            while(itr.hasNext()) {
                int check = itr.next().getStock();
                int istock = Integer.parseInt(stock);
                if(istock!=check){
                    itr.remove();
                }
            }
        }

        //Filtering price
        if(!price.equals("")) {
            Iterator<Inventory> itr = all.iterator();
            while(itr.hasNext()) {
                double check = itr.next().getPrice();
                double dprice = Double.parseDouble(price);
                if(check==dprice){
                    itr.remove();
                }
            }
        }

        ArrayList<String> filteredid = new ArrayList<>();
        for(Inventory iter: all){
            filteredid.add(iter.getItemID());
        }

        mav.addObject("inventoryList", ir.findAllById(filteredid));
        return mav;
    }
}
