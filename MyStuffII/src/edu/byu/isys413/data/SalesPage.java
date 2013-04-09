package edu.byu.isys413.data;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ibm.icu.util.Calendar;

import edu.byu.isys403.swt.manager.WindowManager;

public class SalesPage {

	protected Shell SalesPage;
	private Text custFName;
	private Text custLName;
	private Text custPhone;
	private Text txtFName;
	private Text txtLName;
	private Text txtPhone;
	private Text txtEmail;
	private Text txtAddress;
	private Text txtState;
	private Text txtCity;
	private Text txtZip;
	private Label lblFName;
	private Label lblLName;
	private Label LblPhone;
	private Label lblEmail;
	private Label lblAddress;
	private Label lblState;
	private Label lblCity;
	private Label lblZip;
	private Button btnSave;
	private Button btnCancel;
	private Table table;
	private Text txtSubTotal;
	private Label lblSubtotal;
	private Text txtTax;
	private Text txtTotal;
	private Label lblTax;
	private Label lblTotal;
	private Button btnCancel_1;
	private Button btnFinish;
	private Button btnNewCustomer;
	private Composite compositeNewCustomer;
	private Label lblMystuffDigital;
	private Label custNameFound;
	private String[] item;
	private java.util.List<RevenueSource> revenueSourceObjects = new LinkedList<RevenueSource>();
	private Store store = null;
	private Employee employee;
	private Sale sale;
	private Txn txn;
	private JournalEntry journalentry;
	private Commission commission;
	private ConceptualProduct conceptualproduct;
	private PhysicalProduct physicalproduct;
	private Rental rental = null;
	private Customer findCust = null;
	private float subtotal;
	private float tax;
	private float total;
	private float commissionAmount = 0.0f;
	private Payment payment = null;
	private Text txtItemNumber;
	private int quantity;
	private Label lblEmpName;
	private Button btnAddMembership;
	private TableViewerColumn columnQuantity;
	private TableViewerColumn columnDateDue;
	private TableViewerColumn columnItem;
	private TableViewerColumn columnPrice;
	private TableViewer tableViewer;
	private Integer dateDue = null;
	private SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SalesPage window = new SalesPage();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	/**
	 * Open the window.
	 * @throws DataException 
	 * @throws edu.byu.isys403.swt.manager.DataException 
	 */
	public void open() throws DataException, edu.byu.isys403.swt.manager.DataException {
		Display display = Display.getDefault();
		createContents();
		SalesPage.open();
		SalesPage.layout();
		WindowManager.getInstance().centerWindow(SalesPage);
		
		Button btnBatch = new Button(SalesPage, SWT.NONE);
		btnBatch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BatchHandler window = new BatchHandler();
				SalesPage.close();
				window.open();				
			}
		});
		GridData gd_btnBatch = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnBatch.heightHint = 232;
		btnBatch.setLayoutData(gd_btnBatch);
		btnBatch.setText("Email/Conversion");
		SalesPage.layout();
		while (!SalesPage.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws DataException 
	 */
	protected void createContents() throws DataException {
		SalesPage = new Shell();
		SalesPage.setBackground(SWTResourceManager.getColor(192, 192, 192));
		SalesPage.setSize(868, 622);
		SalesPage.setText("Sales Page");
		SalesPage.setLayout(new GridLayout(4, false));
		
		//============This is where we will create the objects that we will need to save when we close the transaction========================\\
		
		txn = BusinessObjectDAO.getInstance().create("Txn");
		txn.setDirty();
		txn.setObjectAlreadyInDB(false);
		txn.setTxndate(new Date());
		
		journalentry = BusinessObjectDAO.getInstance().create("JournalEntry");
		journalentry.setDirty();
		journalentry.setObjectAlreadyInDB(false);
	//=========================================================================================================================================\\
		
		
		lblMystuffDigital = new Label(SalesPage, SWT.NONE);
		lblMystuffDigital.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblMystuffDigital.setFont(SWTResourceManager.getFont("Eras Demi ITC", 12, SWT.NORMAL));
		lblMystuffDigital.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblMystuffDigital.setText("MyStuff - Digital Life My Way");
		new Label(SalesPage, SWT.NONE);
		
		Composite composite = new Composite(SalesPage, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 25;
		gd_composite.widthHint = 266;
		composite.setLayoutData(gd_composite);
		
		txtItemNumber = new Text(composite, SWT.BORDER);
		
		Button btnAddItem = new Button(composite, SWT.NONE);
		btnAddItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {	
				try { //Create the sale object to add all of the items to
					sale = BusinessObjectDAO.getInstance().create("Sale");
				} catch (DataException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}				
				
				String id = null;
				String type = null;
				String botype = null;
				
				
				try{
					 // get our connection
			        Connection conn = ConnectionPool.getInstance().get();
			        Statement stmt = conn.createStatement();
		            try{           
		            ResultSet rs = stmt.executeQuery( "SELECT businessobject.id, businessobject.botype FROM businessobject, physicalproduct WHERE businessobject.id = physicalproduct.id AND physicalproduct.serialnumber = '" + txtItemNumber.getText() + "'");
		            try {
		                while ( rs.next() ) {
		                	id = (String) rs.getObject(1);
		                    type = (String) rs.getObject(2);
		                    botype = type.substring(21);
		                }
		            } finally {
		                try { rs.close(); } catch (Throwable ignore) { /* Propagate the original exception
		        instead of this one that you may want just logged */ }
		            }
		        } finally {
		            try { stmt.close(); } catch (Throwable ignore) { /* Propagate the original exception
		        instead of this one that you may want just logged */ }
		        }
				}catch(Exception e3){
					e3.printStackTrace();
				}
				
				try {
						if (txtItemNumber.getText().contains("s") || txtItemNumber.getText().contains("r")){
							physicalproduct = ProductController.getInstance().getCorrectPhysical(txtItemNumber.getText());
							conceptualproduct = null;
							quantity = 1;
							if (physicalproduct.getTypekind().equals("rent")){
								dateDue = Integer.valueOf(JOptionPane.showInputDialog(null, "Days out"));
								Calendar c = Calendar.getInstance();
								
								rental.setDateout(c.getTime());
								c.add(Calendar.DAY_OF_MONTH, dateDue);
								
								rental.setDatedue(c.getTime());
								System.out.println(c.getTime() + " Added time");
								revenueSourceObjects.add(rental);
							}
							
						}
						else{
							conceptualproduct = BusinessObjectDAO.getInstance().searchForBO("ConceptualProduct", new SearchCriteria("sku", txtItemNumber.getText()));
							physicalproduct = null;
							quantity = Integer.valueOf(JOptionPane.showInputDialog(null, "Quantity"));
						}
					} catch (DataException e2) {
						e2.printStackTrace();
					}				
				
				
				
				//Set the quantity of the items to be added				
				sale.setQuantity(quantity);				
				revenueSourceObjects.add(sale);			
				
				tableRefresh();
				
				
				//Get the subtotal each time
				try {
					subtotal += Float.valueOf(sale.getProductPrice(txtItemNumber.getText())) * sale.getQuantity();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DecimalFormat df = new DecimalFormat("#####.00#");
				txtSubTotal.setText(String.valueOf(df.format(subtotal)));
				
				//Get the taxes from the subtotal each time
				tax = subtotal * store.getSalestaxrate();
				txtTax.setText(String.valueOf(df.format(tax)));
				
				//Get total by adding subtotal and tax
				total = tax + subtotal;
				txtTotal.setText(String.valueOf(df.format(total)));
				
				if (conceptualproduct == null){
					try {
						commissionAmount += (physicalproduct.getProductCommission(physicalproduct, quantity));
					} catch (DataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else{
					commissionAmount += (conceptualproduct.getProductCommission(conceptualproduct, quantity));
				}
				

				
			}
		});//end addSelectionListener
		btnAddItem.setText("Add Item");
		new Label(SalesPage, SWT.NONE);
		
		Composite compositeFindCustomer = new Composite(SalesPage, SWT.BORDER);
		compositeFindCustomer.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		compositeFindCustomer.setLayout(new GridLayout(2, false));
		GridData gd_compositeFindCustomer = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_compositeFindCustomer.widthHint = 304;
		gd_compositeFindCustomer.heightHint = 162;
		compositeFindCustomer.setLayoutData(gd_compositeFindCustomer);
		
		Label lblEnterCustomerName = new Label(compositeFindCustomer, SWT.NONE);
		lblEnterCustomerName.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblEnterCustomerName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		lblEnterCustomerName.setFont(SWTResourceManager.getFont("Eras Demi ITC", 10, SWT.NORMAL));
		lblEnterCustomerName.setText("Enter customer name and number");
		
		Label lblNewLabel = new Label(compositeFindCustomer, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblNewLabel.setText("First Name");
		
		custFName = new Text(compositeFindCustomer, SWT.BORDER);
		custFName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(compositeFindCustomer, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblNewLabel_1.setText("Last Name");
		
		custLName = new Text(compositeFindCustomer, SWT.BORDER);
		custLName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPhone = new Label(compositeFindCustomer, SWT.NONE);
		lblPhone.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblPhone.setText("Phone");
		
		custPhone = new Text(compositeFindCustomer, SWT.BORDER);
		custPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		custNameFound = new Label(compositeFindCustomer, SWT.NONE);
		GridData gd_custNameFound = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_custNameFound.widthHint = 115;
		custNameFound.setLayoutData(gd_custNameFound);
		custNameFound.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		custNameFound.setText("New Label");
		
		
		btnAddMembership = new Button(compositeFindCustomer, SWT.NONE);
		btnAddMembership.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				
			}
		});
		btnAddMembership.setText("Add Membership");
		btnAddMembership.setVisible(false);
		
		Button btnFindCustomer = new Button(compositeFindCustomer, SWT.NONE);
		btnFindCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				try {
					findCust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("first_name", custFName.getText()), new SearchCriteria("last_name", custLName.getText()), new SearchCriteria("phone", custPhone.getText()));
				} catch (DataException e1) {
					e1.printStackTrace();
				}
				if (findCust != null){ //If there was a customer found with the first name, last name, and email entered, then show the customer's name
					custNameFound.setText(findCust.getFirst_name() + " " + findCust.getLast_name());
					custNameFound.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
					custNameFound.setVisible(true);
					try {
						//If the customer does not have a membership, then show the membership button
						if (findCust.getMembership() == null){
							btnAddMembership.setVisible(true);
						}
						else{
							btnAddMembership.setVisible(false);
						}
					} catch (DataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else{ 
					custNameFound.setText("Customer Not Found");
					custNameFound.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					custNameFound.setVisible(true);
				}
			}
		});
		GridData gd_btnFindCustomer = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnFindCustomer.widthHint = 105;
		btnFindCustomer.setLayoutData(gd_btnFindCustomer);
		btnFindCustomer.setText("Find Customer");
		custNameFound.setVisible(false);
		
		Label label = new Label(SalesPage, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 3);
		gd_label.heightHint = 478;
		label.setLayoutData(gd_label);
		
		Composite compositeItems = new Composite(SalesPage, SWT.NONE);
		compositeItems.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		GridData gd_compositeItems = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_compositeItems.widthHint = 400;
		gd_compositeItems.heightHint = 244;
		compositeItems.setLayoutData(gd_compositeItems);
		
		tableViewer = new TableViewer(compositeItems, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 32, 400, 212);
		
		columnItem = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnItem = columnItem.getColumn();
		tblclmnItem.setAlignment(SWT.CENTER);
		tblclmnItem.setWidth(100);
		tblclmnItem.setText("Item");
		
		columnQuantity = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnQuantity = columnQuantity.getColumn();
		tblclmnQuantity.setWidth(100);
		tblclmnQuantity.setText("Quantity");
		
		columnPrice = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPrice = columnPrice.getColumn();
		tblclmnPrice.setAlignment(SWT.CENTER);
		tblclmnPrice.setWidth(100);
		tblclmnPrice.setText("Price");
		
		columnDateDue = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDateDue = columnDateDue.getColumn();
		tblclmnDateDue.setAlignment(SWT.CENTER);
		tblclmnDateDue.setWidth(100);
		tblclmnDateDue.setText("Date Due");
		
		columnItem.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {		
				RevenueSource s = (RevenueSource) element;
				String str = null;
					str = s.getProductName(s.getProductid(), txtItemNumber.getText());
				return str;
			}
		});//setLabelProvider
		
		columnQuantity.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				String str = null;
				RevenueSource s = (RevenueSource) element;
					str = String.valueOf(s.getQuantity());	
				return str;
			}
		});//setLabelProvider
		
		columnPrice.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				RevenueSource s = (RevenueSource) element;			
				String str = null;
				str = (s.getProductPrice(txtItemNumber.getText()));				
				return str;
			}
		});//setLabelProvider
		
		columnDateDue.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {	
				String str = null;
				RevenueSource s = (RevenueSource) element;
					 str = String.valueOf(s.getDatedue());
				return str;
			}
		});//setLabelProvider
		
		
	
		
		Label lblScannedItems = new Label(compositeItems, SWT.NONE);
		lblScannedItems.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblScannedItems.setFont(SWTResourceManager.getFont("Eras Demi ITC", 11, SWT.NORMAL));
		lblScannedItems.setAlignment(SWT.CENTER);
		lblScannedItems.setBounds(10, 11, 171, 15);
		lblScannedItems.setText("Scanned Items");
		
		lblEmpName = new Label(compositeItems, SWT.NONE);
		lblEmpName.setAlignment(SWT.CENTER);
		lblEmpName.setBounds(171, 8, 199, 18);
		lblEmpName.setFont(SWTResourceManager.getFont("Eras Demi ITC", 10, SWT.NORMAL));
		lblEmpName.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblEmpName.setText(employee.getFirst_name() + " " + employee.getLast_name());
		new Label(SalesPage, SWT.NONE);
		
		
		btnNewCustomer = new Button(SalesPage, SWT.NONE);
		btnNewCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {		
					compositeNewCustomer.setVisible(true);	
					txtFName.setFocus();
			}
		});
		btnNewCustomer.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnNewCustomer.setText("New Customer");
		new Label(SalesPage, SWT.NONE);
		new Label(SalesPage, SWT.NONE);
		
		compositeNewCustomer = new Composite(SalesPage, SWT.BORDER);
		compositeNewCustomer.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		compositeNewCustomer.setLayout(new GridLayout(4, false));
		GridData gd_compositeNewCustomer = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_compositeNewCustomer.heightHint = 275;
		gd_compositeNewCustomer.widthHint = 328;
		compositeNewCustomer.setLayoutData(gd_compositeNewCustomer);
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		compositeNewCustomer.setVisible(false);
		
		Label lblNewCustomer = new Label(compositeNewCustomer, SWT.NONE);
		lblNewCustomer.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblNewCustomer.setFont(SWTResourceManager.getFont("Eras Demi ITC", 10, SWT.NORMAL));
		lblNewCustomer.setText("New Customer Information");
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		
		lblFName = new Label(compositeNewCustomer, SWT.NONE);
		lblFName.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblFName.setText("First Name");
		
		txtFName = new Text(compositeNewCustomer, SWT.BORDER);
		txtFName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		
		lblLName = new Label(compositeNewCustomer, SWT.NONE);
		lblLName.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblLName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLName.setText("Last Name");
		
		txtLName = new Text(compositeNewCustomer, SWT.BORDER);
		txtLName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		
		LblPhone = new Label(compositeNewCustomer, SWT.NONE);
		LblPhone.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		LblPhone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		LblPhone.setText("Phone");
		
		txtPhone = new Text(compositeNewCustomer, SWT.BORDER);
		txtPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		
		lblEmail = new Label(compositeNewCustomer, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmail.setText("Email");
		
		txtEmail = new Text(compositeNewCustomer, SWT.BORDER);
		txtEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		
		lblAddress = new Label(compositeNewCustomer, SWT.NONE);
		lblAddress.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAddress.setText("Address");
		
		txtAddress = new Text(compositeNewCustomer, SWT.BORDER);
		txtAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		
		lblState = new Label(compositeNewCustomer, SWT.NONE);
		lblState.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblState.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblState.setText("State");
		
		txtState = new Text(compositeNewCustomer, SWT.BORDER);
		txtState.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		
		lblCity = new Label(compositeNewCustomer, SWT.NONE);
		lblCity.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblCity.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCity.setText("City");
		
		txtCity = new Text(compositeNewCustomer, SWT.BORDER);
		txtCity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		
		lblZip = new Label(compositeNewCustomer, SWT.NONE);
		lblZip.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblZip.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblZip.setText("Zip");
		
		txtZip = new Text(compositeNewCustomer, SWT.BORDER);
		txtZip.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeNewCustomer, SWT.NONE);
		new Label(compositeNewCustomer, SWT.NONE);
		
		btnSave = new Button(compositeNewCustomer, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Customer newCust = null;
				try {
					newCust = BusinessObjectDAO.getInstance().create("Customer");
					newCust.setFirst_name(txtFName.getText());
					newCust.setLast_name(txtLName.getText());
					newCust.setPhone(txtPhone.getText());
					newCust.setEmail(txtEmail.getText());
					newCust.setAddress(txtAddress.getText());
					newCust.setState(txtState.getText());
					newCust.setCity(txtCity.getText());
					newCust.setZip(txtZip.getText());
					newCust.save();
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				try {
					if (BusinessObjectDAO.getInstance().read(newCust.getId()) != null){
						txtFName.setText("");
						txtLName.setText("");
						txtPhone.setText("");
						txtEmail.setText("");
						txtAddress.setText("");
						txtState.setText("");
						txtCity.setText("");
						txtZip.setText("");
						
						custNameFound.setText(newCust.getFirst_name() + " " + newCust.getLast_name());
						custNameFound.setVisible(true);
						custFName.setText(newCust.getFirst_name());
						custLName.setText(newCust.getLast_name());
						custPhone.setText(newCust.getPhone());
						
					}
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnSave = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSave.widthHint = 57;
		btnSave.setLayoutData(gd_btnSave);
		btnSave.setText("Save");
		
		btnCancel = new Button(compositeNewCustomer, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compositeNewCustomer.setVisible(false);
			}
		});
		GridData gd_btnCancel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnCancel.widthHint = 59;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setText("Cancel");
		
		Composite compositeTotals = new Composite(SalesPage, SWT.NONE);
		compositeTotals.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		compositeTotals.setLayout(new GridLayout(6, false));
		GridData gd_compositeTotals = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_compositeTotals.heightHint = 236;
		gd_compositeTotals.widthHint = 261;
		compositeTotals.setLayoutData(gd_compositeTotals);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		
		lblSubtotal = new Label(compositeTotals, SWT.NONE);
		lblSubtotal.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblSubtotal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSubtotal.setText("SubTotal");
		
		txtSubTotal = new Text(compositeTotals, SWT.BORDER | SWT.RIGHT);
		txtSubTotal.setEditable(false);
		txtSubTotal.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		
		lblTax = new Label(compositeTotals, SWT.NONE);
		lblTax.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblTax.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTax.setText("Tax");
		
		txtTax = new Text(compositeTotals, SWT.BORDER | SWT.RIGHT);
		txtTax.setEditable(false);
		txtTax.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		
		lblTotal = new Label(compositeTotals, SWT.NONE);
		lblTotal.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblTotal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTotal.setText("Total");
		
		txtTotal = new Text(compositeTotals, SWT.BORDER | SWT.RIGHT);
		txtTotal.setEditable(false);
		txtTotal.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		new Label(compositeTotals, SWT.NONE);
		
		btnFinish = new Button(compositeTotals, SWT.NONE);
		btnFinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//==============================Set Txn attributes =============================\\			
				txn.setCustomerid(custNameFound.getText());
				txn.setEmployeeid(employee.getId());
				txn.setStoreid(store.getId());
				txn.setSubtotal(subtotal);
				txn.setTax(tax);
				txn.setTotal(total);
				txn.setCommissionid(commission.getId());
				//==============================Set Txn attributes =============================\\
				
				
			if (JOptionPane.showConfirmDialog(null, "Do you want to finalize this transaction?") == 0){
				try {
					payment = BusinessObjectDAO.getInstance().create("Payment");
					payment.setAmount(total);
					payment.setChange(0);
					payment.save();
					
					txn.setPaymentid(payment.getId());
					txn.save();
					
					commission.setAmount(commissionAmount);
					commission.setCommdate(new Date());
					commission.setDirty();
					commission.setEmployeeid(employee.getId());
					commission.save();
					AccountingController.getInstance().createJournalEntries(txn);
					WindowManager.getInstance().clearForms(SalesPage);
					
				} catch (DataException | edu.byu.isys403.swt.manager.DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		GridData gd_btnFinish = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnFinish.widthHint = 74;
		btnFinish.setLayoutData(gd_btnFinish);
		btnFinish.setText("Finish");
		
		btnCancel_1 = new Button(compositeTotals, SWT.NONE);
		btnCancel_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SalesPage.close();
			}
		});
		GridData gd_btnCancel_1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnCancel_1.widthHint = 79;
		btnCancel_1.setLayoutData(gd_btnCancel_1);
		btnCancel_1.setText("Cancel");
		

	}
	
	
	/**Sets the store and employee for this sale*/
	public void setEmployeeAndStore(Employee employee, Store store){
		this.employee = employee;
		this.store = store;
	}
	
	
	
	/**Supposed to update the table with the new info*/
	public void tableRefresh(){			
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setInput(revenueSourceObjects);
	}
}
