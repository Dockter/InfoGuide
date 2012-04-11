package net.dockter.sguide.gui;

import org.getspout.spoutapi.gui.GenericComboBox;



public class MyCombo extends GenericComboBox{
	private GUIGuide plugin;

	@Override
	public void onSelectionChanged(int i, String text) {
		super.onSelectionChanged(i, text);
		plugin.onSelect(i, text);
	}
	public MyCombo(GUIGuide plugin) {
		super();
		this.plugin = plugin;
	}
}
