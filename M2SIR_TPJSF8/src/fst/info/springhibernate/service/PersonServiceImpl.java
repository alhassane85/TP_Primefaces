package fst.info.springhibernate.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fst.info.springhibernate.dao.PersonDAO;
import fst.info.springhibernate.model.Person;


@Service
@ManagedBean(name="personService")
@SessionScoped
public class PersonServiceImpl implements PersonService,Serializable{
	private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    Person p;

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
    @Transactional
    public void onRowEdit(RowEditEvent event) {
    	 p=(Person) event.getObject();
    	
        personDAO.updatePerson(p);
       
      //  FacesMessage msg = new FacesMessage("Suppression réussie");
        
        FacesMessage msg = new FacesMessage("Personne edité", ((Person) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
      //  FacesMessage msg = new FacesMessage("Personne modifée", ((Person) event.getObject()).getName());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    @Override
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(" Modification annulée", ((Person) event.getObject()).getCountry());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    @Transactional(propagation=Propagation.REQUIRED)
    public void updatePerson(Person person){
        this.personDAO.updatePerson(person);
    }
    @Override
    public void onCellEdit(CellEditEvent event) {
      /*  Object oldValue = event.getOldValue();
        String newValue = (String)event.getNewValue();
       // p.setCountry(newValue);
       
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }*/
    }

	@Override
	public void modifyPerson(Person p) {
		// TODO Auto-generated method stub
		
	}

	

	
 
}
