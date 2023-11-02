package com.fsse2309.lab_b01;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonApi {
    private List<PersonData> personDataList = new ArrayList<>();

    @PostMapping("/person/create")
    public PersonData createPerson(@RequestBody PersonData newPerson){
        personDataList.add(newPerson);
        return newPerson;
    }
    @GetMapping("/person/all")
    public List<PersonData> listPerson(){
        return personDataList;
    }
    @DeleteMapping("/person/delete")
    public PersonData deletePerson(@RequestBody PersonData deleteperson){
        for (PersonData personData:personDataList){
            if(personData.getHkid().equals(deleteperson.getHkid())){
                personDataList.remove(personData);
                return deleteperson;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/person/update")
    public PersonData updatePerson(@RequestBody PersonData updatePerson) {
        for(PersonData personData : personDataList) {
            if (personData.getHkid().equals(updatePerson.getHkid())) {
                personData.setFirstName(updatePerson.getFirstName());
                personData.setLastName(updatePerson.getLastName());
                return updatePerson;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}

