package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private  final Logger log= LoggerFactory.getLogger(BookController.class);
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    /**
     * CRUD
     * Create
     * Retrive
     * Update
     * Delete
     * Find all books that exist in the database (ArrayList of books)
     * http://localhost:8080/api/books
     * @return
     */
    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();

    }

    /**
     * Search one book in database by id
     * http://localhost:8080/api/books/1
     * http://localhost:8080/api/books/2
     * http://localhost:8080/api/books/3
     * @param id
     * @return
     */

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable long id){
        Optional<Book> bookOpt = bookRepository.findById(id);
        //opcion1
        //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        //opcion2
        if(bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Create a new book in the database
     * There is no conflict with findAll() because these are different HTTP methods: Get vs Post
     * Save book received by parameters in the database
     * @param book
     * @return
     */
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book){

        if (book.getId()!=null){
            log.warn("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }


    /**
     * Update an existing book in the database
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){

     if(book.getId()==null){
         log.warn("Trying to update a non existing book");
         return ResponseEntity.badRequest().build();
     }
     if(!bookRepository.existsById(book.getId())){
         log.warn("Trying to update a non existing book");
         return ResponseEntity.notFound().build();
     }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);


    }

    /**
     * Delete a book
     */

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if(!bookRepository.existsById(id)){
            log.warn("Trying to delete a non existing book");
            return ResponseEntity.notFound().build();

        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll(){

        log.info("Rest Request for deleting all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
