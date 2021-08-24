package com.dxc.spring.VendorSystem.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.spring.VendorSystem.model.Item;
import com.dxc.spring.VendorSystem.model.Vendor;
import com.dxc.spring.VendorSystem.serviceRepo.ItemRepo;
import com.dxc.spring.VendorSystem.serviceRepo.VendorRepo;
import com.dxc.spring.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/vendor")
public class VendorController {
	@Autowired
	public VendorRepo ven;
	@Autowired
	public ItemRepo ir;

	@PostMapping("/insert")
	public Vendor insert(@RequestBody Vendor v) {
		return ven.save(v);
	}

	@GetMapping("/fetch")
	public List<Vendor> findAllVendors() {
		return (List<Vendor>) ven.findAll();
	}

//	@GetMapping("/find/{id}")
//	public List<Vendor> findAllVendors(@PathVariable int id) {
//		return (List<Vendor>) ven.findAllById(Collections.singleton(id));
//	}
	
	// get employee by id rest api
	@GetMapping("/find/{id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable int id) {
		Vendor vendor= ven.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor not exist with id :" + id));
		return ResponseEntity.ok(vendor);
	}

//	@GetMapping("/delete/{id}")
//	public List<Vendor> deleteVendor(@PathVariable int id) {
//		List<Item> items = ir.findByVendorId(id);
//		for (Item item : items) {
//			int itemId = item.getItemId();
//			ir.deleteById(itemId);
//		}
//		ven.deleteById(id);
//		return (List<Vendor>) ven.findAll();
//	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteVendor(@PathVariable int id){
		Vendor vendor = ven.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor not exist with id :" + id));
		List<Item> items = ir.findByVendorId(id);
		for (Item item : items) {
			int itemId = item.getItemId();
			ir.deleteById(itemId);
		}
		ven.delete(vendor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
