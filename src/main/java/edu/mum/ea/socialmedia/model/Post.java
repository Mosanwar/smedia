package edu.mum.ea.socialmedia.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Post extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty

    private String body;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post")
    private Set<Like> likes;

    private Boolean malicious;

    private Boolean disabled;
//     private
}
