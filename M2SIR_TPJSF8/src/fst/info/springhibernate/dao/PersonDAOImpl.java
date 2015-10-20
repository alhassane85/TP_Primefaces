package fst.info.springhibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fst.info.springhibernate.model.Person;
@Repository
public class PersonDAOImpl implements PersonDAO{

	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		
		session.persist(p);
		logger.info("Person saved successfully, Person Details="+p);
	}
	@Override
	public void updatePerson(Person person) {
		 Session session = this.sessionFactory.getCurrentSession();
		 session.update(person);
		
	}
	
	public void delete(Person person) {
		 Session session = this.sessionFactory.getCurrentSession();
		session.delete(person);
	}
	




	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Person> personsList = session.createQuery("from Person").list();
		for(Person p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
	}

	@Override
	public List<Integer> count() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Person.class);
		ProjectionList projectionList = Projections.projectionList();
	    projectionList.add(Projections.groupProperty("country"));
	    projectionList.add(Projections.rowCount());
	    criteria.setProjection(projectionList);
	    List results = criteria.list();
	    List<Integer> tab = new ArrayList<Integer>();
	    for (Object obj : results) {
	       
	        tab.add( (Integer) obj);
	    }
		return tab;
	}

	

}
