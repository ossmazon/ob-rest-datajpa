package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.entities.Laptop;
import com.example.obrestdatajpa.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private  final Logger log= LoggerFactory.getLogger(BookController.class);
    private LaptopRepository laptopRepository;

    public LaptopController( LaptopRepository laptopRepository){this.laptopRepository= laptopRepository;}


    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
     return laptopRepository.findAll();
    }

    @GetMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop>findById(@PathVariable Integer id){
        Optional<Laptop> laptopOpt= laptopRepository.findById(id);

        if(laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());

        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop>create(@RequestBody Laptop laptop){

        if (laptop.getId()!=null){
            log.warn("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop>update(@RequestBody Laptop laptop){

        if(laptop.getId()==null){
            log.warn("Trying to update a non existing book");
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())){
            log.warn("Trying to update a non existing book");
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Book> delete(@PathVariable Integer id){
        if(!laptopRepository.existsById(id)){
            log.warn("Trying to delete a non existing book");
            return ResponseEntity.notFound().build();

        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptops")
    public ResponseEntity<Book> deleteAll(){

        log.info("Rest Request for deleting all books");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
