package fst.info.springhibernate.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.PieChartModel;
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
    List<Person> tableau;
    List<Integer> tab;
	 public List<Integer> getTab() {
		return tab;
	}

	public void setTab(List<Integer> tab) {
		this.tab = tab;
	}

	public PieChartModel pieModel2;


	public List<Person> getTableau() {
		return tableau;
	}

	public void setTableau(List<Person> tableau) {
		this.tableau = tableau;
	}

	private PersonDAO personDAO;
	 
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
 
    @Override
    @Transactional
    public void addPerson(Person p) {
        this.personDAO.addPerson(p);
        tableau.add(p);
       // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout réussi"));
        FacesMessage msg = new FacesMessage("Personne ajouté", p.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    
    @Override
    @Transactional
    public List<Person> getlistPersons() {
    	if(tableau==null)
    	{
    		tableau=new ArrayList <Person>();
    		for (Person e : this.personDAO.listPersons()) {
        	tableau.add(e);
    		}

    	}
		//logger.info("Person saved successfully, Tableau Person ="+tableau);

        return tableau;
    }
   
    @Override
    @Transactional
    public void delete(Person p) {
    	 tableau.remove(p);
    }
    
    @Override
    @Transactional
    public void onRowEdit(RowEditEvent event) {
    	 p=(Person) event.getObject();
    	 //tableau.remove(p);
        personDAO.updatePerson(p);
       
      //  FacesMessage msg = new FacesMessage("Suppression réussie");
        
        FacesMessage msg = new FacesMessage("Personne edité", p.getName());
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

	@Override
	public List<Integer> count() {
		// TODO Auto-generated method stub
		
    		tab=new ArrayList <Integer>();
    		for (Integer e : this.personDAO.count()) 
        	tab.add(e);

        return tab;
	}

	     
	   public PieChartModel getGraph() {
		   
	        pieModel2 = new PieChartModel();
	        // for(int i=0;i<tab.size();i++)
	        //	 pieModel2.set("Pays "+i, tab.get(i));
	        
        	 pieModel2.set("Pays 1", 10);
        	 pieModel2.set("Pays 2", 50);
        	 pieModel2.set("Pays 3", 100);


	        pieModel2.setTitle("Graphe par Pays");
	        pieModel2.setLegendPosition("e");
	        pieModel2.setFill(false);
	        pieModel2.setShowDataLabels(true);
	        pieModel2.setDiameter(150);
	        return pieModel2;
	    }
	     
	
 
}
