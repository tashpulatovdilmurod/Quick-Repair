package com.ipipe724.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.ipipe724.model.Mechanicher;
import com.ipipe724.model.RequestRepair;
import com.ipipe724.model.User;
import com.ipipe724.service.MechanicerService;
import com.ipipe724.service.RequestRepairService;
import com.ipipe724.service.UserService;

@Controller
public class MechanicerController {
	
	@Autowired
	private MechanicerService mechService;
	
	@Autowired
	private RequestRepairService repairRequestService;
	
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(value="/edit/mech", method = RequestMethod.GET)
    public String editMech(Model model, @RequestParam("id") Optional<Integer> id){
			    
		Mechanicher mech = mechService.findGrade(new Long(id.get()));
        
        model.addAttribute("mech", mech);
       
        return "work/addmech";
    }
	
	@RequestMapping(value="/add/mech", method = RequestMethod.GET)
    public String addMech(Model model){
       
		Mechanicher mech = new Mechanicher();
        model.addAttribute("mech", mech);
       
        return "work/addmech";
    }
	
	@RequestMapping(value = "/add/mech", method = RequestMethod.POST)
    public String addMechPost(@Valid Mechanicher mech, BindingResult bindingResult) {
		
		if(!userService.isUserHasRole("CUSTOMER")) {
			return "redirect:/access-denied";
		}
		
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       User user = userService.findUserByEmail(auth.getName());
       if (bindingResult.hasErrors()) {
           return "work/addmech";
       } else {
    	   mechService.saveGrade(mech);
       }
       return "redirect:/work/mechs";
	 }
	
	/* HERE Repair Request PARTS */
	
	@RequestMapping(value="/add/request/repair", method = RequestMethod.GET)
    public String newGrade(Model model){
		
		if(!userService.isUserHasRole("CUSTOMER")) {
			return "redirect:/access-denied";
		}
		
		RequestRepair request = new RequestRepair();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
                
        //model.addAttribute("request", request);
        model.addAttribute("user",user);
      
        model.addAttribute("id",user.getId());
        
        return "work/customer";
    }
	
	@RequestMapping(value = "/add/fastrepair/request", method = RequestMethod.POST)
    public String addfastrepairPost(@Valid RequestRepair mech, 
    		@RequestParam("location") Optional<String> location,
    		@RequestParam("broken") Optional<String> broken,
    		@RequestParam("priority1") Optional<String> priority1,
    		@RequestParam("priority2") Optional<String> priority2,
    		@RequestParam("priority3") Optional<String> priority3,
    		@RequestParam("car") Optional<String> car,
    		@RequestParam("message") Optional<String> message,
    		@RequestParam("id") Optional<String> id,
    		
    		BindingResult bindingResult) {
		
		
		
		if(!userService.isUserHasRole("CUSTOMER")) {
			return "redirect:/access-denied";
		}
		
       if (bindingResult.hasErrors()) {
           return "work/addmech";
       } else {
    	   
    	   System.out.println("\n\n THE RECEIVED DATA is -> "+id.get());
    	   
    	   User user = userService.findUser(Integer.parseInt(id.get()));
    	   
    	   RequestRepair requestRepair = new RequestRepair();
    	   requestRepair.setLocation(location.get());
    	   requestRepair.setCar(car.get());
    	   requestRepair.setMessage(message.get());
    	   requestRepair.setPriority(priority1.get());
    	   requestRepair.setUser(user);
    	   
    	   repairRequestService.saveGrade(requestRepair);
    	   
    	   //repairRequestService.saveGrade(mech);
       }
       return "redirect:/work/requests";
	 }
	
	@RequestMapping(value="/repair/requests", method = RequestMethod.GET)
    public String requests(Model model, @RequestParam("page") Optional<Integer> pageNumber, 
    	      @RequestParam("size") Optional<Integer> pageSize, @RequestParam("id") Optional<Integer> id){
		
		
		if(!userService.isUserHasRole("CUSTOMER")) {
			return "redirect:/access-denied";
		}
		
		int currentPage = pageNumber.orElse(1);
        int size = pageSize.orElse(50);
        
         
        //Page<Grade> page = gradeService.getPage(currentPage-1, size);
        Page<RequestRepair> repair = repairRequestService.findAllByUser(currentPage-1, size, id.get());
        
        model.addAttribute("repair", repair);
        
        
        return "work/user-applications";
    }
	
	/* it is for the mechanic, the data will be provided */
	
	@RequestMapping(value="/machanic/requests", method = RequestMethod.GET)
    public String requestsMech(Model model, @RequestParam("page") Optional<Integer> pageNumber, 
    	      @RequestParam("size") Optional<Integer> pageSize, @RequestParam("id") Optional<Integer> id){
		
		
		if(!userService.isUserHasRole("MECHANICHER")) {
			return "redirect:/access-denied";
		}
		
		int currentPage = pageNumber.orElse(1);
        int size = pageSize.orElse(50);
        
         
        //Page<Grade> page = gradeService.getPage(currentPage-1, size);
        Page<RequestRepair> repair = repairRequestService.findAllByUser(currentPage-1, size, id.get());
        model.addAttribute("repair", repair);
        
        
        return "work/user-applications";
    }
}
