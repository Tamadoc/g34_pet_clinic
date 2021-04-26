package se.lexicon.pet_clinic.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.pet_clinic.dto.OwnerDto;
import se.lexicon.pet_clinic.entity.Owner;
import se.lexicon.pet_clinic.exception.DataNotFoundException;
import se.lexicon.pet_clinic.repository.OwnerRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    // DI
    OwnerRepository ownerRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OwnerDto save(OwnerDto dto) {
        if (dto == null) throw new IllegalArgumentException("OwnerDto object should not be null");
        if (dto.getId() != null) throw new IllegalArgumentException("Id should be null");
        Owner ownerEntity = modelMapper.map(dto, Owner.class);
        Owner savedOwnerEntity = ownerRepository.save(ownerEntity);
        OwnerDto convertEntityToDto = modelMapper.map(savedOwnerEntity, OwnerDto.class);
        return convertEntityToDto;
    }

    @Override
    public OwnerDto update(OwnerDto dto) throws DataNotFoundException {
        if (dto == null) throw new IllegalArgumentException("OwnerDto object should not be null");
        if (dto.getId() == null) throw new IllegalArgumentException("Id should not be null");
        Optional<Owner> optionalOwner = ownerRepository.findById(dto.getId());
        if (optionalOwner.isPresent()) {
            return modelMapper.map(ownerRepository.save(modelMapper.map(dto, Owner.class)), OwnerDto.class);
        } else {
            throw new DataNotFoundException("OwnerDto not found");
        }

        //ownerRepository.findById(dto.getId()).orElseThrow(() -> new DataNotFoundException("OwnerDto not found"));
        //return modelMapper.map(ownerRepository.save(modelMapper.map(dto,Owner.class)),OwnerDto.class);

    }

    @Override
    public OwnerDto findById(String id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Id should not be null");
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            OwnerDto convertedData = modelMapper.map(optionalOwner.get(), OwnerDto.class);
            return convertedData;
        } else {
            throw new DataNotFoundException("OwnerDto not found");
        }
    }

    @Override
    public void deleteById(String id) {
        // todo: implement delete by id
    }

    @Override
    public List<OwnerDto> getAll() {
        List<Owner> ownerList = new ArrayList<>();
        ownerRepository.findAll().iterator().forEachRemaining(ownerList::add);
        List<OwnerDto> ownerDtoList = ownerList.stream()
                .map(owner -> modelMapper.map(owner, OwnerDto.class))
                .collect(Collectors.toList());
        return ownerDtoList;
    }

    @Override
    public List<OwnerDto> advanceSearch(String firstName, String lastname) {
        if (firstName != null && lastname != null) return searchByFullName(firstName, lastname);
        else if (firstName != null) return searchByFirstName(firstName);
        else if (lastname != null) return searchByLastName(lastname);
        else return getAll();
    }

    private List<OwnerDto> searchByFirstName(String firstname) {
        return ownerRepository.findByFirstNameIgnoreCase(firstname).stream().map(owner -> modelMapper.map(owner, OwnerDto.class)).collect(Collectors.toList());
        /*List<OwnerDto> ownerDtoList = new ArrayList<>();
        for (Owner owner : ownerList) {
            OwnerDto ownerDto = modelMapper.map(owner, OwnerDto.class);
            ownerDtoList.add(ownerDto);
        }
        return ownerDtoList;*/
    }

    private List<OwnerDto> searchByLastName(String lastName) {
        return ownerRepository.findByLastNameIgnoreCase(lastName).stream().map(owner -> modelMapper.map(owner, OwnerDto.class)).collect(Collectors.toList());
    }


    private List<OwnerDto> searchByFullName(String fistName, String lastName) {
        return ownerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(fistName, lastName)
                .stream()
                .map(owner -> modelMapper.map(owner, OwnerDto.class))
                .collect(Collectors.toList());
    }
}
