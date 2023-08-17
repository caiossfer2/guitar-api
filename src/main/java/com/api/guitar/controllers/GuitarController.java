package com.api.guitar.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.guitar.dtos.GuitarDto;
import com.api.guitar.models.GuitarModel;
import com.api.guitar.services.GuitarService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/guitar")
public class GuitarController {

	final GuitarService guitarService;

	public GuitarController(GuitarService guitarService) {
		//super();
		this.guitarService = guitarService;
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid GuitarDto guitarDto){
		var guitarModel = new GuitarModel();
        BeanUtils.copyProperties(guitarDto, guitarModel);
        guitarModel.setRegistrationDateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(guitarService.save(guitarModel));
	    
	}
	
	@GetMapping
	public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(guitarService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
		Optional<GuitarModel> guitarModelOptional = guitarService.findById(id);
		if (!guitarModelOptional.isPresent()) {
	       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guitar not found.");
	    }
		return ResponseEntity.status(HttpStatus.OK).body(guitarModelOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> edit(@RequestBody @Valid GuitarDto guitarDto, @PathVariable(value = "id") UUID id){
		Optional<GuitarModel> guitarModelOptional = guitarService.findById(id);
        if (!guitarModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guitar not found.");
        }
        var guitarModel = new GuitarModel();
        BeanUtils.copyProperties(guitarDto, guitarModel);
        guitarModel.setId(guitarModelOptional.get().getId());
        guitarModel.setRegistrationDateTime(guitarModelOptional.get().getRegistrationDateTime());
        return ResponseEntity.status(HttpStatus.OK).body(guitarService.save(guitarModel));	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
		Optional<GuitarModel> guitarModelOptional = guitarService.findById(id);
		if (!guitarModelOptional.isPresent()) {
	       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guitar not found.");
	    }
		guitarService.delete(guitarModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Guitar deleted successfully.");	}
	
}


	