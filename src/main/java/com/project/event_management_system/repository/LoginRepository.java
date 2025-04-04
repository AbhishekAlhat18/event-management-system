package com.project.event_management_system.repository;

import com.project.event_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {
    //get all user by email
    public Optional<User> findByEmail(String email);

//    @Query("SELECT u FROM Registration u WHERE u.email = :email")
//    public List<Person> getByEmail(@Param("email") String email);
//
//    @Query("SELECT u FROM Registration u WHERE u.email = :email AND u.password=:password")
//    public  Person getDataForAuthentication(@Param("email") String email, @Param("password") String password);

}
