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
