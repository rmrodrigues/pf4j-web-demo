package org.rmrodrigues.pf4j.demo.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.rmrodrigues.pf4j.demo.api.ExporterBase;
import org.rmrodrigues.pf4j.demo.web.integration.PF4JWrapper;

/**
 * The listener interface for receiving personApp events. The class that is
 * interested in processing a personApp event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addPersonAppListener<code> method. When
 * the personApp event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see PersonAppEvent
 * @author rmrodrigues
 */
public class PersonAppListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(PersonAppListener.class);

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		PF4JWrapper.getInstance().stopPlugins();
		logger.info("pf4j-web-demo application stopped.");

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		if (context != null) {
			if (context.getInitParameter("pf4j.pluginsDir") != null) {
				PF4JWrapper.init(context.getInitParameter("pf4j.pluginsDir"));
			} else {
				PF4JWrapper.init(context.getInitParameter("plugins"));
			}
		}
		PF4JWrapper.getInstance().loadPlugins();
		PF4JWrapper.getInstance().startPlugins();

		List<ExporterBase> greetings = PF4JWrapper.getInstance().getExtensions(
				ExporterBase.class);

		logger.info("Plugins home: "
				+ System.getProperty("pf4j.pluginsDir", "plugins"));
		logger.info(String.format(
				"Found %d extensions for extension point '%s'",
				greetings.size(), ExporterBase.class.getName()));
		logger.info("pf4j-web-demo application started.");

	}

}
