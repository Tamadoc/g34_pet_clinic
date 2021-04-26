package se.lexicon.pet_clinic.service;

import se.lexicon.pet_clinic.dto.OwnerDto;
import se.lexicon.pet_clinic.exception.DataNotFoundException;

import java.util.List;

public interface OwnerService {
    OwnerDto save(OwnerDto dto);

    OwnerDto update(OwnerDto dto) throws DataNotFoundException;

    OwnerDto findById(String id) throws DataNotFoundException;

    void deleteById(String id);

    List<OwnerDto> getAll();

    List<OwnerDto> advanceSearch(String firstName, String lastname);
}
