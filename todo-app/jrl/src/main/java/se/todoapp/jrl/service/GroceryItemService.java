package se.todoapp.jrl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import se.todoapp.jrl.model.GroceryItems;
import se.todoapp.jrl.repository.GroceryItemsRepository;

@Component
public class GroceryItemService {

	private final GroceryItemsRepository groceryItemsRepository;
	private final ServiceTransaction executor;

	@Autowired
	public GroceryItemService (GroceryItemsRepository groceryItemsRepository, ServiceTransaction executor) {
		this.groceryItemsRepository = groceryItemsRepository;
		this.executor = executor;
	
	}
	
	public GroceryItems addGrocery (GroceryItems groceryItems) throws ServiceException {
		try {
			return executor.execute(() -> {
				return groceryItemsRepository.save(groceryItems);
			});
		} catch (DataAccessException e) {
			throw new ServiceException("Could not add a new grocery to cart", e);
		}
  		
	}
}
 
