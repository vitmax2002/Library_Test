package library.model;

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
    private Authority2 name;

    @OneToMany(mappedBy = "authority",fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users=new HashSet<>();

    public Authority() {
    }

    public Authority(Authority2 name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public Authority2 getName() {
        return name;
    }

    public void setName(Authority2 name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
