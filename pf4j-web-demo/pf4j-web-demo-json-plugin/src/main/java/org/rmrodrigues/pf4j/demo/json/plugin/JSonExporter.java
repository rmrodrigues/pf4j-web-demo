/**
 * 
 */
package org.rmrodrigues.pf4j.demo.json.plugin;

import java.lang.reflect.Type;
import java.util.List;

import org.rmrodrigues.pf4j.demo.api.ExporterBase;
import org.rmrodrigues.pf4j.demo.api.model.Person;

import ro.fortsoft.pf4j.Extension;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author rmrodrigues
 * 
 */
@Extension
public class JSonExporter extends ExporterBase {
	
	private static final String NAME="JSON";
	
	private static final String CONTENTTYPE="application/json";

	public JSonExporter() {
		super(NAME,CONTENTTYPE);
	}

	public byte[] export(Person person) {
		Gson gson = new Gson();

		return gson.toJson(person).toString().getBytes();

	}
	
	
	public byte[] export(List<Person> personList) {
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Person>>() {}.getType();
		return gson.toJson(personList,listType).toString().getBytes();

	}


}
