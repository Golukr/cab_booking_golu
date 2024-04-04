package org.demoProject.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.demoProject.model.Address;
import org.demoProject.model.Customer;
import org.demoProject.model.Driver;
import org.demoProject.model.TripBooking;
import org.demoProject.model.TripBooking;
import org.demoProject.service.AddressService;
import org.demoProject.service.CabService;
import org.demoProject.service.CustomerService;
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
@RequestMapping("/trip")
@SessionAttributes("customerid")

public class TripBookingController {

	@Autowired
	private TripBookingService tripbookingservice;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
    private TwilioService twilioService;
	
	@Autowired
	private CabService cabService;
	
	@GetMapping("/settrip")
	public String settrip(Model model)
	{
		TripBooking trip=new TripBooking();
		Integer customerId = (Integer) model.getAttribute("customerid");
		Customer customer = customerService.findById(customerId);
		
		List<String> fromLoc = tripbookingservice.getAllLoc1();
		
		List<String> toLoc = tripbookingservice.getAllLoc2();
		
		List<String> carType = cabService.listAllCarType();
		
		trip.setCustomerId(customer);
		trip.setStatus(true);
		trip.setDriverId(new Driver());
		model.addAttribute("trip",trip);
		model.addAttribute("from", fromLoc);
		model.addAttribute("to", toLoc);
		model.addAttribute("cabtype", carType);
		TripBooking temp = (TripBooking) model.getAttribute("trip");
//		List<String> toLoc2 = tripbookingservice.getAllLoc2AgainstLoc1(temp.getFromLocation());
//System.out.println(toLoc2);
		return "tripbooking/settrip";
	}
	@PostMapping("/addtrip")
	public String addtrip(@ModelAttribute("trip")TripBooking trip,Model model)
	{
		Address address;
		try {
			address = addressService.findByLocation1AndLocation2(trip.getFromLocation(), trip.getToLocation());
		}
		catch(Exception e) {
			return "tripbooking/addunsuccess";
		}
		if(address != null && address.getLocation1().equals(address.getLocation2())) {
			return "tripbooking/sameaddress";
		}
		System.out.println("address is : "+address);
		List<Driver> driverList;
		if(trip.getDriverId().getCab().getCarType().equals("bike"))
			driverList = driverService.findByBikeOrderByRating();
		else if(trip.getDriverId().getCab().getCarType().equals("2-seater"))
				driverList = driverService.findBy2SeaterOrderByRating();
		else if(trip.getDriverId().getCab().getCarType().equals("4-seater"))
				driverList = driverService.findBy4SeaterOrderByRating();
		else
			driverList = driverService.findBy4SeaterOrderByRating();
		List<TripBooking> tripList = tripbookingservice.findByStatusDate(true,trip.getFromDateTime());
		List<Driver> tripDriver = new ArrayList<>();
		for (TripBooking trips : tripList) {
			tripDriver.add(trips.getDriverId());
		}
		driverList.removeAll(tripDriver);
		if(driverList.size() == 0) {
			model.addAttribute("trip",trip);
			return "tripbooking/drivernotfound";
		}
		System.out.println(driverList);
		trip.setDriverId(driverList.get(driverList.size()-1));
		if(address==null)
			return "tripbooking/addunsuccess";
		trip.setDistanceInKm(address.getDistance());
		Float bill = trip.getDistanceInKm() * trip.getDriverId().getCab().getPerKmRate();
		
		LocalDateTime toDateTime = trip.getFromDateTime().plusHours(address.getDatetime().getHour())
									.plusMinutes(address.getDatetime().getMinute())
									.plusSeconds(address.getDatetime().getSecond());
		
		
		trip.setToDateTime(toDateTime);
		trip.setBill(bill);
		trip.setStatus(true);
		model.addAttribute("trip",trip);
		return "tripbooking/addtrip";
	}
	
