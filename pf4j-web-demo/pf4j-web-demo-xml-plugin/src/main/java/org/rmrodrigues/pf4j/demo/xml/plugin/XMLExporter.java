/**
 * 
 */
package org.rmrodrigues.pf4j.demo.xml.plugin;

import java.util.List;

import org.rmrodrigues.pf4j.demo.api.ExporterBase;
import org.rmrodrigues.pf4j.demo.api.model.Person;

import ro.fortsoft.pf4j.Extension;

import com.thoughtworks.xstream.XStream;

/**
 * @author rmrodrigues
 * 
 */
@Extension
public class XMLExporter extends ExporterBase {

	private static final String NAME = "XML";
	private static final String CONTENTTYPE = "application/xml";

	public XMLExporter() {
		super(NAME, CONTENTTYPE);
	}

	public byte[] export(Person person) {
		XStream xstream = new XStream();
		xstream.alias("person", Person.class);
		String xml = xstream.toXML(person);
		return xml.getBytes();

	}

	public byte[] export(List<Person> person) {
		PersonList personList = new PersonList(person);
		XStream xstream = new XStream();
		xstream.alias("person", Person.class);
		xstream.alias("persons", PersonList.class);
		xstream.addImplicitCollection(PersonList.class, "list");
		String xml = xstream.toXML(personList);
		return xml.getBytes();

	}

}
