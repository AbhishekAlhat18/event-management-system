package com.project.event_management_system.repository;

import com.project.event_management_system.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Registration, Integer> {
    //get all user by email
    public Registration findByEmail(String email);

    @Query("SELECT u FROM Registration u WHERE u.email = :email")
    public List<Registration> getByEmail(@Param("email") String email);

    @Query("SELECT u FROM Registration u WHERE u.email = :email AND u.password=:password")
    public  Registration getDataForAuthentication(@Param("email") String email, @Param("password") String password);
}
