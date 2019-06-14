package se.todoapp.jrl.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.todoapp.jrl.model.GroceryItems;
import se.todoapp.jrl.repository.GroceryItemsRepository;

@Component
@RequestMapping("/")
@RestController
public class GroceryItemService {

	private final GroceryItemsRepository groceryItemsRepository;
	private final ServiceTransaction executor;

	@Autowired
	public GroceryItemService(GroceryItemsRepository groceryItemsRepository, ServiceTransaction executor) {
		this.groceryItemsRepository = groceryItemsRepository;
		this.executor = executor;
	}
	
	@GetMapping
	@RequestMapping("/testURL")
	public String hello() {
	return "Hello";
	}
	
	@PostMapping
	@Column (unique=true, nullable=false)
	@RequestMapping(value = "/additem/{grocery}", method = RequestMethod.POST)
	public GroceryItems addGrocery(@PathVariable String grocery, GroceryItems groceryItems) throws ServiceException  {
		GroceryItems gc = new GroceryItems(grocery);
		
		
		try {
		return executor.execute(() -> {
		 	
				return groceryItemsRepository.save(gc);
		});
		
	} catch (DataException e) {
		throw new ServiceException("Duplicate entry", e);
	}
	}
	
	@RequestMapping("/removeGrocery")
	public Long deleteByName(GroceryItems gc) throws ServiceException {
	try {
		return executor.execute(() ->{
			
		groceryItemsRepository.deleteBygroceryName(gc.getGroceryName());
		return groceryItemsRepository.deleteBygroceryName(gc.getGroceryName());
		});
		
	} catch (DataException e) {
		throw new ServiceException("Did not find the grocery", e);
		
	}
		 
	}
   

	@GetMapping
	@RequestMapping("/getitems")
	public List<GroceryItems> getListOfItemsInCart() {
		List<GroceryItems> gList = new ArrayList<>();
		gList = groceryItemsRepository.findAll();
		return gList;
	}
}
