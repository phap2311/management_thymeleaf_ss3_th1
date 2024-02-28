package com.example.customer_management_thymeleaf.controller;

import com.example.customer_management_thymeleaf.model.Customer;
import com.example.customer_management_thymeleaf.service.CustomerService;
import com.example.customer_management_thymeleaf.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring5.processor.SpringErrorClassTagProcessor;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;
    @GetMapping("")
    public String index(Model model){
        List<Customer>customerList = iCustomerService.findAll();
        model.addAttribute("customers",customerList);
        return "/index";
    }
    @GetMapping("/create")
    public String create(Model model){
            model.addAttribute("customer",new Customer());
            return "/create";

    }
    @PostMapping("/save")
    public String save(Customer customer){
        customer.setId((int) (Math.random()*10000));
        iCustomerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model){
        model.addAttribute("customer", iCustomerService.findByid(id));
        return "/update";
    }
    @PostMapping("/update")
    public  String update(Customer customer){
        iCustomerService.update(customer.getId(),customer);
        return "redirect: /customers";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("customer",iCustomerService.findByid(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirect){
        iCustomerService.remove(customer.getId());
        redirect.addFlashAttribute("success","remove customer successfully");
        return "redirect: /customers";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("customer",iCustomerService.findByid(id));
        return "/view";
    }

}
