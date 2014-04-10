/*******************************************************
	 *  Class name: AddAdjustmentsView
 	 *  Inheritance:
	 *  Attributes: model
	 *  Methods:	AddAdjustmentsView, askConfirmation, setAddListener, setCancelListener,
	 * 				setClientListener, getTypeAdjustment, getAdjustment,
	 *				getClient, getTIN, showSuccess, showWrongInput, clear,
	 *				updatePersonnelList, updateClientList
	 *  Functionality: View
	 *  Visibility: public
	 *******************************************************/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddAdjustmentsView extends JPanel {
	
	private JLabel selectClientLbl;
	private JLabel selectPersLbl;
	private JLabel amountLbl;
	private JLabel reasonLbl;
	private JLabel statusLbl;

	private JTextField amountTextFld;
	private JTextField reasonTextFld;

	private JButton addBtn;

	private JComboBox<Object> personnelCBox;
	private JComboBox<Object> clientCBox;
	
	private final static int TEXTBOX_WIDTH = 180;
	private final static int TEXTBOX_HEIGHT = 41;
	
	private PayrollSystemModel model;
	public AddAdjustmentsView(PayrollSystemModel model) {
		this.model = model;
		addBtn = new JButton(new ImageIcon(getClass().getResource("/images/buttons/add.png")));
		
		selectClientLbl = new JLabel("Select Client: ");
		selectPersLbl = new JLabel("Select Personnel: ");
		amountLbl = new JLabel("Amount: ");
		reasonLbl = new JLabel("Reason: ");
		statusLbl = new JLabel("Status: New adjustments can be added!");
		statusLbl.setIcon(loadScaledImage("/images/notifs/right.png", .08f));
		
		amountTextFld = new CustomTextField("Amount", "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		reasonTextFld = new CustomTextField("Reason", "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		
		personnelCBox = new JComboBox<Object>();
		clientCBox = new JComboBox<Object>();
		
		modifyUI();
		initFont();
	}
	
	private void modifyUI() {
		setSize(new Dimension(851,650));
		setBackground(Utils.BODY_COLOR);
		
		statusLbl.setForeground(Utils.statusFGColor);
		
		personnelCBox.setPreferredSize(new Dimension(350,25));
		personnelCBox.setBackground(Utils.comboBoxBGColor);
		personnelCBox.setForeground(Utils.comboBoxFGColor);
		clientCBox.setPreferredSize(new Dimension(350,25));
		clientCBox.setBackground(Utils.comboBoxBGColor);
		clientCBox.setForeground(Utils.comboBoxFGColor);
		
		amountTextFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		reasonTextFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		
		addBtn.setContentAreaFilled(false);
		addBtn.setBorder(null);
		addBtn.setOpaque(false);
		addBtn.setForeground(null);
		addBtn.setFocusPainted(false);
		addBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/images/buttons/add-r.png")));
		addBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/buttons/add-p.png")));
		addBtn.setSize(new Dimension(addBtn.getIcon().getIconWidth(), addBtn.getIcon().getIconHeight()));
		
		addComponentsToPane();
	}
	
	private void addComponentsToPane(){
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(110,40,0,20);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(selectClientLbl,gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(30,40,0,20);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(selectPersLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(30,40,0,20);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(amountLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(30,40,0,20);
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(reasonLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(30,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(addBtn,gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(30,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(amountTextFld,gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(30,0,0,0);
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(reasonTextFld,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(110,3,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(clientCBox,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(30,3,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(personnelCBox,gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.insets = new Insets(0,10,13,0);
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(statusLbl,gbc);
	}
	
	public void initFont()
	{
		clientCBox.setFont(Utils.comboBoxFont);
		personnelCBox.setFont(Utils.comboBoxFont);
		selectClientLbl.setFont(Utils.labelFont);
		selectPersLbl.setFont(Utils.labelFont);
		reasonLbl.setFont(Utils.labelFont);
		amountLbl.setFont(Utils.labelFont);
		reasonLbl.setFont(Utils.labelFont);
		statusLbl.setFont(Utils.statusBarFont);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Utils.addAdjHColor);
		g2d.fillRect(0, 0, this.getWidth(), 70);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Utils.headerFont);
        g2d.drawString("Add Adjustments", 20, 35);
		g2d.setFont(Utils.descFont);
        g2d.drawString("This section allows you to add adjustments to a personnel.", 20, 55);
		
		g2d.setColor(Utils.statusBGColor);
		g2d.fillRect(0, this.getHeight()-Utils.HEIGHT, this.getWidth(), Utils.HEIGHT);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawLine(0, this.getHeight()-Utils.HEIGHT, this.getWidth(), this.getHeight()-Utils.HEIGHT);
	}
	public boolean askConfirmation(){
		int confirmation = JOptionPane.showConfirmDialog(null, "Please confirm!", "Please confirm!",
		
		JOptionPane.YES_NO_OPTION);
		if(confirmation ==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	
	public void setAddListener(ActionListener list){
		addBtn.addActionListener(list);
	}
	
	public void setClientListener(ActionListener list){
		clientCBox.addActionListener(list);
	}
	
	public String getTypeAdjustment(){ 
		return reasonTextFld.getText(); 
	}
	
	public float getAdjustment(){
		try{
			return Float.parseFloat(amountTextFld.getText());
		}catch(Exception e){
			return 0;
		}
	}
	
	public String getClient(){
		return (String)clientCBox.getSelectedItem(); 
	}
	
	public String getTIN(){
		String s = (String)personnelCBox.getSelectedItem();
		int i;
		
		for(i = 0;i<s.length();i++){
			if(s.charAt(i)=='~')
				break;
		}
		return s.substring(i+2,s.length());
	}
	
	public void showSuccess(){
		statusLbl.setText("Successfully added adjustment!");
	}
	
	public void showWrongInput(){
		statusLbl.setText("Wrong input!"); 
	}
	
	public void clear(){
		amountTextFld.setText("");
		reasonTextFld.setText("");
	}
	
	public void updatePersonnelList(){
		personnelCBox.removeAllItems();
		ArrayList<String> personnel = model.getPersonnelList((String)clientCBox.getSelectedItem());
		
		for(String t : personnel){
			personnelCBox.addItem(t);
		}
	}
	
	public void updateClientList(){
		clientCBox.removeAllItems();
		ArrayList<String> clients = model.getClientList();
		for(String t : clients){
			clientCBox.addItem(t);
		}
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
