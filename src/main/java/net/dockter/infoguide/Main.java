package net.dockter.infoguide;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dockter.infoguide.gui.GUIGuide;
import net.dockter.infoguide.guide.GuideManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Main extends JavaPlugin {

	private static Main instance;
	private static FileConfiguration bypass;

	public static Main getInstance() {
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
		config.addDefault("PromptTitle", "Info Guide");
		config.addDefault("TitleX", 190);
		config.addDefault("DisplayOnLogin", true);
		config.addDefault("DefaultGuide", "Initial");
		config.addDefault("GuestGuide", "Initial");
		config.addDefault("MemberGuide", "Initial");
		config.addDefault("SuperMemberGuide", "Initial");
		config.addDefault("ModeratorGuide", "Initial");
		config.options().copyDefaults(true);
		saveConfig();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new GuideListener(this), this);
		File file = new File(this.getDataFolder() + File.separator + "users.yml");
		if (!file.exists()) {
			File init = new File(this.getDataFolder() + File.separator + "guides" + File.separator + "Initial.yml");
			init.getParentFile().mkdirs();
			try {
				init.createNewFile();
			} catch (IOException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
			YamlConfiguration yconf = YamlConfiguration.loadConfiguration(init);
			yconf.addDefault("Name", "Initial");
			yconf.addDefault("Date", "Future");			
			yconf.addDefault("Pageone", "Page 1 of InfoGuide");
			yconf.addDefault("Pagetwo", "Page 2 of InfoGuide");
			yconf.addDefault("Pagethree", "Page 3 of InfoGuide");
			yconf.addDefault("Pagefour", "Page 4 of InfoGuide");
			yconf.addDefault("Pagefive", "Page 5 of InfoGuide");
			yconf.addDefault("Pagesix", "Page 6 of InfoGuide");
			yconf.addDefault("Pageseven", "Page 7 of InfoGuide");
			yconf.addDefault("Pageeight", "Page 8 of InfoGuide");
			yconf.addDefault("Pagenine", "Page 9 of InfoGuide");
			yconf.addDefault("Pageten", "Page 10 of InfoGuide");
			yconf.options().copyDefaults(true);
			try {
				yconf.save(init);
			} catch (IOException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				file.createNewFile();
			} catch (IOException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		bypass = YamlConfiguration.loadConfiguration(file);
	}

	public static boolean isBypassing(String name) {
		return bypass.contains(name) && bypass.getBoolean(name);
	}

	public static boolean canBypass(String name) {
		return bypass.contains(name);
	}

	public void setBypass(String name, boolean value) {
		bypass.set(name, value);
		try {
			bypass.save(new File(this.getDataFolder() + File.separator + "users.yml"));
		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    	Player player = null;
	    	if (cmd.getName().equalsIgnoreCase("gcme")) {
	    		sender.sendMessage("InfoGuide - Secret GC Command.");
	    		System.gc();
	    	}
	    	if (sender instanceof Player) {
	    		player = (Player) sender;
	    	}
	     
	         if (cmd.getName().equalsIgnoreCase("infoguide")) {
	    		if (player == null) {
	    			sender.sendMessage("InfoGuide cannot be run from the server console.");
	    		} else {
	    			((SpoutPlayer) sender).getMainScreen().attachPopupScreen(new GUIGuide((SpoutPlayer) sender));	    			
	    		}
	    		return true;
	    	}
	    	return false;
	    }
}
