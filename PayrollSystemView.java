/*******************************************************
	 *  Class name: PayrollSystemView
 	 *  Inheritance: JFrame
	 *  Attributes: 
	 *  Methods:	PayrollSystemView, setAddPersonnelListener, setRemovePListener,
     *				setViewPListener, setAddDTRListener, setAddAdjustmentListener,
     *				setRemoveAdjustmentListener, setViewSummaryReportListener,
     *				setGenerateSummaryReportListener, setModifyTaxTableListener,
     *				setModifyClientVarListener, setGeneratePayslipsListener,
     *				setChangePasswordListener, setBackupDataListener, fileChooser,
     *				showSuccess, showPeriodStartDateNotFound, showErrorDTR,
     *				showErrorPersonnel
	 *  Functionality: View
	 *  Visibility: public
	 *******************************************************/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.utils.FadingUtils;

import com.mysql.jdbc.Connection;

public class PayrollSystemView extends JPanel {
	private PayrollSystemModel model;
	private BufferedImage top_img, side_img, title_img; 
	private BufferedImage import_img, view_img, generate_img, misc_img;
	
	private JFrame payrollSystemFrame;
	
	private JLabel status1,status2,status3,status4,status5,status6;
	
	private JLabel clientCnt, employeeCnt, employeeLbl, clientLbl;
	private JLabel dateLbl, currentDate;
	
	private JLayeredPane mainPanel;
	private JPanel homepane1,homepane3,homepane4;
	
	private JPanel menuPanel;
	private AddAdjustmentsView addAdjPanel;
	private DTRView addDTRPanel;
	private PersonnelView addPersPanel;
	private GeneratePayslipsView genPayslipsPanel;
	private GenerateSummaryReportView genSummaryPanel;
	private ModifyTaxTableView modifyTaxPanel;
	private RemoveAdjustmentsView removeAdjPanel;
	private RemovePersonnelView removePersPanel;
	private ViewPersonnelView viewPersPanel;
	private ViewSummaryReportView viewSummPanel;
	private BackUpView backUpPanel;

	private LogInView loginPanel;
	private SettingsView settingsPanel;
	private static JPanel blackPane;
	
	private JToggleButton homeBtn;
	private JButton settingsBtn;
	private JButton nextBtn;
	
	private ButtonGroup sideGroup;
	private JToggleButton addAdjBtn;
	private JToggleButton remAdjBtn;
	private JToggleButton backUpBtn;
	private JToggleButton addPersonnelBtn;
	private JToggleButton addDTRBtn;
	private JToggleButton genSummaryBtn;
	private JToggleButton genPayslipsBtn;
	private JToggleButton modifyTaxBtn;
	private JToggleButton viewPersBtn;
	private JToggleButton viewSummBtn;
	
	private ArrayList<BalloonTip> balloonTips;
	
	private static Font homestat = new Font("Helvetica", Font.PLAIN, 12);
	private static Font count_big = new Font("Bebas Neue", Font.PLAIN, 72);
	private static Font tooltipfont = new Font("Helvetica", Font.PLAIN, 12);
	
	private boolean resize;
	private boolean is_home;
	

	
	public void updateLastChecked(String s){
		status1.setText("Last Checked: " + s);
	}
	
	public void updateLastUpdatedData(String s){
		status2.setText("Last Updated Data: " + s);
	}
	
	public void updateLastGeneratedReport(String s){
		status3.setText("Last Generated Report: " + s);
	}
	
	public void updateLastClientModified(String s){
		status4.setText("Last Client Vars Modified: " + s);
	}
	
	public void updateLastGeneratedPayslips(String s){
		status5.setText("Last Generated Payslips: " + s);
	}
	
	public void updateLastBackUp(String s){
		status6.setText("Last Back-Up: " + s);
	}

