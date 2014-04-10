/*******************************************************
	 *  Class name: RemovePersonnelView
 	 *  Inheritance: JFrame
	 *  Attributes: model
	 *  Methods:	RemovePersonnelView, getClient, setRemoveListener
	 *				setCancelListener
	 *  Functionality: View
	 *  Visibility: public
	 *******************************************************/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class RemovePersonnelView extends JPanel {

	@SuppressWarnings("unused")
	private PayrollSystemModel model;
	
	private JLabel selectClientLbl;
	private JLabel personnelLbl;
	private JLabel statusLbl;
	
	private JTable personnelTable;
	private JScrollPane personnelPane;
	
	private JLabel titleLbl;

	private JButton cancelBtn;
	private JButton removeBtn;
	private JButton applyBtn;

	private JComboBox<Object> clientCBox;
	
	public RemovePersonnelView(PayrollSystemModel model)
	{
		this.model = model;
		
		/*Testing*/
		DefaultTableModel tableModel = new DefaultTableModel() {
		    public boolean isCellEditable(int rowIndex, int columnIndex) {
	            return false;
	        }
		};
		
		Object[][] sample = {{"Sample Personnel 1", "Sean"},
						     {"Sample Personnel 2", "Matthew"},
						     {"Sample Personnel 3", "JM"},
						     {"Sample Personnel 4", "Marygrace"},
						     {"Sample Personnel 5", "Yoon Min"},
						     {"Sample Personnel 6", "Kate"}};
		
		tableModel.addColumn("Name");
		
		for(Object[] x: sample)
			tableModel.addRow(x);
		
		//////////
		
		cancelBtn = new JButton("Cancel");
		applyBtn = new JButton("Apply");
		removeBtn = new JButton("Remove Selected");
		
		titleLbl = new JLabel("Remove Personnel");
		statusLbl = new JLabel("Status: Successfully Removed!");
		
		personnelTable = new JTable(tableModel);
		personnelPane = new JScrollPane(personnelTable);
		
		selectClientLbl = new JLabel("Select Client: ");
		personnelLbl = new JLabel("<Client>'s Personnel List ");
		
		clientCBox = new JComboBox<Object>();
		
		modifyUI();
	}
	
	private void modifyUI(){
		
		setLayout(new GridBagLayout());	
		
		titleLbl.setFont(new Font("Helvetica", Font.PLAIN, 32));
		personnelLbl.setFont(new Font("Helvetica", Font.PLAIN, 16));
		
		personnelTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
		personnelTable.setFillsViewportHeight(true);
		personnelPane.setPreferredSize(new Dimension(500,200));
		
		removeBtn.setPreferredSize(new Dimension(140,30));
		applyBtn.setPreferredSize(new Dimension(140,30));
		cancelBtn.setPreferredSize(new Dimension(140,30));
		
		clientCBox.setPreferredSize(new Dimension(500,30));
		clientCBox.setBorder(new LineBorder(Color.GRAY));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0,0,30,0);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(titleLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,0,5,0);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(selectClientLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(15,0,15,0);
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(personnelLbl,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0,0,5,0);
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(clientCBox,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0,0,5,0);
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(personnelPane,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(removeBtn,gbc);
		
		gbc.insets = new Insets(30,0,0,0);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(statusLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,70,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 5;
		add(applyBtn,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,0,0,0);
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 5;
		add(cancelBtn,gbc);
	}
	
	public String getClient(){ return null; }
	
	public void setRemoveListener(){}
	public void setCancelListener(){}
}
