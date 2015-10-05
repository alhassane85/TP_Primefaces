package fst.info.springhibernate.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fst.info.springhibernate.dao.PersonDAO;
import fst.info.springhibernate.model.Person;


@Service
@ManagedBean(name="personService")
@SessionScoped
public class PersonServiceImpl implements PersonService,Serializable{

	private PersonDAO personDAO;
	 
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
 
    @Override
    @Transactional
    public void addPerson(Person p) {
        this.personDAO.addPerson(p);
    }
 
    @Override
    @Transactional
    public List<Person> getlistPersons() {
        return this.personDAO.listPersons();
    }
 
}
