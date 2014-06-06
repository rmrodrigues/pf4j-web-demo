package org.rmrodrigues.pf4j.demo.api;

import org.rmrodrigues.pf4j.demo.api.model.Person;

import ro.fortsoft.pf4j.ExtensionPoint;

public interface PluginBase extends ExtensionPoint {
	
	public String getName();

	/**
	 * Export.
	 */
	public void export(Person person);

}
