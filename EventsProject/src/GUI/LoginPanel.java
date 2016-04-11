package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {

	private JButton loginButton;
	private JButton forgotPasswordButton;
	private JTextField userField;
	private JPasswordField passField;
	
	public LoginPanel() {
		initialize();
	}
	
	public JButton getLoginButton() {
		return loginButton;
	}
	
	public JButton getForogotPasswordButton() {
		return forgotPasswordButton;
	}
	
	public JTextField getUserField () {
		return userField;
	}
	
	public JPasswordField getPassField () {
		return passField;
	}
	
	
	private void initialize () {
		
		setLayout(new MigLayout("insets 5 5 5 5", 		//layout constraints 
							"[grow][][150!][grow]", 	//column constraints
							"[grow][][][][][grow]"));	//row constraints
		
		loginButton = new JButton("Login");
		forgotPasswordButton = new JButton("Forgot Password");
		userField = new JTextField();
		passField = new JPasswordField();
		
		add(new JLabel("Username:"), 	"cell 1 1, right");
		add(new JLabel("Password:"), 	"cell 1 2, right");
		
		
		add(userField,					"cell 2 1, grow, left");
		add(passField,					"cell 2 2, grow, left");
		
		add(loginButton,				"cell 1 3, grow, span 2");
		add(loginButton,				"cell 1 3, grow, span 2");
		
		add(forgotPasswordButton,		"cell 1 4, grow, span 2");	

	
		
	
	}
	
	
}
