package com.example.demo.repository;
import com.example.demo.model.Organization;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.organizations FROM User u WHERE u.id = :id")
    Set<Organization> getOrganizationsByUserId(@Param("id") long id);
}