package net.dockter.sguide.gui;

import net.dockter.sguide.Main;
import org.bukkit.ChatColor;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.player.SpoutPlayer;

public class BypassButton extends GenericButton {

	private GUIGuide plugin;
	private SpoutPlayer splr;

	public BypassButton(SpoutPlayer player, GUIGuide plugin) {
		super();
		setTooltip("If this button is green, the guide screen will you login!");
		splr = player;
		ChatColor ccolor;
		if (Main.getInstance().isBypassing(splr.getName())) {
			ccolor = ChatColor.GREEN;
		} else {
			ccolor = ChatColor.WHITE;
		}
		setText(ccolor + "B");
		this.plugin = plugin;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		Main.getInstance().setBypass(splr.getName(), !Main.getInstance().isBypassing(splr.getName()));
		ChatColor ccolor;
		if (Main.getInstance().isBypassing(splr.getName())) {
			ccolor = ChatColor.GREEN;
		} else {
			ccolor = ChatColor.WHITE;
		}
		setText(ccolor + "B");
	}
}
