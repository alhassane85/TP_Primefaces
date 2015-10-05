package fst.info.springhibernate.service;

import java.util.List;

import fst.info.springhibernate.model.Person;
 
public interface PersonService {
 
    public void addPerson(Person p);
    public List<Person> getlistPersons();
     
}