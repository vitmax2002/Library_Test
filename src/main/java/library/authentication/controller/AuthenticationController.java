package library.authentication.controller;

import library.authentication.AuthenticationDto;
import library.authentication.AuthenticationResponse;
import library.authentication.RegisterDto;
import library.authentication.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {
    private Logger log= LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto request)
    {
        log.debug("Response {}",request);
   return ResponseEntity.ok(service.register(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id)
    {
        return new ResponseEntity<>(service.deleteUser(id),HttpStatus.ACCEPTED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationDto request)
    {
        log.debug("Account {}",request);
    return ResponseEntity.ok(service.authenticate(request));
    }
}
