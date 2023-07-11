package com.esempla.library.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="publisher")
public class Publisher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher() {
    }



    @Override
    public String toString()
    {
        return "id: "+id+" name: "+name;
    }

    @Override
    public int hashCode() {
        return  id* name.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if(this==object) return true;
        if(!(object instanceof Publisher)) return false;
        Publisher publisher=(Publisher) object;
        return this.id==publisher.id && this.name.equals(publisher.name);
    }
}
