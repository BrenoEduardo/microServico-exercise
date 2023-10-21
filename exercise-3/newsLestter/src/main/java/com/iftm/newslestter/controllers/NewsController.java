package com.iftm.newslestter.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iftm.newslestter.services.NewsService;
import com.iftm.newslestter.models.dtos.NewsDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/news")
public class NewsController {

    @Autowired
    private NewsService service;

    @Value("${eureka.instance.instance-id}")
    private String instaceId;

    @GetMapping
    public ResponseEntity<List<NewsDTO>> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<NewsDTO> findById(@PathVariable("id") final ObjectId id) {
        return service.findById(id);
    }

    @GetMapping("/instance")
    public String getInstanceId() {
        return instaceId;
    }

    @PostMapping
    public ResponseEntity<NewsDTO> create(@RequestBody final NewsDTO newsDTO) {
        return service.save(newsDTO);
    }

    @PutMapping
    public ResponseEntity<NewsDTO> update(@RequestBody final NewsDTO newsDTO) {
        return service.update(newsDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final ObjectId id) {
        return service.delete(id);
    }
}
