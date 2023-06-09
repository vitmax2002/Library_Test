package com.esempla.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="authority")
public class Authority {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name="name",length = 50)
    private AuthorityEnum name;

    @OneToMany(mappedBy = "authority",fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users=new HashSet<>();

    public Authority() {
    }

    public Authority(AuthorityEnum name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public AuthorityEnum getName() {
        return name;
    }

    public void setName(AuthorityEnum name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public String toString()
    {
        return "name: "+name;
    }

    @Override
    public int hashCode() {
        return  name.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if(this==object) return true;
        if(!(object instanceof Authority)) return false;
        Authority authority=(Authority) object;
        return  this.name.equals(authority.name);
    }

}
