/*******************************************************
	 *  Class name: PayrollSystem
 	 *  Inheritance: JPanel
	 *  Attributes: model
	 *  Methods: 	main
	 *  Functionality: Main
	 *  Visibility: public
	 *******************************************************/

import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PayrollSystem{
	
	public static void main(String[] args){
		Connection con;
		
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/payroll system";
			String uname = "root";
			String pass = "p@ssword";
			con = DriverManager.getConnection (url, uname, pass);
			System.out.println("Connected!");
			boolean loggedIn = false;
			JPasswordField pwd = new JPasswordField(10);
			int action = JOptionPane.OK_OPTION;
			PayrollSystemModel model = new PayrollSystemModel(con);
			SettingsView sView = new SettingsView(model);
			PayrollSystemView view = new PayrollSystemView(sView, model);
			view.setVisible(true);
			PayrollSystemController controller = new PayrollSystemController(model, view, sView, con);
			/*while(action == JOptionPane.OK_OPTION && !loggedIn){
				pwd = new JPasswordField(10);
				pwd.addAncestorListener(new RequestFocusListener());
				//RequestFocusListener is an External Class
				action = JOptionPane.showConfirmDialog(null, pwd ,"Enter Password",JOptionPane.OK_CANCEL_OPTION);  
				inputPass = new String(pwd.getPassword());
				if(action == JOptionPane.OK_OPTION){ 
					Statement st = con.createStatement();	
					ResultSet rs = st.executeQuery("select password from password where password = '"+inputPass+"'");
					int rowCount = 0;  
					if(rs.next()){
						loggedIn = true;
						PayrollSystemModel model = new PayrollSystemModel(con);
						PayrollSystemView view = new PayrollSystemView(model);
						view.setVisible(true);
						PayrollSystemController controller = new PayrollSystemController(model, view, con);
					}else{
						JOptionPane.showMessageDialog(null, "Wrong Password.", "Wrong Password.", JOptionPane.ERROR_MESSAGE); 
					}
					rs.close();
				}
			}*/
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Database not found! Program will now exit.", "Database not found! Program will now exit.", JOptionPane.ERROR_MESSAGE); 
			System.out.println("Error: " + e);
		}	
	}	
}