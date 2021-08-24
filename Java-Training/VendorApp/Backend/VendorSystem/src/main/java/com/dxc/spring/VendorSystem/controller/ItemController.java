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
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/item")
public class ItemController {
	@Autowired
	public ItemRepo ir;
	@Autowired
	public VendorRepo ven;
	
	@PostMapping("/insert")
	public Item insert(@RequestBody Item i) {
		return ir.save(i);
	}
	
	@GetMapping("/fetch")
	public List<Item> findAllItems() {
		return (List<Item>) ir.findAll();
	}
	
	@GetMapping("/fetch/{id}") 
	public List<Item> findItemsByVendorId(@PathVariable int id) {
		return (List<Item>) ir.findByVendorId(id);
	}
	
//	@GetMapping("/find/{id}")
//	public List<Item> findAllItems(@PathVariable int id) {
//		return (List<Item>) ir.findAllById(Collections.singleton(id));
//	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable int id) {
		Item item= ir.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not exist with id :" + id));
		return ResponseEntity.ok(item);
	}
	
//	@GetMapping("/delete/{id}")
//	public List<Item> deleteItem(@PathVariable int id) {
//		ir.deleteById(id);
//		return (List<Item>) ir.findAll();
//	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable int id){
		Item item = ir.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not exist with id :" + id));
		ir.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item itemDetails){
		Item item = ir.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not exist with id :" + id));
		
		item.setItemName(itemDetails.getItemName());
		
		Item updatedItem = ir.save(item);
		return ResponseEntity.ok(updatedItem);
	}
}
