package edu.mum.ea.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    private String email;

    private String firstName;

    private String phoneNumber;

    private String lastName;

    private Integer age;

    private Boolean active;

    private Boolean blocked;

    private String city;

    @ManyToMany
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(
                    name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "ID"))
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
}
