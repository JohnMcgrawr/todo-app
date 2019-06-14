package se.todoapp.jrl.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;

import se.todoapp.jrl.model.GroceryItems;

@RepositoryRestResource

public interface GroceryItemsRepository extends PagingAndSortingRepository<GroceryItems, Long> {
	
	GroceryItems save (String string);
	
	List<GroceryItems> findAll();
	
	Long deleteBygroceryName (String item);

	GroceryItems findById(String grocery);
 
}
