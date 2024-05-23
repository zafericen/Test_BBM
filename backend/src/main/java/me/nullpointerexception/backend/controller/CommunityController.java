package me.nullpointerexception.backend.controller;

import me.nullpointerexception.backend.model.community.Community;
import me.nullpointerexception.backend.repository.CommunityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/community/{id}")
@RestController
public class CommunityController {

    CommunityRepository communityRepository;

    @GetMapping(value = "/get")
    public ResponseEntity<Object> getCommunity(@PathVariable String id){
        try {
            Community community = communityRepository.getCommunity(id);
            return new ResponseEntity<>(community, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Community not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get/{categoryId}")
    public ResponseEntity<Object> getCommunityForCategory(@PathVariable String categoryId){
        try {
            Community community = communityRepository.getCommunityForCategory(UUID.fromString(categoryId));
            return new ResponseEntity<>(community, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Community not found", HttpStatus.NOT_FOUND);
        }
    }
}
