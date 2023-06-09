package com.esempla.library.controller;

import com.esempla.library.authentication.AuthenticationResponse;
import com.esempla.library.service.AuthenticationService;
import com.esempla.library.service.dto.AuthenticationDto;
import com.esempla.library.service.dto.RegisterDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class AuthenticationController {
    private Logger log= LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto request)
    {
        log.debug("You have registered with{}",request);
   return ResponseEntity.ok(authenticationService.register(request));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id)
    {
        authenticationService.deleteUser(id);
        log.debug("User with id {} was deleted",id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationDto request)
    {
        log.debug("You have authenticated with {}",request);
    return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
