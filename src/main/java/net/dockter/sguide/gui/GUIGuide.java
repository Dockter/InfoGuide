package net.dockter.sguide.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import net.dockter.sguide.Main;
import net.dockter.sguide.guide.Guide;
import net.dockter.sguide.guide.GuideManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.getspout.spoutapi.gui.CheckBox;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.ComboBox;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericCheckBox;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericSlider;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class GUIGuide extends GenericPopup {

	final GenericTextField guideField, guideInvisible;
	final GenericLabel guideName, guideDate, pagelabel;
	public static Guide currentGuide;
	public int pageno = 1;
	final GenericButton close, newb, saveb, deleteb, pd, pu;	
	final ComboBox box; 
	final CheckBox checkBox;	
	private final SpoutPlayer player;
	public final Logger log = Logger.getLogger("Minecraft");

	public GUIGuide(SpoutPlayer player) {
		super();		
		this.player = player;
		// Label
		//GenericLabel label = new GenericLabel(Main.getInstance().getConfig().getString("PromptTitle"));
		//label.setX(Main.getInstance().getConfig().getInt("TitleX")).setY(25);
		GenericLabel label = new GenericLabel();
		//label.setText("InfoGuide");
		label.setText(Main.getInstance().getConfig().getString("PromptTitle"));
		label.setAnchor(WidgetAnchor.CENTER_CENTER);
		label.shiftXPos(-35).shiftYPos(-122);		
		label.setPriority(RenderPriority.Lowest);
		label.setWidth(-1).setHeight(-1);

		guideName = new GenericLabel("TheGuideNameHere");
		guideName.setWidth(-1).setHeight(-1);
		guideName.setAnchor(WidgetAnchor.CENTER_CENTER);
		guideName.shiftXPos(-200).shiftYPos(-105);
		//guideName.setX(80).setY(42);

		//Current Guide Loaded Name Displayed Here
		guideInvisible = new GenericTextField();
		guideInvisible.setWidth(150).setHeight(18);
		guideInvisible.setAnchor(WidgetAnchor.CENTER_CENTER);
		guideInvisible.shiftXPos(-200).shiftYPos(-110);			
		guideInvisible.setMaximumCharacters(30);
		guideInvisible.setMaximumLines(1);		
		guideInvisible.setVisible(false);

		//Update Date
		guideDate = new GenericLabel("Updated: "+new SimpleDateFormat("HH:mm dd-MM").format(Calendar.getInstance().getTime()));
		guideDate.setWidth(-1).setHeight(-1);
		guideDate.setAnchor(WidgetAnchor.CENTER_CENTER);
		guideDate.shiftXPos(-200).shiftYPos(90);	

		//ComboBox
		box = new MyCombo(this);
		box.setText("Guides");
		box.setAnchor(WidgetAnchor.CENTER_CENTER);	
		box.setWidth(GenericLabel.getStringWidth("12345678901234567890123459"));
		box.setHeight(18);
		box.shiftXPos(25).shiftYPos(-110);		
		box.setAuto(true);		
		refreshItems();

		// Border
		GenericTexture border = new GenericTexture("http://www.almuramc.com/images/sguide.png");		
		border.setAnchor(WidgetAnchor.CENTER_CENTER);
		border.setPriority(RenderPriority.High);
		border.setWidth(626).setHeight(240);		
		border.shiftXPos(-220).shiftYPos(-128);

		// Background gradient
		//GenericGradient gradient = new GenericGradient();
		//gradient.setTopColor(new Color(0.25F, 0.25F, 0.25F, 1.0F));
		//gradient.setBottomColor(new Color(0.35F, 0.35F, 0.35F, 1.0F));
		//gradient.setWidth(300).setHeight(200);
		//gradient.setX(65).setY(20);
		//gradient.setPriority(RenderPriority.Highest);

		// TextBox
		guideField = new GenericTextField();
		guideField.setText("first guide goes here"); // The default text
		//textField.setCursorPosition(3); // Puts the cursor after the third character
		//guideField.setFieldColor(new Color(1.0F, 1.0F, 1.0F, 1.0F)); // White background
		guideField.setAnchor(WidgetAnchor.CENTER_CENTER);
		guideField.setBorderColor(new Color(1.0F, 1.0F, 1.0F, 1.0F)); // White border
		guideField.setMaximumCharacters(1000);
		guideField.setMaximumLines(13);
		guideField.setHeight(160).setWidth(377);
		//guideField.setX(84).setY(60);
		guideField.shiftXPos(-195).shiftYPos(-83);
		guideField.setMargin(0);

		//Close Button
		close = new CloseButton(this);
		close.setAuto(true);
		close.setAnchor(WidgetAnchor.CENTER_CENTER);
		close.setHeight(18).setWidth(40);
		close.shiftXPos(142).shiftYPos(87);

		pu = new PageUpButton(this);
		pu.setAuto(true).setText("<<<");
		pu.setAnchor(WidgetAnchor.CENTER_CENTER);
		pu.setHeight(18).setWidth(40);
		pu.shiftXPos(17).shiftYPos(87);
		
		
				
		pagelabel = new GenericLabel();
		pagelabel.setText(Integer.toString(pageno));
		pagelabel.setAnchor(WidgetAnchor.CENTER_CENTER);
		pagelabel.shiftXPos(66).shiftYPos(92);		
		pagelabel.setPriority(RenderPriority.Lowest);
		pagelabel.setWidth(5).setHeight(18);
		
		
		
		pd = new PageDownButton(this);
		pd.setAuto(true).setText(">>>");
		pd.setAnchor(WidgetAnchor.CENTER_CENTER);
		pd.setHeight(18).setWidth(40);
		pd.shiftXPos(82).shiftYPos(87);
		
		//checkBox = new GenericCheckBox();
		checkBox = new BypassCheckBox(player, this);
		checkBox.setText("Bypass");		
		checkBox.setAnchor(WidgetAnchor.CENTER_CENTER);
		checkBox.setHeight(20).setWidth(19);
		checkBox.shiftXPos(-52).shiftYPos(87);
		checkBox.setAuto(true);
		
		this.setTransparent(true);		
		attachWidgets(Main.getInstance(), border, label);

		this.setTransparent(true);
		attachWidget(Main.getInstance(), label);
		attachWidget(Main.getInstance(), border);
		attachWidget(Main.getInstance(), guideField);		
		attachWidget(Main.getInstance(), close);
		attachWidget(Main.getInstance(), pu);
		attachWidget(Main.getInstance(), pagelabel);
		attachWidget(Main.getInstance(), pd);
		attachWidget(Main.getInstance(), guideName);
		attachWidget(Main.getInstance(), guideInvisible);
		attachWidget(Main.getInstance(), guideDate);
		attachWidget(Main.getInstance(), box);
		if(Main.getInstance().canBypass(player.getName())|| player.hasPermission("infoguide.bypass")|| player.hasPermission("infoguide.admin"))
			attachWidget(Main.getInstance(), checkBox);

		// Attach New / Edit / Save / Delete buttons
		if (player.hasPermission("infoguide.edit") || player.hasPermission("infoguide.admin")) {
			saveb = new SaveButton(this);
			saveb.setAnchor(WidgetAnchor.CENTER_CENTER);
			saveb.setAuto(true).setHeight(18).setWidth(40).shiftXPos(-145).shiftYPos(87);
			attachWidget(Main.getInstance(), saveb);
		} else {
			saveb = null;
		}

		if (player.hasPermission("infoguide.admin"));
		{
			// Add "Set as Default" checkbox
		}

		if (player.hasPermission("infoguide.create") || player.hasPermission("infoguide.edit") || player.hasPermission("infoguide.admin")) {
			// Add New Button
			guideDate.setVisible(false);
			newb = new NewButton(this);
			newb.setAuto(true);
			newb.setAnchor(WidgetAnchor.CENTER_CENTER);
			newb.setAuto(true).setHeight(18).setWidth(40).shiftXPos(-190).shiftYPos(87);
			attachWidget(Main.getInstance(), newb);
		} else {
			newb = null;
			guideDate.setVisible(true);
		}

		if (player.hasPermission("infoguide.delete") || player.hasPermission("infoguide.admin")) {
			deleteb = new DeleteButton(this);
			deleteb.setAnchor(WidgetAnchor.CENTER_CENTER);
			deleteb.setAuto(true).setHeight(18).setWidth(40).shiftXPos(-100).shiftYPos(87);
			attachWidget(Main.getInstance(), deleteb);
		} else {
			deleteb = null;
		}

		if (player.hasPermission("infoguide.bypass") || player.hasPermission("infoguide.admin")) {
			// Add bypass Button since Checkbox widget doesnt exist.
		}
	
		setGuide(GuideManager.getLoadedGuides().get(Main.getInstance().getConfig().getString("DefaultGuide")));
	}
	
	private Guide guide;

	public void setGuide(Guide guide) {
		if (guide == null) {
			return;
		}
		this.guide = guide;
		guideDate.setText("Updated: "+guide.getDate());
		guideName.setText(guide.getName()).setWidth(-1);
		currentGuide = guide;
		pageno = 1;
		pagelabel.setText(Integer.toString(pageno));
		guideField.setText(guide.getPageone());

	}

	public void pageUp() {
		if (pageno==10) guideField.setText(currentGuide.getPagenine());
		if (pageno==9) guideField.setText(currentGuide.getPageeight());
		if (pageno==8) guideField.setText(currentGuide.getPageseven());
		if (pageno==7) guideField.setText(currentGuide.getPagesix());
		if (pageno==6) guideField.setText(currentGuide.getPagefive());
		if (pageno==5) guideField.setText(currentGuide.getPagefour());
		if (pageno==4) guideField.setText(currentGuide.getPagethree());
		if (pageno==3) guideField.setText(currentGuide.getPagetwo());
		if (pageno==2) guideField.setText(currentGuide.getPageone());
		pageno = pageno - 1;
			if (pageno==0) pageno=1;
		pagelabel.setText(Integer.toString(pageno));
	}
	
	public void pageDown() {
		if (pageno==1) guideField.setText(currentGuide.getPagetwo());
		if (pageno==2) guideField.setText(currentGuide.getPagethree());
		if (pageno==3) guideField.setText(currentGuide.getPagefour());
		if (pageno==4) guideField.setText(currentGuide.getPagefive());
		if (pageno==5) guideField.setText(currentGuide.getPagesix());
		if (pageno==6) guideField.setText(currentGuide.getPageseven());
		if (pageno==7) guideField.setText(currentGuide.getPageeight());
		if (pageno==8) guideField.setText(currentGuide.getPagenine());
		if (pageno==9) guideField.setText(currentGuide.getPageten());		
		pageno = pageno + 1;
			if (pageno==11) pageno=10;	
		pagelabel.setText(Integer.toString(pageno));
	}
	
	public void onNewClick() {
		setGuide(new Guide("", "", "", "", "", "", "", "", "", "", "", "", ""));
		guideName.setVisible(false);
		guideInvisible.setVisible(true);
	}

	void onSaveClick() {
		if (pageno==1) guide.setPageone(guideField.getText());
		if (pageno==2) guide.setPagetwo(guideField.getText());
		if (pageno==3) guide.setPagethree(guideField.getText());
		if (pageno==4) guide.setPagefour(guideField.getText());
		if (pageno==5) guide.setPagefive(guideField.getText());
		if (pageno==6) guide.setPagesix(guideField.getText());
		if (pageno==7) guide.setPageseven(guideField.getText());
		if (pageno==8) guide.setPageeight(guideField.getText());
		if (pageno==9) guide.setPagenine(guideField.getText());
		if (pageno==10) guide.setPageten(guideField.getText());
		
		guide.setDate(new SimpleDateFormat("HH:mm dd-MM").format(Calendar.getInstance().getTime()));
		if (guideInvisible.isVisible()) {
			guide.setName(guideInvisible.getText());
			guideName.setText(guideInvisible.getText()).setWidth(-1);
			guideInvisible.setVisible(false);
			guideName.setVisible(true);
			GuideManager.addGuide(guide);
		}
		guide.save();
		refreshItems();
	}

	void onDeleteClick() {
		if(box.getItems().size()==1) return;
		GuideManager.removeLoadedGuide(guideName.getText());
		refreshItems();
		setGuide(GuideManager.getLoadedGuides().get(box.getItems().get(0)));
	}

	void onCloseClick() {
		player.closeActiveWindow();		
	}

	void onSelect(int i, String text) {
		setGuide(GuideManager.getLoadedGuides().get(text));
	}

	private void refreshItems() {
		List<String> items = new ArrayList<String>();
		for (String gguide : GuideManager.getLoadedGuides().keySet()) {
			if (player.hasPermission("infoguide.view." + gguide) || player.hasPermission("infoguide.view")) {
				items.add(gguide);
			}
		}
		Collections.sort(items, String.CASE_INSENSITIVE_ORDER);
		box.setItems(items);		
		box.setDirty(true);
	}
}
