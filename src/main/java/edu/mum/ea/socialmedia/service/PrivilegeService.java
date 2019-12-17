package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Privilege;

import java.util.List;
import java.util.Optional;

public interface PrivilegeService {

    Privilege add(Privilege privilege);
    Optional<Privilege> findById(Long id);
    List<Privilege> findAll();
    void delete(Long id);

}
