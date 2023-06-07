package com.esempla.library.repository;

import com.esempla.library.model.Authority;
import com.esempla.library.model.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityEnum> {

    Optional<Authority> findByName(AuthorityEnum authority);
}
