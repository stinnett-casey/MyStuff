package edu.byu.isys413.data;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;
import edu.byu.isys403.swt.manager.WindowManager;

public class Login {

	protected Shell shlLogin;
	private Text txtUsername;
	private Text txtPassword;
	private Label lblFail;
	private Employee employee;
	private Store store;
	StringBuilder sb = new StringBuilder();
	SalesPage window = new SalesPage();
	
	
	 /**
     *  Authenticates a user.  If the "new InitialDirContext" doesn't throw
     *  an exception, the user and password validated with LDAP.  We could then
     *  use this DirContext to query the user's email and address information,
     *  but all we care about is authentication.
     */
    public boolean authenticate(String NetID, String Password) {
        try{
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldaps://ldap.byu.edu/");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + NetID + ", ou=People, o=byu.edu");
            env.put(Context.SECURITY_CREDENTIALS, Password);
            DirContext ctx = new InitialDirContext(env);
            return true;
        }catch (NamingException e) {
            return false;
        }
    }

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws edu.byu.isys403.swt.manager.DataException 
	 */
	public void open() throws edu.byu.isys403.swt.manager.DataException {
		Display display = Display.getDefault();
		createContents();
		shlLogin.open();
		WindowManager.getInstance().centerWindow(shlLogin);
		shlLogin.layout();
		while (!shlLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLogin = new Shell();
		shlLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		shlLogin.setSize(396, 250);
		shlLogin.setText("Login");
		shlLogin.setLayout(new BorderLayout(0, 0));
		
		
		InetAddress ip;
		try {
	 
			ip = InetAddress.getLocalHost();
			//System.out.println("Current IP address : " + ip.getHostAddress());	 
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	 
			byte[] mac = network.getHardwareAddress();	 
			//System.out.print("Current MAC address : " + mac);	 
			
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
	 
		} catch (UnknownHostException e) {	 
			e.printStackTrace();	 
		} catch (SocketException e){	 
			e.printStackTrace();	 
		}
		
		try {
			store = BusinessObjectDAO.getInstance().searchForBO("Store", new SearchCriteria("subnetid", "E0-94-67-05-DA-9C"));
		} catch (DataException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Composite composite = new Composite(shlLogin, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite.setLayoutData(BorderLayout.NORTH);
		
		Label lblMystuffLogin = new Label(composite, SWT.NONE);
		lblMystuffLogin.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblMystuffLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblMystuffLogin.setAlignment(SWT.CENTER);
		lblMystuffLogin.setFont(SWTResourceManager.getFont("Eras Demi ITC", 15, SWT.NORMAL));
		lblMystuffLogin.setBounds(10, 10, 360, 32);
		lblMystuffLogin.setText("MyStuff Login");
		
		Composite composite_1 = new Composite(shlLogin, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new GridLayout(1, false));
		
		Label label = new Label(composite_1, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.widthHint = 370;
		label.setLayoutData(gd_label);
		
		Label lblUsername = new Label(composite_1, SWT.NONE);
		lblUsername.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblUsername.setFont(SWTResourceManager.getFont("Eras Demi ITC", 12, SWT.NORMAL));
		lblUsername.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblUsername.setText("Username");
		
		txtUsername = new Text(composite_1, SWT.BORDER);
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == 13){
					authenticateLogin();
				}
			}
		});
		txtUsername.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtUsername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPassword = new Label(composite_1, SWT.NONE);
		lblPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblPassword.setFont(SWTResourceManager.getFont("Eras Demi ITC", 12, SWT.NORMAL));
		lblPassword.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblPassword.setText("Password");
		
		txtPassword = new Text(composite_1, SWT.BORDER);
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == 13){
					authenticateLogin();
				}				
		    }
		});
		txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtPassword.setEchoChar('*');
		
		lblFail = new Label(composite_1, SWT.NONE);
		lblFail.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblFail.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblFail.setText("Either your username or password was not found. Please try again.");
		lblFail.setVisible(false);
		
		
		Button btnLogin = new Button(composite_1, SWT.NONE);		
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				authenticateLogin();
		    }
		   
			}
		);
		GridData gd_btnLogin = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLogin.widthHint = 370;
		btnLogin.setLayoutData(gd_btnLogin);
		btnLogin.setText("Login");

		
		
	}
	
	public void authenticateLogin(){
		// run the LDAP
		if (txtUsername.getText() != "" && txtPassword.getText() != "")	{
	        LDAP ldap = new LDAP();
	        try {
				if (ldap.authenticate(txtUsername.getText(), txtPassword.getText())) {  
					employee = BusinessObjectDAO.getInstance().searchForBO("Employee", new SearchCriteria("username", txtUsername.getText()));
					window.setEmployeeAndStore(employee, store);
					shlLogin.close();
					try {								
						window.open();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else{
					lblFail.setVisible(true);
				}
			} catch (DataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
