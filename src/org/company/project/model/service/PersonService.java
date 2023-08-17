package org.company.project.model.service;

import org.apache.log4j.Logger;
import org.company.project.model.entity.Person;
import org.company.project.model.repository.PersonRepository;

import java.sql.SQLException;
import java.util.List;

public class PersonService {
    private PersonService(){}
    private static final PersonService PERSON_SERVICE = new PersonService();

    public static PersonService getInstance() {
        return PERSON_SERVICE;
    }
    public PersonService save (Person person) throws Exception {
        person.setSalary(person.getSalary() - (person.getSalary()*10/100));
        try(PersonRepository personRepository = new PersonRepository()) {
            personRepository.insert(person);
            personRepository.commit();
            return this;
        }
    }
    public PersonService asyncSave (Person person) throws Exception{
        new Thread(){
            @Override
            public void run() {
                try {
                    save(person);
                } catch (Exception e) {
                    Logger logger =Logger.getLogger(PersonService.class);
                    logger.error(e.getMessage());
                    logger.error(e.getClass());
                    e.printStackTrace();

                }
            }
        }.start();
        return this;
    }
    public PersonService remove (Person person) throws Exception{
        try(PersonRepository personRepository = new PersonRepository()){
            personRepository.delete(person);
            personRepository.commit();
            return this;
        }
    }
    public PersonService change (Person person) throws Exception{
        try(PersonRepository personRepository = new PersonRepository()) {
            personRepository.update(person);
            personRepository.commit();
            return this;
        }
    }
    public Person findOne (Person person) throws Exception{
        try(PersonRepository personRepository = new PersonRepository()){
            return personRepository.selectOne(person);
        }
    }
    public List<Person> findAll () throws Exception{
        try(PersonRepository personRepository = new PersonRepository()){
            return personRepository.selectAll();
        }
    }
 }
