package edu.mum.ea.socialmedia.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.mum.ea.socialmedia.model.Advertisment;
import edu.mum.ea.socialmedia.repository.AdvertismentRepository;

@Service
@Transactional
public class AdvertismentServiceImpl implements AdvertismentService{

	@Autowired
	private AdvertismentRepository advertismentRepository;
	@Override
	public Advertisment saveAdvertisment(Advertisment advertisment) {
		// TODO Auto-generated method stub
		
		return advertismentRepository.save(advertisment);
	}

	@Override
	public Advertisment getAdvertisment(Long id) {
		// TODO Auto-generated method stub
		return advertismentRepository.getOne(id);
	}

}
