package net.dockter.sguide;

import net.dockter.sguide.gui.GUIGuide;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
		if (!(event.getPlayer().hasPermission("infoguide.noguide"))&&!instance.isBypassing(event.getPlayer().getName())) {
			final SpoutPlayer splr = (SpoutPlayer) event.getPlayer();
			Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {

				public void run() {
					splr.getMainScreen().attachPopupScreen(new GUIGuide(splr));
				}
			}, 20L);
		}
	}
	
	@EventHandler
	public void onInput(KeyPressedEvent event) {
		if(event.getKey() == Keyboard.KEY_F12)
			event.getPlayer().getMainScreen().attachPopupScreen(new GUIGuide(event.getPlayer()));
	}
}
