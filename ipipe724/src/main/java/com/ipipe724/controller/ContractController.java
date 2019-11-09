package com.ipipe724.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContractController {

	
	@RequestMapping(value="/contracts", method = RequestMethod.GET)
    public String contracts(Model model){
		
			/*
			if(!userService.isUserHasRole("OPERATOR")) {
				return "redirect:/access-denied";
			}
	        
			int currentPage = pageNumber.orElse(1);
	        int size = pageSize.orElse(50);
	        
	         
	        Page<Contract> page = contractService.getPage(currentPage-1, size);
	        
	        model.addAttribute("page", page);
	        
	        int totalPages = page.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
	        
	        return "main/contracts";
	        */
        return "redirect:/add/request/repair";
    }
}
