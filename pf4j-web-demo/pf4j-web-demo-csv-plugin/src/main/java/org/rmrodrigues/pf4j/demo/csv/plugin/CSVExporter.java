/**
 * 
 */
package org.rmrodrigues.pf4j.demo.csv.plugin;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsefa.Serializer;
import org.jsefa.csv.CsvIOFactory;
import org.rmrodrigues.pf4j.demo.api.ExporterBase;
import org.rmrodrigues.pf4j.demo.api.model.Person;
import org.rmrodrigues.pf4j.demo.csv.plugin.model.PersonCSV;

import ro.fortsoft.pf4j.Extension;

/**
 * @author rmrodrigues
 * 
 */
@Extension
public class CSVExporter extends ExporterBase {

	private static final String NAME="CSV";
	private static final String CONTENTTYPE="application/CSV";
	public CSVExporter() {
		super(NAME,CONTENTTYPE);
	}


	public byte[] export(Person person) {
		PersonCSV personCSV = mapPerson(person);

		Serializer serializer = CsvIOFactory.createFactory(PersonCSV.class)
				.createSerializer();
		StringWriter writer = new StringWriter();
		serializer.open(writer);
		serializer.write(personCSV);
		serializer.close(true);
		return writer.toString().getBytes();

	}
	
	public byte[] export(List<Person> person) {
		List<PersonCSV> personCSVList = mapPerson(person);

		Serializer serializer = CsvIOFactory.createFactory(PersonCSV.class)
				.createSerializer();
		StringWriter writer = new StringWriter();
		serializer.open(writer);
		for (PersonCSV personCSV : personCSVList) {
			serializer.write(personCSV);
		}

		serializer.close(true);
		return writer.toString().getBytes();

	}
	
	private List<PersonCSV> mapPerson(List<Person> personList) {
		List<PersonCSV> result = new ArrayList<PersonCSV>();
		for (Person curPerson : personList) {
			result.add(mapPerson(curPerson));
		}
		
		return result;
	}

	private PersonCSV mapPerson(Person person) {
		return new PersonCSV(person.getFirstName(), person.getLastName(),
				person.getAge(), person.getBirthdate(), person.getAddress());
	}

}
