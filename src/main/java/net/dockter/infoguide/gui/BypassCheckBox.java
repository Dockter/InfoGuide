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

package net.dockter.infoguide.gui;

import net.dockter.infoguide.Main;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
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
