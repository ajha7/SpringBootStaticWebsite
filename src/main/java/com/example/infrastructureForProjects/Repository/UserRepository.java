package com.example.infrastructureForProjects.Repository;

import com.example.infrastructureForProjects.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // @Query(value = "SELECT username FROM usersDB WHERE username=:name", nativeQuery = true)
    // String findUsername(@Param("name") String name);

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT hashed_password FROM usersDB WHERE hashed_password=:hashedPassword", nativeQuery = true)
    String findHash(@Param("hashedPassword") String hashedPassword);

    //@Query(value = "SELECT hashed_password FROM usersDB WHERE username=:name", nativeQuery = true)
    // String findHashByUsername(@Param("name") String name);
}