	public PayrollSystemView(SettingsView sView, PayrollSystemModel model){	
		this.model = model; 
		homepane1 = new JPanel();
		homepane3 = new JPanel();
		homepane4 = new JPanel();
		menuPanel = new JPanel();

		status1 = new JLabel("Last Checked: 03-16-14", loadScaledImage("/images/icons/clock.png",.55f),JLabel.RIGHT);
		status2 = new JLabel("Last Updated Data: 01-01-14 ( FedEx )", loadScaledImage("/images/icons/paper.png",.55f),JLabel.RIGHT);
		status3 = new JLabel("Last Generated Report: 01-01-14 ( LBC )", loadScaledImage("/images/icons/paper.png",.55f),JLabel.RIGHT);
		status4 = new JLabel("Last Client Modified: 01-01-14 ( LBC )", loadScaledImage("/images/icons/paper.png",.55f),JLabel.RIGHT);
		status5 = new JLabel("Last Generated Payslips: 01-01-14 ( LBC )", loadScaledImage("/images/icons/files.png",.55f),JLabel.RIGHT);
		status6 = new JLabel("Last Back-Up: 01-01-14", loadScaledImage("/images/icons/disk.png",.55f),JLabel.RIGHT);

		clientCnt = new JLabel("",JLabel.CENTER);
		clientLbl = new JLabel("CLIENT",JLabel.CENTER);
		employeeCnt = new JLabel("",JLabel.CENTER);
		employeeLbl = new JLabel("PERSONNEL",JLabel.CENTER);
		currentDate = new JLabel("",JLabel.CENTER);
		dateLbl = new JLabel("Year / Month / Day",JLabel.CENTER);
		
		title_img = loadImage("/images/appTitle.png");
		top_img = loadImage("/images/top_white.png");
		side_img = loadImage("/images/side_white.png");
		import_img = loadImage("/images/import.jpg");
		generate_img = loadImage("/images/generate.jpg");
		view_img = loadImage("/images/view.jpg");
		misc_img = loadImage("/images/misc.jpg");
		
		addAdjPanel = new AddAdjustmentsView(model); 
		genPayslipsPanel = new GeneratePayslipsView(model);
		genSummaryPanel = new GenerateSummaryReportView();
		modifyTaxPanel = new ModifyTaxTableView(model); 
		removeAdjPanel = new RemoveAdjustmentsView(model); 
		removePersPanel = new RemovePersonnelView(model); 
		viewPersPanel = new ViewPersonnelView(model); 
		viewSummPanel = new ViewSummaryReportView(model);
		settingsPanel = sView;
		backUpPanel = new BackUpView();
		loginPanel = new LogInView();
		addDTRPanel = new DTRView();
		addPersPanel = new PersonnelView();
		
		mainPanel = new JLayeredPane();
		blackPane = new JPanel(){
			private static final long serialVersionUID=100L;
			public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.setColor(new Color(0,0,0,200));
		        g.fillRect(0, 0, 3000, 3000);
		    }
		};
		blackPane.setOpaque(false);
		
		/*Initialize - Buttons*/
		sideGroup = new ButtonGroup();
		homeBtn = new CustomToggleButton("Home                                ");
		addAdjBtn = new CustomToggleButton("Add Adjustments           ");
		remAdjBtn = new CustomToggleButton("Remove Adjustments   ");
		settingsBtn = new JButton(loadScaledImage("/images/buttons/settings.png", .8f));
		nextBtn = new JButton(loadScaledImage("/images/buttons/next.png", .50f));
		backUpBtn = new CustomToggleButton("Back Up Data                  ");
		addPersonnelBtn = new CustomToggleButton("Personnel                         ");
		addDTRBtn = new CustomToggleButton("  DTR (Daily Time Record)");
		genSummaryBtn = new CustomToggleButton("Summary Report           ");
		genPayslipsBtn = new CustomToggleButton("Payslips                           ");
		modifyTaxBtn = new CustomToggleButton("Tax Table                          ");
		viewPersBtn = new CustomToggleButton("Personnel                          ");
		viewSummBtn = new CustomToggleButton("Summary Report            ");
		
		modifyUI();
		
		resize = false;
		is_home = true;
		setCount();
		
		mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	resize = true;
            	repaint();
            }
        });
		
		blackPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {  
				if(settingsPanel.isVisible())
				{
					settingsPanel.setVisible(false);
					blackPane.setVisible(false);
				}
			}
		});
		
		//Prevents specified panels to disappear when clicked.
		settingsPanel.addMouseListener(new MouseAdapter(){});
		
		/*Initialize Application Frame*/
		payrollSystemFrame = new JFrame();
		payrollSystemFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		payrollSystemFrame.setSize(1064, 750);
		payrollSystemFrame.setContentPane(mainPanel);
		payrollSystemFrame.setTitle("APS: A Payroll System");
		payrollSystemFrame.setResizable(true);
		payrollSystemFrame.setMinimumSize(new Dimension(970, 720));
		payrollSystemFrame.setLocationRelativeTo(null);
		payrollSystemFrame.setVisible(true);
	}
	
	public void setCloseListener(WindowListener list){
		payrollSystemFrame.addWindowListener(list);
	}
	
	private void showHome(boolean option) {
		homepane1.setVisible(option);
		homepane3.setVisible(option);
		homepane4.setVisible(option);
		nextBtn.setVisible(option);
	}
	
	private void showHome2(boolean option) {
		homepane1.setVisible(true);
		homepane3.setVisible(option);
		homepane4.setVisible(option);
		nextBtn.setVisible(option);
	}
	
	
	public static void showBlackPane(boolean option) {
		blackPane.setVisible(option);
	}
	
	private void modifyUI() {	
		this.setLayout(null);
		this.setSize(new Dimension(1064,720));
		this.setBackground(new Color(0xe5edf4));
		
		settingsPanel.setBounds(new Rectangle(this.getWidth()/2 - settingsPanel.getWidth()/2,
											  this.getHeight()/2 - settingsPanel.getHeight()/2,
											  settingsPanel.getWidth(),settingsPanel.getHeight()));
		settingsPanel.setVisible(false);
		
		//Default
		loginPanel.setBounds(new Rectangle(this.getWidth()/2 - loginPanel.getWidth()/2 - 20,
				 						   this.getHeight()/2 - loginPanel.getHeight()/2 - 50,
				 						   loginPanel.getWidth(),loginPanel.getHeight()));
		loginPanel.setVisible(true);
		blackPane.setVisible(true);
		blackPane.setSize(new Dimension(3000,3000));
		//
		
		addAdjPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		addAdjPanel.setBounds(214, 50, addAdjPanel.getWidth(), addAdjPanel.getHeight());
		
		genPayslipsPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		genPayslipsPanel.setBounds(214, 50, genPayslipsPanel.getWidth(), genPayslipsPanel.getHeight());
		
		genSummaryPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		genSummaryPanel.setBounds(214, 50, genPayslipsPanel.getWidth(), genPayslipsPanel.getHeight());
		
		modifyTaxPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		modifyTaxPanel.setBounds(214, 50, modifyTaxPanel.getWidth(), modifyTaxPanel.getHeight());
		
		removeAdjPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		removeAdjPanel.setBounds(214, 50, removeAdjPanel.getWidth(), removeAdjPanel.getHeight());
		
		viewPersPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		viewPersPanel.setBounds(214, 50, viewPersPanel.getWidth(), viewPersPanel.getHeight());
		
		viewSummPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		viewSummPanel.setBounds(214, 50, viewSummPanel.getWidth(), viewSummPanel.getHeight());
		
		addDTRPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		addDTRPanel.setBounds(214, 305, addDTRPanel.getWidth(), addDTRPanel.getHeight());
		
		addPersPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		addPersPanel.setBounds(214, 305, addPersPanel.getWidth(), addPersPanel.getHeight());
		
		backUpPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-70));
		backUpPanel.setBounds(214, 50, backUpPanel.getWidth(), backUpPanel.getHeight());
		
		homepane1.setOpaque(false);
		homepane1.setSize(new Dimension(560,200));
		homepane1.setBounds(245, 120, homepane1.getWidth(), homepane1.getHeight());
		homepane1.setLayout(new GridBagLayout());
		
		homepane3.setOpaque(false);
		homepane3.setSize(new Dimension(300,200));
		homepane3.setBounds(250, 320, homepane3.getWidth(), homepane3.getHeight());
		homepane3.setLayout(new GridBagLayout());
		
		homepane4.setOpaque(false);
		homepane4.setSize(new Dimension(380,120));
		homepane4.setBounds(604, 380, homepane4.getWidth(), homepane4.getHeight());
		homepane4.setLayout(new GridLayout(2,0));
		
		menuPanel.setOpaque(false);
		menuPanel.setSize(new Dimension(214,this.getHeight()));
		menuPanel.setBounds(0, 50, menuPanel.getWidth(), menuPanel.getHeight());
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
		
		settingsBtn.setBounds(this.getWidth()-50, 5, settingsBtn.getWidth(), settingsBtn.getHeight());

		nextBtn.setBounds(this.getWidth()-80, 342, nextBtn.getWidth()/2, nextBtn.getHeight()/2);
		
		initLabels();
		initButtons();
		initToolTips();
		
		addComponentsToPane();
	}
	
	private void addComponentsToPane() {
		sideGroup.add(homeBtn);
		sideGroup.add(addDTRBtn);
		sideGroup.add(addPersonnelBtn);
		sideGroup.add(viewSummBtn);
		sideGroup.add(viewPersBtn);
		sideGroup.add(genPayslipsBtn);
		sideGroup.add(genSummaryBtn);
		sideGroup.add(addAdjBtn);
		sideGroup.add(remAdjBtn);
		sideGroup.add(backUpBtn);
		sideGroup.add(modifyTaxBtn);
		
		menuPanel.add(Box.createRigidArea(new Dimension(0,15)));
		menuPanel.add(homeBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,65)));
		menuPanel.add(addDTRBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,5)));
		menuPanel.add(addPersonnelBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,65)));
		menuPanel.add(viewSummBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,5)));
		menuPanel.add(viewPersBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,70)));
		menuPanel.add(genPayslipsBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,10)));
		menuPanel.add(genSummaryBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,70)));
		menuPanel.add(addAdjBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,5)));
		menuPanel.add(remAdjBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,5)));
		menuPanel.add(backUpBtn);
		menuPanel.add(Box.createRigidArea(new Dimension(0,5)));
		menuPanel.add(modifyTaxBtn);
		
		/** Place buttons into frame using Grid Bag Constraints. */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.ipadx = 0;
		gbc.ipady = 20;
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		homepane1.add(status1,gbc);
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 1;
		homepane1.add(status3,gbc);

		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 2;
		homepane1.add(status5,gbc);
		
		gbc.ipadx = 70;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 1;
		gbc.gridy = 1;
		homepane1.add(status4,gbc);
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 1;
		gbc.gridy = 0;
		homepane1.add(status2,gbc);
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 1;
		gbc.gridy = 2;
		homepane1.add(status6,gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		homepane3.add(clientCnt,gbc);
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 1;
		homepane3.add(clientLbl,gbc);
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,100,0,0);
		gbc.gridx = 1;
		gbc.gridy = 0;
		homepane3.add(employeeCnt,gbc);
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,100,0,0);
		gbc.gridx = 1;
		gbc.gridy = 1;
		homepane3.add(employeeLbl,gbc);
		
		homepane4.add(currentDate);
		homepane4.add(dateLbl);
		
		add(homepane1);
		add(homepane3);
		add(homepane4);
		add(menuPanel);
		add(settingsBtn);
		add(nextBtn);
		
		add(addAdjPanel);
		add(genPayslipsPanel);
		add(genSummaryPanel);
		add(modifyTaxPanel);
		add(removeAdjPanel);
		add(removePersPanel);
		add(viewPersPanel);
		add(viewSummPanel);
		add(addDTRPanel);
		add(addPersPanel);
		add(backUpPanel);
		
		addAdjPanel.setVisible(false);
		genPayslipsPanel.setVisible(false);
		genSummaryPanel.setVisible(false);
		modifyTaxPanel.setVisible(false);
		removeAdjPanel.setVisible(false);
		removePersPanel.setVisible(false);
		viewSummPanel.setVisible(false);
		viewPersPanel.setVisible(false);
		addDTRPanel.setVisible(false);
		addPersPanel.setVisible(false);
		backUpPanel.setVisible(false);
	
		mainPanel.add(this, new Integer(0), 0);
		mainPanel.add(blackPane, new Integer(1), 0);
		mainPanel.add(settingsPanel, new Integer(2), 0);
		mainPanel.add(loginPanel, new Integer(3), 0);
	}
		
	private void initToolTips()
	{
		balloonTips = new ArrayList<BalloonTip>();
		
		// UI Related
		ArrayList<JComponent> holder = new ArrayList<JComponent>();
		ArrayList<String> tips = new ArrayList<String>();
		
		RoundedBalloonStyle rbs = new RoundedBalloonStyle(6,6,Utils.toolTipBGColor,Utils.toolTipFGColor);
		
		//
		holder.add(homeBtn);
		holder.add(addPersonnelBtn);
		holder.add(addDTRBtn);
		holder.add(nextBtn);
		holder.add(settingsBtn);
		holder.add(backUpBtn);
		holder.add(genSummaryBtn);
		holder.add(genPayslipsBtn);
		holder.add(modifyTaxBtn);
		holder.add(viewPersBtn);
		holder.add(viewSummBtn);
		holder.add(addAdjBtn);
		holder.add(remAdjBtn);
		// ------
		// ------
		
		tips.add("<html>Returns to Main Menu.<br></html>");
		tips.add("<html>Imports personnel excel file.<br></html>");
		tips.add("<html>Imports DTR of a client.<br></html>");
		tips.add("<html><b>Next Time Period</b><br><br>This button is used to move to<br>the next time period.</html>");
		tips.add("<html>Settings allows you to change password,<br>client variables, and manage back-ups.</html>");
		tips.add("<html>Allows you to back up your system.<br></html>");
		tips.add("<html>Generate summary reports of selected<br>client per time period</html>");
		tips.add("<html>Generate payslips of selected<br>client.</html>");
		tips.add("<html>Allows you to modify the system's<br>current tax table.</html>");
		tips.add("<html>Shows a table of personnel<br>per client</html>");
		tips.add("<html>Shows a summary report of a client<br>depending on your choice.</html>");
		tips.add("<html>Allows you to add adjustment<br>to a client.</html>");
		tips.add("<html>Allows you to remove adjustment<br>to a client.</html>");
		// Initializes the balloon tips for fade in and fade out of tool tips. Order of things MATTERS!
		
		for(String x: tips) {
			JLabel label = new JLabel(x);
			label.setFont(tooltipfont);
			
			balloonTips.add(new BalloonTip(holder.get(balloonTips.size()), label, rbs, Orientation.RIGHT_BELOW, AttachLocation.ALIGNED, 40, 10, false));
		
			final int index = balloonTips.size()-1;
			
			holder.get(index).addMouseListener(new MouseAdapter() { 
		          public void mouseEntered(MouseEvent e) {
		        	  FadingUtils.fadeInBalloon(balloonTips.get(index), null, 200, 16);
		          }
		          public void mouseExited(MouseEvent e) { 
		        	  FadingUtils.fadeOutBalloon(balloonTips.get(index), new ActionListener(){
		        		  @Override
							public void actionPerformed(ActionEvent e) { balloonTips.get(index).setVisible(false); }
		        		}
		        	  , 200, 16);
		          } 
		    });
			balloonTips.get(index).setVisible(false);
		}
	}
	
	private void initLabels() {
		status1.setFont(homestat);
		status2.setFont(homestat);
		status3.setFont(homestat);
		status4.setFont(homestat);
		status5.setFont(homestat);
		status6.setFont(homestat);
		
		employeeLbl.setFont(homestat);
		clientLbl.setFont(homestat);
		
		employeeCnt.setFont(count_big);
		employeeCnt.setForeground(Utils.employFontColor);
		clientCnt.setFont(count_big);
		clientCnt.setForeground(Utils.clientFontColor);
		
		currentDate.setFont(count_big);
		currentDate.setForeground(Utils.dateFontColor);
		dateLbl.setFont(homestat);
		dateLbl.setSize(new Dimension(380,200));
	}
	
	private void initButtons() {
		nextBtn.setContentAreaFilled(false);
		nextBtn.setBorder(null);
		nextBtn.setOpaque(false);
		nextBtn.setForeground(null);
		nextBtn.setFocusPainted(false);
		nextBtn.setRolloverIcon(loadScaledImage("/images/buttons/next-r.png", .5f));
		nextBtn.setPressedIcon(loadScaledImage("/images/buttons/next-p.png", .5f));
		nextBtn.setPreferredSize(new Dimension(nextBtn.getIcon().getIconWidth(), nextBtn.getIcon().getIconHeight()));
		nextBtn.setSize(new Dimension(nextBtn.getIcon().getIconWidth(), nextBtn.getIcon().getIconHeight()));
		
		settingsBtn.setContentAreaFilled(false);
		settingsBtn.setBorder(null);
		settingsBtn.setOpaque(false);
		settingsBtn.setForeground(null);
		settingsBtn.setFocusPainted(false);
		settingsBtn.setRolloverIcon(loadScaledImage("/images/buttons/settings-r.png", .8f));
		settingsBtn.setPressedIcon(loadScaledImage("/images/buttons/settings-p.png", .8f));
		settingsBtn.setPreferredSize(new Dimension(settingsBtn.getIcon().getIconWidth(), settingsBtn.getIcon().getIconHeight()));
		settingsBtn.setSize(new Dimension(settingsBtn.getIcon().getIconWidth(), settingsBtn.getIcon().getIconHeight()));
		
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorder(null);
		homeBtn.setHorizontalTextPosition(JButton.CENTER);
		homeBtn.setVerticalTextPosition(JButton.CENTER);
		homeBtn.setIcon(loadScaledImage("/images/effects/home.png", .5f));
		homeBtn.setSelectedIcon(loadScaledImage("/images/effects/home-p.png", .5f));
		homeBtn.setRolloverIcon(loadScaledImage("/images/effects/home-r.png", .5f));
		homeBtn.setForeground(null);
		homeBtn.setFocusPainted(false);
		homeBtn.setFont(Utils.menuFont);
		homeBtn.setForeground(Utils.menuFGColor);
		homeBtn.setSelected(true); //Set as default pressed button when application opens
		
		addDTRBtn.setContentAreaFilled(false);
		addDTRBtn.setBorder(null);
		addDTRBtn.setHorizontalTextPosition(JButton.CENTER);
		addDTRBtn.setVerticalTextPosition(JButton.CENTER);
		addDTRBtn.setIcon(loadScaledImage("/images/effects/dtr.png", .5f));
		addDTRBtn.setSelectedIcon(loadScaledImage("/images/effects/dtr-p.png", .5f));
		addDTRBtn.setRolloverIcon(loadScaledImage("/images/effects/dtr-r.png", .5f));
		addDTRBtn.setForeground(null);
		addDTRBtn.setFocusPainted(false);
		addDTRBtn.setFont(Utils.menuFont);
		addDTRBtn.setForeground(Utils.menuFGColor);
		
		addPersonnelBtn.setContentAreaFilled(false);
		addPersonnelBtn.setBorder(null);
		addPersonnelBtn.setHorizontalTextPosition(JButton.CENTER);
		addPersonnelBtn.setVerticalTextPosition(JButton.CENTER);
		addPersonnelBtn.setIcon(loadScaledImage("/images/effects/personnel.png", .5f));
		addPersonnelBtn.setSelectedIcon(loadScaledImage("/images/effects/personnel-p.png", .5f));
		addPersonnelBtn.setRolloverIcon(loadScaledImage("/images/effects/personnel-r.png", .5f));
		addPersonnelBtn.setForeground(null);
		addPersonnelBtn.setFocusPainted(false);
		addPersonnelBtn.setFont(Utils.menuFont);
		addPersonnelBtn.setForeground(Utils.menuFGColor);
		
		viewSummBtn.setContentAreaFilled(false);
		viewSummBtn.setBorder(null);
		viewSummBtn.setHorizontalTextPosition(JButton.CENTER);
		viewSummBtn.setVerticalTextPosition(JButton.CENTER);
		viewSummBtn.setIcon(loadScaledImage("/images/effects/summaryreport.png", .5f));
		viewSummBtn.setSelectedIcon(loadScaledImage("/images/effects/summaryreport-p.png", .5f));
		viewSummBtn.setRolloverIcon(loadScaledImage("/images/effects/summaryreport-r.png", .5f));
		viewSummBtn.setForeground(null);
		viewSummBtn.setFocusPainted(false);
		viewSummBtn.setFont(Utils.menuFont);
		viewSummBtn.setForeground(Utils.menuFGColor);
		
		viewPersBtn.setContentAreaFilled(false);
		viewPersBtn.setBorder(null);
		viewPersBtn.setHorizontalTextPosition(JButton.CENTER);
		viewPersBtn.setVerticalTextPosition(JButton.CENTER);
		viewPersBtn.setIcon(loadScaledImage("/images/effects/personnel.png", .5f));
		viewPersBtn.setSelectedIcon(loadScaledImage("/images/effects/personnel-p.png", .5f));
		viewPersBtn.setRolloverIcon(loadScaledImage("/images/effects/personnel-r.png", .5f));
		viewPersBtn.setForeground(null);
		viewPersBtn.setFocusPainted(false);
		viewPersBtn.setFont(Utils.menuFont);
		viewPersBtn.setForeground(Utils.menuFGColor);
		
		genPayslipsBtn.setContentAreaFilled(false);
		genPayslipsBtn.setBorder(null);
		genPayslipsBtn.setHorizontalTextPosition(JButton.CENTER);
		genPayslipsBtn.setVerticalTextPosition(JButton.CENTER);
		genPayslipsBtn.setIcon(loadScaledImage("/images/effects/summaryreport2.png", .5f));
		genPayslipsBtn.setSelectedIcon(loadScaledImage("/images/effects/summaryreport2-p.png", .5f));
		genPayslipsBtn.setRolloverIcon(loadScaledImage("/images/effects/summaryreport2-r.png", .5f));
		genPayslipsBtn.setForeground(null);
		genPayslipsBtn.setFocusPainted(false);
		genPayslipsBtn.setFont(Utils.menuFont);
		genPayslipsBtn.setForeground(Utils.menuFGColor);
		
		genSummaryBtn.setContentAreaFilled(false);
		genSummaryBtn.setBorder(null);
		genSummaryBtn.setHorizontalTextPosition(JButton.CENTER);
		genSummaryBtn.setVerticalTextPosition(JButton.CENTER);
		genSummaryBtn.setIcon(loadScaledImage("/images/effects/summaryreport.png", .5f));
		genSummaryBtn.setSelectedIcon(loadScaledImage("/images/effects/summaryreport-p.png", .5f));
		genSummaryBtn.setRolloverIcon(loadScaledImage("/images/effects/summaryreport-r.png", .5f));
		genSummaryBtn.setForeground(null);
		genSummaryBtn.setFocusPainted(false);
		genSummaryBtn.setFont(Utils.menuFont);
		genSummaryBtn.setForeground(Utils.menuFGColor);
		
		backUpBtn.setContentAreaFilled(false);
		backUpBtn.setBorder(null);
		backUpBtn.setHorizontalTextPosition(JButton.CENTER);
		backUpBtn.setVerticalTextPosition(JButton.CENTER);
		backUpBtn.setIcon(loadScaledImage("/images/effects/backup.png", .5f));
		backUpBtn.setSelectedIcon(loadScaledImage("/images/effects/backup-p.png", .5f));
		backUpBtn.setRolloverIcon(loadScaledImage("/images/effects/backup-r.png", .5f));
		backUpBtn.setForeground(null);
		backUpBtn.setFocusPainted(false);
		backUpBtn.setFont(Utils.menuFont);
		backUpBtn.setForeground(Utils.menuFGColor);
		
		addAdjBtn.setContentAreaFilled(false);
		addAdjBtn.setBorder(null);
		addAdjBtn.setHorizontalTextPosition(JButton.CENTER);
		addAdjBtn.setVerticalTextPosition(JButton.CENTER);
		addAdjBtn.setIcon(loadScaledImage("/images/effects/addAdj.png", .5f));
		addAdjBtn.setSelectedIcon(loadScaledImage("/images/effects/addAdj-p.png", .5f));
		addAdjBtn.setRolloverIcon(loadScaledImage("/images/effects/addAdj-r.png", .5f));
		addAdjBtn.setForeground(null);
		addAdjBtn.setFocusPainted(false);
		addAdjBtn.setFont(Utils.menuFont);
		addAdjBtn.setForeground(Utils.menuFGColor);
		
		remAdjBtn.setContentAreaFilled(false);
		remAdjBtn.setBorder(null);
		remAdjBtn.setHorizontalTextPosition(JButton.CENTER);
		remAdjBtn.setVerticalTextPosition(JButton.CENTER);
		remAdjBtn.setIcon(loadScaledImage("/images/effects/remAdj.png", .5f));
		remAdjBtn.setSelectedIcon(loadScaledImage("/images/effects/remAdj-p.png", .5f));
		remAdjBtn.setRolloverIcon(loadScaledImage("/images/effects/remAdj-r.png", .5f));
		remAdjBtn.setForeground(null);
		remAdjBtn.setFocusPainted(false);
		remAdjBtn.setFont(Utils.menuFont);
		remAdjBtn.setForeground(Utils.menuFGColor);
		
		modifyTaxBtn.setContentAreaFilled(false);
		modifyTaxBtn.setBorder(null);
		modifyTaxBtn.setHorizontalTextPosition(JButton.CENTER);
		modifyTaxBtn.setVerticalTextPosition(JButton.CENTER);
		modifyTaxBtn.setIcon(loadScaledImage("/images/effects/tax.png", .5f));
		modifyTaxBtn.setSelectedIcon(loadScaledImage("/images/effects/tax-p.png", .5f));
		modifyTaxBtn.setRolloverIcon(loadScaledImage("/images/effects/tax-r.png", .5f));
		modifyTaxBtn.setForeground(null);
		modifyTaxBtn.setFocusPainted(false);
		modifyTaxBtn.setFont(Utils.menuFont);
		modifyTaxBtn.setForeground(Utils.menuFGColor);
		
		addDTRBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = true;
				showHome2(false);
				repaint();
				
				addDTRPanel.setVisible(true);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(false);
			}
		});
		
		addPersonnelBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = true;
				showHome2(false);
				repaint();
				
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(true);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(false);
			}
		});
		
		homeBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = true;
				showHome(true);
				repaint();
				
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(false);
			}
		});
		
		genPayslipsBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = false;
				showHome(false);
				repaint();
				
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(true);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(false);
				genPayslipsPanel.updateClientList();
			}
		});
		
		genSummaryBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = false;
				showHome(false);
				repaint();
				
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(true);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(false);
			}
		});
		
		viewPersBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = false;
				showHome(false);
				repaint();
				
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(true);
				backUpPanel.setVisible(false);
			}
		});
		
		viewSummBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = false;
				showHome(false);
				repaint();
				
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(true);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(false);
			}
		});
		
		addAdjBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = false;
				showHome(false);
				repaint();
				addAdjPanel.updateClientList();
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(true);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(false);
			}
		});
		
		remAdjBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = false;
				showHome(false);
				repaint();
				removeAdjPanel.updateClientList();
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(true);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(false);
			}
		});
		
		modifyTaxBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = false;
				showHome(false);
				repaint();
				modifyTaxPanel.updateBracketList();
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(true);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(false);
			}
		});
		
		backUpBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_home = false;
				showHome(false);
				repaint();
				
				addDTRPanel.setVisible(false);
				addPersPanel.setVisible(false);
				addAdjPanel.setVisible(false);
				genPayslipsPanel.setVisible(false);
				genSummaryPanel.setVisible(false);
				modifyTaxPanel.setVisible(false);
				removeAdjPanel.setVisible(false);
				removePersPanel.setVisible(false);
				viewSummPanel.setVisible(false);
				viewPersPanel.setVisible(false);
				backUpPanel.setVisible(true);
			}
		});
		
		settingsBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				settingsPanel.setVisible(true);
				blackPane.setVisible(true);
			}
		});
		
	}
	
	public void paintComponent(Graphics g)
	{	
			if(resize)
			{	
				this.setSize(mainPanel.getSize());

				addDTRPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-305));
				addPersPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-305));
				viewSummPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-50));
				viewPersPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-50));
				genSummaryPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-50));
				genPayslipsPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-50));
				addAdjPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-50));
				removeAdjPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-50));
				backUpPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-50));
				modifyTaxPanel.setSize(new Dimension(this.getWidth()-213,this.getHeight()-50));
				settingsPanel.setBounds(new Rectangle(this.getWidth()/2 - settingsPanel.getWidth()/2,
						  							  this.getHeight()/2 - settingsPanel.getHeight()/2,
						  							  settingsPanel.getWidth(),settingsPanel.getHeight()));

				settingsBtn.setBounds(this.getWidth()-45, 5, settingsBtn.getWidth(), settingsBtn.getHeight());
				resize = false;
			}
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        
	        g2d.drawImage(top_img, 0, 0, this.getWidth(), 50, null);
	        g2d.drawImage(title_img, 28, 15, title_img.getWidth()/2, title_img.getHeight()/2, null);
	        g2d.drawImage(side_img, 0, 50, 213, this.getHeight(), null);
	        
	        g2d.drawImage(import_img, 0, 105, import_img.getWidth()/2, import_img.getHeight()/2, null);
	        g2d.drawImage(view_img, 0, 225, import_img.getWidth()/2, import_img.getHeight()/2, null);
	        g2d.drawImage(generate_img, 0, 350, import_img.getWidth()/2, import_img.getHeight()/2, null);
	        g2d.drawImage(misc_img, 0, 485, import_img.getWidth()/2, import_img.getHeight()/2, null);
	        
	        g2d.setColor(Color.LIGHT_GRAY);
	        g2d.drawLine(0, 675, 213, 675); 
	        
	        g2d.drawLine(214, 50, 213, this.getHeight());
	        
	        /************/
	        
	        if(is_home){
	        	g2d.setColor(Utils.menuHColor);
				g2d.fillRect(214, 50, this.getWidth(), 90);
					
		        g2d.setFont(Utils.menuHeader2Font);
		        g2d.setColor(Color.WHITE);
		        g2d.drawString("888 Gallant Manpower Agency Inc.", 250, 80);
		    	
		    	g2d.setFont(Utils.menuHeaderFont);
		        g2d.setColor(Utils.menuFGColor);
		        g2d.setColor(Color.WHITE);
		        g2d.drawString("SYSTEM SUMMARY", 250, 120);
		     
		        g2d.setColor(Color.LIGHT_GRAY);
		        g2d.drawLine(214, 300, this.getWidth(), 310);
		        
		        g2d.setColor(Color.darkGray);
				g2d.fillRect(214, 300, this.getWidth(), 30);
				
				g2d.setFont(Utils.menuHeader2Font);
		        g2d.setColor(Color.WHITE);
		        g2d.drawString("Total Records", 350, 320);
		        g2d.drawString("Current Time Period", 720, 320);
	        }
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
	
	private ImageIcon loadScaledImage(String img_url, float percent)
	{	
		ImageIcon img_icon = new ImageIcon(this.getClass().getResource(img_url));
		int new_width = (int) (img_icon.getIconWidth()*percent);
		int new_height = (int) (img_icon.getIconHeight()*percent);
		Image img = img_icon.getImage().getScaledInstance(new_width,new_height,java.awt.Image.SCALE_SMOOTH);  
		img_icon = new ImageIcon(img);
		return img_icon;
	}
	
	public void setAddDTRListener(ActionListener list){
		((DTRView) addDTRPanel).setAddDTRListener(list);
	}
	
	public void setDTRFileLocationListener(ActionListener list){
		((DTRView) addDTRPanel).setFileLocationListener(list);
	}
	
	public String getDTRFileLocation(){
		return ((DTRView) addDTRPanel).getFileLocation();
	}
	
	public void setDTRFileLocation(String location){
		((DTRView) addDTRPanel).setFileLocation(location);
	}
	
	public void setAddPersonnelListener(ActionListener list){
		((PersonnelView) addPersPanel).setAddPersonnelListener(list);
	}
	
	public void setPersonnelFileLocationListener(ActionListener list){
		((PersonnelView) addPersPanel).setFileLocationListener(list);
	}
	
	public String getPersonnelFileLocation(){
		return ((PersonnelView) addPersPanel).getFileLocation();
	}
	
	public void setPersonnelFileLocation(String location){
		((PersonnelView) addPersPanel).setFileLocation(location);
	}
	
	public void setNextTimeListener(ActionListener list){
		nextBtn.addActionListener(list);
	}
	
	public File fileChooser(){
		JFileChooser fc = null;
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xls");
		fc.setFileFilter(filter);
		
		//In response to a button click:
		int returnVal = fc.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			return fc.getSelectedFile();
		}else{
			return null;
		}
	}
	
	public void showSuccess(){
		JOptionPane.showMessageDialog(null, "Excel successfully added!", "Excel successfully added!", JOptionPane.PLAIN_MESSAGE); 
	}
	
	public void showPeriodStartDateNotFound(){
		String error = "Period Start Date not found. Program will now quit.";
		JOptionPane.showMessageDialog(null, error, error, JOptionPane.ERROR_MESSAGE); 
	}
	
	public void setStatusDTR(String e, boolean b){
		addDTRPanel.setStatus(e, b);
	}
	
	public void setStatusPersonnel(String e, boolean b){
		addPersPanel.setStatus(e, b);
	}
	
	public void setStatusDTR(String e){
		addDTRPanel.setStatus(e);
	}
	
	public void setStatusPersonnel(String e){
		addPersPanel.setStatus(e);
	}
	public void updateTimePeriod(String psd){
		currentDate.setText(psd);
	}
	
	public boolean askConfirmation(){
		int confirmation = JOptionPane.showConfirmDialog(null, "Please confirm!", "Please confirm!",
		
		JOptionPane.YES_NO_OPTION);
		if(confirmation ==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	
	public void setCount(){
		clientCnt.setText(model.countClient() + "");
		employeeCnt.setText(model.countEmployee() + "");
	}
	
	public LogInView getLogInView(){
		return loginPanel;
	}
	
	public GeneratePayslipsView getGenPayslips(){
		return genPayslipsPanel;
	}
	
	public AddAdjustmentsView getAdjPanel(){
		return addAdjPanel;
	}
	
	public RemoveAdjustmentsView getRemAdjPanel(){
		return removeAdjPanel;
	}
	
	public ModifyTaxTableView getTaxPanel(){
		return modifyTaxPanel;
	}
}
