package com.ruben.sampleproject.plants.repositories;

import com.ruben.sampleproject.plants.entities.Adventure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AdventureRepository extends CrudRepository<Adventure, Integer> {
    public List<Adventure> findByCountry(String country);
    public List<Adventure> findByState(String state);
    public Iterable<Adventure> findAll();
    public Optional<Adventure> findById(Integer id);
}