package se.lexicon.pet_clinic.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.pet_clinic.entity.Visit;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, String> {
    // to do: implement basic CRUD

    //to do: select visit by pet name
    //to do: select visit by pet type name

    List<Visit> findByPetName(String petName);

    List<Visit> findByPetPetTypeName(String petTypeName);
}
