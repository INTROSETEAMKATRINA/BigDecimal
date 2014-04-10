/*******************************************************
	 *  Class name: ModifyClientVariablesView
 	 *  Inheritance: JPanel
	 *  Attributes: model
	 *  Methods: 	ModifyClientVariablesView, getClient, getSHVariable, getLHVariable
	 *				setModifyListener, setCancelListener
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.math.BigDecimal;

public class ModifyClientVariablesView extends JPanel {
	
	private JLabel selectClientLbl;
	private JComboBox<Object> clientCBox;
	
	private JLabel var1Lbl;
	private JLabel var2Lbl;
	private JLabel var3Lbl;
	private JLabel var4Lbl;
	private JLabel var5Lbl;
	private JLabel var6Lbl;
	private JLabel var7Lbl;
	private JLabel var8Lbl;
	private JLabel var9Lbl;
	private JLabel var10Lbl;
	private JLabel var11Lbl;
	private JLabel var12Lbl;
	private JLabel statusLbl;
	
	private CustomTextField var1TxtFld;
	private CustomTextField var2TxtFld;
	private CustomTextField var3TxtFld;
	private CustomTextField var4TxtFld;
	private CustomTextField var5TxtFld;
	private CustomTextField var6TxtFld;
	private CustomTextField var7TxtFld;
	private CustomTextField var8TxtFld;
	private CustomTextField var9TxtFld;
	private CustomTextField var10TxtFld;
	private CustomTextField var11TxtFld;
	private CustomTextField var12TxtFld;
	
	private JPanel panel;
	private JScrollPane scrollPane;

	private JButton applyBtn;
	
	private BigDecimal rotVar = new BigDecimal("1.25");
	private BigDecimal rnsdVar = new BigDecimal(".10");
	private BigDecimal lhRate = new BigDecimal("30");
	private BigDecimal lhVar = new BigDecimal("1.00");
	private BigDecimal lhOTVar = new BigDecimal("1.30");
	private BigDecimal lhNSDVar = new BigDecimal(".10");
	private BigDecimal lhRDVar = new BigDecimal("2.60");
	private BigDecimal shRate = new BigDecimal("0");
	private BigDecimal shVar = new BigDecimal(".30");
	private BigDecimal shOTVar = new BigDecimal(".09");
	private BigDecimal shNSDVar = new BigDecimal(".03");
	private BigDecimal shRDVar = new BigDecimal("1.5");
	
	//FIXED
	private final static int FOOTER_HEIGHT = 70;

	private final static int TEXTBOX_WIDTH = 180;
	private final static int TEXTBOX_HEIGHT = 41;
	
	private PayrollSystemModel model;
	public ModifyClientVariablesView(PayrollSystemModel model){
		this.model = model;
		selectClientLbl = new JLabel("Select Client: ");
		clientCBox = new JComboBox<Object>();
		
		applyBtn = new JButton(new ImageIcon(getClass().getResource("/images/buttons/apply.png")));
		
		var1Lbl = new JLabel("Regular Overtime: ");
		var2Lbl = new JLabel("Regular NSD: ");
		var3Lbl = new JLabel("Legal Holiday Rate: ");
		var4Lbl = new JLabel("Legal Holiday Variable: ");
		var5Lbl = new JLabel("Legal Holiday Overtime: ");
		var6Lbl = new JLabel("Legal Holiday NSD: ");
		var7Lbl = new JLabel("Legal Holiday RD: ");
		var8Lbl = new JLabel("Special Holiday Rate: ");
		var9Lbl = new JLabel("Special Holiday Variable: ");
		var10Lbl = new JLabel("Special Holiday Overtime: ");
		var11Lbl = new JLabel("Special Holiday NSD: ");
		var12Lbl = new JLabel("Special Holiday RD: ");
		statusLbl = new JLabel();
		
		var1TxtFld = new CustomTextField(rotVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var2TxtFld = new CustomTextField(rnsdVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var3TxtFld = new CustomTextField(lhRate.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var4TxtFld = new CustomTextField(lhVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var5TxtFld = new CustomTextField(lhOTVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var6TxtFld = new CustomTextField(lhNSDVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var7TxtFld = new CustomTextField(lhRDVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var8TxtFld = new CustomTextField(shRate.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var9TxtFld = new CustomTextField(shVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var10TxtFld = new CustomTextField(shOTVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var11TxtFld = new CustomTextField(shNSDVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		var12TxtFld = new CustomTextField(shRDVar.toString(), "/images/effects/in.png", "/images/effects/out.png", TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		panel = new JPanel();
		scrollPane = new JScrollPane(panel);
		
		modifyUI();
		initFont();
	}
	
	private void modifyUI(){
		setSize(new Dimension(486,450));
		setBackground(Utils.BODY_COLOR);
		
		panel.setBackground(Color.WHITE);
		
		clientCBox.setPreferredSize(new Dimension(350,25));
		clientCBox.setBackground(Utils.comboBoxBGColor);
		clientCBox.setForeground(Utils.comboBoxFGColor);
		
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(null);
		
		statusLbl.setForeground(Utils.statusFGColor);
		
		var1TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var2TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var3TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var4TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var5TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var6TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var7TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var8TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var9TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var10TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var11TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		var12TxtFld.setPreferredSize(new Dimension(TEXTBOX_WIDTH,TEXTBOX_HEIGHT));
		
		panel.setPreferredSize(new Dimension(400,500));
		scrollPane.setPreferredSize(new Dimension(470,250));
		
		applyBtn.setContentAreaFilled(false);
		applyBtn.setBorder(null);
		applyBtn.setOpaque(false);
		applyBtn.setForeground(null);
		applyBtn.setFocusPainted(false);
		applyBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/images/buttons/apply-r.png")));
		applyBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/buttons/apply-p.png")));
		applyBtn.setSize(new Dimension(applyBtn.getIcon().getIconWidth(), applyBtn.getIcon().getIconHeight()));
		
		addComponentsToPane();
	}
	
	private void addComponentsToPane(){
		setLayout(new GridBagLayout());
		ArrayList<JComponent> comp = new ArrayList<JComponent>();
		
		comp.add(var1Lbl);
		comp.add(var1TxtFld);
		comp.add(var2Lbl);
		comp.add(var2TxtFld);
		comp.add(var3Lbl);
		comp.add(var3TxtFld);
		comp.add(var4Lbl);
		comp.add(var4TxtFld);
		comp.add(var5Lbl);
		comp.add(var5TxtFld);
		comp.add(var6Lbl);
		comp.add(var6TxtFld);
		comp.add(var7Lbl);
		comp.add(var7TxtFld);
		comp.add(var8Lbl);
		comp.add(var8TxtFld);
		comp.add(var9Lbl);
		comp.add(var9TxtFld);
		comp.add(var10Lbl);
		comp.add(var10TxtFld);
		comp.add(var11Lbl);
		comp.add(var11TxtFld);
		comp.add(var12Lbl);
		comp.add(var12TxtFld);
		
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		
		for(int i = 0; i < 12; i++)
		{
			gbc.gridy = i;
			for(int j = 0; j < 2; j++)
			{
				if(j == 0)
					gbc.anchor = GridBagConstraints.EAST;
				else
					gbc.anchor = GridBagConstraints.WEST;
				
				gbc.gridx = j;
				
				panel.add(comp.get((i*2)+j),gbc);
			}
		}
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(60,10,0,10);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(selectClientLbl, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(60,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(clientCBox, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,0,0,0);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(scrollPane, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(40,10,0,0);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(statusLbl, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(40,0,0,10);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(applyBtn, gbc);
	}
	
	public void initFont(){
		selectClientLbl.setFont(Utils.labelFont);
		clientCBox.setFont(Utils.comboBoxFont);
		
		var1Lbl.setFont(Utils.labelFont);
		var2Lbl.setFont(Utils.labelFont);
		var3Lbl.setFont(Utils.labelFont);
		var4Lbl.setFont(Utils.labelFont);
		var5Lbl.setFont(Utils.labelFont);
		var6Lbl.setFont(Utils.labelFont);
		var7Lbl.setFont(Utils.labelFont);
		var8Lbl.setFont(Utils.labelFont);
		var9Lbl.setFont(Utils.labelFont);
		var10Lbl.setFont(Utils.labelFont);
		var11Lbl.setFont(Utils.labelFont);
		var12Lbl.setFont(Utils.labelFont);
		
		var1TxtFld.setFont(Utils.textFieldFont);
		var2TxtFld.setFont(Utils.textFieldFont);
		var3TxtFld.setFont(Utils.textFieldFont);
		var4TxtFld.setFont(Utils.textFieldFont);
		var5TxtFld.setFont(Utils.textFieldFont);
		var6TxtFld.setFont(Utils.textFieldFont);
		var7TxtFld.setFont(Utils.textFieldFont);
		var8TxtFld.setFont(Utils.textFieldFont);
		var9TxtFld.setFont(Utils.textFieldFont);
		var10TxtFld.setFont(Utils.textFieldFont);
		var11TxtFld.setFont(Utils.textFieldFont);
		var12TxtFld.setFont(Utils.textFieldFont);
		
		statusLbl.setFont(Utils.statusBarFont);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Utils.modifyVarHColor);
		g2d.fillRect(0, 0, this.getWidth(), 70);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Utils.headerFont);
        g2d.drawString("Modify Variables", 20, 35);
		g2d.setFont(Utils.descFont);
        g2d.drawString("This section allows you to modify allowed variables.", 20, 55);
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawLine(0, this.getHeight()-FOOTER_HEIGHT, this.getWidth(), this.getHeight()-FOOTER_HEIGHT);
	}
	
	public String getClient(){
		return (String)clientCBox.getSelectedItem();
	}
	
	public void setVariables(){
		try{
			BigDecimal vars[] = model.getVariables(getClient());
			var1TxtFld.setHint(vars[0].toString());
			var2TxtFld.setHint(vars[1].toString());
			var3TxtFld.setHint(vars[2].toString());
			var4TxtFld.setHint(vars[3].toString());
			var5TxtFld.setHint(vars[4].toString());
			var6TxtFld.setHint(vars[5].toString());
			var7TxtFld.setHint(vars[6].toString());
			var8TxtFld.setHint(vars[7].toString());
			var9TxtFld.setHint(vars[8].toString());
			var10TxtFld.setHint(vars[9].toString());
			var11TxtFld.setHint(vars[10].toString());
			var12TxtFld.setHint(vars[11].toString());
		}catch(Exception ex){
			var1TxtFld.setHint(rotVar.toString());
			var2TxtFld.setHint(rnsdVar.toString());
			var3TxtFld.setHint(lhRate.toString());
			var4TxtFld.setHint(lhVar.toString());
			var5TxtFld.setHint(lhOTVar.toString());
			var6TxtFld.setHint(lhNSDVar.toString());
			var7TxtFld.setHint(lhRDVar.toString());
			var8TxtFld.setHint(shRate.toString());
			var9TxtFld.setHint(shVar.toString());
			var10TxtFld.setHint(shOTVar.toString());
			var11TxtFld.setHint(shNSDVar.toString());
			var12TxtFld.setHint(shRDVar.toString());
		}
		repaint();
	}
	
	public BigDecimal[] getVariables() throws Exception{
		BigDecimal variables[] = new BigDecimal[12];
		variables[0] = new BigDecimal(var1TxtFld.getText());
		variables[1] = new BigDecimal(var2TxtFld.getText());
		variables[2] = new BigDecimal(var3TxtFld.getText());
		variables[3] = new BigDecimal(var4TxtFld.getText());
		variables[4] = new BigDecimal(var5TxtFld.getText());
		variables[5] = new BigDecimal(var6TxtFld.getText());
		variables[6] = new BigDecimal(var7TxtFld.getText());
		variables[7] = new BigDecimal(var8TxtFld.getText());
		variables[8] = new BigDecimal(var9TxtFld.getText());
		variables[9] = new BigDecimal(var10TxtFld.getText());
		variables[10] = new BigDecimal(var11TxtFld.getText());
		variables[11] = new BigDecimal(var12TxtFld.getText());
		return variables;
	}
	
	public void updateClientList(){
		clientCBox.removeAllItems();
		ArrayList<String> clients = model.getClientList();
		
		for(String t : clients){
			clientCBox.addItem(t);
		}
	}
	
	public void setClientListener(ActionListener list){
		clientCBox.addActionListener(list);
	}
	
	public void setModifyListener(ActionListener list){
		applyBtn.addActionListener(list);
	}
	
	private ImageIcon loadScaledImage(String img_url, float percent){	
		ImageIcon img_icon = new ImageIcon(this.getClass().getResource(img_url));
		int new_width = (int) (img_icon.getIconWidth()*percent);
		int new_height = (int) (img_icon.getIconHeight()*percent);
		Image img = img_icon.getImage().getScaledInstance(new_width,new_height,java.awt.Image.SCALE_SMOOTH);  
		img_icon = new ImageIcon(img);
		return img_icon;
	}
	
	public void showSuccess(){
		statusLbl.setText("Status: Variables succesfully updated!");
		statusLbl.setIcon(loadScaledImage("/images/notifs/right.png",.08f));
	}
	
	public void showError(String s){
		statusLbl.setText(s);
		statusLbl.setIcon(loadScaledImage("/images/notifs/wrong.png",.08f));
	}
	
}
