package net.dockter.sguide.guide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.dockter.sguide.Main;
import org.bukkit.Bukkit;



public class GuideManager {
	private static final Map<String, Guide> loadedGuides = new HashMap<String, Guide>();
	
	static {
		load();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			public void run() {
				load();
			}
		}, 20L*5, 20L*5);
	}
	
	public static void disable() {
		for(Guide guide : loadedGuides.values()) {
			guide.save();
		}
	}
	
	public static Map<String, Guide> getLoadedGuides() {
		return loadedGuides;
	}
	
	public static void addGuide(Guide newGuide) {
		loadedGuides.put(newGuide.getName(), newGuide);
	}

	public static void removeLoadedGuide(String text) {
		if(!(loadedGuides.containsKey(text)))
			return;
		loadedGuides.get(text).delete();
		loadedGuides.remove(text);
	}

	private static void load() {
		loadedGuides.clear();
		File dir = new File("plugins"+File.separator+"infoguide"+File.separator+"guides");
		dir.mkdirs();
		for(File file : dir.listFiles()) {
			Guide g = Guide.load(file);
			loadedGuides.put(g.getName(),g);
		}
		if(loadedGuides.isEmpty()) {
			System.out.println("No guides detected, turning off!");
			Bukkit.getServer().getPluginManager().disablePlugin(Main.getInstance());
		}
	}

}
