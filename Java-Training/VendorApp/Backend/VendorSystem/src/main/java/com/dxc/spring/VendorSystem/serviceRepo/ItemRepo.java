package com.dxc.spring.VendorSystem.serviceRepo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dxc.spring.VendorSystem.model.Item;

public interface ItemRepo extends CrudRepository<Item, Integer>{
	List<Item> findByVendorId(int vendorId);
}
