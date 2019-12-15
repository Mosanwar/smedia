package edu.mum.ea.socialmedia.repository;

import edu.mum.ea.socialmedia.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