	@PostMapping("/addtripprocess")
	public String processaddtrip(@ModelAttribute("trip")TripBooking trip,Model model)
	{
		Integer customerId = (Integer) model.getAttribute("customerid");
		Customer customer = customerService.findById(customerId);
		trip.setCustomerId(customer);
		TripBooking tr=tripbookingservice.addTripBooking(trip);
		
		if(tr==null)
			return "tripbooking/addunsuccess";
		Message msg = twilioService.sendSms(("+91" + customer.getMobileNumber()), "Dear "+ tr.getCustomerId().getUserName()+"\nYour trip has been booked \nFrom :"+ tr.getFromLocation()+" \nTo :" +tr.getToLocation() + "\nOn :" + tr.getFromDateTime()+" \n Driver Name : "+tr.getDriverId().getUserName()+"\nDriver Mobile No :" +"+91" +tr.getDriverId().getMobileNumber());
		
		System.out.println(msg);
		
		msg = twilioService.sendSms(("+91" + tr.getDriverId().getMobileNumber()),"Dear "+ tr.getDriverId().getUserName()+ "\nYour have been booked \nFrom :"+ tr.getFromLocation()+" \nTo :" +tr.getToLocation() + "\nOn :" + tr.getFromDateTime());
		System.out.println(msg);	
		return "tripbooking/addsuccess";
		}
	
	//Modify
	
		@GetMapping("/modifytrips")
		public String modifytrip(Model model)
		{
			TripBooking trip=new TripBooking();
			model.addAttribute("trip",trip);
			return "tripbooking/modifytrip";
		}
		
		@PostMapping("/fetchtrip")
		public String fetchtrip(@ModelAttribute("trip") TripBooking trip,Model model)
		{
			 trip=tripbookingservice.findById(trip.getTripBookingId());
			if(trip!=null)
			{
				model.addAttribute("trip",trip);
				return "tripbooking/fetchsuccess";
			}
			else
			{
				
				return "tripbooking/fetchunsuccess";
			}
		}
		
		@PostMapping("/modifytrip")
		public String processmodifytrip(@ModelAttribute("trip")TripBooking trip,Model model)
		{
			TripBooking tr=tripbookingservice.updateTripBooking(trip);
			if(tr!=null)
				return "tripbooking/modifysuccess";
			else
				return "tripbooking/modifyunsuccess";
		}
		
		@GetMapping("/canceltrip")
		public String canceltrip(@RequestParam("tripid") Integer tripId,Model model) {
			TripBooking trip = tripbookingservice.findById(tripId);
			if(trip!=null)
			{
				model.addAttribute("trip",trip);
				return "tripbooking/fetchcancelsuccess";
			}	
				return "tripbooking/fetchunsuccess";
		}

		
		@PostMapping("/canceltripprocess")
		public String processcanceltrip(@ModelAttribute("trip") TripBooking trip,Model model)
		{
			trip=tripbookingservice.findById(trip.getTripBookingId());
			trip.setStatus(false);
			trip.setFromDateTime(trip.getFromDateTime());
			tripbookingservice.updateTripBooking(trip);
			
			Integer customerId = (Integer) model.getAttribute("customerid");
			List<TripBooking> trips=tripbookingservice.findAllTrips(customerId);

			if(trips.size()>0)
			{
				model.addAttribute("trips",trips);
			    return "tripbooking/viewalltrips";
			}
			
				return "tripbooking/fetchunsuccess";
		}
		
		//findbyid
		
		@GetMapping("/findtrip")
		public String findtripbooking(Model model)
		{
			TripBooking trip=new TripBooking();
			model.addAttribute("trip",trip);
			return "tripbooking/findtrip";
		}
		
		@PostMapping("/fetchtripdetails")
		public String fetchtripdetails(@ModelAttribute("trip") TripBooking trip,Model model)
		{
			trip=tripbookingservice.findById(trip.getTripBookingId());
			if(trip!=null)
			{
				model.addAttribute("trip",trip);
				return "tripbooking/findsuccess";
			}
			else
			{
				
				return "tripbooking/fetchunsuccess";
			}
		}
		
		@GetMapping("/gettrip")
		public String getdriver(@RequestParam("tripid") Integer tripId,Model model) {
			TripBooking trip = tripbookingservice.findById(tripId);
			model.addAttribute("trip", trip);
			return "tripbooking/gettrip";
		}
		
//		//findlistoftrips
//		@GetMapping("/tripsofcustomer")
//		public String findtrips(Model model)
//		{
//			Customer customer=new Customer();
//			model.addAttribute(customer);
//			return "tripbooking/tripsofcustomer";
//		}
		
		@GetMapping("/viewalltrips")
		public String viewtrips(Model model)
		{
			Integer customerId = (Integer) model.getAttribute("customerid");
			List<TripBooking> trips=tripbookingservice.findAllTrips(customerId);
//			System.out.println(trips);
			if(trips.size()>0)
			{
				model.addAttribute("trips",trips);
			    return "tripbooking/viewalltrips";
			}
			
				return "tripbooking/fetchunsuccess";
		}		
}
