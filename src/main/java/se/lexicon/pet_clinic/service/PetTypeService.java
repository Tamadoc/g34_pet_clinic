package se.lexicon.pet_clinic.service;

import se.lexicon.pet_clinic.dto.PetTypeDto;
import se.lexicon.pet_clinic.exception.DataNotFoundException;

import java.util.List;

public interface PetTypeService {
    // to do: define SAVE, UPDATE, DELETE, Find All, FIND BY ID

    PetTypeDto save(PetTypeDto dto);

    PetTypeDto update(PetTypeDto dto) throws DataNotFoundException;

    void deleteById(int id);

    List<PetTypeDto> findAll();

    PetTypeDto findById(int id) throws DataNotFoundException;

}
