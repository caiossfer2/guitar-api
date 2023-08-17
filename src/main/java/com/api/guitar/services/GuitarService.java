package com.api.guitar.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.guitar.models.GuitarModel;
import com.api.guitar.repositories.GuitarRepository;

import jakarta.transaction.Transactional;

@Service
public class GuitarService {

	final GuitarRepository guitarRepository;

	public GuitarService(GuitarRepository guitarRepository) {
		//super();
		this.guitarRepository = guitarRepository;
	}
	
	@Transactional
	public GuitarModel save(GuitarModel guitarModel) {
		return guitarRepository.save(guitarModel);
	}
	
	public List<GuitarModel> findAll() {
		return guitarRepository.findAll();
	}
	
	public Optional<GuitarModel> findById(UUID id) {
		return guitarRepository.findById(id);
	}
	
	@Transactional
    public void delete(GuitarModel guitarModel) {
        guitarRepository.delete(guitarModel);
    }
	
	
	 
	 
}
