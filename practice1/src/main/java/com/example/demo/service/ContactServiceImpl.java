package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactUpdateRequest;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public void update(ContactUpdateRequest contactUpdateRequest) {
		Contact contact = findById(contactUpdateRequest.getId());
        contact.setLastName(contactUpdateRequest.getLastName());
        contact.setFirstName(contactUpdateRequest.getFirstName());
        contact.setEmail(contactUpdateRequest.getEmail());
        contact.setPhone(contactUpdateRequest.getPhone());
        contact.setZipCode(contactUpdateRequest.getZipCode());
        contact.setAddress(contactUpdateRequest.getAddress());
        contact.setBuildingName(contactUpdateRequest.getBuildingName());
        contact.setContactType(contactUpdateRequest.getContactType());
        contact.setBody(contactUpdateRequest.getBody());

        contactRepository.save(contact);
    }
	
    @Autowired
    private ContactRepository cpmtactRepository;
    
	public List<Contact> searchAll() {
		return contactRepository.findAll();
	}

	public Contact findById(Long id) {
		return contactRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		 Contact contact = findById(id);
		 contactRepository.delete(contact);
		 }
}


