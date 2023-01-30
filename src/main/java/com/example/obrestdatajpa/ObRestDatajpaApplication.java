package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.entities.Laptop;
import com.example.obrestdatajpa.repository.BookRepository;
import com.example.obrestdatajpa.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;


@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository =context.getBean(BookRepository.class);

		LaptopRepository laptopRepository=context.getBean(LaptopRepository.class);
		//Crear una laptop
		Laptop laptop1 = new Laptop(1,"S21","Intell",250,1528.20,true);
		Laptop laptop2 = new Laptop(2,"S28","Lenovo",259,1999.20,false);
		//almacenar laptop
		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);
		System.out.println("Numero de laptops en la base de datos "+laptopRepository.findAll().size());

		//CRUD
		//crear libro
		Book book =new Book(1L,"Homo Deus","Yuval Noah",450,29.99, LocalDate.of(2018,12,1),true);
		Book book2 =new Book(2L,"Homo Deus","Yuval Noah",450,29.99, LocalDate.of(2018,12,1),true);
		//almacenar libro

		System.out.println("Numero de libros en base de datos "+repository.findAll().size());
		repository.save(book);
		repository.save(book2);

		//recuperar libro
		System.out.println("Numero de libros en base de datos "+repository.findAll().size());

		//boorar libro
//		repository.deleteById(1L);

		System.out.println("Numero de libros en base de datos "+repository.findAll().size());
	}


}
