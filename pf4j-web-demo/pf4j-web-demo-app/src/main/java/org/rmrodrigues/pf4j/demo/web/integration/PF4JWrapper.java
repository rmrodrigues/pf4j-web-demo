/**
 * 
 */
package org.rmrodrigues.pf4j.demo.web.integration;

import java.io.File;
import java.util.List;
import java.util.Set;

import ro.fortsoft.pf4j.DefaultPluginManager;
import ro.fortsoft.pf4j.PluginClassLoader;
import ro.fortsoft.pf4j.PluginManager;
import ro.fortsoft.pf4j.PluginState;
import ro.fortsoft.pf4j.PluginStateListener;
import ro.fortsoft.pf4j.PluginWrapper;
import ro.fortsoft.pf4j.RuntimeMode;
import ro.fortsoft.pf4j.Version;

/**
 * The Class PF4JWrapper.
 * 
 * @author rmrodrigues
 */
public class PF4JWrapper implements PluginManager {

	/** The instance. */
	private static PF4JWrapper instance;

	/** The plugins folder. */
	private String pluginsFolder;

	/** The plugin manager. */
	private PluginManager pluginManager;

	/** The has custom plugin path. */
	boolean hasCustomPluginPath = false;

	/**
	 * Instantiates a new p f4 j wrapper.
	 */
	private PF4JWrapper() {
		initialize();

	}

	/**
	 * Instantiates a new p f4 j wrapper.
	 * 
	 * @param path
	 *            the path
	 */
	private PF4JWrapper(String path) {
		this.pluginsFolder = path;
		this.hasCustomPluginPath = true;
		initialize();
	}
	
	public static void init(String dataPath) {
		if (dataPath == null) {
			instance = new PF4JWrapper();
		} else {
			instance = new PF4JWrapper(dataPath);
		}

	}
	
	/**
	 * Gets the single instance of PF4JWrapper.
	 * 
	 * @return single instance of PF4JWrapper
	 */
	public static PF4JWrapper getInstance() {
		if (instance == null)
			instance = new PF4JWrapper();
		return instance;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		if (this.hasCustomPluginPath) {
			pluginManager = new DefaultPluginManager(new File(
					this.pluginsFolder));
		} else {
			pluginManager = new DefaultPluginManager();
		}

	}

	@Override
	public List<PluginWrapper> getPlugins() {
		return pluginManager.getPlugins();
	}

	@Override
	public List<PluginWrapper> getPlugins(PluginState pluginState) {
		return pluginManager.getPlugins(pluginState);
	}

	@Override
	public List<PluginWrapper> getResolvedPlugins() {
		return pluginManager.getResolvedPlugins();
	}

	@Override
	public List<PluginWrapper> getUnresolvedPlugins() {
		return pluginManager.getUnresolvedPlugins();
	}

	@Override
	public List<PluginWrapper> getStartedPlugins() {
		return pluginManager.getStartedPlugins();
	}

	@Override
	public PluginWrapper getPlugin(String pluginId) {
		return pluginManager.getPlugin(pluginId);
	}

	@Override
	public void loadPlugins() {
		pluginManager.loadPlugins();
	}

	@Override
	public String loadPlugin(File pluginArchiveFile) {
		return pluginManager.loadPlugin(pluginArchiveFile);
	}

	@Override
	public void startPlugins() {
		pluginManager.startPlugins();
	}

	@Override
	public PluginState startPlugin(String pluginId) {
		return pluginManager.startPlugin(pluginId);
	}

	@Override
	public void stopPlugins() {
		pluginManager.stopPlugins();
	}

	@Override
	public PluginState stopPlugin(String pluginId) {
		return pluginManager.stopPlugin(pluginId);
	}

	@Override
	public boolean unloadPlugin(String pluginId) {
		return pluginManager.unloadPlugin(pluginId);
	}

	@Override
	public boolean disablePlugin(String pluginId) {
		return pluginManager.disablePlugin(pluginId);
	}

	@Override
	public boolean enablePlugin(String pluginId) {
		return pluginManager.enablePlugin(pluginId);
	}

	@Override
	public boolean deletePlugin(String pluginId) {
		return pluginManager.deletePlugin(pluginId);
	}

	@Override
	public PluginClassLoader getPluginClassLoader(String pluginId) {
		return pluginManager.getPluginClassLoader(pluginId);
	}

	@Override
	public <T> List<T> getExtensions(Class<T> type) {
		return pluginManager.getExtensions(type);
	}

	@Override
	public Set<String> getExtensionClassNames(String pluginId) {
		return pluginManager.getExtensionClassNames(pluginId);
	}

	@Override
	public RuntimeMode getRuntimeMode() {
		return pluginManager.getRuntimeMode();
	}

	@Override
	public void addPluginStateListener(PluginStateListener listener) {
		pluginManager.addPluginStateListener(listener);
	}

	@Override
	public void removePluginStateListener(PluginStateListener listener) {
		pluginManager.removePluginStateListener(listener);
	}

	@Override
	public void setSystemVersion(Version version) {
		pluginManager.setSystemVersion(version);
	}

	@Override
	public Version getSystemVersion() {
		return pluginManager.getSystemVersion();
	}


}