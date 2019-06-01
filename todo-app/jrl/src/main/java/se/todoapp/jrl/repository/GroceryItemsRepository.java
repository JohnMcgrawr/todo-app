package se.todoapp.jrl.repository;

import org.springframework.data.repository.CrudRepository;

import se.todoapp.jrl.model.GroceryItems;

public interface GroceryItemsRepository extends CrudRepository <GroceryItems, Long> {

 
}
