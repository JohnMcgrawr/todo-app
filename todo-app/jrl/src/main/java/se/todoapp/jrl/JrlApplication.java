package se.todoapp.jrl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import se.todoapp.jrl.model.GroceryItems;
import se.todoapp.jrl.repository.GroceryItemsRepository;
import se.todoapp.jrl.service.GroceryItemService;
import se.todoapp.jrl.service.ServiceException;

@SpringBootApplication
public class JrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(JrlApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) throws ServiceException {
		return args -> {
				
				GroceryItemService groceryItemService = context.getBean(GroceryItemService.class);
//				groceryItemService.addGrocery(new GroceryItems("item1"));
//				groceryItemService.deleteByName(new GroceryItems("item1"));
 				System.out.println("Working...");

		};
	}
}


