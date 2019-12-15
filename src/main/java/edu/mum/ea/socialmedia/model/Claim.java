package edu.mum.ea.socialmedia.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Claim extends AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String calimBody;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    public Claim(String calimBody){
        this.setCalimBody(calimBody);
    }

}
