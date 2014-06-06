/**
 * 
 */
package org.rmrodrigues.pf4j.demo.costum.plugin;

import java.util.List;

import org.rmrodrigues.pf4j.demo.api.ExporterBase;
import org.rmrodrigues.pf4j.demo.api.model.Person;

import ro.fortsoft.pf4j.Extension;

/**
 * @author rmrodrigues
 * 
 */
@Extension
public class CostumExporter extends ExporterBase {

	private static final String NAME="COSTUM";
	private static final String CONTENTTYPE="text/plain";
	public CostumExporter() {
		super(NAME,CONTENTTYPE);
	}

	public byte[] export(Person person) {
		StringBuffer sbf=new StringBuffer();
		sbf.append("Person: ").append("\tFirst Name: " + person.getFirstName()).
		append("\tLast Name: " + person.getLastName()).append("\tAge: " + person.getAge()).
		append("\tBirthdate: " + person.getBirthdate()).append("\tAddress: " + person.getAddress());
        return sbf.toString().getBytes();
	}
	
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
