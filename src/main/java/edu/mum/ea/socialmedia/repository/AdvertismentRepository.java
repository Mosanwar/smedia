package edu.mum.ea.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ea.socialmedia.model.Advertisment;

@Repository
public interface AdvertismentRepository extends JpaRepository<Advertisment, Long>{

}
