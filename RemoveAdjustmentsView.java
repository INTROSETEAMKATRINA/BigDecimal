/*******************************************************
	 *  Class name: RemoveAdjustmentsView
 	 *  Inheritance: JFrame
	 *  Attributes: model
	 *  Methods:	RemoveAdjustmentsView, askConfirmation, setRemoveListener, setCancelListener,
     *				setClientListener, setPersonnelListener, showSuccess,
     *				showNoAdjustments, updatePersonnelList, updateClientList,
     *				updateAdjustmentsList, getClient, getTIN, getTypeAdjustment,
     *				getAdjustment, getNumAdjustments
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

public class RemoveAdjustmentsView extends JPanel {
	
	private JLabel selectClientLbl;
	private JLabel selectPersLbl;
	private JLabel selectAdjLbl;
	private JLabel statusLbl;

	private JButton removeBtn;

	private JComboBox<Object> personnelCBox;
	private JComboBox<Object> clientCBox;
	private JComboBox<Object> adjCBox;
	
	private PayrollSystemModel model;
	public RemoveAdjustmentsView(PayrollSystemModel model) {
		this.model = model;
		removeBtn = new JButton(new ImageIcon(getClass().getResource("/images/buttons/remove.png")));
		
		adjCBox = new JComboBox<Object>();
		
		selectAdjLbl = new JLabel("Select Adjustment");
		selectClientLbl = new JLabel("Select Client: ");
		selectPersLbl = new JLabel("Select Personnel: ");
		statusLbl = new JLabel("Status: Adjustments can be removed!");
		statusLbl.setIcon(loadScaledImage("/images/notifs/right.png",.08f));
		
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
		clientCBox.setPreferredSize(new Dimension(350,25));
		adjCBox.setPreferredSize(new Dimension(350,25));
		personnelCBox.setBackground(Utils.comboBoxBGColor);
		clientCBox.setBackground(Utils.comboBoxBGColor);
		adjCBox.setBackground(Utils.comboBoxBGColor);
		personnelCBox.setForeground(Utils.comboBoxFGColor);
		clientCBox.setForeground(Utils.comboBoxFGColor);
		adjCBox.setForeground(Utils.comboBoxFGColor);
		
		removeBtn.setContentAreaFilled(false);
		removeBtn.setBorder(null);
		removeBtn.setOpaque(false);
		removeBtn.setForeground(null);
		removeBtn.setFocusPainted(false);
		removeBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/images/buttons/remove-r.png")));
		removeBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/buttons/remove-p.png")));
		removeBtn.setSize(new Dimension(removeBtn.getIcon().getIconWidth(), removeBtn.getIcon().getIconHeight()));
		
		addComponentsToPane();
	}
	
	private void addComponentsToPane(){
		setLayout(new GridBagLayout());
		
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
		gbc.insets = new Insets(50,40,0,20);
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(selectPersLbl,gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(50,40,0,20);
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(selectAdjLbl,gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(80,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(removeBtn,gbc);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(50,0,0,0);
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(adjCBox,gbc);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(50,0,0,0);
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(personnelCBox,gbc);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(110,0,0,0);
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(clientCBox,gbc);

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
		adjCBox.setFont(Utils.comboBoxFont);
		selectClientLbl.setFont(Utils.labelFont);
		selectPersLbl.setFont(Utils.labelFont);
		selectAdjLbl.setFont(Utils.labelFont);
		statusLbl.setFont(Utils.statusBarFont);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Utils.remAdjHColor);
		g2d.fillRect(0, 0, this.getWidth(), 70);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Utils.headerFont);
        g2d.drawString("Remove Adjustments", 20, 35);
		g2d.setFont(Utils.descFont);
        g2d.drawString("This section allows you to remove adjustments of a personnel.", 20, 55);
		
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
	
	public void setRemoveListener(ActionListener list){
		removeBtn.addActionListener(list);
	}
	
	public void setClientListener(ActionListener list){
		clientCBox.addActionListener(list);
	}
	
	public void setPersonnelListener(ActionListener list){
		personnelCBox.addActionListener(list);
	}
	
	public void showSuccess(){
		statusLbl.setText("Successfully removed adjustment!"); 
	}

	public void showNoAdjustments(){
		statusLbl.setText("No adjustments to be removed!"); 
	}
		
	public void updatePersonnelList(){
		personnelCBox.removeAllItems();
		ArrayList<String> personnel = model.getPersonnelList(getClient());
		
		for(String t : personnel)
			personnelCBox.addItem(t);
	}
	
	public void updateClientList(){
		clientCBox.removeAllItems();
		ArrayList<String> clients = model.getClientList();
		
		for(String t : clients)
			clientCBox.addItem(t);
	}
	
	public void updateAdjustmentsList(){
		adjCBox.removeAllItems();
		ArrayList<String> adjustments = model.getAdjustmentsList(getTIN());
		
		for(String t : adjustments)
			adjCBox.addItem(t);
	}
	
	public String getClient(){ 
		return (String)clientCBox.getSelectedItem(); 
	}
	
	public String getTIN(){
		String s = (String)personnelCBox.getSelectedItem();
		int i;
		
		if(s == null || s.length() == 0){
			return null;
		}
		
		for(i = 0;i<s.length();i++){
			if(s.charAt(i)=='~'){
				break;
			}
		}
		return s.substring(i+2,s.length());
	}
	
	public String getTypeAdjustment(){
		String s = (String)adjCBox.getSelectedItem();
		int i;
		
		for(i = 0;i<s.length();i++){
			if(s.charAt(i)=='~'){
				break;
			}
		}
		
		return s.substring(0,i-1);
	}
	
	public float getAdjustment(){
		String s = (String)adjCBox.getSelectedItem();
		int i;
		for(i = 0;i<s.length();i++){
			if(s.charAt(i)=='~'){
				break;
			}
		}
		return Float.parseFloat(s.substring(i+2,s.length()));
	}
	
	public int getNumAdjustments(){
		return adjCBox.getItemCount();
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
