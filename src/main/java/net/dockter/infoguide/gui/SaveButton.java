package net.dockter.infoguide.gui;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;



public class SaveButton extends GenericButton{
	private GUIGuide guide;
	public SaveButton(GUIGuide guide) {
		super("Save");
		this.guide = guide;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		guide.onSaveClick();
	}
	
	
}
