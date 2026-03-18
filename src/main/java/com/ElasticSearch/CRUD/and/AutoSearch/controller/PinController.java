package com.ElasticSearch.CRUD.and.AutoSearch.controller;

import com.ElasticSearch.CRUD.and.AutoSearch.model.PinTags;
import com.ElasticSearch.CRUD.and.AutoSearch.service.PinTagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PinController {
    @Autowired
    PinTagServiceImpl pinTagService;

    @PostMapping("/insert")
    ResponseEntity<String>  insertPin(@RequestBody PinTags pinTags){
        pinTagService.insertPin(pinTags);
        return ResponseEntity.ok("Pin Added");
    }

    @GetMapping("/pins")
    List<PinTags> getAllPins(){
        return pinTagService.getAllPins();
    }

    @GetMapping("/pin/{id}")
    PinTags getPin(@PathVariable Integer id){
        return pinTagService.getPin(id);
    }

    @DeleteMapping("/del/{id}")
    ResponseEntity<Boolean> deletePin(@PathVariable Integer id){
        return ResponseEntity.ok(pinTagService.deletePin(id));
    }

    @GetMapping("/search")
    List<PinTags> searchPins(@RequestParam String keyword){
        return pinTagService.searchPins(keyword);
    }
}
