package edu.mum.ea.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.ea.socialmedia.model.Advertisment;

import java.util.List;

@Repository
public interface AdvertismentRepository extends JpaRepository<Advertisment, Long>{

    @Query("select a from Advertisment a where a.minAge < :age and a.maxAge > :age and a.targetAddress = :city" )
    List<Advertisment> getAdsByUser(Integer age, String city);
}
