/*******************************************************
	 *  Class name: GeneratePayslipsView
 	 *  Inheritance: JFrame
	 *  Attributes: model, file
	 *  Methods: 	GeneratePayslipsView, setSelectFileListener, setClientListener,
	 *				setGenerateListener, setCancelListener, fileSaver,
	 *				getClient, showSuccess, setFileDirectory,
	 *				getPeriodStartDate, getFileDirectory, showError,
	 *				updateClientList, updateDateList
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;
import java.util.ArrayList;

public class GeneratePayslipsView extends JPanel {

	private JLabel selectClientLbl;
	private JLabel saveLbl;
	private JLabel locationLbl;
	private JLabel statusLbl;

	private JButton generateBtn;
	private JButton selSaveBtn;

	private JComboBox<Object> clientCBox;
	
	private PayrollSystemModel model;
	
	private File file;
	public GeneratePayslipsView(PayrollSystemModel model){
		this.model = model;
		generateBtn = new JButton(new ImageIcon(getClass().getResource("/images/buttons/generate.png")));
		selSaveBtn = new JButton(new ImageIcon(getClass().getResource("/images/buttons/select.png")));
		
		selectClientLbl = new JLabel("Select Client: ");
		saveLbl = new JLabel("Save Location: ");
		locationLbl = new JLabel();
		statusLbl = new JLabel("Do you wanna generate a payslip?");
		statusLbl.setIcon(null);
		
		clientCBox = new JComboBox<Object>();
		
		modifyUI();
	}
	
	private void modifyUI(){
		setSize(new Dimension(851,650));
		setBackground(Utils.BODY_COLOR);
		
		statusLbl.setForeground(Utils.statusFGColor);
		
		clientCBox.setPreferredSize(new Dimension(350,25));
		clientCBox.setBackground(Utils.comboBoxBGColor);
		clientCBox.setForeground(Utils.comboBoxFGColor);
		
		locationLbl.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY), new EmptyBorder(5,5,5,5)));
		locationLbl.setPreferredSize(new Dimension(350,25));
		
		generateBtn.setContentAreaFilled(false);
		generateBtn.setBorder(null);
		generateBtn.setOpaque(false);
		generateBtn.setForeground(null);
		generateBtn.setFocusPainted(false);
		generateBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/images/buttons/generate-r.png")));
		generateBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/buttons/generate-p.png")));
		generateBtn.setSize(new Dimension(generateBtn.getIcon().getIconWidth(), generateBtn.getIcon().getIconHeight()));
		
		selSaveBtn.setContentAreaFilled(false);
		selSaveBtn.setBorder(null);
		selSaveBtn.setOpaque(false);
		selSaveBtn.setForeground(null);
		selSaveBtn.setFocusPainted(false);
		selSaveBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/images/buttons/select-r.png")));
		selSaveBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/buttons/select-p.png")));
		selSaveBtn.setPreferredSize(new Dimension(selSaveBtn.getIcon().getIconWidth(), selSaveBtn.getIcon().getIconHeight()));
		
		initFont();
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
		add(selectClientLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(110,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(clientCBox,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(50,40,0,20);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(saveLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(50,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(locationLbl,gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(50,10,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 1;
		add(selSaveBtn,gbc);
		
		gbc.fill = GridBagConstraints.WEST;
		gbc.insets = new Insets(50,10,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 2;
		add(generateBtn,gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.insets = new Insets(0,10,13,0);
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(statusLbl,gbc);
	}
	
	public void initFont()
	{
		clientCBox.setFont(Utils.comboBoxFont);
		selectClientLbl.setFont(Utils.labelFont);
		saveLbl.setFont(Utils.labelFont);
		locationLbl.setFont(Utils.labelFont);
		statusLbl.setFont(Utils.statusBarFont);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Utils.genPaysHColor);
		g2d.fillRect(0, 0, this.getWidth(), 70);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Utils.headerFont);
        g2d.drawString("Generate Payslips", 20, 35);
		g2d.setFont(Utils.descFont);
        g2d.drawString("This section allows you to generate payslips of a client.", 20, 55);
		
		g2d.setColor(Utils.statusBGColor);
		g2d.fillRect(0, this.getHeight()-Utils.HEIGHT, this.getWidth(), Utils.HEIGHT);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawLine(0, this.getHeight()-Utils.HEIGHT, this.getWidth(), this.getHeight()-Utils.HEIGHT);
	}
	
	public void setSelectFileListener(ActionListener list){
		selSaveBtn.addActionListener(list);
	}
	
	public void setGenerateListener(ActionListener list){
		generateBtn.addActionListener(list);
	}
	
	/*public void setCancelListener(ActionListener list){
		backBtn.addActionListener(list);
	}*/
	
	public File fileSaver(){
		//Create a file chooser
		JFileChooser fc = new JFileChooser();
		//In response to a button click:
		int returnVal = fc.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			if(fc.toString().contains(".csv")){
				return fc.getSelectedFile();
			}
			return new File(fc.getSelectedFile()+".csv");
		}else{
			return null;
		}
	}
	
	public String getClient(){
		return (String)clientCBox.getSelectedItem();
	}
	
	public void showSuccess(){
		JOptionPane.showMessageDialog(null, "Generate payslips success!", "Generate payslips success!", JOptionPane.PLAIN_MESSAGE); 
	}
	
	public void setFileDirectory(File f){
		file = f;
		
		if(f!=null){
			locationLbl.setText(file.getPath());
		}else{
			locationLbl.setText("");
		}
	}
	
	public File getFileDirectory(){
		return file;
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
	
	public void updateClientList(){
		clientCBox.removeAllItems();
		ArrayList<String> clients = model.getClientList();
		
		for(String t : clients){
			clientCBox.addItem(t);
		}
	}
	
	public boolean askConfirmation(){
		int confirmation = JOptionPane.showConfirmDialog(null, "Overwrite file?", "Overwrite file?",
		
		JOptionPane.YES_NO_OPTION);
		if(confirmation ==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
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
