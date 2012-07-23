package net.dockter.sguide.gui;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;



public class DeleteButton extends GenericButton{
	private GUIGuide guide;
	public DeleteButton(GUIGuide guide) {
		super("Delete");
		this.guide = guide;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		guide.onDeleteClick();
	}
	
	
}
