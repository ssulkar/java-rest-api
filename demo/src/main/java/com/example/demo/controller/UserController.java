package com.example.demo.controller;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Organization;
import com.example.demo.model.User;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") long userId){
        return userRepository.findById(userId);
    }

    @GetMapping("/users/{userId}/organizations")
    public Set<Organization> getAllOrganizationsByUserId(@PathVariable (value = "userId") Long userId) {
        return userRepository.getOrganizationsByUserId(userId);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }


    /**
     * This function removes a user from the database.
     * First it removes all references of the given user from all organizations.
     * This has to be done because the user is not the owner of the many-to-many relationship.
     */
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userRepository.findById(userId).map(user -> {
            for(Organization organization: user.getOrganizations()){
                organization.removeUser(user);
                organizationRepository.save(organization);
            }
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
    }
}