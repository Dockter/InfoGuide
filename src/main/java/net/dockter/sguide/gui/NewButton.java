package net.dockter.sguide.gui;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;



public class NewButton extends GenericButton{
	private GUIGuide guide;
	public NewButton(GUIGuide guide) {
		super("New");
		this.guide = guide;
		setHeight(15);
		setWidth(40);
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		guide.onNewClick();
	}
	
	
}
