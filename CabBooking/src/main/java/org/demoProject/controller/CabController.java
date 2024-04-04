package org.demoProject.controller;

import java.util.*;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.demoProject.model.Cab;
import org.demoProject.service.CabService;
import org.demoProject.service.CabServiceImpl;
import org.demoProject.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cab")
public class CabController {
	

	@Autowired
	private CabService cabService;
	
	@Autowired
    private TwilioService twilioService;

	@GetMapping("/addcab")
	public String addCab(Model model) {
		Cab cab = new Cab();
		model.addAttribute("cab", cab);
		return "cab/addcab";
	}

	@PostMapping("/addcabprocess")
	public String addCabProcess(@ModelAttribute("cab") Cab cab, Model model) {
		Cab cab2 = cabService.addCab(cab);
		if (cab2 == null) {
			return "cab/addunsuccess";
		} else {
			return "cab/addsuccess";
		}
	}

//	@GetMapping("/updatecab")
//	public String updateCab(Model model) {
//		Cab cab = new Cab();
//		model.addAttribute("cab",cab);
//		return "cab/updatecab";
//		
//	}
//	
//	@PostMapping("/fetchcab")
//	public String fetchCab(@ModelAttribute("cab") Cab cab,Model model) {
//		cab = cabService.findById(cab.getCabId());
//		if(cab == null) {
//			return "cab/cabnotfound";
//		}
//		model.addAttribute("cab",cab);
//		return "cab/cabupdatefound";
//	}
//	
//	@PostMapping("/updatecabprocess")
//	public String updateCabProcess(@ModelAttribute("cab") Cab cab,Model model) {
//		Cab cab2 = cabService.updateCab(cab);
//		if(cab2 == null) {
//			return "cab/updateunsuccess";
//		}else {
//			return "cab/updatesuccess";
//		}
//	}
	@GetMapping("/updatecab")
	public String updateCab(Model model) {
		List<Cab> lists = cabService.findAll();
		model.addAttribute("lists", lists);
		return "cab/updatecablist";

	}

	@GetMapping("/fetchcab")
	public String fetchCab(@RequestParam("cabid") Integer cabid, Model model) {
		Cab cab = cabService.findById(cabid);
		if (cab == null) {
			return "cab/cabnotfound";
		}
		model.addAttribute("cab", cab);
		return "cab/cabupdatefound";
	}

	@PostMapping("/updatecabprocess")
	public String updateCabProcess(@ModelAttribute("cab") Cab cab, Model model) {
		Cab cab2 = cabService.updateCab(cab);
		if (cab2 == null) {
			return "cab/updateunsuccess";
		} else {
			return "cab/updatesuccess";
		}
	}

	@GetMapping("/deletecab")
	public String deleteCab(Model model) {
		Cab cab = new Cab();
		model.addAttribute("cab", cab);
		return "cab/deletecab";
	}

	@PostMapping("/fetchdeletecab")
	public String fetchDeleteCab(@ModelAttribute("cab") Cab cab, Model model) {
		cab = cabService.findById(cab.getCabId());
		if (cab == null) {
			return "cab/cabnotfound";
		}
		model.addAttribute("cab", cab);
		return "cab/cabdeletefound";
	}

	@PostMapping("/deletecabprocess")
	public String deleteCabrProcess(@ModelAttribute("cab") Cab cab2, Model model) {
		Cab cab = cabService.deleteCab(cab2.getCabId());
		if (cab2 != null) {
			return "cab/deletesuccess";
		}
		return "cab/deleteunsuccess";

	}

	@GetMapping("/listcabtypes")
	public String listCabTypes(Model model) {
		Cab cab = new Cab();
		model.addAttribute("cab", cab);
		List<Cab> listCab = cabService.findAll();

		// distinct cab
		List<Cab> lists = new ArrayList<>();
		for (Cab cab2 : listCab) {
			if (!lists.contains(cab2)) {
				lists.add(cab2);
			}
		}
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "cab/listcabsearch";

	}

	@PostMapping("/listcabtypes")
	public String listCabTypesProcess(@ModelAttribute("cab") Cab cab, Model model) {
		List<Cab> list = cabService.findByCarType(cab.getCarType());
		if (list.isEmpty() == false) {
			model.addAttribute("list", list);
			return "cab/listcabtypes";
		}
		return "cab/listunsuccess";
	}

	@GetMapping("/countcabtypes")
	public String countCabTypes(Model model) {
		Cab cab = new Cab();
		model.addAttribute("cab", cab);
		List<Cab> listCab = cabService.findAll();

		// distinct cab
		List<Cab> lists = new ArrayList<>();
		for (Cab cab2 : listCab) {
			if (!lists.contains(cab2)) {
				lists.add(cab2);
			}
		}
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "cab/countcabsearch";

	}

	@PostMapping("/countcabtypes")
	public String countCabTypesProcess(@ModelAttribute("cab") Cab cab, Model model) {
		Integer count = cabService.countByCarType(cab.getCarType());
		model.addAttribute("count", count);
		return "cab/countcab";
	}

	@GetMapping("/listcabtypesx")
	public String indexlistCabTypes(Model model) {
		Cab cab = new Cab();
		model.addAttribute("cab", cab);

		List<Cab> listCab = cabService.findAll();

		// distinct cab
		List<Cab> lists = new ArrayList<>();
		for (Cab cab2 : listCab) {
			if (!lists.contains(cab2)) {
				lists.add(cab2);
			}
		}
		System.out.println(lists);
		model.addAttribute("lists", lists);

		return "cab/listcabsearch2";

	}

	@PostMapping("/listcabtypesx")
	public String indexlistCabTypesProcess(@ModelAttribute("cab") Cab cab, Model model) {
		List<Cab> list = cabService.findByCarType(cab.getCarType());
		if (list.isEmpty() == false) {
			model.addAttribute("list", list);
			return "cab/listcabtypes2";
		}
		return "cab/listunsuccess";
	}

	@GetMapping("/countcabtypesx")
	public String indexcountCabTypes(Model model) {
		Cab cab = new Cab();
		model.addAttribute("cab", cab);

		List<Cab> listCab = cabService.findAll();

		// distinct cab
		List<Cab> lists = new ArrayList<>();
		for (Cab cab2 : listCab) {
			if (!lists.contains(cab2)) {
				lists.add(cab2);
			}
		}
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "cab/countcabsearch2";

	}

	@PostMapping("/countcabtypesx")
	public String indexcountCabTypesProcess(@ModelAttribute("cab") Cab cab, Model model) {
		Integer count = cabService.countByCarType(cab.getCarType());
		model.addAttribute("count", count);
		return "cab/countcab2";
	}

}
