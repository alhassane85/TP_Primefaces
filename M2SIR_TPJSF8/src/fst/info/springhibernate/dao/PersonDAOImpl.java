package fst.info.springhibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public void updatePerson(Person person) {
		logger.info("**********Modification******************"+person);

		 Session session = this.sessionFactory.getCurrentSession();
		// session.delete(person);
		 //session.saveOrUpdate(person);
		 session.update(person);
		/*if(session.contains(person)){
			session.save(person);
			//update(person);/
		} else {
			person = (Person) session.merge(person);
		}
		return person;*/
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

	

}
