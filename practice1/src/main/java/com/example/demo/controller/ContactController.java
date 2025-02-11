package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactRequest;
import com.example.demo.form.ContactUpdateRequest;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactServiceImpl;

@Controller
public class ContactController {
    // @Autowiredアノテーションについては後ほど解説します。
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactRequest", new ContactRequest());

        return "contact";
    }

    @PostMapping("/contact")
    public String contact(@Validated @ModelAttribute("contactRequest") ContactRequest contactRequest, BindingResult errorResult, HttpServletRequest request) {

        if (errorResult.hasErrors()) {
          return "contact";
        }

        HttpSession session = request.getSession();
        session.setAttribute("contactRequest", contactRequest);

        return "redirect:/contact/confirm";
    }

    @GetMapping("/contact/confirm")
    public String confirm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        ContactRequest contactRequest = (ContactRequest) session.getAttribute("contactRequest");
        model.addAttribute("contactRequest", contactRequest);
        return "confirmation";
    }

    @PostMapping("/contact/register")
    public String register(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        ContactRequest contactRequest = (ContactRequest) session.getAttribute("contactRequest");

        Contact contact = new Contact();
        contact.setLastName(contactRequest.getLastName());
        contact.setFirstName(contactRequest.getFirstName());
        contact.setEmail(contactRequest.getEmail());
        contact.setPhone(contactRequest.getPhone());
        contact.setZipCode(contactRequest.getZipCode());
        contact.setAddress(contactRequest.getAddress());
        contact.setBuildingName(contactRequest.getBuildingName());
        contact.setContactType(contactRequest.getContactType());
        contact.setBody(contactRequest.getBody());

        contactRepository.save(contact);

        return "redirect:/contact/complete";
    }

    @GetMapping("/contact/complete")
    public String complete(Model model, HttpServletRequest request) {

        if (request.getSession(false) == null) {
          return "redirect:/contact";
        }

        HttpSession session = request.getSession();
        ContactRequest contactRequest = (ContactRequest) session.getAttribute("contactRequest");
        model.addAttribute("contactRequest", contactRequest);

        session.invalidate();

        return "completion";
    }
    
    @Autowired
    private ContactServiceImpl contactServiceImple;
    
	@GetMapping(value = "/admin/contact")
	public String displayList(Model model) {
		List<Contact> contactlist = contactServiceImple.searchAll();
		model.addAttribute("contactlist", contactlist);
		return "admin/contact";
	}
	
	@GetMapping("/admin/{id}")
	public String displayAdminDetail(@PathVariable Long id, Model model) {
		Contact contact = contactServiceImple.findById(id);
	    model.addAttribute("contactData", contact);
	    return "admin/detail";
	}
	
	  @GetMapping("/admin/{id}/edit")
	  public String displayEdit(@PathVariable Long id, Model model) {
		Contact contact = contactServiceImple.findById(id);
	    ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest();
	    contactUpdateRequest.setId(contact.getId());
	    contactUpdateRequest.setFirstName(contact.getFirstName());
	    contactUpdateRequest.setLastName(contact.getLastName());
	    contactUpdateRequest.setEmail(contact.getEmail());
	    contactUpdateRequest.setPhone(contact.getPhone());
	    contactUpdateRequest.setZipCode(contact.getZipCode());
	    contactUpdateRequest.setAddress(contact.getAddress());
	    contactUpdateRequest.setBuildingName(contact.getBuildingName());
	    contactUpdateRequest.setContactType(contact.getContactType());
	    contactUpdateRequest.setBody(contact.getBody());
	    model.addAttribute("contactUpdateRequest", contactUpdateRequest);
	    return "admin/edit";
	  }
	  
	  @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	  public String update(@Validated @ModelAttribute ContactUpdateRequest contactUpdateRequest, BindingResult result, Model model) {

	    if (result.hasErrors()) {
	      List<String> errorList = new ArrayList<String>();

	      for (ObjectError error : result.getAllErrors()) {
	        errorList.add(error.getDefaultMessage());
	      }
	      model.addAttribute("validationError", errorList);
	      return "admin/edit";
	    }
	    
	    contactServiceImple.update(contactUpdateRequest);
	    return String.format("redirect:/admin/%d", contactUpdateRequest.getId());
	  }
	
	@GetMapping("/admin/{id}/delete")
	public String delete(@PathVariable Long id, Model model) {
		contactServiceImple.delete(id);
		return "redirect:/admin/contact";
		}
}