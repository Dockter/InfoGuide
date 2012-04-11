package net.dockter.sguide.gui;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;

public class CloseButton extends GenericButton {

	private GUIGuide guide;

	public CloseButton(GUIGuide guide) {
		super("Close");
		this.guide = guide;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		guide.onCloseClick();
	}
}
