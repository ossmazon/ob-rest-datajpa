package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Laptop;
import com.example.obrestdatajpa.repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private LaptopRepository laptopRepository;

    public LaptopController( LaptopRepository laptopRepository){this.laptopRepository= laptopRepository;}


    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
     return laptopRepository.findAll();
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop>create(@RequestBody Laptop laptop){
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }
}
