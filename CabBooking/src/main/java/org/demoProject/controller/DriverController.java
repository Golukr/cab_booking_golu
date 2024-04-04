package org.demoProject.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.demoProject.dto.LoginToken;
import org.demoProject.model.Customer;
import org.demoProject.model.Driver;
import org.demoProject.model.TripBooking;
import org.demoProject.service.DriverLoginImpl;
import org.demoProject.service.DriverService;
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
@RequestMapping("/driver")
@SessionAttributes("driverid")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private DriverLoginImpl driverLoginImpl;
	
	@Autowired
	private TripBookingService tripbookingservice;
	
	@Autowired
    private TwilioService twilioService;

	// Adding driver

	@GetMapping("/adddriver")
	public String addDriver(Model model) {
		Driver driver = new Driver();
		model.addAttribute(driver);
		return "driver/adddriver";
	}

	@PostMapping("/adddriver")
	public String addDriverProcess(@ModelAttribute("driver") Driver driver, Model model) {
		
		DecimalFormat df = new DecimalFormat("0.0");
		
		Float ratings = Float.parseFloat(df.format(driver.getRating()));
		driver.setRating(ratings);
		Driver driver2 = driverService.addDriver(driver);
		if (driver2 == null) {
			return "driver/addunsuccess";
		} else {
			Message msg = twilioService.sendSms(("+91" + driver.getMobileNumber()),"Dear "+ driver.getUserName()+ "\nYour have been successfully registered in our system. \nUserName :"+ driver.getUserName()+" \nPassword :" +driver.getPassword() + "\nDon't forget to change your password");
			return "driver/addsuccess";
		}
	}
	
	@GetMapping("/getdriver")
	public String getdriver(@RequestParam("tripid") Integer tripId,Model model) {
		TripBooking trip = tripbookingservice.findById(tripId);
		Driver driver = trip.getDriverId();
		if(driver == null) {
			return "driver/fetchunsuccess";
		}
		model.addAttribute("driver",driver);
		return "driver/getdriver";
	}

	// Updating Driver

//	@GetMapping("/updatedriver")
//	public String updateDriver(Model model) {
//		Driver driver = new Driver();
//		model.addAttribute("driver",driver);
//		return "driver/updatedriver";
//		
//	}

	@GetMapping("/fetchupdatedriver")
	public String fetchUpdateDriver(Model model) {
		Integer driverId = (Integer) model.getAttribute("driverid");
		Driver driver = driverLoginImpl.getUserByUserId(driverId);
		if (driver == null) {
			return "driver/drivernotfound";
		}
		model.addAttribute("driver", driver);
		return "driver/driverupdatefound";
	}

	@PostMapping("/updatedriverprocess")
	public String updateDriverProcess(@ModelAttribute("driver") Driver driver, Model model) {
		Driver driver2 = driverService.updateDriver(driver);
		if (driver2 == null) {
			return "driver/updateunsuccess";
		} else {
			return "driver/updatesuccess";
		}
	}
	@GetMapping("/ratedriver")
	public String ratedriver(@RequestParam("driverid") Integer driverId,Model model) {
		Driver driver = driverService.findById(driverId);
		model.addAttribute("driver",driver);		
		
		return"driver/ratedriver";
	}
	@PostMapping("/ratedriver")
	public String ratedriverprocess(@ModelAttribute("driver") Driver driver,Model model) {
		Driver driver2 = driverService.findById(driver.getDriverId());
		Float ratings = (driver.getRating()+driver2.getRating())/2.0f;
		DecimalFormat df = new DecimalFormat("0.0");
		
		ratings = Float.parseFloat(df.format(ratings));
		driver2.setRating(ratings);
		driver = driverService.updateDriver(driver2);
		if (driver == null) {
			return "driver/rateunsuccess";
		} else {
			return "driver/ratesuccess";
		}
	}

	// Delete Driver

	@GetMapping("/deletedriver")
	public String deleteDriver(Model model) {
		List<Driver> lists = driverService.findAll();
		model.addAttribute("lists", lists);
		return "driver/deletedriverlist";
	}

	@GetMapping("/fetchdeletedriver")
	public String fetchDeleteDriver(@RequestParam("driverid") Integer driverid, Model model) {

		Driver driver = driverLoginImpl.getUserByUserId(driverid);
		if (driver == null) {
			return "driver/drivernotfound";
		}
		model.addAttribute("driver", driver);
		return "driver/driverdeletefound";
	}

	@PostMapping("/deletedriverprocess")
	public String deleteDriverProcess(@ModelAttribute("driver") Driver driver, Model model) {
		driver.setCab(null);
		Driver driver2 = driverService.deleteDriver(driver.getDriverId());
		if (driver2 == null) {
			return "driver/deleteunsuccess";
		} else {
			return "driver/deletesuccess";
		}
	}

	@GetMapping("/listbestdrivers")
	public String listBestDriver(Model model) {
		List<Driver> list = driverService.getBestDrivers();
		if (list.isEmpty() == false) {
			model.addAttribute("list", list);
			return "driver/listbestdriver";
		}
		return "driver/addunsuccess";
	}

	@GetMapping("/listdrivers")
	public String listDriver(Model model) {
		List<Driver> list = driverService.findAll();
		if (list.isEmpty() == false) {
			model.addAttribute("list", list);
			return "driver/listdriver";
		}
		return "driver/addunsuccess";
	}

	@GetMapping("/finddriver")
	public String findDriver(Model model) {
		Driver driver = new Driver();
		model.addAttribute("driver", driver);
		return "driver/finddriver";
	}

	@PostMapping("/finddriver")
	public String findDriverProcess(@ModelAttribute("driver") Driver driver, Model model) {
		driver = driverService.findByUserName(driver.getUserName());

		if (driver == null)
			return null;
		model.addAttribute(driver);
		return "driver/showdriver";
	}

//	@GetMapping("/login")
//	public String logindriver(Model model)
//	{
//		Driver driver=new Driver();
//		model.addAttribute("driver",driver);
//		return "driver/logindriver"; 
//		
//	}
//	
//	@PostMapping("/validdriver")
//	public String validdriver(@ModelAttribute("driver") Driver driver,Model model)
//	{
//		if(driverService.validateDriver(driver.getUserName(),driver.getPassword())!=null)
//			return "driver/profile"; //here add welcome $name then logged in
//		else
//			return "driver/profilenotfound";
//	}

	@GetMapping("/login")
	public String login(Model model) {
		LoginToken token = new LoginToken();
		model.addAttribute("logintoken", token);
		return "driver/logindriver";
	}

	@PostMapping("/validdriver")
	public String checkLogin(@ModelAttribute("logintoken") LoginToken token, Model model) {
		Driver driver = driverLoginImpl.checkLogin(token);
		if (driver != null) {
			model.addAttribute("driverid", driver.getDriverId());
			return "driver/profile"; // here add welcome $name then logged in
		} 
		return "driver/profilenotfound";
	}

	@GetMapping("/checkloggedin")
	public String checkloggedin(Model model) {
		Integer driverId = (Integer) model.getAttribute("driverid");
		Driver driver = driverLoginImpl.getUserByUserId(driverId);
		System.out.println(driver);
		model.addAttribute("driver", driver);
		return "driver/displayuser";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		LoginToken token = new LoginToken();
		model.addAttribute("logintoken", token);
	    return "driver/logindriver";
	}

}
