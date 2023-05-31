package library.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import library.model.Publisher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
//    @Query(value = "select name from publisher where name =:nume", nativeQuery = true)
//    String findByName(@Param("nume") String nume);

   void deleteByName(String name);

   boolean existsByName(String name);
   boolean existsById(int id);


    Optional<Publisher> findByName(String name);
}
