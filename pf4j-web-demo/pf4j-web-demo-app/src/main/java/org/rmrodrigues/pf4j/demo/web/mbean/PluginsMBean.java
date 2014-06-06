package org.rmrodrigues.pf4j.demo.web.mbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.rmrodrigues.pf4j.demo.api.ExporterBase;
import org.rmrodrigues.pf4j.demo.api.model.Person;
import org.rmrodrigues.pf4j.demo.web.integration.PF4JWrapper;

import ro.fortsoft.pf4j.PluginState;
import ro.fortsoft.pf4j.PluginWrapper;

@ManagedBean(name = "pluginsMBean")
@SessionScoped
public class PluginsMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7473027855852017369L;

	public List<PluginWrapper> getPlugins() {
		return PF4JWrapper.getInstance().getPlugins();
	}

	public boolean isActive(PluginWrapper pluginWrapper) {
		return pluginWrapper.getPluginState().equals(PluginState.STARTED);

	}

	public String disable(PluginWrapper pluginWrapper) {
		PF4JWrapper.getInstance().stopPlugin(pluginWrapper.getPluginId());
		return "";
	}

	public String delete(PluginWrapper pluginWrapper) {
		PF4JWrapper.getInstance().deletePlugin(pluginWrapper.getPluginId());
		return "";
	}

	public String enable(PluginWrapper pluginWrapper) {
		PF4JWrapper.getInstance().enablePlugin(pluginWrapper.getPluginId());
		PF4JWrapper.getInstance().startPlugin(pluginWrapper.getPluginId());
		return "";
	}

	public String download(Person p, ExporterBase plugin) {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) fc
				.getExternalContext().getResponse();
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();

			out.write(plugin.export(p));
			response.setContentType(plugin.getContentType());
			response.addHeader("Content-Disposition",
					"attachment; filename=\"Person\"");
			out.flush();
		} catch (IOException e1) {
		}
		try {
			if (out != null) {
				out.close();
			}
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {

		}

		return "";
	}

	private UploadedFile uploadedFile;

	public void submit() throws IOException {
		String fileName = FilenameUtils.getName(uploadedFile.getName());
		String pluginsHome = System.getProperty("pf4j.pluginsDir", "plugins");
		String contentType = uploadedFile.getContentType();
		byte[] bytes = uploadedFile.getBytes();
		System.out.println(pluginsHome + "/" + fileName);
		FileOutputStream out = new FileOutputStream(pluginsHome + "/"
				+ fileName);
		out.write(bytes);
		out.close();
		String newPluginID = PF4JWrapper.getInstance().loadPlugin(
				new File(pluginsHome + "/" + fileName));

		PF4JWrapper.getInstance().enablePlugin(newPluginID);

		PF4JWrapper.getInstance().startPlugin(newPluginID);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(String.format(
						"Plugin '%s'  successfully installed!", fileName)));
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
