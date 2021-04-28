package se.lexicon.pet_clinic.service;

import se.lexicon.pet_clinic.dto.PetDto;
import se.lexicon.pet_clinic.exception.DataNotFoundException;

import java.util.List;

public interface PetService {
    // to do: define SAVE, UPDATE, DELETE, Find All, FIND BY ID, find By Name, find By PetTypeName, find By OwnerFirstNameAndLastName, find By OwnerTelephone

    PetDto save(PetDto dto);

    PetDto update(PetDto dto) throws DataNotFoundException;

    void deleteById(String id);

    List<PetDto> findAll();

    PetDto findById(String id) throws DataNotFoundException;

    List<PetDto> findByName(String petName) throws DataNotFoundException;

    List<PetDto> findByPetTypeName(String petTypeName) throws DataNotFoundException;

    List<PetDto> findByOwnerFirstNameAndLastName(String firstName, String lastname) throws DataNotFoundException;

    List<PetDto> findByOwnerTelephone(String telephone) throws DataNotFoundException;
}
