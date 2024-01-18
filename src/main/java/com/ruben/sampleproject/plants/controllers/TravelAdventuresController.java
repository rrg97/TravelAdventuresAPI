package com.ruben.sampleproject.plants.controllers;

import com.ruben.sampleproject.plants.entities.Adventure;
import com.ruben.sampleproject.plants.repositories.AdventureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

    private final AdventureRepository adventureRepository;

    public TravelAdventuresController(AdventureRepository adventureRepo) {
        this.adventureRepository = adventureRepo;
    }

    // Add controller methods below:
    @GetMapping()
    @ResponseStatus(code=HttpStatus.OK)
    public Iterable<Adventure> getAdventures(){
        Iterable<Adventure> adventures = this.adventureRepository.findAll();

        return adventures;
    }

    @GetMapping("/bycountry/{country}")
    @ResponseStatus(code=HttpStatus.OK)
    public List<Adventure> getAdventuresByCountry(@PathVariable String country){
        List<Adventure> adventures = this.adventureRepository.findByCountry(country);

        if (adventures.size() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No items have been found");
        }

        return adventures;
    }

    @GetMapping("/bystate")
    @ResponseStatus(code=HttpStatus.OK)
    public List<Adventure> getAdventuresByState(@RequestParam String state){
        List<Adventure> adventures = this.adventureRepository.findByState(state);

        if (adventures.size() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No items have been found");
        }

        return adventures;
    }

    @PostMapping()
    @ResponseStatus(code=HttpStatus.CREATED, reason="Adventure created successfully!")
    public Adventure createNewAdventure(@RequestBody Adventure adventure){
        this.adventureRepository.save(adventure);

        return adventure;
    }

    @PutMapping("{id}")
    @ResponseStatus(code=HttpStatus.OK, reason="Adventure succesfully updated")
    public Adventure replaceAdventure(@RequestBody Adventure adventure, @PathVariable Integer id){
        Optional<Adventure> adventureOptional = this.adventureRepository.findById(id);

        if(!adventureOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No item has been found with the id provided");
        }
        this.adventureRepository.save(adventure);

        return adventure;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteAdventure(@PathVariable Integer id){
        Optional<Adventure> adventureOptional = this.adventureRepository.findById(id);

        if(!adventureOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No item has been found with the id provided");
        }

        Adventure adventure = adventureOptional.get();
        this.adventureRepository.delete(adventure);
    }

}

