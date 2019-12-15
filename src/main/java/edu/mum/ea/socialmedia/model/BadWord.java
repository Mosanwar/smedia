package edu.mum.ea.socialmedia.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class BadWord {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String word;
}
