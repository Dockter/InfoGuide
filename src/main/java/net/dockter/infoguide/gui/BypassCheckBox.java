package net.dockter.sguide.gui;

import net.dockter.sguide.Main;
import org.bukkit.ChatColor;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericCheckBox;
import org.getspout.spoutapi.player.SpoutPlayer;

public class BypassCheckBox extends GenericCheckBox {

	private GUIGuide plugin;
	private SpoutPlayer splr;

	public BypassCheckBox(SpoutPlayer player, GUIGuide plugin) {
		super();
		setTooltip("Click this to bypass InfoGuide on Login.");
		splr = player;		
		if (Main.getInstance().isBypassing(splr.getName())) {
			setChecked(true);
		} else {
			setChecked(false);
		}		
		this.plugin = plugin;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		Main.getInstance().setBypass(splr.getName(), !Main.getInstance().isBypassing(splr.getName()));
		if (Main.getInstance().isBypassing(splr.getName())) {
			setChecked(true);
		} else {
			setChecked(false);
		}		
		setDirty(true);
	}
}
