package com.esempla.library.controller;

import com.esempla.library.model.Authority;
import com.esempla.library.repository.AuthorityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Authority> addAuthority(@RequestBody Authority authority)
    {
        return new ResponseEntity<>(authorityRepository.save(authority), HttpStatus.CREATED);
    }
}
