package org.demoProject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.demoProject.dto.LoginToken;
//import org.demoProject.dto.LoginToken;
import org.demoProject.model.AbstractUser;
import org.demoProject.model.Admin;
import org.demoProject.model.Customer;
import org.demoProject.model.Driver;
import org.demoProject.model.TripBooking;
import org.demoProject.service.AdminLoginImpl;
import org.demoProject.service.AdminService;
import org.demoProject.service.CustomerService;
import org.demoProject.service.DriverService;
import org.demoProject.service.TripBookingService;
import org.demoProject.service.TwilioService;
//import org.demoProject.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/admin")
@SessionAttributes("adminid")
public class AdminController {
	@Autowired
	private AdminService adminservice;

	@Autowired
	private AdminLoginImpl adminLoginImpl;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	CustomerService customerService;

	@Autowired
	private TripBookingService tripbookingservice;
	
	@Autowired
    private TwilioService twilioService;

	// Add (correct code)
	@GetMapping("/addadmin")
	public String addadmin(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "admin/addadmin";
	}

	@PostMapping("/addadmin")
	public String processaddadmin(@ModelAttribute("admin") Admin admin) {
		if (adminservice.findAdminbyusername(admin.getUserName()) == null) {
			adminservice.addAdmin(admin);
			return "admin/addsuccess";
		}
//		Admin adm=adminservice.addAdmin(admin);
//		if(adm==null)
		return "admin/addunsuccess";
	}

	// Modify

//	@GetMapping("/modifyadmin")
//	public String modifyadmin(Model model) {
//		Admin admin = new Admin();
//		model.addAttribute(admin);
//		return "admin/modifyadmin";
//	}

	@GetMapping("/fetchadmin")
	public String fetchadmin( Model model) {
		Integer adminId = (Integer) model.getAttribute("adminid");
		Admin admin = adminLoginImpl.getUserByUserId(adminId);
		if (admin != null) {
			admin.setMobileNumber(admin.getMobileNumber().substring(3));
			model.addAttribute("admin", admin);
			return "admin/fetchsuccess";
		} else {

			return "admin/fetchunsuccess";
		}
	}

	@PostMapping("/modifyadmin")
	public String processmodifyadmin(@ModelAttribute("admin") Admin admin) {
		admin.setMobileNumber("+91" + admin.getMobileNumber());
		Admin adm = adminservice.updateAdmin(admin);
		if (adm != null)
			return "admin/modifysuccess";
		else
			return "admin/modifyunsuccess";
	}

	// Delete

	@GetMapping("/deleteadmin")
	public String deleteadmin(Model model) {
		Admin admin = new Admin();
		model.addAttribute(admin);
		return "admin/deleteadmin";
	}

	@PostMapping("/fetchdeleteadmin")
	public String fetchfordeleteadmin(@ModelAttribute("admin") Admin admin, Model model) {
		admin = adminservice.findById(admin.getAdminId());
		if (admin != null) {
			model.addAttribute("admin", admin);
			return "admin/fetchdeletesuccess";
		} else {

			return "admin/fetchunsuccess";
		}
	}

	@PostMapping("/deleteadmin")
	public String processdeleteadmin(@ModelAttribute("admin") Admin admin) {
		Admin adm = adminservice.deleteAdmin(admin.getAdminId());
		if (adm != null)
			return "admin/deletesuccess";
		else
			return "admin/deleteunsuccess";
	}
//	@GetMapping("/delete/{adminId}") //works if we pass admin id through url
//	public String processdeleteadmin(@PathVariable("adminId")Integer adminId)
//	{
//		Admin adm=adminservice.deleteAdmin(adminId);
//		if(adm==null)
//			return "deleteunsuccess";
//		else
//			return "deletesuccess";
//	}

	// findtripsofcustomer

//	@GetMapping("/tripsofcustomer")
//	public String findtrips(Model model) {
//		Customer customer = new Customer();
//		model.addAttribute(customer);
//		return "admin/tripsofcustomer";
//	}
	
//	@PostMapping("/tripsofcustomer")
//	public String viewtrips(@ModelAttribute("customer") Customer customer, Model model) {
//		List<TripBooking> lists = adminservice.findAllTripsofcustomer(customer.getCustomerId());
//		if (lists.size() > 0) {
//			model.addAttribute("lists", lists);
//			return "admin/viewalltrips";
//		} else
//			return "admin/fetchunsuccess";
//	}
	
	@GetMapping("/tripsofcustomer")
	public String findtrips(Model model) {
		List<Customer> lists = customerService.findAll();
		model.addAttribute("lists",lists);
		return "admin/tripsofcustomer2";
	}

