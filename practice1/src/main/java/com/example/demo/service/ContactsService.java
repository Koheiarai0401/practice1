package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ContactsUpdateRequest;
import com.example.demo.entity.Contacts;
import com.example.demo.repository.ContactsRepository;

@Service
public class ContactsService {
	@Autowired
	private ContactsRepository contactsRepository;
	
	public List<Contacts> searchAll() {
		return contactsRepository.findAll();
	}
	
	public Contacts findById(Long id) {
		return contactsRepository.findById(id).get();
	}
	
	public void update(ContactsUpdateRequest contactsUpdateRequest) {
		Contacts contacts = findById(contactsUpdateRequest.getId());
		contacts.setFirstName(contactsUpdateRequest.getFirstName());
		contacts.setLastName(contactsUpdateRequest.getLastName());
		contacts.setEmail(contactsUpdateRequest.getEmail());
		contacts.setPhone(contactsUpdateRequest.getPhone());
		contacts.setZipCode(contactsUpdateRequest.getZipCode());
		contacts.setAddress(contactsUpdateRequest.getAddress());
		contacts.setBuildingName(contactsUpdateRequest.getBuildingName());
		contacts.setContactType(contactsUpdateRequest.getContactType());
		contacts.setBody(contactsUpdateRequest.getBody());
		contactsRepository.save(contacts);
		}
	
	 public void delete(Long id) {
		 Contacts contacts = findById(id);
		 contactsRepository.delete(contacts);
		 }
}
