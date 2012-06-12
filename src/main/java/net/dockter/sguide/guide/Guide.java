package net.dockter.sguide.guide;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;



public class Guide {
	public final static Logger log = Logger.getLogger("Minecraft");
	public static Guide load(File file) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);		
		return new Guide(config.getString("Name"),config.getString("Date"),config.getString("Text"),config.getString("Pageone"),config.getString("Pagetwo"),config.getString("Pagethree"),config.getString("Pagefour"),config.getString("Pagefive"),config.getString("Pagesix"),config.getString("Pageseven"),config.getString("Pageeight"),config.getString("Pagenine"),config.getString("Pageten"));
	}
	private String name, date, text, pageone, pagetwo, pagethree, pagefour, pagefive, pagesix, pageseven, pageeight, pagenine, pageten;

	public Guide(String name, String date, String text, String pageone, String pagetwo, String pagethree, String pagefour, String pagefive, String pagesix, String pageseven, String pageeight, String pagenine, String pageten) {
		this.name = name;
		this.date = date;	
		this.text = text;
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
		
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public String getPageone() {
		return pageone;
	}

	public String getPagetwo() {
		return pagetwo;
	}

	public String getPagethree() {
		return pagethree;
	}

	public String getPagefour() {
		return pagefour;
	}

	public String getPagefive() {
		return pagefive;
	}

	public String getPagesix() {
		return pagesix;
	}

	public String getPageseven() {
		return pageseven;
	}

	public String getPageeight() {
		return pageeight;
	}

	public String getPagenine() {
		return pagenine;
	}

	public String getPageten() {
		return pageten;
	}

	public void save() {
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
