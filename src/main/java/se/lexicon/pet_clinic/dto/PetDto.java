package se.lexicon.pet_clinic.dto;

import lombok.Data;
import se.lexicon.pet_clinic.entity.Owner;
import se.lexicon.pet_clinic.entity.PetType;

import java.time.LocalTime;

@Data
public class PetDto {
    // done: implement PetDao
    private String id;
    private String name;
    private LocalTime birthDate;
    private PetType petType;
    private Owner owner;
}