	@GetMapping("/gettripscustomer")
	public String viewtrips(@RequestParam("customerid") Integer customerid, Model model) {
		Customer customer = customerService.findById(customerid);
		model.addAttribute("customer", customer);
		List<TripBooking> lists = adminservice.findAllTripsofcustomer(customerid);
		if (lists.size() > 0) {
			model.addAttribute("lists", lists);
			return "admin/viewalltrips";
		} else
			return "admin/tripnotfound";
	}
	
	@GetMapping("/getdriver")
	public String getdriver(@RequestParam("tripid") Integer tripId,Model model) {
		TripBooking trip = tripbookingservice.findById(tripId);
		Driver driver = trip.getDriverId();
		if(driver == null) {
			return "admin/fetchunsuccess";
		}
		model.addAttribute("driver",driver);
		return "admin/getdriver";
	}

	// findtripscustomerwise

	@GetMapping("/viewtripscustomerwise")
	public String listtripsCustomers(Model model) {
		List<TripBooking> lists = adminservice.findAllTripsCustomerwise();
		if (lists.size() > 0) {
			model.addAttribute("lists", lists);
			return "admin/listcustomertrips";
		} else
			return "admin/fetchunsuccess";

	}

	// findtripsdriverwise

	@GetMapping("/viewtripsdriverwise")
	public String listtripsDriver(Model model) {
		List<TripBooking> lists = adminservice.findAllTripsDriverwise();
		if (lists.size() > 0) {
			model.addAttribute("lists", lists);
			return "admin/listdrivertrips";
		} else
			return "admin/fetchunsuccess";

	}

	@GetMapping("/viewtripsdatewise")
	public String listtripsbydate(Model model) {
		List<TripBooking> lists = adminservice.findAllTripsDatewise();
		if (lists.size() > 0) {
			model.addAttribute("lists", lists);
			return "admin/tripsdatelist";
		} else
			return "admin/fetchunsuccess";

	}
	
	@GetMapping("/listbestdrivers")
	public String listBestDriver(Model model) {
		List<Driver> list = driverService.getBestDrivers();
		if (list.isEmpty() == false) {
			model.addAttribute("list", list);
			return "admin/listbestdriver";
		}
		return "admin/addunsuccess";
	}

	@GetMapping("/listdrivers")
	public String listDriver(Model model) {
		List<Driver> list = driverService.findAll();
		if (list.isEmpty() == false) {
			model.addAttribute("list", list);
			return "driver/listdriver";
		}
		return "tripbooking/addunsuccess";
	}
	
//	@GetMapping("/login")
//	public String loginadmin(Model model)
//	{
//		Admin admin=new Admin();
//		model.addAttribute("admin",admin);
//		return "admin/loginadmin"; 
//		
//	}
//	
//	@PostMapping("/validadmin")
//	public String validcustomer(@ModelAttribute("admin") Admin admin,Model model)
//	{
//		if(adminservice.validateAdmin(admin.getUserName(),admin.getPassword())!=null)
//			return "admin/profile"; //here add welcome $name then logged in
//		else
//			return "admin/profilenotfound";
//	}

	@GetMapping("/login")
	public String login(Model model) {
		LoginToken token = new LoginToken();
		model.addAttribute("logintoken", token);
		return "admin/loginadmin";
	}

	@PostMapping("/validadmin")
	public String checkLogin(@ModelAttribute("logintoken") LoginToken token, Model model) {
		Admin admin = adminLoginImpl.checkLogin(token);
		Integer adminid = null;
		if (admin != null) {
			adminid = admin.getAdminId();
			System.out.println(adminid);
			System.out.println(admin.getMobileNumber());
			model.addAttribute("adminid", adminid);
			twilioService.sendSms(admin.getMobileNumber(), "You have logged in as admin.");
			return "admin/profile"; // here add welcome $name then logged in
		} 
		else
			return "admin/profilenotfound";
	}
	@GetMapping("/checkloggedin")
	public String checkloggedin(Model model) {
		Integer adminId = (Integer) model.getAttribute("adminid");
		Admin admin = adminLoginImpl.getUserByUserId(adminId);
		System.out.println(admin);
		model.addAttribute("admin",admin);
		return "admin/displayuser";
	}
	@GetMapping("/logout")
	public String logout(Model model) {
		LoginToken token = new LoginToken();
		Integer adminId = (Integer) model.getAttribute("adminid");
		Admin admin = adminservice.findById(adminId);
		model.addAttribute("logintoken", token);
		adminId= null;
		System.out.println(adminId);
		twilioService.sendSms(admin.getMobileNumber(), "You have logged out as admin.");
	    return "admin/loginadmin";
	}
	

}
