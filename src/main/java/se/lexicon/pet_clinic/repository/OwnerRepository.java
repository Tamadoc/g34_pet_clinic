package se.lexicon.pet_clinic.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.pet_clinic.entity.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, String> {
    // select * from Owner where address like ?;
    List<Owner> findByAddressContains(String address);

    List<Owner> findByTelephone(String telephone);

    List<Owner> findByFirstNameIgnoreCase(String firstName);

    List<Owner> findByLastNameIgnoreCase(String lastName);

    List<Owner> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}
