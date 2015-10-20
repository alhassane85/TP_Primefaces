package fst.info.springhibernate.dao;

import java.util.List;

import fst.info.springhibernate.model.Person;
 
public interface PersonDAO {
 
    public void addPerson(Person p);
    public void updatePerson(Person p);
    public void delete(Person p);

    public List <Integer> count();
    public List<Person> listPersons();
}