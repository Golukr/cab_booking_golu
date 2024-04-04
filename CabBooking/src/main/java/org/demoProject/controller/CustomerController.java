package org.demoProject.controller;

import java.util.List;

import org.demoProject.dto.LoginToken;
import org.demoProject.model.Admin;
import org.demoProject.model.Customer;
import org.demoProject.model.Driver;
import org.demoProject.model.TripBooking;
import org.demoProject.service.AdminLoginImpl;
import org.demoProject.service.CustomerLoginImpl;
import org.demoProject.service.CustomerService;
import org.demoProject.service.TripBookingService;
import org.demoProject.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.twilio.rest.api.v2010.account.Message;


@Controller
@RequestMapping("/customer")

@SessionAttributes("customerid")
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
	@Autowired
	private CustomerLoginImpl customerLoginImpl;
	
	@Autowired
	private TripBookingService tripBookingService;

	@Autowired
    private TwilioService twilioService;
	
	@GetMapping("/addcustomer")
	public String addcustomer(Model model)
	{
		Customer customer=new Customer();
		model.addAttribute("customer",customer);
		return "customer/addcustomer";
	}
	
	@PostMapping("/addcustomer")
	public String processaddcustomer(@ModelAttribute("customer")Customer customer)
	{
		if(customerservice.findcustomerbyusername(customer.getUserName())==null)
		{
		
		  customerservice.addCustomer(customer);
		  twilioService.sendSms(("+91" + customer.getMobileNumber()),"Dear "+ customer.getUserName()+ "\nYou have registered yourself as customer.");
		  return "customer/addsuccess";
		}
			return "customer/addunsuccess";
		}
	
	@GetMapping("/getcustomer")
	public String getcustomer(@RequestParam("tripid") Integer tripId,Model model) {
		TripBooking trip = tripBookingService.findById(tripId);
		Customer customer = trip.getCustomerId();
		if(customer == null) {
			return "customer/fetchunsuccess";
		}
		model.addAttribute("customer",customer);
		return "customer/getcustomer";
	}
	
//	@GetMapping("/modifycustomer")
//	public String modifycustomer(Model model)
//	{
//		Customer customer=new Customer();
//		model.addAttribute(customer);
//		return "customer/modifycustomer";
//	}
	
	@GetMapping("/fetchcustomer")
	public String fetchcustomer(Model model)
	{
		Integer customerId = (Integer) model.getAttribute("customerid");
		Customer customer = customerLoginImpl.getUserByUserId(customerId);
		customer=customerservice.findById(customer.getCustomerId());
		if(customer!=null)
		{
			model.addAttribute("customer",customer);
			return "customer/fetchsuccess";
		}
		else
		{
			
			return "customer/fetchunsuccess";
		}
	}
	
	@PostMapping("/modifycustomer")
	public String processmodifycustomer(@ModelAttribute("customer") Customer customer)
	{
		
		Customer cust=customerservice.updateCustomer(customer);
		if(cust!=null) {
			twilioService.sendSms(("+91" + customer.getMobileNumber()),"Dear "+ customer.getUserName()+  "\nYour details has been updated.");
			return "customer/modifysuccess";
		}
		else
			return "customer/modifyunsuccess";
	}
	
	
	//Delete
	
//	@GetMapping("/deletecustomer")
//	public String deletecustomer(Model model)
//	{
//		Customer customer=new Customer();
//		model.addAttribute(customer);
//		return "customer/deletecustomer";
//	}
	
	@GetMapping("/fetchdeletecustomer")
	public String fetchfordeleteadmin(Model model)
	{
		Integer customerId = (Integer) model.getAttribute("customerid");
		Customer customer = customerLoginImpl.getUserByUserId(customerId);
		customer=customerservice.findById(customer.getCustomerId());
		if(customer!=null)
		{
			model.addAttribute("customer",customer);
			return "customer/fetchdeletesuccess";
		}
		else
		{
			
			return "customer/fetchunsuccess";
		}
	}

	
	@PostMapping("/deletecustomer")
	public String processdeletecustomer(@ModelAttribute("customer")Customer customer)
	{
		Customer cust=customerservice.deleteCustomer(customer.getCustomerId());
		if(cust!=null)
			return "customer/deletesuccess";
		else
			return "customer/deleteunsuccess";
	}
	
	
	//Find by id
	
	
	@GetMapping("/findcustomer")
	public String findcustomer(Model model)
	{
		Customer customer=new Customer();
		model.addAttribute(customer);
		return "customer/findcustomer";
	}
	
	@PostMapping("/fetchcustomerdetails")
	public String fetchcustomerdetails(@ModelAttribute("customer") Customer customer,Model model)
	{
		customer=customerservice.findById(customer.getCustomerId());
		if(customer!=null)
		{
			model.addAttribute("customer",customer);
			return "customer/findsuccess";
		}
		else
		{
			
			return "customer/fetchunsuccess";
		}
	}
	
	//validate customer
	
//	@GetMapping("/login")
//	public String logincustomer(Model model)
//	{
//		Customer customer=new Customer();
//		model.addAttribute("customer",customer);
//		return "customer/logincustomer"; 
//		
//	}
//	
//	@PostMapping("/validcustomer")
//	public String validcustomer(@ModelAttribute("customer") Customer customer,Model model)
//	{
//		if(customerservice.validateCustomer(customer.getUserName(),customer.getPassword())!=null)
//			return "customer/profile"; //here add welcome $name then logged in
//		else
//			return "customer/profilenotfound";
//	}
	
	
	//findall customers
	
	
	@GetMapping("/findallcustomers")
	public String listCustomers(Model model)
	{
		List<Customer> lists=customerservice.findAll();
		model.addAttribute("lists",lists);
		if(lists.size()>0)
		return "customer/listcustomers";
		else
			return "customer/fetchunsuccess";
	}
	
	
	@GetMapping("/login")
	public String login(Model model) {
		LoginToken token = new LoginToken();
		model.addAttribute("logintoken", token);
		return "customer/logincustomer";
	}

	@PostMapping("/validcustomer")
	public String checkLogin(@ModelAttribute("logintoken") LoginToken token, Model model) {
		Customer customer = customerLoginImpl.checkLogin(token);
		if (customer != null) {
			model.addAttribute("customerid", customer.getCustomerId());
			return "customer/profile"; // here add welcome $name then logged in
		} 
		else
			return "customer/profilenotfound";
	}
	@GetMapping("/checkloggedin")
	public String checkloggedin(Model model) {
		Integer customerId = (Integer) model.getAttribute("customerid");
		Customer customer = customerLoginImpl.getUserByUserId(customerId);
		System.out.println(customer);
		model.addAttribute("customer",customer);
		return "customer/displayuser";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		LoginToken token = new LoginToken();
		model.addAttribute("logintoken", token);
	    return "admin/loginadmin";
	}
	

}
