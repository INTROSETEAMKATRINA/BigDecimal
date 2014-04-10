/*******************************************************
	 *  Class name: ViewPersonnelView
 	 *  Inheritance: JFrame
	 *  Attributes: model
	 *  Methods:	ViewPersonnelView, getClient, setPickerListener
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;

public class ViewPersonnelView extends JPanel {

	@SuppressWarnings("unused")
	private PayrollSystemModel model;
	
	private JLabel selectPersLbl;
	private JLabel selectClientLbl;
	private JLabel statusLbl;
	private JComboBox<Object> viewCBox;
	private JComboBox<Object> clientCBox;
	private JTable personnelTable;
	private JTableHeader header;
	private JScrollPane personnelPane;
	
	public ViewPersonnelView(PayrollSystemModel model) {
		this.model = model;
		
		statusLbl = new JLabel("Status: No Data Found!");
		statusLbl.setIcon(loadScaledImage("/images/notifs/warning.png",.08f));
		
		selectPersLbl = new JLabel("Select Personnel: ");
		selectClientLbl = new JLabel("Select Client: ");

		clientCBox = new JComboBox<Object>();
		viewCBox = new JComboBox<Object>();
		
		personnelTable = new JTable(30,12);
		personnelTable.setRowHeight(32);
		personnelTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		personnelTable.setColumnSelectionAllowed(true);
		personnelTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		header = personnelTable.getTableHeader();
		header.setBackground(new Color(0xFAFAFA));
		header.setPreferredSize(new Dimension(header.getPreferredSize().width, 25));
		header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		header.setReorderingAllowed(false);
		
		for(int i = 0; i < personnelTable.getColumnCount(); i++)
		{
			if(i == 0) {
				personnelTable.getColumnModel().getColumn(i).setCellRenderer(new ColorfulCellRenderer(new Color(0xFAFAFA),Color.BLACK,Utils.colorfulPColumn));
				personnelTable.getColumnModel().getColumn(i).setPreferredWidth(40);
			}
			else if(Utils.colorfulPColumn.contains(i))
			{
				switch(i){
				case 2:
					personnelTable.getColumnModel().getColumn(i).setCellRenderer(new ColorfulCellRenderer(new Color(0xbee1fe),Color.BLACK,Utils.colorfulPColumn));
					break;
				default:
					personnelTable.getColumnModel().getColumn(i).setCellRenderer(new ColorfulCellRenderer(Color.ORANGE,Color.BLACK,Utils.colorfulPColumn));
					break;
				};
			} else
				personnelTable.getColumnModel().getColumn(i).setCellRenderer(new ColorfulCellRenderer(Color.WHITE,Color.BLACK,Utils.colorfulPColumn));
		}
		personnelTable.addMouseListener(new TableMouseListener());
		
		personnelPane = new JScrollPane(personnelTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		modifyUI();
	}
	
	
	
	private void modifyUI() {
		setSize(new Dimension(851,670));
		setBackground(Utils.BODY_COLOR);
		
		personnelPane.setPreferredSize(new Dimension(this.getWidth()-500,this.getHeight()-300));
	
		viewCBox.setPreferredSize(new Dimension(350,25));
		viewCBox.setBackground(Utils.comboBoxBGColor);
		viewCBox.setForeground(Utils.comboBoxFGColor);
		clientCBox.setPreferredSize(new Dimension(350,25));
		clientCBox.setBackground(Utils.comboBoxBGColor);
		clientCBox.setForeground(Utils.comboBoxFGColor);
		
		initFont();
		addComponentsToPane();
	}
	
	private void addComponentsToPane() {
		this.setLayout(new GridBagLayout());	
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(15,15,5,0);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(selectClientLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(15,0,5,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(clientCBox,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,15,5,20);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(selectPersLbl,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,0,5,0);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(viewCBox,gbc);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5,15,25,15);
		gbc.weighty = 1;
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(personnelPane,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.insets = new Insets(0,10,13,0);
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(statusLbl,gbc);
	}

	public void initFont() {
		personnelTable.setFont(Utils.tableFont);
		header.setFont(Utils.tableFont);
		clientCBox.setFont(Utils.comboBoxFont);
		selectClientLbl.setFont(Utils.labelFont);
		selectPersLbl.setFont(Utils.labelFont);
		viewCBox.setFont(Utils.comboBoxFont);
		statusLbl.setFont(Utils.statusBarFont);
	}
	
	public class TableMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			int rowNum = table.rowAtPoint(e.getPoint());
			int colNum = table.columnAtPoint(e.getPoint());
					
			if(colNum == 0) {
				table.setColumnSelectionInterval(0, table.getColumnCount()-1);
			}
					
			if(SwingUtilities.isRightMouseButton(e))
			{
				table.clearSelection();
				table.changeSelection(rowNum, colNum, false, false);
				
				if(colNum == 0) {
					table.setColumnSelectionInterval(0, table.getColumnCount()-1);
				}
			
				int rowIndex = table.getSelectedRow();
				int colIndex = table.getSelectedColumn();
						
				if(rowIndex == rowNum && colIndex == 0 && e.isPopupTrigger() && e.getComponent() instanceof JTable)
				{
					JMenuItem menuItem = new JMenuItem("Delete Personnel");
						menuItem.setFont(Utils.descFont);
						menuItem.setBackground(Color.WHITE);
						menuItem.setOpaque(false);
					JPopupMenu popup = new JPopupMenu();
						popup.setBackground(Color.WHITE);
						
					popup.add(menuItem);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Utils.statusBGColor);
		g2d.fillRect(0, this.getHeight()-Utils.HEIGHT, this.getWidth(), Utils.HEIGHT);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawLine(0, this.getHeight()-Utils.HEIGHT, this.getWidth(), this.getHeight()-Utils.HEIGHT);
	}
	
	public String getClient(){ 
		return null; 
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
