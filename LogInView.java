import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.utils.FadingUtils;

public class LogInView extends JPanel  {

	private JButton loginBtn;
	private JTextField passwordTxtFld;

	private final static Color HEADER_COLOR = new Color(0x0072bc);
	
	private int draggedAtX, draggedAtY;
	
	private BalloonTip btip1;
	
	//Constants for Login
	private static Font heading = new Font("Century Gothic", Font.BOLD, 18);
	private static Font tooltipfont = new Font("Helvetica", Font.PLAIN, 12);
	private static Color transparent_red = new Color(242,109,125,200);
	
	public LogInView() {
		loginBtn = new JButton(loadScaledImage("/images/buttons/login.png",.5f));
				
		passwordTxtFld = new CustomPTextField("Enter password",  "/images/effects/in.png", "/images/effects/out.png", 250, 47);
		
		modifyUI();
		
		addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                draggedAtX = e.getX();
                draggedAtY = e.getY();
            }
            
            public void mouseReleased(MouseEvent e){
                initToolTips();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                setLocation(e.getX() - draggedAtX + getLocation().x,e.getY() - draggedAtY + getLocation().y);
                btip1.closeBalloon();
            }
        });
	}
	
	private void modifyUI()
	{
		this.setSize(new Dimension(370,120));
		this.setOpaque(false);
		this.setLayout(null);
		
		initButtons();
		initToolTips();
		addComponentsToPane();
	}
	
	private void initToolTips()
	{
		RoundedBalloonStyle rbs = new RoundedBalloonStyle(6,6,transparent_red,Color.LIGHT_GRAY);
		
		String blank_password = "<html>Oops! You entered a wrong password.<br></html>";
		JLabel label = new JLabel(blank_password);
		label.setFont(tooltipfont);
		label.setForeground(Color.WHITE);
		
		btip1 = new BalloonTip(passwordTxtFld, label, rbs, Orientation.LEFT_BELOW, AttachLocation.SOUTH, 60, 10, false);
		
		passwordTxtFld.addFocusListener(new FocusListener()
		{
			@Override
            public void focusGained(FocusEvent e) {
				if(btip1.isVisible())
				FadingUtils.fadeOutBalloon(btip1, new ActionListener(){
					public void actionPerformed(ActionEvent e) { btip1.setVisible(true); }
	        		}, 200, 16);
            }

            @Override
            public void focusLost(FocusEvent e) {}
		});
		
		btip1.setVisible(false);
	}
	
	private void initButtons()
	{
		loginBtn.setContentAreaFilled(false);
		loginBtn.setBorder(null);
		loginBtn.setOpaque(false);
		loginBtn.setForeground(null);
		loginBtn.setFocusPainted(false);
		loginBtn.setRolloverIcon(loadScaledImage("/images/buttons/login-r.png", .5f));
		loginBtn.setPressedIcon(loadScaledImage("/images/buttons/login-p.png", .5f));
		loginBtn.setPreferredSize(new Dimension(loginBtn.getIcon().getIconWidth(), loginBtn.getIcon().getIconHeight()));
		loginBtn.setSize(new Dimension(loginBtn.getIcon().getIconWidth(), loginBtn.getIcon().getIconHeight()));
	}
	
	private void addComponentsToPane()
	{
		passwordTxtFld.setBounds(8,65,passwordTxtFld.getWidth(),passwordTxtFld.getHeight());
		loginBtn.setBounds(passwordTxtFld.getWidth()+10,70,loginBtn.getWidth(),loginBtn.getHeight());
		
		add(passwordTxtFld);
		add(loginBtn);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawLine(0, 55, this.getWidth(), 55); 
		
		g2d.setFont(heading);
	    g2d.setColor(HEADER_COLOR);
	    g2d.drawString("E N T E R    P A S S W O R D", 20, 35);       
	}
	
	private ImageIcon loadScaledImage(String img_url, float percent)
	{
		ImageIcon img_icon = new ImageIcon(getClass().getResource(img_url));
		int new_width = (int) (img_icon.getIconWidth()*percent);
		int new_height = (int) (img_icon.getIconHeight()*percent);
		Image img = img_icon.getImage().getScaledInstance(new_width,new_height,java.awt.Image.SCALE_SMOOTH);  
		img_icon = new ImageIcon(img);
		return img_icon;
	}
	
	public void setLoginListener(ActionListener a){
		loginBtn.addActionListener(a);
	}
	
	public void fadeInBalloon(){
		FadingUtils.fadeInBalloon(btip1, new ActionListener(){
			public void actionPerformed(ActionEvent e) { btip1.setVisible(true); }
		}, 100, 16);
	}
	
	public String getPassword(){
		return passwordTxtFld.getText();
	}
}
