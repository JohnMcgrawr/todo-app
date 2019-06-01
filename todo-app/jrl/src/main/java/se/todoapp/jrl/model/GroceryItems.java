package se.todoapp.jrl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GroceryItems {

	@Id
	@GeneratedValue
	private Long id;

	private String groceryName;

	protected GroceryItems() {

	}

	public GroceryItems(String groceryName) {
		this.groceryName = groceryName;
	}

	public Long getId() {
		return id;
	}

	public String getGroceryName() {
		return groceryName;
	}

	@Override
	public String toString() {
		return "GroceryItems [id= " + id + ", groceryName= " + groceryName + "]";
	}
	
	

}
