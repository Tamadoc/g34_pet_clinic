package se.lexicon.pet_clinic.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.pet_clinic.dto.PetTypeDto;
import se.lexicon.pet_clinic.entity.PetType;
import se.lexicon.pet_clinic.exception.DataNotFoundException;
import se.lexicon.pet_clinic.repository.PetTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetTypeServiceImpl implements PetTypeService {
    // to do: implement PetTypeService Interface

    PetTypeRepository petTypeRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setPetTypeRepository(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PetTypeDto save(PetTypeDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("PetTypeDto object should not be null");
        }
        if (dto.getId() != 0) {
            throw new IllegalArgumentException("Id should be 0");
        }
        PetType petTypeEntity = modelMapper.map(dto, PetType.class);
        PetType savedPetTypeEntity = petTypeRepository.save(petTypeEntity);
        PetTypeDto convertEntityToDto = modelMapper.map(savedPetTypeEntity, PetTypeDto.class);
        return convertEntityToDto;
    }

    @Override
    public PetTypeDto update(PetTypeDto dto) throws DataNotFoundException {
        if (dto == null) {
            throw new IllegalArgumentException("PetTypDto object should not be null");
        }
        if (dto.getId() == 0) {
            throw new IllegalArgumentException("Id should not be 0");
        }
        Optional<PetType> optionalPetType = petTypeRepository.findById(dto.getId());
        if (optionalPetType.isPresent()) {
            return modelMapper.map(petTypeRepository.save(modelMapper.map(dto, PetType.class)), PetTypeDto.class);
        } else {
            throw new DataNotFoundException("PetTypeDto not found");
        }
    }

    @Override
    public void deleteById(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("Id should not be null");
        }
        petTypeRepository.deleteById(id);
    }

    @Override
    public List<PetTypeDto> findAll() {
        List<PetType> petTypeList = new ArrayList<>();
        petTypeRepository.findAll().iterator().forEachRemaining(petTypeList::add);
        List<PetTypeDto> petTypeDtoList = petTypeList.stream()
                .map(petType -> modelMapper.map(petType, PetTypeDto.class))
                .collect(Collectors.toList());
        return petTypeDtoList;
    }

    @Override
    public PetTypeDto findById(int id) throws DataNotFoundException {
        if (id == 0) {
            throw new IllegalArgumentException("Id should not be 0");
        }
        Optional<PetType> optionalPetType = petTypeRepository.findById(id);
        if (optionalPetType.isPresent()) {
            PetTypeDto convertedData = modelMapper.map(optionalPetType.get(), PetTypeDto.class);
            return convertedData;
        } else {
            throw new DataNotFoundException("PetTypeDto not found");
        }
    }
}
