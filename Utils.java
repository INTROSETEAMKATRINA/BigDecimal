import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
	
	//Appearance
	public static Color toolTipBGColor = new Color(255,255,255,215);
	public static Color toolTipFGColor = Color.BLACK;
	
	//FIXED
	public static int HEIGHT = 40;
	public static Color BODY_COLOR = Color.WHITE;
	public static Font tableFont = new Font("Helvetica", Font.PLAIN, 12);
	public static Font menuFont = new Font("Helvetica", Font.BOLD, 12);
	public static Color menuFGColor = new Color(0x56A7E0);
	public static Font menuHeaderFont = new Font("Century Gothic", Font.BOLD, 42);
	public static Font menuHeader2Font = new Font("Helvetica", Font.PLAIN, 16);
	
	//Constants for Home
	public static Color darkgray = new Color(0x616161);
	
	public static Color dateFontColor = new Color(0x0072bc);
	public static Color employFontColor = new Color(0x0072bc);
	public static Color clientFontColor = new Color(0x0072bc);
	
	//Constants for Headers
	public static Font headerFont = new Font("Helvetica", Font.PLAIN, 26);
	public static Font descFont = new Font("Helvetica", Font.PLAIN, 12);
	public static Color genSummHColor = new Color(0x0072bc);
	public static Color genPaysHColor = new Color(0x0072bc);
	public static Color addAdjHColor = new Color(0x0072bc);
	public static Color remAdjHColor = new Color(0x0072bc);
	public static Color imptDTRHColor = new Color(0xc77fde);
	public static Color imptPersHColor = new Color(0xf88b16);
	public static Color settingsHColor = new Color(0x0072bc);
	public static Color modifyVarHColor = Color.GRAY;
	public static Color modifyTaxHColor = new Color(0xa341c7);
	public static Color backUpHColor = new Color(0x659D32);
	public static Color menuHColor = new Color(0x0072bc);
	
	//Constants for Footers
	public static Font statusBarFont = new Font("Helvetica", Font.PLAIN, 12);
	public static Color statusBGColor = new Color(0xFAFAFA);
	public static float statusIconSize = .08f;
	public static Color statusFGColor = Color.BLACK;
	
	//Constants for JLabel
	public static Font labelFont = new Font("Helvetica", Font.PLAIN, 12);
	
	//Constants for JTable
	public static ArrayList<Integer> colorfulSRColumn = new ArrayList<Integer>(Arrays.asList(2,5));
	public static ArrayList<Integer> colorfulPColumn = new ArrayList<Integer>();
	
	//Constants for JTextField
	public static Font textFieldFont = new Font("Helvetica", Font.PLAIN, 12);
	public static Font textPFieldFont = new Font("Helvetica", Font.PLAIN, 26);
	
	//Constants for JComboBox
	public static Font comboBoxFont = new Font("Helvetica", Font.PLAIN, 12);
	public static Color comboBoxBGColor = Color.WHITE;
	public static Color comboBoxFGColor = Color.BLACK;
}
