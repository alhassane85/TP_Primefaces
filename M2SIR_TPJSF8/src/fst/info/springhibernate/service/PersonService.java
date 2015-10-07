package fst.info.springhibernate.service;

import java.util.List;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import fst.info.springhibernate.model.Person;

public interface PersonService {

	public void addPerson(Person p);

	public List<Person> getlistPersons();

	public void onRowEdit(RowEditEvent event);

	public void onRowCancel(RowEditEvent event);
	
	

	public void onCellEdit(CellEditEvent event);

}