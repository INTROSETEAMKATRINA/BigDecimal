import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.pushingpixels.trident.Timeline;

/* Custom Text Field */
public class CustomPTextField extends JPasswordField implements FocusListener, DocumentListener {
	
	private String hint;
	private boolean show;
	private BufferedImage on_focus, not_focus;
	
	private float opacity = 1.0f;
	private AlphaTimer al;
	private Timeline timeline;
	
	private int width, height;
	  
	public CustomPTextField(String hint, String img_url, String hover_url, int width, int height)
	{
		this.hint = hint;
		on_focus = loadImage(img_url);
		not_focus = loadImage(hover_url);

		al = new AlphaTimer();
		timeline = new Timeline(al);
		timeline.setDuration(300);
		
		this.width = width;
		this.height = height;
        
	    addFocusListener(this);
	    getDocument().addDocumentListener(this);
	    
	    setText(this.hint);
		setSize(new Dimension(width, height));
		setFont(Utils.textPFieldFont);
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(0, 15, -5, 10));
		setForeground(Color.LIGHT_GRAY);
		setCaretColor(Color.LIGHT_GRAY);
		setSelectionColor(new Color(0,0,0,15));
	}
	
	protected class AlphaTimer {
		public void setOpacity(float newValue) {
			opacity = newValue;
			repaint();
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		if(isFocusOwner())
		{
			g2d.setComposite(AlphaComposite.SrcOver.derive(1.0f - opacity));
			g2d.drawImage(not_focus, 0, 0, width, height, null);
			
			g2d.setComposite(AlphaComposite.SrcOver.derive(opacity));
			g2d.drawImage(on_focus, 0, 0, width, height, null);
		}
		else
		{
			g2d.setComposite(AlphaComposite.SrcOver.derive(1.0f - opacity));
			g2d.drawImage(on_focus, 0, 0, width, height, null);
			
			g2d.setComposite(AlphaComposite.SrcOver.derive(opacity));
			g2d.drawImage(not_focus, 0, 0, width, height, null);
		}
	}

	public void focusGained(FocusEvent e) {
		selectAll();
	    if(getText().isEmpty())
	    	show = false;
	    
		timeline.addPropertyToInterpolate("opacity", 0.0f, 1.0f);
		timeline.play();
	    repaint();
	}
		
	public void focusLost(FocusEvent e) {
	    if(getText().isEmpty() || getText().equals(hint)) {
	    	setText(hint);
	    	setForeground(Color.LIGHT_GRAY);
	    	show = true;
	    }
	    
		timeline.addPropertyToInterpolate("opacity", 0.0f, 1.0f);
		timeline.play();
	    repaint();
	}
		  
	@SuppressWarnings("deprecation")
	public String getText() {
	    return show ? "" : super.getText();
	}

	public void changedUpdate(DocumentEvent e) {
	}
		
	public void insertUpdate(DocumentEvent e) {
	   	setForeground(Color.BLACK);
	}

	public void removeUpdate(DocumentEvent e) {
	}
	
	private BufferedImage loadImage(String img_url)
	{
		try
		{
			return ImageIO.read(getClass().getResource(img_url));
	    }
		catch (IOException e)
		{
			return null;
		}
	}
}