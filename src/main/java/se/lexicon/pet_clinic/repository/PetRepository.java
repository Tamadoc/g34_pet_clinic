package se.lexicon.pet_clinic.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.pet_clinic.entity.Pet;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, String> {

    // to do: implement basic CRUD

    //to do: select pet by name
    //to do: select pet by pet type name
    //to do: select pet by owner first name and last name
    //to do: select pet by owner telephone

    List<Pet> findByNameIgnoreCase(String name);

    List<Pet> findByPetTypeNameIgnoreCase(String petTypeName);

    List<Pet> findByOwnerFirstNameIgnoreCaseAndOwnerLastNameIgnoreCase(String firstName, String lastname);

    List<Pet> findByOwnerFirstNameIgnoreCase(String firstName);

    List<Pet> findByOwnerLastNameIgnoreCase(String lastname);

    List<Pet> findByOwnerTelephone(String telephone);

}