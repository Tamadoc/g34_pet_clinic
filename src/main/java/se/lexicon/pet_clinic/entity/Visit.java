package se.lexicon.pet_clinic.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Visit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.DETACH,CascadeType.REFRESH} )
    @JoinColumn(name = "pet_id")
    private Pet pet;
    private LocalDate visitDate;
    private String description;

}
