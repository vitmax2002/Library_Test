package com.esempla.library.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity

@Table(name="user1")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username",length = 100)
    private String username;
    @Column(name="password",length=100)
    private String password;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="authority_name",referencedColumnName = "name"))
    private Authority authority;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Authority authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public User() {

    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority.getName().name()));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString()
    {
        return "id: "+id+" userName: "+username+" password: "+password;
    }

    @Override
    public int hashCode() {
        return  id* username.hashCode()* password.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if(this==object) return true;
        if(!(object instanceof User)) return false;
        User user=(User) object;
        return this.id==user.id && this.username.equals(user.username) && this.password.equals(user.password);
    }
}
