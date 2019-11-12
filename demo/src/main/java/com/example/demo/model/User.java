package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String first;

    @NotNull
    @Size(max = 100)
    private String last;

    /**
     * user emails must be unique assuming they are used for registration.
     */
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    @Size(max = 100)
    private String phone;

    /**
     * A many-to-many relationship with organizations.
     * Organizations are not deleted if a user is deleted.
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "users")
    @JsonIgnoreProperties("users")
    private Set<Organization> organizations = new HashSet<>();

    public User(){

    }

    public User(String first, String last, String email, String address, String phone, String name) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }
}