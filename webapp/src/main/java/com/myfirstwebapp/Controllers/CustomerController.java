package com.myfirstwebapp.Controllers;

import com.myfirstwebapp.Entities.Customer;
import com.myfirstwebapp.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //need to inject CustomerService
    @Autowired
    private CustomerService customerService;


    @InitBinder
    public void initBinder(WebDataBinder dataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @RequestMapping("/list")
    public String listCustomers(Model model)
    {
        //get customers from service
        List<Customer> customerList = customerService.getCustomers();

        //add them to the model
        model.addAttribute("customerlist",customerList);

        return "list-customers";
    }


    @RequestMapping("/showFormForAdd")
    public String showForm(Model model)
    {
        //create a model attribute to bind data
        Customer customer = new Customer();
        model.addAttribute("customer",customer);


        return "customer-form";
    }


    @PostMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "customer-form";

        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,Model model)
    {
        //get customer from db
        Customer customer = customerService.getCustomer(theId);

        //set customer as model attribute for pre-populating the form
        model.addAttribute("customer",customer);

        //send over to form
        return "customer-form";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("customerId")int theId)
    {
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }

}
