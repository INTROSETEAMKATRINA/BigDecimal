import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;


public class CustomToggleButton extends JToggleButton implements ItemListener {

	private final static Color LIGHTBLUE = new Color(0x56A7E0);
	
	public CustomToggleButton(String label)
	{
		super(label);
		this.addItemListener(this);
	}
	
	public void itemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	        this.setForeground(Color.WHITE);
	    } else {
	    	this.setForeground(LIGHTBLUE);
	    }
	}
	
}
