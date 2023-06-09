package com.esempla.library.controller;

import com.esempla.library.model.Authority;
import com.esempla.library.repository.AuthorityRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authority")
public class AuthorityController {
    private Logger log= LoggerFactory.getLogger(AuthorityController.class);
    private final AuthorityRepository authorityRepository;

    public AuthorityController(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/add")
    public ResponseEntity<Authority> addAuthority(@RequestBody Authority authority)
    {
        log.debug("Rest request to view all authorities");
        return new ResponseEntity<>(authorityRepository.save(authority), HttpStatus.CREATED);
    }
}
