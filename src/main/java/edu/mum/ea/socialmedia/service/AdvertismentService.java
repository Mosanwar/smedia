package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.Advertisment;

public interface AdvertismentService {
	
	//save Ad
	Advertisment saveAdvertisment(Advertisment advertisment);
	
	//get Ad
	Advertisment getAdvertisment(Long id);
}
