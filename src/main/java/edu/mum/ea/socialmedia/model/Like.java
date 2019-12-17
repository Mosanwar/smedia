package edu.mum.ea.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "[Like]")
public class Like {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    @NotNull
    @JsonIgnore
    private Post post;
}
