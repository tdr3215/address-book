package com.techtalentsouth.AddressBook;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.techtalentsouth.AddressBook.domain.Address;
import com.techtalentsouth.AddressBook.repository.AddressRepository;

@SpringBootApplication
public class AddressBookApplication {
	
	private static final Logger log = LoggerFactory.getLogger(AddressBookApplication.class);

	public static void main(String[] args) {
		
		SpringApplication.run(AddressBookApplication.class, args);
	
		
		
	}
	
	
	@Bean
	CommandLineRunner start(AddressRepository repository) {
		return (args) -> {
			Scanner scanner = new Scanner(System.in);


			Boolean quit = false;
			
			
			while (!quit) {
				String prompt = "1) Add an entry\n2) Remove an entry\n3) Search for a specific entry\n4) Print Address Book\n5) Delete Book\n6) Quit";
				System.out.println("Welcome to our address book.");
				System.out.println(prompt);

				Integer userInput = scanner.nextInt();


				if (userInput == 1) {
					// TODO: Add an entry
					
					System.out.println("First Name: ");
					String firstName = scanner.next();
					
					System.out.println("Last Name: ");
					String lastName = scanner.next();
					
					System.out.println("Phone Number: ");
					String phoneNumber = scanner.next();
					
					System.out.println("Email Address: ");
					String emailAddress = scanner.next();
					Address address = new Address(firstName,lastName,phoneNumber,emailAddress);
					
					repository.save(address);
					repository.findAll().forEach((a) -> {
						System.out.println(a.toString());
					});
					System.out.println("Added new entry!");
					continue;
				} else if(userInput == 2) {
//					TODO: Delete Entry
					System.out.println("Enter an entry's email to remove: ");
					String entry = scanner.next();
					
			
					Boolean emailFound = false;
					for(Address a : repository.findAll()) {
						if(a.getEmailAddress().equals(entry)) {
							emailFound = true;
							repository.deleteById(a.getId());
							System.out.println("Deleted Entry!");
							continue;
						}	
					}
					
					if(!emailFound) {
						System.out.println("Couldn't find that email");
					}
					continue;
				} else if(userInput == 3) {
//					
					System.out.println("1) First Name\n2) Last Name\n3) Phone Number\n4) Email Address");
					System.out.println("Choose a search type");
					Boolean found = false;
					Integer choice = scanner.nextInt();
					
					if (choice > 0 && choice < 5) {
						
						System.out.println("Enter your search: ");
						String req = scanner.next();
						

						for (Address a : repository.findAll()) {
							
							if(choice == 1) {
								if(a.getFirstName().contains(req)) {
									found = true;
									a.printAddress();
									continue;
								}
							} else if(choice == 2) {
								if(a.getLastName().contains(req)) {
									found = true;
									a.printAddress();
									continue;
								}
							} else if(choice == 3) {
								if(a.getPhoneNumber().contains(req)) {
									found =true;
									a.printAddress();
									continue;
								} 
							} else if(choice == 4) {
								if(a.getEmailAddress().contains(req)) {
									found = true;
									a.printAddress();
									continue;
								} 
							}
						}
						
						if(!found) {
							System.out.println("Invalid search query");
						}
						continue;
					}
					
				} else if(userInput == 4) {
//					TODO: Print address book
					
					repository.findAll().forEach(a-> a.printAddress());
					continue;
				} else if(userInput == 5) {
					repository.deleteAll();
					System.out.println("Address Book Cleared");
					continue;
				}
				
				else if (userInput == 6) {
					quit = true;
					System.out.println("Goodbye!");
					continue;
					
				}
			}
			System.exit(0);
		};
	}

}
