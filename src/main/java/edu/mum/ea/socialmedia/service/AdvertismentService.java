package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Advertisment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertismentService {
	
	//save Ad
	Advertisment saveAdvertisment(Advertisment advertisment);
	
	//get Ad
	Advertisment getAdvertisment(Long id);

	List<Advertisment> getAdsByUser(Integer age, String city);
}
