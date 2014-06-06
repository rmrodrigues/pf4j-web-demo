package org.rmrodrigues.pf4j.demo.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.rmrodrigues.pf4j.demo.api.ExporterBase;
import org.rmrodrigues.pf4j.demo.web.integration.PF4JWrapper;

public class PersonAppListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		PF4JWrapper.getInstance().stopPlugins();

	}

	/*
	 * (non-Javadoc)cd
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext c = servletContextEvent.getServletContext();
		if (c != null) {
			if (c.getInitParameter("pf4j.pluginsDir") != null) {
				PF4JWrapper.init(c.getInitParameter("pf4j.pluginsDir"));
			} else {
				PF4JWrapper.init(c.getInitParameter("plugins"));
			}
		}
		PF4JWrapper.getInstance().loadPlugins();
		PF4JWrapper.getInstance().startPlugins();

		List<ExporterBase> greetings = PF4JWrapper.getInstance().getExtensions(
				ExporterBase.class);
		System.out.println(String.format(
				"Found %d extensions for extension point '%s'",
				greetings.size(), ExporterBase.class.getName()));

		System.out.println("Plugins home: "
				+ System.getProperty("pf4j.pluginsDir", "plugins"));

	}

}
