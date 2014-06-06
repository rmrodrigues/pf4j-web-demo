/**
 * 
 */
package org.rmrodrigues.pf4j.demo.api;

import java.util.List;

import org.rmrodrigues.pf4j.demo.api.model.Person;

import ro.fortsoft.pf4j.ExtensionPoint;

/**
 * @author rmrodrigues
 * 
 */
public abstract class ExporterBase implements ExtensionPoint {

	private String name;

	private String contentType;

	/**
	 * 
	 */
	public ExporterBase(String name, String contentType) {
		this.name = name;
		this.contentType = contentType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rmrodrigues.pf4j.demo.api.PluginBase#getName()
	 */
	public String getName() {
		return name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rmrodrigues.pf4j.demo.api.PluginBase#export(org.rmrodrigues.pf4j.
	 * demo.api.model.Person)
	 */
	public abstract byte[] export(Person person);
	
	
	public abstract byte[] export(List<Person> persons);

}
