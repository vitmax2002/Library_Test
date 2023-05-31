package library.authentication.service;

import library.authentication.AuthenticationRequest;
import library.authentication.AuthenticationResponse;
import library.authentication.RegisterRequest;
import library.model.Authority;
import library.model.Authority2;
import library.model.User;
import library.repository.AuthorityRepository;
import library.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthenticationService {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public AuthenticationService(AuthorityRepository authorityRepository,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager=authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        User user=new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Authority2 authority2=null;

        for (Authority2 au : Authority2.values()) {
            if (au.name().equals(request.getAuthority())) {
                authority2 = au;
                break;
            }
        }
        if(authority2==null)
            throw new NoSuchElementException("Nu este asa element in Enumeratie");
        Authority authority=authorityRepository.findByName(authority2)
                                               .orElseThrow(()->new NoSuchElementException("Nu exista asa element"));
        user.setAuthority(authority);
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public String deleteUser(int id)
    {
       User user = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("Nu exista element cu asa id"));
       String s=user.getUsername();
       if(user.getAuthority()!=null)
           user.setAuthority(null);
     userRepository.deleteById(id);
     return "Accountul cu username:"+s+" a fost sters";
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),request.getPassword()));
        var user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
