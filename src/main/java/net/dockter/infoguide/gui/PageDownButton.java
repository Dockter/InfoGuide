package net.dockter.infoguide.gui;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;



public class PageDownButton extends GenericButton{
	private GUIGuide guide;
	public PageDownButton(GUIGuide guide) {
		super("Down");
		this.guide = guide;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		guide.pageDown();
	}
	
	
}
