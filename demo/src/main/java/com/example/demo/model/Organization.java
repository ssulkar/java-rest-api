package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organization")
public class Organization{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Organizations are unique brands.
     */
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    @Size(max = 100)
    private String phone;

    /**
     * Many-to-many relationship between organizations and users.
     * Organizations are the owners of the relationship.
     * The two tables are joined by a table called organization_user.
     * This table contains foreign keys from organization and users.
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "organization_user",
            joinColumns = { @JoinColumn(name = "organization_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    @JsonIgnoreProperties("organizations")
    private Set<User> users = new HashSet<>();

    public Organization() {

    }

    public Organization(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * This is a helper function to delete users from a database that are associated with organizations.
     * This function will remove any instance of the user from this organization.
     */
    public void removeUser(User user){
        if(users.contains(user)){
            users.remove(user);
        }
    }
}