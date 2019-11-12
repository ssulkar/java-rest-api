package com.example.demo.repository;
import com.example.demo.model.Organization;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("SELECT o.users FROM Organization o WHERE o.id = :id")
    Set<User> getUsersByOrganizationId(@Param("id") long id);
}