package se.lexicon.pet_clinic.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.pet_clinic.dto.PetDto;
import se.lexicon.pet_clinic.entity.Pet;
import se.lexicon.pet_clinic.exception.DataNotFoundException;
import se.lexicon.pet_clinic.repository.PetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {
    // todo: implement PetService Interface

    PetRepository petRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setPetRepository(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public PetDto save(PetDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("PetDto object should not be null");
        }
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Id should be null");
        }
        Pet petEntity = modelMapper.map(dto, Pet.class);
        Pet savedPetEntity = petRepository.save(petEntity);
        PetDto convertEntityToDto = modelMapper.map(savedPetEntity, PetDto.class);
        return convertEntityToDto;
    }

    @Transactional
    @Override
    public PetDto update(PetDto dto) throws DataNotFoundException {
        if (dto == null) {
            throw new IllegalArgumentException("PetDto object should not be null");
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Id should not be null");
        }
        Optional<Pet> optionalPet = petRepository.findById(dto.getId());
        if (optionalPet.isPresent()) {
            return modelMapper.map(petRepository.save(modelMapper.map(dto, Pet.class)), PetDto.class);
        } else {
            throw new DataNotFoundException("PetDto not found");
        }
    }

    @Override
    public void deleteById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id should not be null");
        }
        petRepository.deleteById(id);
    }

    @Override
    public List<PetDto> findAll() {
        List<Pet> petList = new ArrayList<>();
        petRepository.findAll().iterator().forEachRemaining(petList::add);
        List<PetDto> petDtoList = petList.stream()
                .map(pet -> modelMapper.map(pet, PetDto.class))
                .collect(Collectors.toList());
        return petDtoList;
    }

    @Override
    public PetDto findById(String id) throws DataNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("Id should not be null");
        }
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            PetDto convertedData = modelMapper.map(optionalPet.get(), PetDto.class);
            return convertedData;
        } else {
            throw new DataNotFoundException("PetDto not found");
        }
    }

    @Override
    public List<PetDto> findByName(String petName) throws DataNotFoundException {
        return petRepository.findByNameIgnoreCase(petName)
                .stream().map(pet -> modelMapper.map(pet, PetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDto> findByPetTypeName(String petTypeName) throws DataNotFoundException {
        return petRepository.findByPetTypeNameIgnoreCase(petTypeName)
                .stream().map(pet -> modelMapper.map(pet, PetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDto> findByOwnerFirstNameAndLastName(String firstName, String lastname) throws DataNotFoundException {
        List<Pet> result;
        if (firstName != null && lastname != null) {
            result = petRepository.findByOwnerFirstNameIgnoreCaseAndOwnerLastNameIgnoreCase(firstName, lastname);
        } else if (firstName != null) {
            result = petRepository.findByOwnerFirstNameIgnoreCase(firstName);
        } else if (lastname != null) {
            result = petRepository.findByOwnerLastNameIgnoreCase(lastname);
        } else {
            return findAll();
        }
        return result.stream().map(pet -> modelMapper.map(pet, PetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDto> findByOwnerTelephone(String telephone) throws DataNotFoundException {
        return petRepository.findByOwnerTelephone(telephone)
                .stream().map(pet -> modelMapper.map(pet, PetDto.class))
                .collect(Collectors.toList());
    }
}
