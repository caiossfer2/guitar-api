package com.api.guitar.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.guitar.models.GuitarModel;

@Repository
public interface GuitarRepository extends JpaRepository<GuitarModel, UUID>{

}
