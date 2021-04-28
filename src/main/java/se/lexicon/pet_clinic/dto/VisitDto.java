package se.lexicon.pet_clinic.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitDto {
    // done: implement VisitDao
    private String id;
    private PetDto pet;
    private LocalDate visitDate;
    private String description;
}
