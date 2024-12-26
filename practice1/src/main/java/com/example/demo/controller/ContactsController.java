package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.ContactsUpdateRequest;
import com.example.demo.entity.Contacts;
import com.example.demo.service.ContactsService;
@Controller
public class ContactsController {
	
	@Autowired
	private ContactsService contactsService;
	
	@GetMapping(value = "/admin/contacts")
	public String displayList(Model model) {
		List<Contacts> contactslist = contactsService.searchAll();
		model.addAttribute("contactslist", contactslist);
		return "admin/contacts";
	}
	
	@GetMapping("/admin/{id}")
	public String displayAdminView(@PathVariable Long id, Model model) {
	    Contacts contacts = contactsService.findById(id);
	    model.addAttribute("contactsData", contacts);
	    return "admin/view";
	}
	
	  @GetMapping("/admin/{id}/edit")
	  public String displayEdit(@PathVariable Long id, Model model) {
	    Contacts contacts = contactsService.findById(id);
	    ContactsUpdateRequest contactsUpdateRequest = new ContactsUpdateRequest();
	    contactsUpdateRequest.setId(contacts.getId());
	    contactsUpdateRequest.setFirstName(contacts.getFirstName());
	    contactsUpdateRequest.setLastName(contacts.getLastName());
	    contactsUpdateRequest.setEmail(contacts.getEmail());
	    contactsUpdateRequest.setPhone(contacts.getPhone());
	    contactsUpdateRequest.setZipCode(contacts.getZipCode());
	    contactsUpdateRequest.setAddress(contacts.getAddress());
	    contactsUpdateRequest.setBuildingName(contacts.getBuildingName());
	    contactsUpdateRequest.setContactType(contacts.getContactType());
	    contactsUpdateRequest.setBody(contacts.getBody());
	    model.addAttribute("contactsUpdateRequest", contactsUpdateRequest);
	    return "admin/edit";
	  }
	  
	  @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	  public String update(@Validated @ModelAttribute ContactsUpdateRequest contactsUpdateRequest, BindingResult result, Model model) {

	    if (result.hasErrors()) {
	      List<String> errorList = new ArrayList<String>();

	      for (ObjectError error : result.getAllErrors()) {
	        errorList.add(error.getDefaultMessage());
	      }
	      model.addAttribute("validationError", errorList);
	      return "admin/edit";
	    }
	    
	    contactsService.update(contactsUpdateRequest);
	    return String.format("redirect:/admin/%d", contactsUpdateRequest.getId());
	  }
	
	@GetMapping("/admin/{id}/delete")
	public String delete(@PathVariable Long id, Model model) {
		contactsService.delete(id);
		return "redirect:/admin/contacts";
		}
}