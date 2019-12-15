package edu.mum.ea.socialmedia.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AssignRolesData {

    private Long targetUser;

    private Set<Role> roles;
}
