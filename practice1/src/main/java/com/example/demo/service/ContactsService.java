package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
