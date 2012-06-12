package net.dockter.sguide;

import net.dockter.sguide.gui.GUIGuide;
import net.dockter.sguide.guide.GuideManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

class GuideListener implements Listener {

	private Main instance;

	public GuideListener(Main aThis) {
		instance = aThis;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (Main.getInstance().getConfig().getBoolean("DisplayOnLogin", true) || (!(event.getPlayer().hasPermission("infoguide.bypassall")))) {
			if (!(event.getPlayer().hasPermission("infoguide.bypass"))
				&& !instance.isBypassing(event.getPlayer().getName())) {
			final SpoutPlayer splr = (SpoutPlayer) event.getPlayer();
				GuideManager.load();
				Bukkit.getScheduler().scheduleSyncDelayedTask(instance,
						new Runnable() {
							public void run() {
								splr.getMainScreen().attachPopupScreen(new GUIGuide(splr));
								}
						}, 20L);
			}
		}
	}

	@EventHandler
	public void onInput(KeyPressedEvent event) {
		if (event.getKey() == Keyboard.KEY_F12){
			GuideManager.load();
			event.getPlayer().getMainScreen().attachPopupScreen(new GUIGuide(event.getPlayer()));
		}
			
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		SpoutPlayer sPlayer = (SpoutPlayer) event.getPlayer();
		sPlayer.getMainScreen().removeWidgets(instance);
	}

}
