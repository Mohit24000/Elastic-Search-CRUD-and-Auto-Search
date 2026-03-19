package com.ElasticSearch.CRUD.and.AutoSearch.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.ElasticSearch.CRUD.and.AutoSearch.model.PinTags;
import com.ElasticSearch.CRUD.and.AutoSearch.service.PinTagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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

    @GetMapping("/autoSuggest/{partialProductName}")
    List<String> autoSuggestPinSearch(@PathVariable("partialProductName") String partialName) throws IOException {
        SearchResponse<PinTags> searchResponse = pinTagService.autoSuggestPinTags(partialName);

        HitsMetadata<PinTags> meta = searchResponse.hits();
        List<Hit<PinTags>> hits = meta.hits();

        List<String> pinList = hits.stream()
                .filter(hit -> hit.source() != null && hit.source().getTags() != null)
                .flatMap(hit -> hit.source().getTags().stream())
                .distinct()
                .toList();
        ;

        return pinList;
    }

}
