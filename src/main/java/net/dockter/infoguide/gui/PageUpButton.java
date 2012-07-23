package net.dockter.sguide.gui;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;



public class PageUpButton extends GenericButton{
	private GUIGuide guide;
	public PageUpButton(GUIGuide guide) {
		super("Up");
		this.guide = guide;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		guide.pageUp();
	}
	
	
}
