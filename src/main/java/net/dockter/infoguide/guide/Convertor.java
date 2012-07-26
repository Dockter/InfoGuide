package net.dockter.infoguide.guide;

import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;

class Convertor {

	static void handle10(YamlConfiguration config, List<String> pgs) {
		addAndRemoveIfExists(config, pgs, "Pageone");
		addAndRemoveIfExists(config, pgs, "Pagetwo");
		addAndRemoveIfExists(config, pgs, "Pagethree");
		addAndRemoveIfExists(config, pgs, "Pagefour");
		addAndRemoveIfExists(config, pgs, "Pagefive");
		addAndRemoveIfExists(config, pgs, "Pagesix");
		addAndRemoveIfExists(config, pgs, "Pageseven");
		addAndRemoveIfExists(config, pgs, "Pageeight");
		addAndRemoveIfExists(config, pgs, "Pagenine");
		addAndRemoveIfExists(config, pgs, "Pageten");
	}

	private static void addAndRemoveIfExists(YamlConfiguration config, List<String> pgs, String pageone) {
		String value = config.getString(pageone);
		pgs.add(value);
		config.set(pageone, null);
	}
	
}
