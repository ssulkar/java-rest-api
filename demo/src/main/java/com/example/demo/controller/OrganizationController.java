package com.example.demo.controller;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Organization;
import com.example.demo.model.User;
import com.example.demo.repository.OrganizationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/organizations")
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @GetMapping("/organizations/{organizationId}")
    public Optional<Organization> getOrganizationById(@PathVariable("organizationId") long organizationId){
        return organizationRepository.findById(organizationId);
    }

    @GetMapping("/organizations/{organizationId}/users")
    public Set<User> getAllUsersByOrganizationId(@PathVariable (value = "organizationId") Long organizationId) {
        return organizationRepository.getUsersByOrganizationId(organizationId);
    }

    @PostMapping("/organizations")
    public Organization createOrganization(@Valid @RequestBody Organization organization) {
        return organizationRepository.save(organization);
    }

    /**
     * This function adds a user to an organization.
     * A row is created in the join table that contains the organization id and user id.
     */
    @PutMapping("/organizations/{organizationId}/users/{userId}")
    public Organization addUserToOrganization(@PathVariable (value = "userId") Long userId,
                                      @PathVariable (value = "organizationId") Long organizationId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        if(!organizationRepository.existsById(organizationId)) {
            throw new ResourceNotFoundException("OrganizationId " + organizationId + " not found");
        }
        Organization organization = organizationRepository.findById(organizationId).get();
        User user = userRepository.findById(userId).get();
        if(organization.getUsers().contains(user) || user.getOrganizations().contains(organization)){
            throw new ResourceNotFoundException("UserId " + userId + " already in organization.");
        }
        else {
            organization.getUsers().add(user);
            user.getOrganizations().add(organization);
            organizationRepository.save(organization);
            userRepository.save(user);
        }
        return organization;
    }

    /**
     * This function deletes the organization from the database.
     * All references of the organization are removed from users.
     * All rows that contain the organization in the join table are deleted.
     * The users associated with the organization still persist.
     * Organizations are the owners of the many-to-many relationships.
     */
    @DeleteMapping("/organizations/{organizationId}")
    public ResponseEntity<?> deleteOrganization(@PathVariable Long organizationId) {
        return organizationRepository.findById(organizationId).map(organization -> {
            organizationRepository.delete(organization);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("OrganizationId " + organizationId + " not found"));
    }

    /**
     * This function removes a user from an organization.
     * The user is not delete from the database.
     * The row that contains the organization and user is deleted from the join table.
     */
    @DeleteMapping("/organizations/{organizationId}/users/{userId}")
    public Organization deleteUserFromOrganization(@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "organizationId") Long organizationId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        if(!organizationRepository.existsById(organizationId)) {
            throw new ResourceNotFoundException("OrganizationId " + organizationId + " not found");
        }
        Organization organization = organizationRepository.findById(organizationId).get();
        User user = userRepository.findById(userId).get();
        if(organization.getUsers().contains(user) && user.getOrganizations().contains(organization)){
            organization.getUsers().remove(user);
            user.getOrganizations().remove(organization);
            organizationRepository.save(organization);
            userRepository.save(user);
        }
        else {
            throw new ResourceNotFoundException("UserId " + userId + " not found in organization.");
        }
        return organization;
    }
}