package se.lexicon.pet_clinic.dto;

import lombok.Data;

@Data
public class OwnerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
}
