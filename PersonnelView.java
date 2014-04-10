import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PersonnelView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel saveLbl;
	private JLabel locationLbl;
	private JLabel statusLbl;

	private JButton addBtn;
	private JButton selSaveBtn;
	
	public PersonnelView() {
		
		addBtn = new JButton(new ImageIcon(getClass().getResource("/images/buttons/add.png")));
		selSaveBtn = new JButton(new ImageIcon(getClass().getResource("/images/buttons/select.png")));
		
		saveLbl = new JLabel("Save Location: ");
		locationLbl = new JLabel();
		statusLbl = new JLabel("Status:");
		statusLbl.setIcon(loadScaledImage("/images/notifs/right.png",.08f));
		
		modifyUI();
		initFont();
	}
	
	private void modifyUI() {
		setSize(new Dimension(851,395));
		setBackground(Utils.BODY_COLOR);
		
		statusLbl.setForeground(Utils.statusFGColor);
		
		locationLbl.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY), new EmptyBorder(5,5,5,5)));
		locationLbl.setPreferredSize(new Dimension(300,33));
		
		addBtn.setContentAreaFilled(false);
		addBtn.setBorder(null);
		addBtn.setOpaque(false);
		addBtn.setForeground(null);
		addBtn.setFocusPainted(false);
		addBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/images/buttons/add-r.png")));
		addBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/buttons/add-p.png")));
		addBtn.setSize(new Dimension(addBtn.getIcon().getIconWidth(), addBtn.getIcon().getIconHeight()));
		
		selSaveBtn.setContentAreaFilled(false);
		selSaveBtn.setBorder(null);
		selSaveBtn.setOpaque(false);
		selSaveBtn.setForeground(null);
		selSaveBtn.setFocusPainted(false);
		selSaveBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/images/buttons/select-r.png")));
		selSaveBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/buttons/select-p.png")));
		selSaveBtn.setPreferredSize(new Dimension(selSaveBtn.getIcon().getIconWidth(), selSaveBtn.getIcon().getIconHeight()));
		
		addComponentsToPane();
	}
	
	private void addComponentsToPane(){
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(110,40,0,20);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(saveLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(110,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(locationLbl,gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(110,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(selSaveBtn,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(110,12,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 0;
		add(addBtn,gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.insets = new Insets(0,10,13,0);
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(statusLbl,gbc);
	}
	
	public void initFont()
	{
		saveLbl.setFont(Utils.labelFont);
		locationLbl.setFont(Utils.labelFont);
		statusLbl.setFont(Utils.statusBarFont);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Utils.imptPersHColor);
		g2d.fillRect(0, 0, this.getWidth(), 70);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Utils.headerFont);
        g2d.drawString("Import Personnel", 20, 35);
		g2d.setFont(Utils.descFont);
        g2d.drawString("Select excel file directory of DTR.", 20, 55);
		
		g2d.setColor(Utils.statusBGColor);
		g2d.fillRect(0, this.getHeight()-Utils.HEIGHT, this.getWidth(), Utils.HEIGHT);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawLine(0, this.getHeight()-Utils.HEIGHT, this.getWidth(), this.getHeight()-Utils.HEIGHT);
	}
	public void setFileLocationListener(ActionListener list){
		selSaveBtn.addActionListener(list);
	}
	public void setAddPersonnelListener(ActionListener list){
		addBtn.addActionListener(list);
	}
	public void setFileLocation(String location){
		locationLbl.setText(location);
	}
	public String getFileLocation(){
		return locationLbl.getText();
	}
	public void setStatus(String e, boolean b){
		statusLbl.setText(e);
		if(b){
			statusLbl.setIcon(loadScaledImage("/images/notifs/right.png",.08f));
		}else{
			statusLbl.setIcon(loadScaledImage("/images/notifs/wrong.png",.08f));
		}
	}
	public void setStatus(String e){
		statusLbl.setText(e);
		statusLbl.setIcon(null);
	}
	
	private ImageIcon loadScaledImage(String img_url, float percent)
	{	
		ImageIcon img_icon = new ImageIcon(this.getClass().getResource(img_url));
		int new_width = (int) (img_icon.getIconWidth()*percent);
		int new_height = (int) (img_icon.getIconHeight()*percent);
		Image img = img_icon.getImage().getScaledInstance(new_width,new_height,java.awt.Image.SCALE_SMOOTH);  
		img_icon = new ImageIcon(img);
		return img_icon;
	}
}