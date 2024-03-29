package edu.mum.ea.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    @NotEmpty
    private String name;


    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Integer age;
  @Column(nullable = false)
    private Boolean active = true;
  @Column(nullable = false)
    private Boolean blocked = false;

    private String city;
    @NotEmpty
    @Column(nullable = false)
    private String password;
    
    @Transient
    private MultipartFile image;
    
    private String imageURL;

    @ManyToMany
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(
                    name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "ID"))
    @NotNull
    private Set<Role> roles;

    @ManyToMany(
            cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=edu.mum.ea.socialmedia.model.User.class
    )
    @JoinTable(
            name="USER_FOLLOWINGS",
            joinColumns=@JoinColumn(name="USER_ID"),
            inverseJoinColumns=@JoinColumn(name="FOLLOWING_ID")
    )
    @JsonIgnore
    private Set<User> followings;

    @ManyToMany
    @JoinTable(name="USER_FOLLOWINGS",
            joinColumns=@JoinColumn(name="FOLLOWING_ID"),
            inverseJoinColumns=@JoinColumn(name="USER_ID")
    )
    private Set<User> followers;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, Integer age, String phoneNumber, String city) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }
}
