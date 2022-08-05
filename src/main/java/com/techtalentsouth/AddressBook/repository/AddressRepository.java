package com.techtalentsouth.AddressBook.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techtalentsouth.AddressBook.domain.Address;


public interface AddressRepository extends CrudRepository<Address,Long>{
	
}
