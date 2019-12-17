package edu.mum.ea.socialmedia.repository;

import edu.mum.ea.socialmedia.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleName);
    Optional<Role> findByPrivileges_Name(String name);
}
