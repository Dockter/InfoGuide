package net.dockter.sguide;

import net.dockter.sguide.guide.GuideManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin{
	private static Main instance;
	
	public static Plugin getInstance() {
		return instance;
	}

	@Override
	public void onDisable() {
		super.onDisable();
		GuideManager.disable();
	}

	@Override
	public void onEnable() {
		super.onEnable();
		instance = this;
		FileConfiguration config = this.getConfig();
		config.addDefault("PromptTitle", "Generic title!");
		config.options().copyDefaults(true);
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new GuideListener(this), this);
	}

}
