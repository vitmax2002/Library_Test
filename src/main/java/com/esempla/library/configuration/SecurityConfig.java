package com.esempla.library.configuration;

import com.esempla.library.authentication.filter.JwtAuthenticationFilter;

import com.esempla.library.model.Authority;
import com.esempla.library.model.Roles;
import com.esempla.library.model.User;
import com.esempla.library.repository.UserRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserRepository userRepository;
    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    public SecurityConfig(UserRepository userRepository, AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userRepository = userRepository;
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/delete/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/authors/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/authority/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/books/add").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/books/update/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/books/delete/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/borrow/show").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/borrow/add").hasAnyAuthority(Roles.ADMINISTRATOR,Roles.LIBRARIAN)
                .requestMatchers("/api/v1/borrow/delete/**").hasAnyAuthority(Roles.ADMINISTRATOR,Roles.LIBRARIAN)
                .requestMatchers("/api/v1/client/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/publisher/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/user/delete/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/user/delete/**").hasAuthority(Roles.ADMINISTRATOR)
                .requestMatchers("/api/v1/user/delete/**").hasAuthority(Roles.ADMINISTRATOR)
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


//    @Bean("filterChain")
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .x509()
//                .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
//                .userDetailsService(userDetailsService());
//        return http.build();
//    }
//
//    @Bean("userDetails")
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                if (username.equals("Bob")) {
//                    return new User(username, "",
//                            (Authority) AuthorityUtils.commaSeparatedStringToAuthorityList("ADMINISTRATOR"));
//                }
//                throw new UsernameNotFoundException("User not found!");
//            }
//        };
//    }
}
