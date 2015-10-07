package fst.info.springhibernate.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
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
    @Override
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Personne modifée", ((Person) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    @Override
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(" Modification annulée", ((Person) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    @Override
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
 
}
