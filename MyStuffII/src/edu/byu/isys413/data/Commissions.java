package edu.byu.isys413.data;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

public class Commissions {

	protected Shell shlCommissions;
	private Table tableCommissions;
	private TableColumn tblclmnTransactionid;
	private TableViewerColumn tableViewerColumn;
	private TableColumn tblclmnTotalcommission;
	private TableViewerColumn tableViewerColumn_1;
	private Label lblEmployees;
	private Label lblCommissionsPerTransaction;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Commissions window = new Commissions();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCommissions.open();
		shlCommissions.layout();
		while (!shlCommissions.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCommissions = new Shell();
		shlCommissions.setToolTipText("");
		shlCommissions.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shlCommissions.setSize(613, 400);
		shlCommissions.setText("Commissions");
		shlCommissions.setLayout(new GridLayout(12, false));
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		
		lblEmployees = new Label(shlCommissions, SWT.NONE);
		lblEmployees.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblEmployees.setFont(SWTResourceManager.getFont("Eras Demi ITC", 11, SWT.NORMAL));
		lblEmployees.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblEmployees.setText("Employees");
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		
		lblCommissionsPerTransaction = new Label(shlCommissions, SWT.NONE);
		lblCommissionsPerTransaction.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblCommissionsPerTransaction.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblCommissionsPerTransaction.setText("Commissions per Transaction");
		lblCommissionsPerTransaction.setFont(SWTResourceManager.getFont("Eras Demi ITC", 11, SWT.NORMAL));
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		
		List listEmployees = new List(shlCommissions, SWT.BORDER);
		listEmployees.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		GridData gd_listEmployees = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_listEmployees.widthHint = 140;
		gd_listEmployees.heightHint = 205;
		listEmployees.setLayoutData(gd_listEmployees);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		new Label(shlCommissions, SWT.NONE);
		
		TableViewer tableViewer = new TableViewer(shlCommissions, SWT.BORDER | SWT.FULL_SELECTION);
		tableCommissions = tableViewer.getTable();
		tableCommissions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnTransactionid = tableViewerColumn.getColumn();
		tblclmnTransactionid.setWidth(100);
		tblclmnTransactionid.setText("TransactionID");
		
		tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnTotalcommission = tableViewerColumn_1.getColumn();
		tblclmnTotalcommission.setWidth(100);
		tblclmnTotalcommission.setText("TotalCommission");

	}

}
