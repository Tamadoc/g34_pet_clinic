package se.lexicon.pet_clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.pet_clinic.dto.OwnerDto;
import se.lexicon.pet_clinic.exception.DataNotFoundException;
import se.lexicon.pet_clinic.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {

    // DI
    OwnerService ownerService;

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAll() {
        List<OwnerDto> ownerDtoList = ownerService.getAll();
        return ResponseEntity.ok(ownerDtoList);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> findById(@PathVariable("id") String id) {
        if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        try {
            OwnerDto dto = ownerService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<OwnerDto> save(@RequestBody OwnerDto ownerDto) {
        if (ownerDto != null)
            if (ownerDto.getId() != null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        OwnerDto result = ownerService.save(ownerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    //http://localhost:8080/api/v1/owner/search/?firstName=Test Owner&lastName=Test Owner LN
    @GetMapping("/search")
    public ResponseEntity<List<OwnerDto>> advanceSearch(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName",required = false) String lastName){
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);

        List<OwnerDto> ownerDtoList = ownerService.advanceSearch(firstName,lastName);
        return ResponseEntity.ok(ownerDtoList);
    }

}
