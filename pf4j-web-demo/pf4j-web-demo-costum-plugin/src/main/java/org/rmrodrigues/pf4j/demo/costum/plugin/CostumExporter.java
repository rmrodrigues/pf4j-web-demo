/**
 * 
 */
package org.rmrodrigues.pf4j.demo.costum.plugin;

import java.util.List;

import org.rmrodrigues.pf4j.demo.api.ExporterBase;
import org.rmrodrigues.pf4j.demo.api.model.Person;

import ro.fortsoft.pf4j.Extension;

/**
 * The Class CostumExporter.
 *
 * @author rmrodrigues
 */
@Extension
public class CostumExporter extends ExporterBase {

	/** The Constant NAME. */
	private static final String NAME="COSTUM";
	
	/** The Constant CONTENTTYPE. */
	private static final String CONTENTTYPE="text/plain";
	
	/**
	 * Instantiates a new costum exporter.
	 */
	public CostumExporter() {
		super(NAME,CONTENTTYPE);
	}

	/* (non-Javadoc)
	 * @see org.rmrodrigues.pf4j.demo.api.ExporterBase#export(org.rmrodrigues.pf4j.demo.api.model.Person)
	 */
	public byte[] export(Person person) {
		StringBuffer sbf=new StringBuffer();
		sbf.append("Person: ").append("\tFirst Name: " + person.getFirstName()).
		append("\tLast Name: " + person.getLastName()).append("\tAge: " + person.getAge()).
		append("\tBirthdate: " + person.getBirthdate()).append("\tAddress: " + person.getAddress());
        return sbf.toString().getBytes();
	}
	
	/* (non-Javadoc)
	 * @see org.rmrodrigues.pf4j.demo.api.ExporterBase#export(java.util.List)
	 */
	public byte[] export(List<Person> personList) {
		StringBuffer sbf=new StringBuffer();
		for (Person person : personList) {
			sbf.append("########################################################").append("\nPerson: ").append("\n\tFirst Name: " + person.getFirstName()).
			append("\n\tLast Name: " + person.getLastName()).append("\n\tAge: " + person.getAge()).
			append("\n\tBirthdate: " + person.getBirthdate()).append("\n\tAddress: " + person.getAddress()).append("\n########################################################");
		}
        return sbf.toString().getBytes();
	}


}
