/**
 * This file is part of InfoGuide.

Copyright Dockter 2012 <mcsnetworks.com>
InfoGuide is licensed under the GNU General Public License.

InfoGuide is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

As an exception, all classes which do not reference GPL licensed code
are hereby licensed under the GNU Lesser Public License, as described
in GNU General Public License.

InfoGuide is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License,
the GNU Lesser Public License (for classes that fulfill the exception)
and the GNU General Public License along with this program. If not, see
<http://www.gnu.org/licenses/> for the GNU General Public License and
the GNU Lesser Public License.
*/

package net.dockter.infoguide.guide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.dockter.infoguide.Main;
import org.bukkit.Bukkit;



public class GuideManager {
	private static final Map<String, Guide> loadedGuides = new HashMap<String, Guide>();
	
	// Disabled the auto-load functionality, too much strain on servers.
	//static {
		//load();
		//Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

			//public void run() {
			//	load();
			//}
		//}, 20L*5, 20L*5);
	//}
	
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

	public static void load() {
		loadedGuides.clear();
		File dir = new File("plugins"+File.separator+"InfoGuide"+File.separator+"guides");
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
