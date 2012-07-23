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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;



public class Guide {
	public final static Logger log = Logger.getLogger("Minecraft");
	public static Guide load(File file) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);		
		return new Guide(config.getString("Name"),config.getString("Date"),config.getString("Text"),config.getString("Pageone"),config.getString("Pagetwo"),config.getString("Pagethree"),config.getString("Pagefour"),config.getString("Pagefive"),config.getString("Pagesix"),config.getString("Pageseven"),config.getString("Pageeight"),config.getString("Pagenine"),config.getString("Pageten"));
	}
	private String name, date, pageone, pagetwo, pagethree, pagefour, pagefive, pagesix, pageseven, pageeight, pagenine, pageten;

	public Guide(String name, String date, String text, String pageone, String pagetwo, String pagethree, String pagefour, String pagefive, String pagesix, String pageseven, String pageeight, String pagenine, String pageten) {
		this.name = name;
		this.date = date;
		this.pageone = pageone;
		this.pagetwo = pagetwo;
		this.pagethree = pagethree;
		this.pagefour = pagefour;
		this.pagefive = pagefive;
		this.pagesix = pagesix;
		this.pageseven = pageseven;
		this.pageeight = pageeight;
		this.pagenine = pagenine;
		this.pageten = pageten;
		
		if (pageone == null){
			this.pageone = text;
			this.pagetwo = " ";
			this.pagethree = " ";
			this.pagefour = " ";
			this.pagefive = " ";
			this.pagesix = " ";
			this.pageseven = " ";
			this.pageeight= " ";
			this.pagenine = " ";
			this.pageten = " ";
			log.info("Upgrading Guide..");
		}
		prepareForLoad();
	}

	public void prepareForLoad() {
		for(ChatColor possibleColor : ChatColor.values()) {
			pageone = pageone.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			pagetwo = pagetwo.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			pagethree = pagethree.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			pagefour = pagefour.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			pagefive = pagefive.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			pagesix = pagesix.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			pageseven = pageseven.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			pageeight = pageeight.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			pagenine = pagenine.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			pageten = pageten.replaceAll("#"+possibleColor.getChar(), ""+possibleColor);
			
		}
	}

	public void prepareForSave() {
		for(ChatColor possibleColor : ChatColor.values()) {			
			pageone = pageone.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
			pagetwo = pagetwo.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
			pagethree = pagethree.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
			pagefour = pagefour.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
			pagefive = pagefive.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
			pagesix = pagesix.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
			pageseven = pageseven.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
			pageeight = pageeight.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
			pagenine = pagenine.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
			pageten = pageten.replaceAll(""+possibleColor, "#"+possibleColor.getChar());
		}
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public String getPageone() {
		prepareForLoad();
		return pageone;
	}

	public String getPagetwo() {
		prepareForLoad();
		return pagetwo;
	}

	public String getPagethree() {
		prepareForLoad();
		return pagethree;
	}

	public String getPagefour() {
		prepareForLoad();
		return pagefour;
	}

	public String getPagefive() {
		prepareForLoad();
		return pagefive;
	}

	public String getPagesix() {
		prepareForLoad();
		return pagesix;
	}

	public String getPageseven() {
		prepareForLoad();
		return pageseven;
	}

	public String getPageeight() {
		prepareForLoad();
		return pageeight;
	}

	public String getPagenine() {
		prepareForLoad();
		return pagenine;
	}

	public String getPageten() {
		prepareForLoad();
		return pageten;
	}

	public void save() {
		prepareForSave();
		File toSave = new File("plugins"+File.separator+"InfoGuide"+File.separator+"guides"+File.separator+name+".yml");
		if(!toSave.exists())
			try {
				toSave.createNewFile();
			} catch (IOException ex) {
				Logger.getLogger(Guide.class.getName()).log(Level.SEVERE, null, ex);
			}
		YamlConfiguration config = YamlConfiguration.loadConfiguration(toSave);
		config.set("Name", name);
		config.set("Date", date);
		config.set("Pageone", pageone);
		config.set("Pagetwo", pagetwo);
		config.set("Pagethree", pagethree);
		config.set("Pagefour", pagefour);
		config.set("Pagefive", pagefive);
		config.set("Pagesix", pagesix);
		config.set("Pageseven", pageseven);
		config.set("Pageeight", pageeight);
		config.set("Pagenine", pagenine);
		config.set("Pageten", pageten);
		try {
			config.save(toSave);
		} catch (IOException ex) {
			Logger.getLogger(Guide.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void delete() {
		File toSave = new File("plugins"+File.separator+"InfoGuide"+File.separator+"guides"+File.separator+name+".yml");
		toSave.delete();
	}

	public void setPageone(String pageone) {
		this.pageone = pageone;
	}

	public void setPagetwo(String pagetwo) {
		this.pagetwo = pagetwo;
	}

	public void setPagethree(String pagethree) {
		this.pagethree = pagethree;
	}

	public void setPagefour(String pagefour) {
		this.pagefour = pagefour;
	}

	public void setPagefive(String pagefive) {
		this.pagefive = pagefive;
	}

	public void setPagesix(String pagesix) {
		this.pagesix = pagesix;
	}

	public void setPageseven(String pageseven) {
		this.pageseven = pageseven;
	}

	public void setPageeight(String pageeight) {
		this.pageeight = pageeight;
	}

	public void setPagenine(String pagenine) {
		this.pagenine = pagenine;
	}

	public void setPageten(String pageten) {
		this.pageten = pageten;
	}
	public void setDate(String format) {
		this.date = format;
	}

	public void setName(String text) {
		this.name = text;
	}
}
